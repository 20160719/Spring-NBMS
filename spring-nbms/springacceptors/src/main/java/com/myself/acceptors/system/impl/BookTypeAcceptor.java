package com.myself.acceptors.system.impl;

import com.myself.persistences.entity.Operation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.BusinessResult;
import com.myself.acceptors.system.IBookTypeAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

import java.util.List;

@Service(value = "bookTypeAcceptor")
@Scope(value = "prototype")
public class BookTypeAcceptor extends AbstractSystemAcceptor<Tree> implements IBookTypeAcceptor {

	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@CacheEvict(value = {"allBookTypeResources"}, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BusinessResult creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = {"allBookTypeResources"}, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BusinessResult modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = {"allBookTypeResources"}, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BusinessResult deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getBookTypeService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("allBookTypeResources")
	public List<Tree> queryAllResources() throws CustomException {
		return query(() -> getBookTypeService().queryTrees());
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
//		return query(() -> getBookTypeService().);
		return null;
	}

	@Override
	public void refreshCache() throws CustomException {
		queryAllResources();
	}
}
