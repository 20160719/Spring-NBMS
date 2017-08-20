package com.myself.persistences.entity;

public abstract class TargetHis extends TargetTime {

	private static final long serialVersionUID = 1L;

	private String account;
	
	private String remark;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
