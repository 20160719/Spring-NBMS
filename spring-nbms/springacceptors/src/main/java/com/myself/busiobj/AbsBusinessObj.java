package com.myself.busiobj;

import java.util.List;

import com.myself.dto.EntityDto;
import com.myself.persistences.entity.Operation;

public abstract class AbsBusinessObj<T> {
	
	private EntityDto entityDto;
	
	private List<T> list;
	
	private Operation operation;

	public EntityDto getEntityDto() {
		return entityDto;
	}

	public void setEntityDto(EntityDto entityDto) {
		this.entityDto = entityDto;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
}
