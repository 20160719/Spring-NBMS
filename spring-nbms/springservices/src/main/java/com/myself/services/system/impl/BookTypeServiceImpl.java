package com.myself.services.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myself.persistences.entity.Tree;
import com.myself.persistences.mapper.system.BookTypeMapper;
import com.myself.services.AbstractSystemService;
import com.myself.services.system.IBookTypeService;

@Service(value = "bookTypeServiceImpl")
public class BookTypeServiceImpl extends AbstractSystemService<Tree> implements IBookTypeService {

	
	@Resource(name = "bookTypeMapper")
	private BookTypeMapper bookTypeMapper;

	protected BookTypeMapper getBookTypeMapper() {
		return bookTypeMapper;
	}
	
	@Override
	public Integer creates(List<Tree> list) throws DataAccessException {
		return getBookTypeMapper().creates(list);
	}

	@Override
	public Integer deletes(List<Tree> list) throws DataAccessException {
		return getBookTypeMapper().deletes(list);
	}

	@Override
	public Integer modifies(List<Tree> list) throws DataAccessException {
		return getBookTypeMapper().modifies(list);
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

}
