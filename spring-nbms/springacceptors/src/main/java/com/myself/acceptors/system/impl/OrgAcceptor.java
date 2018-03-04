package com.myself.acceptors.system.impl;

import java.util.List;

import javax.annotation.Resource;

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
import com.myself.services.system.IOrgService;

@Service(value = "orgAcceptor")
//@Scope(value = "prototype")
public class OrgAcceptor extends AbstractSystemAcceptor<Tree> implements IOrgAcceptor {

	@Resource(name = "orgServiceImpl")
	private IOrgService orgService;
	
	protected IOrgService getOrgService() {
		return orgService;
	}
	
	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getOrgService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	public List<Tree> queryAllResources() throws CustomException {
		return queryTrees();
	}

	@Override
	public List<Tree> queryTrees(Tree tree) throws CustomException {
		return transTrees(tree);
	}

	@Override
	public List<Tree> queryTrees() throws CustomException{
		return query(() -> getOrgService().queryTrees());
	}

	@Override
	public CmdResult querySeq() throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshCache() throws CustomException{
		queryTrees();
	}

}
