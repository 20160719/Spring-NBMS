package com.myself.persistences.mapper.system;

import org.apache.ibatis.annotations.Mapper;

import com.myself.persistences.entity.system.User;
import com.myself.persistences.mapper.IAbstractMapper;
@Mapper
public abstract interface UserMapper extends IAbstractMapper<User> {

}
