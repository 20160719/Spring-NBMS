package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.myself.persistences.entity.system.User;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IUserService;

@Service(value = "userServiceImpl")
public class UserServiceImpl extends AbstractSystemService<User> implements IUserService {

	public Integer creates(List<User> list) throws Exception {
		return getUserMapper().creates(list);
	}

	public Integer deletes(List<User> list) throws Exception {
		return getUserMapper().deletes(list);
	}

	public Integer modifies(List<User> list) throws Exception {
		return getUserMapper().modifies(list);
	}

	public User load(User obj) throws Exception {
		return getUserMapper().load(obj);
	}

	public List<User> queries(User obj) throws Exception {
		return getUserMapper().queries(obj);
	}

	public List<User> query(Map<String, Object> map) throws Exception {
		return getUserMapper().query(map);
	}

}
