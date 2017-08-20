package com.myself.acceptors;

import java.util.List;

public interface IListQuery<T> {
	
	public abstract List<T> queries() throws Exception;

}
