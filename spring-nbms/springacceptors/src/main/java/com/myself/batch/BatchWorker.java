package com.myself.batch;

import java.util.List;
import java.util.concurrent.Callable;

import com.myself.services.IBaseService;

public class BatchWorker<T> implements Callable<Integer> {
	
	public static final String BATCH_TYPE_CREATE = "I";
	
	public static final String BATCH_TYPE_MODIFY = "M";
	
	public static final String BATCH_TYPE_DELETE = "D";
	
	private String batchType;

	private List<T> list;
	
	private IBaseService<T> baseService;
	
	public BatchWorker(String batchType, List<T> list, IBaseService<T> baseService) {
		this.batchType = batchType;
		this.list = list;
		this.baseService = baseService;
	}

	@Override
	public Integer call() throws Exception {
		int count = 0;
		if(BATCH_TYPE_CREATE.equals(this.batchType)) {
			count = baseService.creates(this.list);
		} else if(BATCH_TYPE_MODIFY.equals(this.batchType)) {
			count = baseService.modifies(this.list);
		} else {
			count = baseService.deletes(this.list);
		}
		return count;
	}
	
}
