package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.myself.persistences.entity.system.Permission;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IPermsService;

@Service(value = "permsServiceImpl")
public class PermsServiceImpl extends AbstractSystemService<Permission> implements IPermsService {

	public Integer creates(List<Permission> list) throws Exception {
		return getPermsMapper().creates(list);
	}

	public Integer deletes(List<Permission> list) throws Exception {
		return getPermsMapper().deletes(list);
	}

	public Integer modifies(List<Permission> list) throws Exception {
		return getPermsMapper().modifies(list);
	}

	public Permission load(Permission obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Permission> queries(Permission obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Permission> query(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> queryPermissions() throws Exception {
		return getPermsMapper().queryPermissions();
	}

}
