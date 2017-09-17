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
import com.myself.acceptors.system.IBookTypeAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.Tree;

@Service(value = "bookTypeAcceptor")
@Scope(value = "prototype")
public class BookTypeAcceptor extends AbstractSystemAcceptor<Tree> implements IBookTypeAcceptor {

	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@CacheEvict(value = { "allBookTypeResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = { "allBookTypeResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = { "allBookTypeResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("allBookTypeResources")
	public List<Tree> queryAllResources() {
		return query(() -> getBookTypeService().queryTrees());
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
		// return query(() -> getBookTypeService().);
		return null;
	}

	@Override
	public void refreshCache() {
		queryAllResources();
	}
}
