package com.myself.acceptors;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;

public abstract class AbstractAcceptor<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractAcceptor.class);

	public int businessAcceptor(AbsBusinessObj<T> absBusinessObj, IBeforeAcceptor<T> beforeAcceptor,
			IDoAcceptor<T> doAcceptor, IAfterAcceptor afterAcceptor, String opType) throws CustomException {
		beforeAcceptor.beforeAcceptor(absBusinessObj);
		int count = processing(doAcceptor, absBusinessObj.getList());
		afterAcceptor.afterAcceptor();
		return count;

	}

	public int businessAcceptor(AbsBusinessObj<T> absBusinessObj, IDoAcceptor<T> doAcceptor, String opType)
			throws CustomException {
		absBusinessObj.getOperation().setOpType(opType);
		return businessAcceptor(absBusinessObj, beforeAcceptor, doAcceptor, afterAcceptor, opType);
	}

	protected IBeforeAcceptor<T> beforeAcceptor = (absBusinessObj) -> {
		try {
			Assert.notNull(absBusinessObj, "the absBusinessObj must not be null");
			Assert.notNull(absBusinessObj.getEntityDto().getTargetJson(), "the targetJson must not be empty");
			init(absBusinessObj);
		} catch (IllegalArgumentException e) {
			throw new CustomException(e.getMessage());
		}
	};

	protected void init(AbsBusinessObj<T> absBusinessObj) throws CustomException {

	}

	public int processing(IDoAcceptor<T> doAcceptor, List<T> list) throws CustomException {
		try {
			return doAcceptor.doAcceptor(list);
		} catch (Exception e) {
			if (e instanceof DataAccessException) {
				throw new SystemException(e.getCause().getMessage());
			} else if(e instanceof ArithmeticException) {
				throw new SystemException(e.getMessage());
			}
			throw e; 
		}
	}

	protected IAfterAcceptor afterAcceptor = () -> {
		clear();
		return null;
	};

	/**
	 * 杩斿洖涓氬姟瀵硅薄
	 * 
	 * @return
	 * @return CmdResult TODO
	 */
	public CmdResult getCmdResult() {
		return new CmdResult();
	}

	/**
	 * 娓呯悊鍚勭瀵硅薄
	 * 
	 * @return void TODO
	 */
	protected void clear() {

	}

	protected void info(String message) {
		logger.info(message);
	}

	protected void error(String message, Exception e) {
		logger.error(message, e);
	}

}
