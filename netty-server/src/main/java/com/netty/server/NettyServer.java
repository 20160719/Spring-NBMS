package com.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.common.code.MyDelimiterBasedFrameDecoder;
import com.netty.common.utils.CommonUtils;
import com.netty.server.handler.NettyServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
	
	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup workGroup;
	private ServerBootstrap serverBootstrap;
	private ChannelFuture future;
	private Channel channel;
	private int mainThreads;
	private int workThreads;
	private int port;
	
	public NettyServer(int mainThreads, int workThreads, int port) {
		this.mainThreads = mainThreads;
		this.workThreads = workThreads;
		this.port = port;
	}
	
	private void init() {
		this.mainGroup = new NioEventLoopGroup(this.mainThreads);
		this.workGroup = new NioEventLoopGroup(this.workThreads);
		this.serverBootstrap = new ServerBootstrap();
		this.serverBootstrap.group(this.mainGroup, this.workGroup)
							.channel(NioServerSocketChannel.class)
							.option(ChannelOption.SO_BACKLOG, 1024)
							.childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								protected void initChannel(SocketChannel arg0) throws Exception {
									ChannelPipeline pip = arg0.pipeline();
//									pip.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
									pip.addLast(new MyDelimiterBasedFrameDecoder(100, Unpooled.copiedBuffer(CommonUtils.STRING_SPLIT.getBytes())));
//									pip.addLast(new MyDecoder());
									pip.addLast(new StringDecoder());
									pip.addLast(new StringEncoder());
									pip.addLast(new NettyServerHandler());
									logger.info("---- {} ----", new String[]{"initChannel" });
								}
							});
		logger.info("服务器初始化完成...");
	}
	
	public void bind() throws Exception {
		init();
		try {
			this.future = this.serverBootstrap.bind(this.port).sync();
			if(this.future.isSuccess()) {
				logger.info("服务器已启动...");
			}
			this.channel = this.future.sync().channel();
			this.channel.closeFuture().sync();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			this.mainGroup.shutdownGracefully();
			this.workGroup.shutdownGracefully();
		}
	}

}
