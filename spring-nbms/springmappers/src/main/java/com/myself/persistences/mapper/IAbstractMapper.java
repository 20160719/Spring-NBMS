package com.myself.persistences.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.myself.persistences.entity.Tree;

@Mapper
public abstract interface IAbstractMapper<T> extends IBaseMapper<T> {
	
	public abstract List<Tree> queryTrees() throws DataAccessException;
	
}
