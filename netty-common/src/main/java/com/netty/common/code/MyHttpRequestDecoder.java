package com.netty.common.code;

import static io.netty.buffer.ByteBufUtil.readBytes;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.internal.AppendableCharSequence;

public class MyHttpRequestDecoder extends ReplayingDecoder<MyHttpRequestDecoder.State> {
	
	private final int maxInitialLineLength;
    private final int maxHeaderSize;
    private final int maxChunkSize;
    private final boolean chunkedSupported;
    protected final boolean validateHeaders;

    private HttpMessage message;
    private long chunkSize;
    private int headerSize;
    private long contentLength = Long.MIN_VALUE;
    private final AppendableCharSequence sb = new AppendableCharSequence(128);

    /**
     * The internal state of {@link MyHttpRequestDecoder}.
     * <em>Internal use only</em>.
     */
    enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER,
        BAD_MESSAGE,
        UPGRADED
    }

    /**
     * Creates a new instance with the default
     * {@code maxInitialLineLength (4096}}, {@code maxHeaderSize (8192)}, and
     * {@code maxChunkSize (8192)}.
     */
    protected MyHttpRequestDecoder() {
        this(4096, 8192, 8192, true);
    }

    /**
     * Creates a new instance with the specified parameters.
     */
    protected MyHttpRequestDecoder(
            int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean chunkedSupported) {
        this(maxInitialLineLength, maxHeaderSize, maxChunkSize, chunkedSupported, true);
    }

    /**
     * Creates a new instance with the specified parameters.
     */
    protected MyHttpRequestDecoder(
            int maxInitialLineLength, int maxHeaderSize, int maxChunkSize,
            boolean chunkedSupported, boolean validateHeaders) {

        super(State.SKIP_CONTROL_CHARS);

        if (maxInitialLineLength <= 0) {
            throw new IllegalArgumentException(
                    "maxInitialLineLength must be a positive integer: " +
                     maxInitialLineLength);
        }
        if (maxHeaderSize <= 0) {
            throw new IllegalArgumentException(
                    "maxHeaderSize must be a positive integer: " +
                    maxHeaderSize);
        }
        if (maxChunkSize <= 0) {
            throw new IllegalArgumentException(
                    "maxChunkSize must be a positive integer: " +
                    maxChunkSize);
        }
        this.maxInitialLineLength = maxInitialLineLength;
        this.maxHeaderSize = maxHeaderSize;
        this.maxChunkSize = maxChunkSize;
        this.chunkedSupported = chunkedSupported;
        this.validateHeaders = validateHeaders;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        switch (state()) {
        case SKIP_CONTROL_CHARS: {
            try {
                skipControlCharacters(buffer);
                checkpoint(State.READ_INITIAL);
            } finally {
                checkpoint();
            }
        }
        case READ_INITIAL: try {
            String[] initialLine = splitInitialLine(readLine(buffer, maxInitialLineLength));
            if (initialLine.length < 3) {
                // Invalid initial line - ignore.
                checkpoint(State.SKIP_CONTROL_CHARS);
                return;
            }

            message = createMessage(initialLine);
            checkpoint(State.READ_HEADER);

        } catch (Exception e) {
            out.add(invalidMessage(e));
            return;
        }
        case READ_HEADER: try {
            State nextState = readHeaders(buffer);
            checkpoint(nextState);
            if (nextState == State.READ_CHUNK_SIZE) {
                if (!chunkedSupported) {
                    throw new IllegalArgumentException("Chunked messages not supported");
                }
                // Chunked encoding - generate HttpMessage first.  HttpChunks will follow.
                out.add(message);
                return;
            }
            if (nextState == State.SKIP_CONTROL_CHARS) {
                // No content is expected.
                out.add(message);
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
                reset();
                return;
            }
            long contentLength = contentLength();
            if (contentLength == 0 || contentLength == -1 && isDecodingRequest()) {
                out.add(message);
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
                reset();
                return;
            }

            assert nextState == State.READ_FIXED_LENGTH_CONTENT || nextState == State.READ_VARIABLE_LENGTH_CONTENT;

            out.add(message);

            if (nextState == State.READ_FIXED_LENGTH_CONTENT) {
                // chunkSize will be decreased as the READ_FIXED_LENGTH_CONTENT state reads data chunk by chunk.
                chunkSize = contentLength;
            }

            // We return here, this forces decode to be called again where we will decode the content
            return;
        } catch (Exception e) {
            out.add(invalidMessage(e));
            return;
        }
        case READ_VARIABLE_LENGTH_CONTENT: {
            // Keep reading data as a chunk until the end of connection is reached.
            int toRead = Math.min(actualReadableBytes(), maxChunkSize);
            if (toRead > 0) {
                ByteBuf content = readBytes(ctx.alloc(), buffer, toRead);
                if (buffer.isReadable()) {
                    out.add(new DefaultHttpContent(content));
                } else {
                    // End of connection.
                    out.add(new DefaultLastHttpContent(content, validateHeaders));
                    reset();
                }
            } else if (!buffer.isReadable()) {
                // End of connection.
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
                reset();
            }
            return;
        }
        case READ_FIXED_LENGTH_CONTENT: {
            int readLimit = actualReadableBytes();

            // Check if the buffer is readable first as we use the readable byte count
            // to create the HttpChunk. This is needed as otherwise we may end up with
            // create a HttpChunk instance that contains an empty buffer and so is
            // handled like it is the last HttpChunk.
            //
            // See https://github.com/netty/netty/issues/433
            if (readLimit == 0) {
                return;
            }

            int toRead = Math.min(readLimit, maxChunkSize);
            if (toRead > chunkSize) {
                toRead = (int) chunkSize;
            }
            ByteBuf content = readBytes(ctx.alloc(), buffer, toRead);
            chunkSize -= toRead;

            if (chunkSize == 0) {
                // Read all content.
                out.add(new DefaultLastHttpContent(content, validateHeaders));
                reset();
            } else {
                out.add(new DefaultHttpContent(content));
            }
            return;
        }
        /**
         * everything else after this point takes care of reading chunked content. basically, read chunk size,
         * read chunk, read and ignore the CRLF and repeat until 0
         */
        case READ_CHUNK_SIZE: try {
            AppendableCharSequence line = readLine(buffer, maxInitialLineLength);
            int chunkSize = getChunkSize(line.toString());
            this.chunkSize = chunkSize;
            if (chunkSize == 0) {
                checkpoint(State.READ_CHUNK_FOOTER);
                return;
            } else {
                checkpoint(State.READ_CHUNKED_CONTENT);
            }
        } catch (Exception e) {
            out.add(invalidChunk(e));
            return;
        }
        case READ_CHUNKED_CONTENT: {
            assert chunkSize <= Integer.MAX_VALUE;
            int toRead = Math.min((int) chunkSize, maxChunkSize);

            HttpContent chunk = new DefaultHttpContent(readBytes(ctx.alloc(), buffer, toRead));
            chunkSize -= toRead;

            out.add(chunk);

            if (chunkSize == 0) {
                // Read all content.
                checkpoint(State.READ_CHUNK_DELIMITER);
            } else {
                return;
            }
        }
        case READ_CHUNK_DELIMITER: {
            for (;;) {
                byte next = buffer.readByte();
                if (next == HttpConstants.CR) {
                    if (buffer.readByte() == HttpConstants.LF) {
                        checkpoint(State.READ_CHUNK_SIZE);
                        return;
                    }
                } else if (next == HttpConstants.LF) {
                    checkpoint(State.READ_CHUNK_SIZE);
                    return;
                } else {
                    checkpoint();
                }
            }
        }
        case READ_CHUNK_FOOTER: try {
            LastHttpContent trailer = readTrailingHeaders(buffer);
            out.add(trailer);
            reset();
            return;
        } catch (Exception e) {
            out.add(invalidChunk(e));
            return;
        }
        case BAD_MESSAGE: {
            // Keep discarding until disconnection.
            buffer.skipBytes(actualReadableBytes());
            break;
        }
        case UPGRADED: {
            // Do not touch anything read - other handler will replace this codec with the upgraded protocol codec to
            // take the trafic over.
            break;
        }
        }
    }

    @Override
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        decode(ctx, in, out);

        // Handle the last unfinished message.
        if (message != null) {

            // Check if the closure of the connection signifies the end of the content.
            boolean prematureClosure;
            if (isDecodingRequest()) {
                // The last request did not wait for a response.
                prematureClosure = true;
            } else {
                // Compare the length of the received content and the 'Content-Length' header.
                // If the 'Content-Length' header is absent, the length of the content is determined by the end of the
                // connection, so it is perfectly fine.
                prematureClosure = contentLength() > 0;
            }
            reset();

            if (!prematureClosure) {
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
            }
        }
    }

    protected boolean isContentAlwaysEmpty(HttpMessage msg) {
        if (msg instanceof HttpResponse) {
            HttpResponse res = (HttpResponse) msg;
            int code = res.getStatus().code();

            // Correctly handle return codes of 1xx.
            //
            // See:
            //     - http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html Section 4.4
            //     - https://github.com/netty/netty/issues/222
            if (code >= 100 && code < 200) {
                // One exception: Hixie 76 websocket handshake response
                return !(code == 101 && !res.headers().contains(HttpHeaders.Names.SEC_WEBSOCKET_ACCEPT));
            }

            switch (code) {
            case 204: case 205: case 304:
                return true;
            }
        }
        return false;
    }

    private void reset() {
        HttpMessage message = this.message;
        this.message = null;
        contentLength = Long.MIN_VALUE;
        if (!isDecodingRequest()) {
            HttpResponse res = (HttpResponse) message;
            if (res != null && res.getStatus().code() == 101) {
                checkpoint(State.UPGRADED);
                return;
            }
        }

        checkpoint(State.SKIP_CONTROL_CHARS);
    }

    private HttpMessage invalidMessage(Exception cause) {
        checkpoint(State.BAD_MESSAGE);
        if (message != null) {
            message.setDecoderResult(DecoderResult.failure(cause));
        } else {
            message = createInvalidMessage();
            message.setDecoderResult(DecoderResult.failure(cause));
        }
        return message;
    }

    private HttpContent invalidChunk(Exception cause) {
        checkpoint(State.BAD_MESSAGE);
        HttpContent chunk = new DefaultHttpContent(Unpooled.EMPTY_BUFFER);
        chunk.setDecoderResult(DecoderResult.failure(cause));
        return chunk;
    }

    private static void skipControlCharacters(ByteBuf buffer) {
        for (;;) {
            char c = (char) buffer.readUnsignedByte();
            if (!Character.isISOControl(c) &&
                !Character.isWhitespace(c)) {
                buffer.readerIndex(buffer.readerIndex() - 1);
                break;
            }
        }
    }

    private State readHeaders(ByteBuf buffer) {
        headerSize = 0;
        final HttpMessage message = this.message;
        final HttpHeaders headers = message.headers();

        AppendableCharSequence line = readHeader(buffer);
        String name = null;
        String value = null;
        if (line.length() > 0) {
            headers.clear();
            do {
                char firstChar = line.charAt(0);
                if (name != null && (firstChar == ' ' || firstChar == '\t')) {
                    value = value + ' ' + line.toString().trim();
                } else {
                    if (name != null) {
                        headers.add(name, value);
                    }
                    String[] header = splitHeader(line);
                    name = header[0];
                    value = header[1];
                }

                line = readHeader(buffer);
            } while (line.length() > 0);

            // Add the last header.
            if (name != null) {
                headers.add(name, value);
            }
        }

        State nextState;

        if (isContentAlwaysEmpty(message)) {
            HttpHeaders.removeTransferEncodingChunked(message);
            nextState = State.SKIP_CONTROL_CHARS;
        } else if (HttpHeaders.isTransferEncodingChunked(message)) {
            nextState = State.READ_CHUNK_SIZE;
        } else if (contentLength() >= 0) {
            nextState = State.READ_FIXED_LENGTH_CONTENT;
        } else {
            nextState = State.READ_VARIABLE_LENGTH_CONTENT;
        }
        return nextState;
    }

    private long contentLength() {
        if (contentLength == Long.MIN_VALUE) {
            contentLength = HttpHeaders.getContentLength(message, -1);
        }
        return contentLength;
    }

    private LastHttpContent readTrailingHeaders(ByteBuf buffer) {
        headerSize = 0;
        AppendableCharSequence line = readHeader(buffer);
        String lastHeader = null;
        if (line.length() > 0) {
            LastHttpContent trailer = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, validateHeaders);
            do {
                char firstChar = line.charAt(0);
                if (lastHeader != null && (firstChar == ' ' || firstChar == '\t')) {
                    List<String> current = trailer.trailingHeaders().getAll(lastHeader);
                    if (!current.isEmpty()) {
                        int lastPos = current.size() - 1;
                        String newString = current.get(lastPos) + line.toString().trim();
                        current.set(lastPos, newString);
                    } else {
                        // Content-Length, Transfer-Encoding, or Trailer
                    }
                } else {
                    String[] header = splitHeader(line);
                    String name = header[0];
                    if (!HttpHeaders.equalsIgnoreCase(name, HttpHeaders.Names.CONTENT_LENGTH) &&
                        !HttpHeaders.equalsIgnoreCase(name, HttpHeaders.Names.TRANSFER_ENCODING) &&
                        !HttpHeaders.equalsIgnoreCase(name, HttpHeaders.Names.TRAILER)) {
                        trailer.trailingHeaders().add(name, header[1]);
                    }
                    lastHeader = name;
                }

                line = readHeader(buffer);
            } while (line.length() > 0);

            return trailer;
        }

        return LastHttpContent.EMPTY_LAST_CONTENT;
    }

    private AppendableCharSequence readHeader(ByteBuf buffer) {
        AppendableCharSequence sb = this.sb;
        sb.reset();
        int headerSize = this.headerSize;

        loop:
        for (;;) {
            char nextByte = (char) buffer.readByte();
            headerSize ++;

            switch (nextByte) {
            case HttpConstants.CR:
                nextByte = (char) buffer.readByte();
                headerSize ++;
                if (nextByte == HttpConstants.LF) {
                    break loop;
                }
                break;
            case HttpConstants.LF:
                break loop;
            }

            // Abort decoding if the header part is too large.
            if (headerSize >= maxHeaderSize) {
                // TODO: Respond with Bad Request and discard the traffic
                //    or close the connection.
                //       No need to notify the upstream handlers - just log.
                //       If decoding a response, just throw an exception.
                throw new TooLongFrameException(
                        "HTTP header is larger than " +
                        maxHeaderSize + " bytes.");
            }

            sb.append(nextByte);
        }

        this.headerSize = headerSize;
        return sb;
    }

    protected HttpMessage createMessage(String[] initialLine) throws Exception {
        return new DefaultHttpRequest(
                HttpVersion.valueOf(initialLine[2]),
                HttpMethod.valueOf(initialLine[0]), initialLine[1], validateHeaders);
    }

    protected HttpMessage createInvalidMessage() {
        return new DefaultHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "/bad-request", validateHeaders);
    }

    protected boolean isDecodingRequest() {
        return true;
    }
    private static int getChunkSize(String hex) {
        hex = hex.trim();
        for (int i = 0; i < hex.length(); i ++) {
            char c = hex.charAt(i);
            if (c == ';' || Character.isWhitespace(c) || Character.isISOControl(c)) {
                hex = hex.substring(0, i);
                break;
            }
        }

        return Integer.parseInt(hex, 16);
    }

    private AppendableCharSequence readLine(ByteBuf buffer, int maxLineLength) {
        AppendableCharSequence sb = this.sb;
        sb.reset();
        int lineLength = 0;
        while (true) {
            byte nextByte = buffer.readByte();
            if (nextByte == HttpConstants.CR) {
                nextByte = buffer.readByte();
                if (nextByte == HttpConstants.LF) {
                    return sb;
                }
            } else if (nextByte == HttpConstants.LF) {
                return sb;
            } else {
                if (lineLength >= maxLineLength) {
                    // TODO: Respond with Bad Request and discard the traffic
                    //    or close the connection.
                    //       No need to notify the upstream handlers - just log.
                    //       If decoding a response, just throw an exception.
                    throw new TooLongFrameException(
                            "An HTTP line is larger than " + maxLineLength +
                            " bytes.");
                }
                lineLength ++;
                sb.append((char) nextByte);
            }
        }
    }

    private static String[] splitInitialLine(AppendableCharSequence sb) {
        int aStart;
        int aEnd;
        int bStart;
        int bEnd;
        int cStart;
        int cEnd;

        aStart = findNonWhitespace(sb, 0);
        aEnd = findWhitespace(sb, aStart);

        bStart = findNonWhitespace(sb, aEnd);
        bEnd = findWhitespace(sb, bStart);

        cStart = findNonWhitespace(sb, bEnd);
        cEnd = findEndOfString(sb);

        return new String[] {
                sb.substring(aStart, aEnd),
                sb.substring(bStart, bEnd),
                cStart < cEnd? sb.substring(cStart, cEnd) : "" };
    }

    private static String[] splitHeader(AppendableCharSequence sb) {
        final int length = sb.length();
        int nameStart;
        int nameEnd;
        int colonEnd;
        int valueStart;
        int valueEnd;

        nameStart = findNonWhitespace(sb, 0);
        for (nameEnd = nameStart; nameEnd < length; nameEnd ++) {
            char ch = sb.charAt(nameEnd);
            if (ch == ':' || Character.isWhitespace(ch)) {
                break;
            }
        }

        for (colonEnd = nameEnd; colonEnd < length; colonEnd ++) {
            if (sb.charAt(colonEnd) == ':') {
                colonEnd ++;
                break;
            }
        }

        valueStart = findNonWhitespace(sb, colonEnd);
        if (valueStart == length) {
            return new String[] {
                    sb.substring(nameStart, nameEnd),
                    ""
            };
        }

        valueEnd = findEndOfString(sb);
        return new String[] {
                sb.substring(nameStart, nameEnd),
                sb.substring(valueStart, valueEnd)
        };
    }

    private static int findNonWhitespace(CharSequence sb, int offset) {
        int result;
        for (result = offset; result < sb.length(); result ++) {
            if (!Character.isWhitespace(sb.charAt(result))) {
                break;
            }
        }
        return result;
    }

    private static int findWhitespace(CharSequence sb, int offset) {
        int result;
        for (result = offset; result < sb.length(); result ++) {
            if (Character.isWhitespace(sb.charAt(result))) {
                break;
            }
        }
        return result;
    }

    private static int findEndOfString(CharSequence sb) {
        int result;
        for (result = sb.length(); result > 0; result --) {
            if (!Character.isWhitespace(sb.charAt(result - 1))) {
                break;
            }
        }
        return result;
    }

}
