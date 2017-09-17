package com.myself.acceptors.system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
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

@Service(value = "permsAcceptor")
@Scope(value = "prototype")
public class PermsAcceptor extends AbstractSystemAcceptor<Permission> implements IPermsAcceptor {

	@Override
	protected void init(AbsBusinessObj<Permission> absBusinessObj) {
		absBusinessObj.setList(CommonUtils.transTargetList(absBusinessObj.getEntityDto().getTargetJson(), Permission.class));
	}

	@Override
	@CacheEvict(value = { "allPermissionResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().creates(list), Operation.OP_CREATE);
	}

	@Override
	@CacheEvict(value = { "allPermissionResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().modifies(list), Operation.OP_MODIFY);
	}

	@Override
	@CacheEvict(value = { "allPermissionResources" }, allEntries = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Permission> absBusinessObj) throws CustomException {
		return businessAcceptor(absBusinessObj, (list) -> getPermsService().deletes(list), Operation.OP_DELETE);
	}

	@Override
	@Cacheable("allPermissionResources")
	public List<Permission> queryPermissions() {
		return query(() -> getPermsService().queryPermissions());
	}

	@Override
	public Permission queryPermission(Permission permission) {
		List<Permission> permsList = queryPermissions();
		String permId = permission.getPermId();
		return permsList.stream().filter(p -> permId.equals(p.getPermId())).collect(Collectors.toList()).get(0);
	}

	@Override
	public List<Permission> queryPermsByRoleIds(List<String> roleIds) {
		List<Permission> permsList = queryPermissions();
		return CommonUtils.filterPermissionListById(permsList, roleIds);
	}

}
