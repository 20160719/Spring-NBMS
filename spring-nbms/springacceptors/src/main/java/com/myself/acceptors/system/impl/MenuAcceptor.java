package com.myself.acceptors.system.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.CmdResult;
import com.myself.acceptors.system.IMenuAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.Tree;

@Service(value = "menuAcceptor")
@Scope(value = "prototype")
public class MenuAcceptor extends AbstractSystemAcceptor<Tree> implements IMenuAcceptor {

	private static final Logger logger = LoggerFactory.getLogger(MenuAcceptor.class);

	@Override
	protected void init(AbsBusinessObj<Tree> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTree(absBusinessObj.getEntityDto().getTargetJson()));
	}

	@Override
	@CacheEvict(value = { "allMenuResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getMenuService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = { "allMenuResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> {
			int count = getMenuService().modifies(list);
//			int i = 1 / 0;
			return count;
		}, Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = { "allMenuResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Tree> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getMenuService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	public List<Tree> queryAllResources() {
		try {
			return queryTrees();
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tree> queryTrees(Tree tree) throws CustomException {
		return transTrees(tree);
	}

	@Override
	public CmdResult querySeq() {
		return query(() -> getMenuService().queryMenuSeq());
	}

	@Override
	public void refreshCache() {
		queryAllResources();
	}

	@Override
	@Cacheable("allMenuResources")
	public List<Tree> queryTrees() throws CustomException {
		return getMenuService().queryTrees();
	}

	@Override
	protected void error(String message, Exception e) {
		logger.error(message, e);
	}

}
