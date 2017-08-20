package com.myself.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
	public static final String TIME_FORMAT_HMS = "hh:mm:ss";
	public static final String TIME_FORMAT = TIME_FORMAT_YMD + " " + TIME_FORMAT_HMS;
	
	private DateUtils() {
		
	}
	
	public static SimpleDateFormat getDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	public static String getDateTime(Date date, String pattern) {
		return getDateFormat(pattern).format(date);
	}
	
	public static String getDateTime(String pattern) {
		return getDateTime(new Date(), pattern);
	}
	
	public static String getDateTime() {
		return getDateTime(TIME_FORMAT);
	}
	
	public static String getDateStr(Date date, String pattern) {
		return getDateFormat(pattern).format(date);
	}
	
	public static String getDateStr(String pattern) {
		return getDateStr(pattern);
	}
	
	public static String getDate() {
		return getDateStr(TIME_FORMAT_YMD);
	}
	
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	public static Date getDate(String dateStr, String pattern) {
		try {
			return getDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDate(String dateStr) {
		return getDate(dateStr, TIME_FORMAT);
	}

}
