package com.netty.client.handler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.constants.Constants;
import com.netty.common.entity.HeartBeatPackage;
import com.netty.common.entity.LoginOutPackage;
import com.netty.common.handler.CommonHandler;
import com.netty.common.utils.CommonUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClientHandler extends CommonHandler {

	public NettyClientHandler() {
		super("Netty-Client");
	}

	@Override
	protected void handleAllIdle(ChannelHandlerContext ctx) {
		super.handleAllIdle(ctx);
		sendPingMsg(ctx, null);
	}

	@Override
	protected void sendPingMsg(ChannelHandlerContext ctx, String msg) {
		if (null != msg && !"".equals(msg)) {
			HeartBeatPackage heartBeatPackage = JSONObject.parseObject(msg, HeartBeatPackage.class);
			if (!heartBeatPackage.getLoginFlag()) {
				userLoginReq(ctx);
			}
		} else {
			super.sendPingMsg(ctx, msg);
			HeartBeatPackage heartBeat = new HeartBeatPackage();
			heartBeat.setPackageType(Constants.PACKAGE_TYPE_PING);
			heartBeat.setAccount("bbb");
			heartBeat.setLoginFlag(true);
			heartBeat.setCreateTime(new Date());
			String heartStr = CommonUtils.concatSplitStr(heartBeat);
			ctx.channel().writeAndFlush(heartStr);
		}
	}

	@Override
	protected void userLoginReq(ChannelHandlerContext ctx) {
		super.userLoginReq(ctx);
		LoginOutPackage loginOutPackage = new LoginOutPackage();
		loginOutPackage.setPackageType(Constants.PACKAGE_TYPE_LOGIN);
		loginOutPackage.setAccount("bbb");
		ctx.channel().writeAndFlush(CommonUtils.concatSplitStr(loginOutPackage));
	}

	@Override
	protected void userLoginSuccess(ChannelHandlerContext ctx) {
		super.userLoginSuccess(ctx);
		ctx.channel().pipeline().addFirst(name, new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
		logger.info("-- {} --", new String[] { "pipeline" });
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

}
