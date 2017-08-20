package com.myself.acceptors;

import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;

public abstract interface IBeforeAcceptor<T> {

	public abstract void beforeAcceptor(AbsBusinessObj<T> businessObj) throws CustomException;

}
