package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myself.persistences.entity.Tree;
import com.myself.persistences.mapper.system.RoleMapper;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IRoleService;

@Service(value = "roleServiceImpl")
public class RoleServiceImpl extends AbstractSystemService<Tree> implements
		IRoleService {

	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;
	
	protected final RoleMapper getRoleMapper() {
		return roleMapper;
	}
	
	@Override
	public Integer creates(List<Tree> list) throws DataAccessException {
		return getRoleMapper().creates(list);
	}

	@Override
	public Integer deletes(List<Tree> list) throws DataAccessException {
		return getRoleMapper().deletes(list);
	}

	@Override
	public Integer modifies(List<Tree> list) throws DataAccessException {
		return getRoleMapper().modifies(list);
	}

	@Override
	public Tree load(Tree obj) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> queries(Tree obj) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tree> query(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(cacheNames="roles", key="'allRoles'")
	public List<Tree> queryTrees() throws DataAccessException {
		return getRoleMapper().queryTrees();
	}

	@Override
	public String queryRoleSeq() throws DataAccessException {
		return querySeqByName("ROLE");
	}

}
