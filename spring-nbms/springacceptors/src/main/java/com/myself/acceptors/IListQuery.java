package com.myself.acceptors;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IListQuery<T> {
	
	public abstract List<T> queries() throws DataAccessException;

}
