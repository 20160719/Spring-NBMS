package com.myself.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils extends org.springframework.util.CollectionUtils {
	
	private CollectionUtils () {
		
	}
	
	public static <T> List<T> getList() {
		return new ArrayList<T>();
	}
	
	public static <K, V> Map<K, V> getMap(){
		return new HashMap<K, V>();
	}
	
	public static <T> Set<T> getSet() {
		return new HashSet<T>();
	}

}
