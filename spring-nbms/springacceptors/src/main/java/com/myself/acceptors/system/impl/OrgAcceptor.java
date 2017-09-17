package com.myself.acceptors.system.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.CmdResult;
import com.myself.acceptors.system.IOrgAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.Tree;

@Service(value = "orgAcceptor")
@Scope(value = "prototype")
public class OrgAcceptor extends AbstractSystemAcceptor<Tree> implements IOrgAcceptor {

	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@CacheEvict(value = { "allOrgResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = { "allOrgResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = { "allOrgResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("allOrgResources")
	public List<Tree> queryAllResources() {
		return query(() -> getOrgService().queryTrees());
	}

	@Override
	public List<Tree> queryTrees(Tree tree) throws Exception {
		return transTrees(tree);
	}

	@Override
	public List<Tree> queryTrees() {
		return queryAllResources();
	}

	@Override
	public CmdResult querySeq() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshCache() {
		queryAllResources();
	}

}
