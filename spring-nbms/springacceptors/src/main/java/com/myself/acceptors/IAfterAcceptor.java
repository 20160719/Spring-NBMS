package com.myself.acceptors;

import com.myself.exception.CustomException;

public abstract interface IAfterAcceptor {

	public abstract Object afterAcceptor() throws CustomException;

}
