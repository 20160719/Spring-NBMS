package com.myself.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myself.common.utils.DateUtils;
import com.myself.persistences.entity.Operation;

public abstract class AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;
    
    protected String opCode;
    
    protected String account;
	
	protected static final String SUFFIX = ".action";
	
	protected String INDEX = getDirectory() + "index";
	
	@RequestMapping(value = "/index" + SUFFIX, method = RequestMethod.GET)
	public String index(@RequestParam("opCode") String opCode){
		initIndex();
		return INDEX;
	}
	
	protected Operation getOperation() {
		Operation operation = new Operation();
		operation.setOpCode(this.opCode);
		operation.setAccount(this.account);
		operation.setOpTime(DateUtils.getTimestamp());
		return operation;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	protected void setRequestParam(String key, Object value) {
		request.setAttribute(key, value);
	}
	
	protected OutputStream getInputStream() {
		try {
			return  response.getOutputStream();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	protected void initIndex() {
		
	}
	
	protected abstract String getDirectory();
	
	protected void info(String message) {
		logger.info(message);
	}
	
	protected void error(String message, Exception e) {
		logger.error(message, e);
	}

}
