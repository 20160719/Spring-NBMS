package com.netty.common.utils;

import com.alibaba.fastjson.JSONObject;

public class CommonUtils {
	
	public static final String STRING_SPLIT = "_@#@_";
	
	private CommonUtils() {
		
	}
	
	public static String toJSONString(Object obj) {
		return JSONObject.toJSONString(obj);
	}
	
	public static String concatSplitStr(Object obj) {
		return toJSONString(obj).concat(STRING_SPLIT);
	}

}
