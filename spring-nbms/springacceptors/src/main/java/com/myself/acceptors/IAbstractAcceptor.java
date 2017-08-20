package com.myself.acceptors;

import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;

public interface IAbstractAcceptor<T> {

	public BusinessResult creates(AbsBusinessObj<T> absBusinessObj) throws CustomException;

	public BusinessResult modifies(AbsBusinessObj<T> absBusinessObj) throws CustomException;

	public BusinessResult deletes(AbsBusinessObj<T> absBusinessObj) throws CustomException;
	
}
