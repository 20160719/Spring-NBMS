package com.myself.services.basic.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.myself.persistences.entity.basic.Book;
import com.myself.services.AbstractBasicService;
import com.myself.services.basic.IBookService;

@Service(value = "bookServiceImpl")
public class BookServiceImpl extends AbstractBasicService<Book> implements
		IBookService {

	public Integer creates(List<Book> list) throws Exception {
//		Ass
		return null;
	}

	public Integer deletes(List<Book> list) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer modifies(List<Book> list) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Book load(Book obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> queries(Book obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> query(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
