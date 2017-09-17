package com.myself.exception;

public class SystemException extends CustomException {

	private static final long serialVersionUID = -3430453837464272280L;

	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(String code, String message) {
		super(code, message);
	}

}
