package com.myself.persistences.entity.system;

import java.sql.Timestamp;

public class User extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userTypeId;

	private String userTypeName;

	// 用户ID
	private String userId;
	// 姓名
	private String userName;
	// 性别
	private String userSex;
	// 年龄
	private String userAge;
	// 生日
	private String birthday;

	private String address;

	// 创建时间
	protected Timestamp createTime;
	// 修改时间
	protected Timestamp modifyTime;
	// 用户角色
	private String roleIds;

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	protected Timestamp getCreateTime() {
		return createTime;
	}

	protected void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", userId=" + userId
				+ ", userName=" + userName + ", userSex=" + userSex + ", userAge=" + userAge + ", birthday=" + birthday
				+ ", address=" + address + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", roleIds="
				+ roleIds + ", account=" + account + ", password=" + password + "]";
	}


}
