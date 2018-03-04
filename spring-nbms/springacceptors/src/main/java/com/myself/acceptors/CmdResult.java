package com.myself.acceptors;

/**
 * 
 * @author zhanghong
 * 新建、修改、删除操作的返回结果
 */

public class CmdResult {

	private Integer intResult;

	private String strResult;

	private String retCode;

	private String retMess;

	private String retBody;
	
	private int status;
	
	private String code;
	
	private String msg;
	
	private int successCount;
	
	private int falidCount;
	
	private String successBody;
	
	private String falidBody;

	public Integer getIntResult() {
		return intResult;
	}

	public CmdResult setIntResult(Integer intResult) {
		this.intResult = intResult;
		return this;
	}

	public String getStrResult() {
		return strResult;
	}

	public CmdResult setStrResult(String strResult) {
		this.strResult = strResult;
		return this;
	}

	public String getRetMess() {
		return retMess;
	}

	public CmdResult setRetMess(String retMess) {
		this.retMess = retMess;
		return this;
	}

	public String getRetCode() {
		return retCode;
	}

	public CmdResult setRetCode(String retCode) {
		this.retCode = retCode;
		return this;
	}

	public String getRetBody() {
		return retBody;
	}

	public CmdResult setRetBody(String retBody) {
		this.retBody = retBody;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public CmdResult setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getCode() {
		return code;
	}

	public CmdResult setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public CmdResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public CmdResult setSuccessCount(int successCount) {
		this.successCount = successCount;
		return this;
	}

	public int getFalidCount() {
		return falidCount;
	}

	public CmdResult setFalidCount(int falidCount) {
		this.falidCount = falidCount;
		return this;
	}

	public String getSuccessBody() {
		return successBody;
	}

	public CmdResult setSuccessBody(String successBody) {
		this.successBody = successBody;
		return this;
	}

	public String getFalidBody() {
		return falidBody;
	}

	public CmdResult setFalidBody(String falidBody) {
		this.falidBody = falidBody;
		return this;
	}
	
	public static CmdResult getCmdResult() {
		return new CmdResult();
	}

}
