package com.myself.persistences.mapper.system;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public abstract interface CommonMapper {
	
	/**
	 * 获取序列号
	 * @param seqName
	 * @return
	 * @throws Exception
	 * @return long
	 * TODO
	 */
	public abstract String querySeqByName(@Param("seqName") String seqName) throws Exception;
	
	/**
	 *获取系统时间
	 * @return
	 * @throws Exception
	 * @return Date
	 * TODO
	 */
	public abstract Date queryDateTime() throws Exception;
	
	/**
	 * 获取系统时间戳
	 * @return
	 * @throws Exception
	 * @return Timestamp
	 * TODO
	 */
	public abstract Timestamp queryTimestamp() throws Exception;
	

	
}
