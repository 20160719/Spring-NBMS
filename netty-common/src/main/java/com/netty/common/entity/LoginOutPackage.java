package com.netty.common.entity;

public class LoginOutPackage extends DataPackage {
	
	private String password;
	
	public LoginOutPackage() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
