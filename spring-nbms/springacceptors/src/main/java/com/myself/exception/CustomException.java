package com.myself.exception;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = -5146289906926181443L;
	
	private String retCode = "";
	private String retMesg = "";
	
	private static String DEFAULT_CODE = "";

	public CustomException(String retCode, String retMesg) {
		super(retMesg);
		this.retCode = retCode;
	}
	
	public static CustomException getCustomException(String retMesg) {
		return new CustomException(DEFAULT_CODE, retMesg);
	}
	
	public static CustomException getCustomException(String retCode, String retMesg) {
		return new CustomException(retCode, retMesg);
	}

	public String getRetCode() {
		return retCode;
	}

	public String getRetMesg() {
		return retMesg;
	}

}
