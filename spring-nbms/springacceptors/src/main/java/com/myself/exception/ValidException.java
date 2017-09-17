package com.myself.exception;

public class ValidException extends CustomException {

	private static final long serialVersionUID = 1L;

	public ValidException(String code, String message) {
		super(code, message);
	}

	public ValidException(String message) {
		super(message);
	}

}
