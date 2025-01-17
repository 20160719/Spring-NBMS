package com.myself.acceptors;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

public abstract class AbstractSystemAcceptor<T> extends AbstractAcceptor<T> {

	/**
	 *
	 * @param listQuery
	 * @return
	 * @throws CustomException
	 * @return List<T>
	 * TODO
	 */
	protected List<T> query(IListQuery<T> listQuery) throws CustomException {
		try {
			return listQuery.queries();
		} catch (DataAccessException e) {
			throw new CustomException(e.getMessage());
		}
	}

	protected CmdResult query(IStringQuery stringQuery) throws CustomException {
		try {
			return CmdResult.getCmdResult().setStrResult(stringQuery.query());
		} catch (DataAccessException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<Tree> queryAllResources() throws CustomException {
		return null;
	}
	
	/**
	 * 杩囨护List闆嗗悎
	 * @param tree
	 * @return
	 * @throws CustomException
	 */
	protected List<Tree> transTrees(Tree tree) throws CustomException {
		List<Tree> treeList = queryAllResources();
		Assert.notEmpty(treeList, "the list must not be empty");
		treeList = CommonUtils.setChildrenListForTree(treeList);
		return CommonUtils.filterTreeList(treeList, tree);
	}

}
