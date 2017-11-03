package com.zzyyaa.test.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
