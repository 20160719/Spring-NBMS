package com.myself.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static final String STRING_MERGE = "|";

	public static final String STRING_SPLIT = "\\|";

	public static final String STRING_MERGE_COMMA = ",";

	public static final String STRING_SPLIT_COMMA = "\\,";

	private StringUtils() {
		
	}
	
	public static boolean isNotBlank(String str) {
		return (!(org.apache.commons.lang.StringUtils
				.isBlank(org.apache.commons.lang.StringUtils.trimToEmpty(str))));
	}

	public static String mergeToStr(List<String> list, String STRING_MERGE) {
		return String.join(STRING_MERGE, list);
	}

	public static String mergeToStr(List<String> list) {
		return String.join(STRING_MERGE, list);
	}

	public static List<String> splitToList(String sourceStr, String splitStr) {
		return new ArrayList<String>(Arrays.asList(sourceStr.split(splitStr)));
	}

	public static List<String> splitToList(String sourceStr) {
		return new ArrayList<String>(Arrays.asList(sourceStr.split(STRING_SPLIT)));
	}

}
