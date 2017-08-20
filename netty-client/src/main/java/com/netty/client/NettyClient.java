package com.netty.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.client.handler.NettyClientHandler;
import com.netty.common.code.MyDelimiterBasedFrameDecoder;
import com.netty.common.constants.Constants;
import com.netty.common.entity.HeartBeatPackage;
import com.netty.common.entity.LoginOutPackage;
import com.netty.common.utils.CommonUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
	
	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
	
	private EventLoopGroup group;
	private Bootstrap bootstrap;
	private ChannelInitializer<SocketChannel> chanelInit;
	private ChannelFuture future;
	private Channel channel;
	private String host;
	private int port;
	
	public NettyClient(int groupNum, String host, int port) {
		this.group = new NioEventLoopGroup(groupNum);
		this.bootstrap = new Bootstrap();
		this.host = host;
		this.port = port;
		this.bootstrap.group(group)
		    .channel(NioSocketChannel.class)
		    .option(ChannelOption.TCP_NODELAY, true);
		initChannel();
	}
	
	private void initChannel() {
		this.chanelInit = new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel arg0) throws Exception {
				ChannelPipeline pip = arg0.pipeline();
//				pip.addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
				pip.addLast(new MyDelimiterBasedFrameDecoder(100, Unpooled.copiedBuffer(CommonUtils.STRING_SPLIT.getBytes())));
//				pip.addLast(new MyDecoder());
				pip.addLast(new StringDecoder());
				pip.addLast(new StringEncoder());
				pip.addLast(new NettyClientHandler());
			}
		};
		this.bootstrap.handler(this.chanelInit);
		logger.info("客户端初始化完成...");
	}
	
	public void connect() {
		try {
			this.future = this.bootstrap.connect(host, port).sync();
			this.channel = this.future.channel();
			sendData();
			this.channel.closeFuture().sync();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			this.group.shutdownGracefully();	
		}
	}
	
	public void sendData() throws Exception {
		
		HeartBeatPackage heartBeat = new HeartBeatPackage();
		heartBeat.setPackageType(Constants.PACKAGE_TYPE_PING);
		heartBeat.setCreateTime(new Date());
		heartBeat.setAccount("aaa");
		String heartStr = CommonUtils.concatSplitStr(heartBeat);
		this.channel.writeAndFlush(heartStr);
//		Thread.sleep(5000);
		LoginOutPackage loginOutPackage = new LoginOutPackage();
		loginOutPackage.setPackageType(Constants.PACKAGE_TYPE_LOGIN);
		loginOutPackage.setAccount("bbb");
//		logger.info("str: {}", new String[]{CommonUtils.concatSplitStr(loginOutPackage) });
//		this.channel.writeAndFlush(CommonUtils.concatSplitStr(loginOutPackage));
//		while(true) {
//			Thread.sleep(3000);
//			this.channel.writeAndFlush(heartStr);
//		}
//		
	}

//	private class MyChannelFutureListener implements ChannelFutureListener {
//		@Override
//		public void operationComplete(ChannelFuture future) throws Exception {
//			if (future.isSuccess()) {
//				System.out.println("Connect to server successfully...");
//			} else {
//				System.out.println("Failed to connect to server, try connect after 10s");
//				future.channel().eventLoop().schedule(new Runnable() {
//					@Override
//					public void run() {
//						connect();
//					}
//				}, 10, TimeUnit.SECONDS);
//			}
//		}
//	}

}
