package com.myself.acceptors.system;

import java.util.List;

import com.myself.acceptors.IAbstractAcceptor;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.system.Permission;

public abstract interface IPermsAcceptor extends IAbstractAcceptor<Permission> {
	
	public abstract List<Permission> queryPermissions() throws CustomException;

	public abstract Permission queryPermission(Permission permission) throws CustomException;

	public abstract List<Permission> queryPermsByRoleIds(List<String> roleIds) throws CustomException;

}
