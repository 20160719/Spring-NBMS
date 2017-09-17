package com.myself.acceptors.system;


import java.util.List;

import com.myself.acceptors.IAbstractAcceptor;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.system.User;

public abstract interface IUserAcceptor extends IAbstractAcceptor<User> {
	
	public abstract User load(User user) throws CustomException;
	
	public abstract List<User> queries(User user) throws CustomException;
	
}
