package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myself.persistences.entity.system.User;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IUserService;

@Service(value = "userServiceImpl")
public class UserServiceImpl extends AbstractSystemService<User> implements IUserService {

	public Integer creates(List<User> list) throws DataAccessException {
		return getUserMapper().creates(list);
	}

	public Integer deletes(List<User> list) throws DataAccessException {
		return getUserMapper().deletes(list);
	}

	public Integer modifies(List<User> list) throws DataAccessException {
		return getUserMapper().modifies(list);
	}

	@Cacheable("users")
	public User load(User obj) throws DataAccessException {
		return getUserMapper().load(obj);
	}

	public List<User> queries(User obj) throws DataAccessException {
		return getUserMapper().queries(obj);
	}

	public List<User> query(Map<String, Object> map) throws DataAccessException {
		return getUserMapper().query(map);
	}

}
