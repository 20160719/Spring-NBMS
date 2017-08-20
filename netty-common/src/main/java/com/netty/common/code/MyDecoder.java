package com.netty.common.code;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyDecoder extends ByteToMessageDecoder {
	
	private MyDelimiterBasedFrameDecoder frameDecoder;
	
	private MyHttpRequestDecoder requestDecoder;
	
	private boolean readed = false;
	
	public MyDecoder() {
		frameDecoder = new MyDelimiterBasedFrameDecoder(1000, Unpooled.copiedBuffer("_@#@_".getBytes()));
		requestDecoder = new MyHttpRequestDecoder();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(!readed) {
			frameDecoder.decode(ctx, in, out);
			if(null != out && !out.isEmpty()) {
				readed = true;
			}
		}
		if(!readed) {
			requestDecoder.decode(ctx, in, out);
			if(null != out && !out.isEmpty()) {
				readed = true;
			}
		}
		if(!readed) {
			System.out.println("Faild...");
			return;
		}
	}

}
