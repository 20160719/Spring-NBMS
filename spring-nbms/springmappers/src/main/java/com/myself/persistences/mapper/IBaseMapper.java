package com.myself.persistences.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myself.persistences.entity.Operation;

public abstract interface IBaseMapper<T> {

	/**
	 * ����
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer creates(@Param("list") List<T> list)
			throws Exception;

	/**
	 * ������ʷ
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer createHiss(@Param("list") List<T> list,  @Param("operation") Operation operation) throws Exception;

	/**
	 * ɾ��
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer deletes(@Param("list") List<T> list)
			throws Exception;

	/**
	 * �޸�
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer modifies(@Param("list") List<T> list)
			throws Exception;

	/**
	 * record
	 * @param record
	 * @return
	 * @throws Exception
     */
	public abstract T load(@Param("record") T record) throws Exception;

	/**
	 * ��ѯ������¼
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 * @return List<T> TODO
	 */
	public abstract List<T> queries(@Param("record") T record) throws Exception;

	/**
	 * ��ѯ������¼
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @return List<T> TODO
	 */
	public abstract List<T> query(@Param("map") Map<String, Object> map)
			throws Exception;

}
