package com.myself.controllers;

import javax.annotation.Resource;

import com.myself.acceptors.CmdResult;
import com.myself.busiobj.AbsBusinessObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.Assert;

import com.myself.acceptors.system.IBookTypeAcceptor;
import com.myself.acceptors.system.IMenuAcceptor;
import com.myself.acceptors.system.IOrgAcceptor;
import com.myself.acceptors.system.IPermsAcceptor;
import com.myself.acceptors.system.IRoleAcceptor;
import com.myself.acceptors.system.IUserAcceptor;
import com.myself.busiobj.system.SystemObj;
import com.myself.dto.EntityDto;
import com.myself.dto.system.SystemDto;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;

public abstract class AbstractSystemController<T> extends AbstractController {

	@Resource(name = "roleAcceptor")
	private IRoleAcceptor roleAcceptor;

	@Resource(name = "menuAcceptor")
	private IMenuAcceptor menuAcceptor;

	@Resource(name = "orgAcceptor")
	private IOrgAcceptor orgAcceptor;

	@Resource(name = "permsAcceptor")
	private IPermsAcceptor permsAcceptor;

	@Resource(name = "bookTypeAcceptor")
	private IBookTypeAcceptor bookTypeAcceptor;

	@Resource(name = "userAcceptor")
	private IUserAcceptor userAcceptor;

	protected CmdResult doController(EntityDto entityDto, IBeforeController<T> beforeController,
			IDoController<T> doController) throws CustomException {
		entityDto.setAccount(SecurityUtils.getSubject().getSession().getAttribute("account").toString());
		AbsBusinessObj<T> absBusinessObj = beforeController.beforeController(entityDto);
		CmdResult result = new CmdResult();
		int count = 0;
		int status = 0;
		String msg = "操作失败";
		try {
			count = doController.doController(absBusinessObj);
			status = 1;
			msg = "操作成功";
		} catch (CustomException e) {
			if (e instanceof SystemException) {
				msg += ", " + e.getMessage();
			} else {
				msg += ", " + e.getMessage();
			}
		}
		result.setSuccessCount(count);
		result.setStatus(status);
		result.setMsg(msg);
		return result;
	}

	public SystemObj<T> beforeAction(EntityDto entityDto) throws CustomException {
		Assert.notNull(entityDto, "entityDto must not be null");
		SystemObj<T> businessObj = new SystemObj<T>();
		businessObj.setEntityDto(entityDto);
		Operation operation = getOperation();
		operation.setOpCode(entityDto.getOpCode());
		operation.setAccount(entityDto.getAccount());
		businessObj.setOperation(operation);
		return businessObj;
	}

	protected IBeforeController<T> beforeController = (entityDto) -> beforeAction(entityDto);

	protected SystemDto getSystemDto() {
		return new SystemDto();
	}

	protected final IRoleAcceptor getRoleAcceptor() {
		return roleAcceptor;
	}

	protected final IMenuAcceptor getMenuAcceptor() {
		return menuAcceptor;
	}

	protected final IOrgAcceptor getOrgAcceptor() {
		return orgAcceptor;
	}

	protected IPermsAcceptor getPermsAcceptor() {
		return permsAcceptor;
	}

	protected IBookTypeAcceptor getBookTypeAcceptor() {
		return bookTypeAcceptor;
	}

	protected IUserAcceptor getUserAcceptor() {
		return userAcceptor;
	}

}
