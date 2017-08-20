package com.myself.acceptors;

import javax.annotation.Resource;

import com.myself.services.basic.IBookService;

public abstract class AbstractBasicAcceptor<T> extends AbstractAcceptor<T> {

	@Resource(name = "bookServiceImpl")
	private IBookService bookService;

	protected final IBookService getBookService() {
		return this.bookService;
	}

}
