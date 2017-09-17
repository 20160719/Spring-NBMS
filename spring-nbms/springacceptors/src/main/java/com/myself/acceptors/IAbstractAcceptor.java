package com.myself.acceptors;

import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;

public interface IAbstractAcceptor<T> {

	public int creates(AbsBusinessObj<T> absBusinessObj) throws CustomException;
	
	public int modifies(AbsBusinessObj<T> absBusinessObj) throws CustomException;
	
	public int deletes(AbsBusinessObj<T> absBusinessObj) throws CustomException;
	
}
