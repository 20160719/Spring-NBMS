package com.myself.acceptors.system.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractSystemAcceptor;
import com.myself.acceptors.system.IPermsAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.common.utils.CommonUtils;
import com.myself.exception.CustomException;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.system.Permission;
import com.myself.services.system.IPermsService;

@Service(value = "permsAcceptor")
//@Scope(value = "prototype")
public class PermsAcceptor extends AbstractSystemAcceptor<Permission> implements IPermsAcceptor {

	@Resource(name = "permsServiceImpl")
	private IPermsService permsService;
	
	protected IPermsService getPermsService() {
		return permsService;
	}
	
	@Override
	protected void init(AbsBusinessObj<Permission> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTargetList(absBusinessObj.getEntityDto().getTargetJson(), Permission.class));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	public List<Permission> queryPermissions() throws CustomException {
		return getPermsService().queryPermissions();
	}

	@Override
	public Permission queryPermission(Permission permission) throws CustomException{
		List<Permission> permsList = queryPermissions();
		String permId = permission.getPermId();
		return permsList.stream().filter(p -> permId.equals(p.getPermId())).collect(Collectors.toList()).get(0);
	}

	@Override
	public List<Permission> queryPermsByRoleIds(List<String> roleIds) throws CustomException {
		List<Permission> permsList = queryPermissions();
		return CommonUtils.filterPermissionListById(permsList, roleIds);
	}

}
