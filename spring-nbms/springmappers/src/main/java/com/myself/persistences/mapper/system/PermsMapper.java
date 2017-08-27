package com.myself.persistences.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myself.persistences.entity.system.Permission;
import com.myself.persistences.mapper.IAbstractMapper;
@Mapper
public abstract interface PermsMapper extends IAbstractMapper<Permission> {
	
	public abstract List<Permission> queryPermissions() throws Exception;

}
