package com.myself.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomShiroSessionManager extends DefaultWebSessionManager {

	private static final Logger log = LoggerFactory.getLogger(CustomShiroSessionManager.class);
	
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		if(sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey)sessionKey).getServletRequest();
		}
		if(null != request && null != sessionId) {
			Object sessionObj = request.getAttribute(sessionId.toString());
			if(null != sessionObj) {
				return (Session)sessionObj;
			}
		}
		log.info("uri: {}", ((HttpServletRequest)request).getRequestURL().toString());
		Session session = super.retrieveSession(sessionKey);
		if(null != request && null != sessionId) {
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}
	
}
