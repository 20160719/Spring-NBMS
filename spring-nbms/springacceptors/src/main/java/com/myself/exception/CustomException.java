package com.myself.exception;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = -5146289906926181443L;
	
	private String code = "";
	
	private String msg = "";
	
	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
