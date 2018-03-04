package com.myself.services.basic.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myself.persistences.entity.basic.Book;
import com.myself.persistences.mapper.basic.BookMapper;
import com.myself.services.AbstractBasicService;
import com.myself.services.basic.IBookService;

@Service(value = "bookServiceImpl")
public class BookServiceImpl extends AbstractBasicService<Book> implements
		IBookService {

	@Resource(name = "bookMapper")
	private BookMapper bookMapper;
	
	protected final BookMapper getBookMapper() {
		return bookMapper;
	}
	
	public Integer creates(List<Book> list) throws DataAccessException {
//		Ass
		return null;
	}

	public Integer deletes(List<Book> list) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer modifies(List<Book> list) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Book load(Book obj) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> queries(Book obj) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> query(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
