package com.netty.client;

public class AppClient {
	public static void main(String[] args) {
		try {
			for(int i = 0; i < 1; i++) {
				NettyClient client = new NettyClient(1, "127.0.0.1", 8060);
				client.connect();
//				client.sendData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
