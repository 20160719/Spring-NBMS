package com.myself.persistences.entity.system;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 帐号
	protected String account;
	// 密码
	protected String password;

	public String getAccount() {
		return account;
	}

	public Account setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Account setPassword(String password) {
		this.password = password;
		return this;
	}

}
