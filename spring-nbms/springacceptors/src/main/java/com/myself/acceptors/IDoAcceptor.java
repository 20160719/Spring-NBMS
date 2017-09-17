package com.myself.acceptors;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myself.exception.CustomException;

public abstract interface IDoAcceptor<T> {
	
	public abstract int doAcceptor(List<T> list) throws DataAccessException, CustomException;

}
