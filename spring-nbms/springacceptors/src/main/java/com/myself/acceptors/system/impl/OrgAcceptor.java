package com.myself.acceptors.system.impl;

import java.util.List;

import com.myself.persistences.entity.Operation;
import com.myself.common.utils.CommonUtils;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.BusinessResult;
import com.myself.acceptors.system.IOrgAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

@Service(value = "orgAcceptor")
@Scope(value = "prototype")
public class OrgAcceptor extends AbstractSystemAcceptor<Tree> implements IOrgAcceptor {

	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}
	
	@Override
	@CacheEvict(value = {"allOrgResources"}, allEntries = true)
	public BusinessResult creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = {"allOrgResources"}, allEntries = true)
	public BusinessResult modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = {"allOrgResources"}, allEntries = true)
	public BusinessResult deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("allOrgResources")
	public List<Tree> queryAllResources() throws CustomException {
		return query(() -> getOrgService().queryTrees());
	}

	@Override
	public List<Tree> queryTrees(Tree tree) throws CustomException {
		return transTrees(tree);
	}

	@Override
	public List<Tree> queryTrees() throws CustomException {
		return queryAllResources();
	}

	@Override
	public BusinessResult querySeq() throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshCache() throws CustomException {
		queryAllResources();
	}

}
