package com.myself.persistences.mapper.system;

import org.apache.ibatis.annotations.Mapper;

import com.myself.persistences.entity.Tree;
import com.myself.persistences.mapper.IAbstractMapper;

@Mapper
public abstract interface MenuMapper extends IAbstractMapper<Tree> {

}
