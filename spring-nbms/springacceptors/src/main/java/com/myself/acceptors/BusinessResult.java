package com.myself.acceptors;

public class BusinessResult {

	private Integer intResult;

	private String strResult;

	private String retCode;

	private String retMess;

	private String retBody;

	public Integer getIntResult() {
		return intResult;
	}

	public BusinessResult setIntResult(Integer intResult) {
		this.intResult = intResult;
		return this;
	}

	public String getStrResult() {
		return strResult;
	}

	public BusinessResult setStrResult(String strResult) {
		this.strResult = strResult;
		return this;
	}

	public String getRetMess() {
		return retMess;
	}

	public BusinessResult setRetMess(String retMess) {
		this.retMess = retMess;
		return this;
	}

	public String getRetCode() {
		return retCode;
	}

	public BusinessResult setRetCode(String retCode) {
		this.retCode = retCode;
		return this;
	}

	public String getRetBody() {
		return retBody;
	}

	public BusinessResult setRetBody(String retBody) {
		this.retBody = retBody;
		return this;
	}

}
