package com.myself.acceptors.system.impl;

import com.myself.persistences.entity.Operation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.BusinessResult;
import com.myself.acceptors.system.IUserAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.system.User;

@Service(value = "userAcceptor")
@Scope(value = "prototype")
public class UserAcceptor extends AbstractSystemAcceptor<User> implements IUserAcceptor {

	@Override
	public BusinessResult creates(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getUserService().creates(list), Operation.OP_CREATE);
	}

	@Override
	public BusinessResult modifies(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getUserService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	public BusinessResult deletes(AbsBusinessObj<User> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getUserService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("users")
	public User load(User user) throws CustomException {
		try {
			return getUserService().load(user);
		} catch (Exception e) {
			throw CustomException.getCustomException(e.getMessage());
		}
	}

}
