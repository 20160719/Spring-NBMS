package com.myself.controllers;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.myself.acceptors.basic.IBookAcceptor;
import com.myself.busiobj.basic.BasicObj;
import com.myself.dto.EntityDto;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Operation;

public abstract class AbstractBasicController extends AbstractController {
	
	@Resource(name = "bookAcceptor")
	private IBookAcceptor bookAcceptor;
	
	public <T> BasicObj<T> doAction(EntityDto entityDto) throws CustomException {
		Assert.notNull(entityDto, "entityDto must not be null"); 
		BasicObj<T> basicObj = new BasicObj<T>();
		basicObj.setEntityDto(entityDto);
		Operation operation = getOperation();
		basicObj.setOperation(operation);
		return basicObj;
	}

	protected final IBookAcceptor getBookAcceptor() {
		return bookAcceptor;
	}

}
