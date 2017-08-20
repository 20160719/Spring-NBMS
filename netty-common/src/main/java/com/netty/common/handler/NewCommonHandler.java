package com.netty.common.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;

public class NewCommonHandler extends ChannelHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof String) {
			handleString(ctx, (String)msg);
		} else if(msg instanceof HttpRequestDecoder) {
			handleHttp(ctx, (FullHttpRequest)msg);
		}
//		super.channelRead(ctx, msg);
	}

	private void handleString(ChannelHandlerContext ctx, String msg) {
		// TODO Auto-generated method stub
		System.out.println("msg: " + msg);
	}

	private void handleHttp(ChannelHandlerContext ctx, FullHttpRequest req) {
		HttpRequest request = (HttpRequest)req;
		System.out.println(request.getUri());
		
	}

}
