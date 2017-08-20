package com.myself.acceptors.system;

import java.util.List;

import com.myself.acceptors.ISystemAcceptor;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

public abstract interface IRoleAcceptor extends ISystemAcceptor<Tree> {

	public abstract List<Tree> queryRolesByRoleIds(List<String> roleIds) throws CustomException;

}
