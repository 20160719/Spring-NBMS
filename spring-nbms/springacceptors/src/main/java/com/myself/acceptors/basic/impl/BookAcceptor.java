package com.myself.acceptors.basic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.acceptors.AbstractBasicAcceptor;
import com.myself.acceptors.basic.IBookAcceptor;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.SystemException;
import com.myself.persistences.entity.basic.Book;
import com.myself.services.basic.IBookService;

@Service(value = "bookAcceptor")
//@Scope(value = "prototype")
public class BookAcceptor extends AbstractBasicAcceptor<Book> implements IBookAcceptor {

	@Resource(name = "bookServiceImpl")
	private IBookService bookService;

	protected IBookService getBookService() {
		return bookService;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int creates(AbsBusinessObj<Book> absBusinessObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int modifies(AbsBusinessObj<Book> absBusinessObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
	public int deletes(AbsBusinessObj<Book> absBusinessObj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
