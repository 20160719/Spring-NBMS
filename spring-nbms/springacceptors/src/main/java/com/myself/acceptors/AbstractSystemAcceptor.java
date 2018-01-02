package com.myself.acceptors;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;
import com.myself.services.system.IBookTypeService;
import com.myself.services.system.IMenuService;
import com.myself.services.system.IOrgService;
import com.myself.services.system.IPermsService;
import com.myself.services.system.IRoleService;
import com.myself.services.system.IUserService;

public abstract class AbstractSystemAcceptor<T> extends AbstractAcceptor<T> {

	@Resource(name = "userServiceImpl")
	private IUserService userService;

	@Resource(name = "roleServiceImpl")
	private IRoleService roleService;

	@Resource(name = "menuServiceImpl")
	private IMenuService menuService;

	@Resource(name = "permsServiceImpl")
	private IPermsService permsService;

	@Resource(name = "orgServiceImpl")
	private IOrgService orgService;

	@Resource(name = "bookTypeServiceImpl")
	private IBookTypeService bookTypeService;

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
			return getCmdResult().setStrResult(stringQuery.query());
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

	protected IUserService getUserService() {
		return this.userService;
	}

	protected IRoleService getRoleService() {
		return this.roleService;
	}

	protected IMenuService getMenuService() {
		return this.menuService;
	}

	protected IPermsService getPermsService() {
		return this.permsService;
	}

	protected IOrgService getOrgService() {
		return this.orgService;
	}

	protected IBookTypeService getBookTypeService() {
		return bookTypeService;
	}

}
