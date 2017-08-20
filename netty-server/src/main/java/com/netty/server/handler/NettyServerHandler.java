package com.netty.server.handler;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.constants.Constants;
import com.netty.common.entity.HeartBeatPackage;
import com.netty.common.handler.CommonHandler;
import com.netty.common.utils.CommonUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class NettyServerHandler extends CommonHandler {

	public NettyServerHandler() {
		super("Netty-Server");
	}

	@Override
	protected void handleReaderIdle(ChannelHandlerContext ctx) {
		super.handleReaderIdle(ctx);
		sendPongMsg(ctx, null);
	}

	@Override
	protected void sendPongMsg(ChannelHandlerContext ctx, String msg) {
		if (null != msg && !"".equals(msg)) {
			HeartBeatPackage heartBeatPackage = JSONObject.parseObject(msg, HeartBeatPackage.class);
			logger.info("---- {} ----", new String[] { CommonUtils.concatSplitStr(heartBeatPackage) });
			if (!heartBeatPackage.getLoginFlag()) {
				// 通知客户端登陆
				sendServerCommomRespPackage(ctx, Constants.PACKAGE_TYPE_LOGIN_NEED);
			}
		} else {
			super.sendPongMsg(ctx, msg);
			Channel ch = ctx.channel();
			HeartBeatPackage heartBeat = new HeartBeatPackage();
			heartBeat.setPackageType(Constants.PACKAGE_TYPE_PONG);
			heartBeat.setCreateTime(new Date());
			heartBeat.setLoginFlag(true);
			heartBeat.setAccount(addressToUser.get(ch.remoteAddress().toString()));
			String heartStr = CommonUtils.concatSplitStr(heartBeat);
			ByteBuf buf = Unpooled.copiedBuffer(heartStr.getBytes());
			ch.writeAndFlush(buf);
		}
	}


}
