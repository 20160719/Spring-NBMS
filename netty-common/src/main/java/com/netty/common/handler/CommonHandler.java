package com.netty.common.handler;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.constants.Constants;
import com.netty.common.entity.DataPackage;
import com.netty.common.entity.LoginOutPackage;
import com.netty.common.entity.ServerCommonRespPackage;
import com.netty.common.utils.CommonUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public abstract class CommonHandler extends SimpleChannelInboundHandler<String> {

	protected static final Logger logger = LoggerFactory.getLogger(CommonHandler.class);

	protected static Map<String, Channel> users = new ConcurrentHashMap<String, Channel>();
	
	protected static Map<String, String> addressToUser = new ConcurrentHashMap<String, String>();

	protected int heartBeatCount = 0;

	protected final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	protected String name;

	public CommonHandler(String name) {
		this.name = name;
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			IdleState state = e.state();
			if (IdleState.READER_IDLE == state) {
				handleReaderIdle(ctx);
			} else if (IdleState.WRITER_IDLE == state) {
				handleWriterIdle(ctx);
			} else if (IdleState.ALL_IDLE == state) {
				handleAllIdle(ctx);
			}
		}
	}
	
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		logger.info("---- {} --> {}, msg: {} ----", new String[] {ctx.channel().remoteAddress().toString(), "messageReceived", msg});
		DataPackage dataPackage = JSONObject.parseObject(msg, DataPackage.class);
		String type = dataPackage.getPackageType();
		String str = !dataPackage.getLoginFlag() ? msg : null;
		logger.info("dataPackage：{}", new Object[] { dataPackage });
		if (Constants.PACKAGE_TYPE_LOGIN.equals(type)) {
			userLogin(ctx, msg);
		} else if(Constants.PACKAGE_TYPE_LOGIN_NEED.equals(type)) {
			userLoginReq(ctx);
		} else if (Constants.PACKAGE_TYPE_LOGIN_SUCCESS.equals(type)) {
			userLoginSuccess(ctx);
		} else if(Constants.PACKAGE_TYPE_PING.equals(type)) {
			sendPongMsg(ctx, str);
		} else if(Constants.PACKAGE_TYPE_PONG.equals(type)) {
			sendPingMsg(ctx, str);
		} else {
			
		}
	}
	
	protected void userLoginSuccess(ChannelHandlerContext ctx) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "userLoginSuccess" });
		
	}

	protected void userLoginReq(ChannelHandlerContext ctx) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "userLoginReq" });
	}


	protected void userLogin(ChannelHandlerContext ctx, String msg) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "userLogin" });
		LoginOutPackage loginOutPackage = JSONObject.parseObject(msg, LoginOutPackage.class);
		String account = loginOutPackage.getAccount();
		//true 用户需要登录
		boolean needLogin =  null != account && !users.containsKey(account);
		if(needLogin) {
			users.put(account, ctx.channel());
			addressToUser.put(ctx.channel().remoteAddress().toString(), account);
			ctx.channel().pipeline().addFirst(name, new IdleStateHandler(5, 0, 0));
			sendServerCommomRespPackage(ctx, Constants.PACKAGE_TYPE_LOGIN_SUCCESS);
		} else {
			logger.info("---- {}用户已经登陆... ----", new String[]{account });
			ctx.channel().close();
		}
	}

	protected void customQuery(ChannelHandlerContext ctx, ByteBuf msg) {
		// TODO Auto-generated method stub

	}

	protected void sendPingMsg(ChannelHandlerContext ctx, String msg) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "sendPingMsg" });
	}

	protected void sendPongMsg(ChannelHandlerContext ctx, String msg) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "sendPongMsg" });
	}

	protected static void sendServerCommomRespPackage(ChannelHandlerContext ctx, String packageType) {
		ServerCommonRespPackage respPackage = ServerCommonRespPackage.getInstance();
		respPackage.setPackageType(packageType);
		respPackage.setAccount(addressToUser.get(ctx.channel().remoteAddress().toString()));
		if(Constants.PACKAGE_TYPE_LOGIN_SUCCESS.equals(packageType)) {
			respPackage.setLoginFlag(true);
		}
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "sendServerCommomRespPackage" });
		ctx.channel().writeAndFlush(CommonUtils.concatSplitStr(respPackage));
	}
	
	protected void handleReaderIdle(ChannelHandlerContext ctx) {
		heartBeatCount++;
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "handleReaderIdle" });
	}

	protected void handleWriterIdle(ChannelHandlerContext ctx) {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "handleWriterIdle" });
	}

	protected void handleAllIdle(ChannelHandlerContext ctx) {
		heartBeatCount++;
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "handleAllIdle" });
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "channelActive" });
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String address = ctx.channel().remoteAddress().toString();
		addressToUser.remove(address);
		users.remove(address);
		logger.info("---- {} --> {} ----", new String[] {ctx.channel().remoteAddress().toString(), "channelInactive" });
	}

}
