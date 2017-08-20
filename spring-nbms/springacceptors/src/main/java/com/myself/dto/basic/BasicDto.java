package com.myself.dto.basic;

import java.sql.Timestamp;

import com.myself.dto.EntityDto;

public class BasicDto extends EntityDto {

	// 閹笛嗩攽閺傜懓绱�
	private String executeType;
	// 閹笛嗩攽閺冨爼妫�
	private Timestamp executeTime;

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	public Timestamp getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Timestamp executeTime) {
		this.executeTime = executeTime;
	}

}
