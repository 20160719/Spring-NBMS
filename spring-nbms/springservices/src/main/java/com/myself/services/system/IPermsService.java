package com.myself.services.system;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myself.persistences.entity.system.Permission;
import com.myself.services.IBaseService;

public abstract interface IPermsService extends IBaseService<Permission> {
	
	public abstract List<Permission> queryPermissions() throws DataAccessException;

}
