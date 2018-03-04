package com.myself.acceptors.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.CmdResult;
import com.myself.acceptors.system.IRoleAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.Tree;
import com.myself.services.system.IRoleService;

@Service(value = "roleAcceptor")
@Scope(value = "prototype")
public class RoleAcceptor extends AbstractSystemAcceptor<Tree> implements IRoleAcceptor {

	@Resource(name = "roleServiceImpl")
	private IRoleService roleService;
	
	protected IRoleService getRoleService() {
		return roleService;
	}
	
	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getRoleService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getRoleService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getRoleService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	public List<Tree> queryAllResources() throws CustomException {
		return queryTrees();
	}

	@Override
	public List<Tree> queryRolesByRoleIds(List<String> roleIds) throws CustomException {
		List<Tree> roleList = queryAllResources();
		return CommonUtils.filterTreeListById(roleList, roleIds);
	}

	@Override
	public void refreshCache() throws CustomException {
		queryTrees();
	}

	@Override
	public List<Tree> queryTrees(Tree tree) throws CustomException {
		return transTrees(tree);
	}

	@Override
	public List<Tree> queryTrees() throws CustomException {
		return query(() -> getRoleService().queryTrees());
	}

	@Override
	public CmdResult querySeq() throws CustomException {
		return query(() -> getRoleService().queryRoleSeq());
	}

}
