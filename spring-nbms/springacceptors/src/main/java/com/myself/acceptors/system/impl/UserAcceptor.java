package com.myself.acceptors.system.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.system.IUserAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.SystemUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.exception.ValidException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.system.User;

@Service(value = "userAcceptor")
@Scope(value = "prototype")
public class UserAcceptor extends AbstractSystemAcceptor<User> implements IUserAcceptor {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> {
			validUsers(list);
			return getUserService().creates(list);
		}, Operation.OP_CREATE);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getUserService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getUserService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("users")
	public List<User> queries(User user) throws CustomException {
		return query(() -> getUserService().queries(user));
	}

	@Override
	//@Cacheable("users")
	public User load(User user) throws CustomException {
		try {
			return getUserService().load(user);
		} catch (DataAccessException e) {
			throw new SystemException(e.getCause().getMessage());
		}
	}

	private void validUsers(List<User> list) throws CustomException {
		User user1 = SystemUtils.getUser();
		for (User u : list) {
			String account = u.getAccount();
			user1.setAccount(account);
			User user = load(user1);
			if (null != user)
				throw new ValidException("User Info has exist, Account: " + account);
		}
	}

}
