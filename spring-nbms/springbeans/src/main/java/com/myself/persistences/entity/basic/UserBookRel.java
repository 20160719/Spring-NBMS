package com.myself.persistences.entity.basic;

import java.sql.Timestamp;

public class UserBookRel {

	protected String userId;
	
	protected String account;
	
	protected String bookId;
	
	protected Timestamp borrowTime;
	
	protected String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Timestamp getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
