package com.myself.acceptors;

import java.util.List;

public abstract interface IDoAcceptor<T> {
	
	public abstract Object doAcceptor(List<T> list) throws Exception;

}
