package com.myself.dto;

public abstract class EntityDto {

	//鎿嶄綔浜鸿处鍙�
	private String account;
	// 鎿嶄綔绫诲瀷
	private String operateType;
	// 搴忓垪鍙�
	private String serialNum;
	// 鎿嶄綔鐮�
	private String opCode;
	// json瀛楃涓�
	private String targetJson;
	
	private String remark;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getTargetJson() {
		return targetJson;
	}

	public void setTargetJson(String targetJson) {
		this.targetJson = targetJson;
	}

	protected String getRemark() {
		return remark;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}

}
