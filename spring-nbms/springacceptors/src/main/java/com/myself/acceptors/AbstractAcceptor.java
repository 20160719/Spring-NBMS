package com.myself.acceptors;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.myself.batch.BatchWorker;
import com.myself.busiobj.AbsBusinessObj;
import com.myself.exception.CustomException;
import com.myself.services.IBaseService;

public abstract class AbstractAcceptor<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractAcceptor.class);

	public BusinessResult businessAcceptor(AbsBusinessObj<T> absBusinessObj, IBeforeAcceptor<T> beforeAcceptor,
										   IDoAcceptor<T> doAcceptor, IAfterAcceptor afterAcceptor, String opType) throws CustomException {
		Assert.notNull(absBusinessObj, "the absBusinessObj must not be null");
		beforeAcceptor.beforeAcceptor(absBusinessObj);
		Object obj = processing(doAcceptor, absBusinessObj.getList());
		afterAcceptor.afterAcceptor();
		//int i = 1 / 0;
		return getBusinessResult().setIntResult((Integer) obj);
	}

	public BusinessResult businessAcceptor(AbsBusinessObj<T> absBusinessObj, IDoAcceptor<T> doAcceptor, String opType) throws CustomException {
		absBusinessObj.getOperation().setOpType(opType);
		return businessAcceptor(absBusinessObj, beforeAcceptor, doAcceptor, afterAcceptor, opType);
	}

	private Object processing(IDoAcceptor<T> doAcceptor, List<T> list) throws CustomException {
		try {
			return doAcceptor.doAcceptor(list);
		} catch (Exception e) {
			error(list.toString(), e);
			throw CustomException.getCustomException("aaa", e.getMessage());
		}
	}

	protected IBeforeAcceptor<T> beforeAcceptor = (absBusinessObj) -> {
		Assert.notNull(absBusinessObj.getEntityDto().getTargetJson(), "the targetJson must not be empty");
		init(absBusinessObj);
	};

	protected void init(AbsBusinessObj<T> absBusinessObj) throws CustomException {
		
	}
	
	protected IAfterAcceptor afterAcceptor = () -> {
		clear();
		return null;
	};

	/**
	 * 姣忔鎵瑰鐞嗙殑澶у皬
	 */
	public static final int BATCH_NUMBER = 100;

	public static final int BATCH_MIN = 50;

	/**
	 * 灏哃ist鍒嗘垚澶氫釜List<BatchWorker<T>>
	 * @param baseService
	 * @param batchType
     * @return
     */
	public List<BatchWorker<T>> getBatchWorkers(IBaseService<T> baseService, String batchType, List<T> list) {
		List<BatchWorker<T>> batchWorkerList = com.myself.common.utils.CollectionUtils.getList();
		int max = BATCH_MIN;
		int size = list.size();
		if (size > max) {
			size -= max;
		} else {
			max = 0;
		}
		int preSize = BATCH_NUMBER;
		int mod = (size % preSize);
		int page = (size / preSize);
		int count = mod == 0 ? page : page + 1;
		int fromIndex = 0;
		int toIndex = 0;
		BatchWorker<T> batchWorker = null;
		for (int i = 0; i < count; i++) {
			fromIndex = i * preSize;
			toIndex = (i + 1) * preSize;
			if (toIndex > size) {
				toIndex = size + max;
			}
			batchWorker = new BatchWorker<T>(batchType, list.subList(fromIndex, toIndex), baseService);
			batchWorkerList.add(batchWorker);
		}
		return batchWorkerList;
	}

	/**
	 * 澶氱嚎绋嬫壒澶勭悊
	 * @param baseService
	 * @param batchType
     * @return
     */
	public int getCount(IBaseService<T> baseService, String batchType, List<T> list) {
		List<BatchWorker<T>> batchWorkerList = getBatchWorkers(baseService, batchType, list);
		int size = batchWorkerList.size();
		size = size / BATCH_NUMBER ;
		size = size > 5 ? 5 : size + 1;
		ExecutorService executor = Executors.newFixedThreadPool(size);
		int count = 0;
		try {
			List<Future<Integer>> results = executor.invokeAll(batchWorkerList);
			for (Future<Integer> f : results) {
				count += f.get().intValue();
			}
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			error(e.getMessage(), e);
		}
		return count;
	}
	
	/**
	 * 杩斿洖涓氬姟瀵硅薄
	 * @return
	 * @return BusinessResult
	 * TODO
	 */
	public BusinessResult getBusinessResult() {
		return new BusinessResult();
	}

	public BusinessResult getSuccessBusiness() {
		BusinessResult result = getBusinessResult();
		result.setRetCode("1000");
		result.setRetMess("success");
		return result;
	}

	public BusinessResult getFailBusiness() {
		BusinessResult result = getBusinessResult();
		result.setRetCode("0000");
		result.setRetMess("fail");
		return result;
	}
	
	/**
	 * 娓呯悊鍚勭瀵硅薄
	 * 
	 * @return void TODO
	 */
	protected void clear() {
//		if (!CollectionUtils.isEmpty(this.list)) {
//			this.list = null;
//		}
	}

	protected void info(String message) {
		logger.info(message);
	}

	protected void error(String message, Exception e) {
		logger.error(message, e);
	}

}
