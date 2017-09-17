package com.myself.services.system;

import org.springframework.dao.DataAccessException;

import com.myself.persistences.entity.Tree;
import com.myself.services.IBaseService;

public abstract interface IRoleService extends IBaseService<Tree> {
	
	public abstract String queryRoleSeq() throws DataAccessException;

}
