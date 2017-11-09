package com.zzyyaa.test.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	
	public static final String ENTER_DELIMITER = "\n";
	
	
	/**
	 * 根据格式获取当前时间
	 * @param pattern
	 * */
	public static String getNowDate(String pattern) {
		String defaultPattern = "yyyy-MM-dd";
		if (pattern == null || "".equals(pattern))
			pattern = defaultPattern;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String result = null;
		try {
			result = sdf.format(date);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("date parse error");
		}
		return result;
	}
	/**
	 * 任意类型的数组转换成对应类型的List
	 * @param 数组
	 * */
	public static <T> List<T> transArray2List(T[] ts) {
		List<T> result = new ArrayList<>();
		if (ts == null || ts.length == 0)
			return result;
		for (T t : ts)
			result.add(t);
		return result;
	}
}
