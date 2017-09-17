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
	protected List<T> query(IListQuery<T> listQuery) throws DataAccessException {
		return listQuery.queries();
	}

	protected CmdResult query(IStringQuery stringQuery) throws DataAccessException {
		return getCmdResult().setStrResult(stringQuery.query());
	}

	public List<Tree> queryAllResources() throws DataAccessException {
		return null;
	}
	
	/**
	 * 杩囨护List闆嗗悎
	 * @param tree
	 * @return
	 * @throws CustomException
	 */
	protected List<Tree> transTrees(Tree tree) throws Exception {
		List<Tree> treeList = queryAllResources();
		Assert.notEmpty(treeList, "the list must not be empty");
		treeList = CommonUtils.setChildrenListForTree(treeList);
		return CommonUtils.filterTreeList(treeList, tree);
	}

	protected final IUserService getUserService() {
		return this.userService;
	}

	protected final IRoleService getRoleService() {
		return this.roleService;
	}

	protected final IMenuService getMenuService() {
		return this.menuService;
	}

	protected final IPermsService getPermsService() {
		return this.permsService;
	}

	protected final IOrgService getOrgService() {
		return this.orgService;
	}

	protected IBookTypeService getBookTypeService() {
		return bookTypeService;
	}

}
