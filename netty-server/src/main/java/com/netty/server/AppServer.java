package com.netty.server;

public class AppServer {
	public static void main(String[] args) {
		try {
			new NettyServer(1, 4, 8060).bind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
