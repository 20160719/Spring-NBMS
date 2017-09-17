package com.myself.services;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myself.persistences.entity.Operation;
import com.myself.persistences.entity.Tree;


public abstract interface IBaseService<T> {

	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰彶閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂负榛橀敓杈冨嚖鎷烽敓鏂ゆ嫹閿熸枻鎷�
	 * 
	 * @param listHis
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer createHiss(List<T> list, Operation operation) throws DataAccessException;

	/**
	 * 閿熸枻鎷烽敓鏂ゆ嫹
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer creates(List<T> list) throws DataAccessException;

	/**
	 * 鍒犻敓鏂ゆ嫹
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer deletes(List<T> list) throws DataAccessException;

	/**
	 * 閿熺潾闈╂嫹
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 * @return Integer TODO
	 */
	public abstract Integer modifies(List<T> list) throws DataAccessException;
	
	/**
	 * 閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰綍
	 * 
	 * @return
	 * @throws Exception
	 * @return T TODO
	 */
	public abstract T load(T obj) throws DataAccessException;

	/**
	 * 閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰綍
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 * @return List<T> TODO
	 */
	public abstract List<T> queries(T obj) throws DataAccessException;

	/**
	 * 閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰綍
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @return List<T> TODO
	 */
	public abstract List<T> query(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @return List<BaseTree>
	 * TODO
	 */
	public abstract List<Tree> queryTrees() throws DataAccessException;
	
}
