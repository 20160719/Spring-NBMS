package com.myself.persistences.mapper.basic;

import org.apache.ibatis.annotations.Mapper;

import com.myself.persistences.entity.basic.Book;
import com.myself.persistences.mapper.IBaseMapper;

@Mapper
public abstract interface BookMapper extends IBaseMapper<Book> {

}
