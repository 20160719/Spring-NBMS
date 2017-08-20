package com.myself.services;

import javax.annotation.Resource;

import com.myself.persistences.mapper.system.BookTypeMapper;
import com.myself.persistences.mapper.system.MenuMapper;
import com.myself.persistences.mapper.system.OrgMapper;
import com.myself.persistences.mapper.system.PermsMapper;
import com.myself.persistences.mapper.system.RoleMapper;
import com.myself.persistences.mapper.system.UserMapper;

public abstract class AbstractSystemService<T> extends AbstractService<T> {

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	@Resource(name = "orgMapper")
	private OrgMapper orgMapper;
	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;
	@Resource(name = "menuMapper")
	private MenuMapper menuMapper;
	@Resource(name = "permsMapper")
	private PermsMapper permsMapper;
	@Resource(name = "bookTypeMapper")
	private BookTypeMapper bookTypeMapper;

	protected final UserMapper getUserMapper() {
		return this.userMapper;
	}

	protected final OrgMapper getOrgMapper() {
		return this.orgMapper;
	}

	protected final  RoleMapper getRoleMapper() {
		return this.roleMapper;
	}

	protected final  MenuMapper getMenuMapper() {
		return this.menuMapper;
	}

	protected final  PermsMapper getPermsMapper() {
		return this.permsMapper;
	}

	protected BookTypeMapper getBookTypeMapper() {
		return bookTypeMapper;
	}

	
}
