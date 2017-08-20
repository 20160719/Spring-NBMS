package com.myself.persistences.mapper;

import java.util.List;

import com.myself.persistences.entity.Tree;

public abstract interface IAbstractMapper<T> extends IBaseMapper<T> {
	
	public abstract List<Tree> queryTrees() throws Exception;
	
}
