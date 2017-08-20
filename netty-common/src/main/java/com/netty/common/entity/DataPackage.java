package com.netty.common.entity;

import java.util.Date;

public class DataPackage {
	
	private String account;
	
	private String packageType;
	
	private String packageData;
	
	private Date createTime;
	
	private boolean loginFlag = false;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageData() {
		return packageData;
	}

	public void setPackageData(String packageData) {
		this.packageData = packageData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

	@Override
	public String toString() {
		return "DataPackage [account=" + account + ", packageType=" + packageType + ", packageData=" + packageData
				+ ", createTime=" + createTime + ", loginFlag=" + loginFlag + "]";
	}

}
