package com.myself.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class MySessionDaoImpl extends AbstractSessionDAO {
	
	private static final Logger log = LoggerFactory.getLogger(MySessionDaoImpl.class);
	
	private static final long EXPIRE_TIME = 30;
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void update(Session session) throws UnknownSessionException {
		
		redisTemplate.opsForValue().set(session.getId().toString(), session, EXPIRE_TIME, TimeUnit.MINUTES);
	}

	@Override
	public void delete(Session session) {
		redisTemplate.delete(session.getId().toString());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		redisTemplate.opsForValue().set(session.getId().toString(), session, EXPIRE_TIME, TimeUnit.MINUTES);
		log.info("doCreate session[sessionId:{}]", sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = (Session) this.redisTemplate.opsForValue().get(sessionId.toString());
		log.info("doReadSession session[sessionId:{}]", sessionId);
        return session;
	}

}
