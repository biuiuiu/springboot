package com.zya.scheduleremind.scheduleTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeUtils {
	
	private static Map<String, Date> timeMap = new HashMap<>();
	private static List<String> sentanceList = new ArrayList<>();
	static{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 30);
		timeMap.put("am", calendar.getTime());//上午下班
		calendar.set(Calendar.HOUR_OF_DAY,17);
		calendar.set(Calendar.MINUTE, 30);
		timeMap.put("pm", calendar.getTime());//下午下班
		
		calendar.set(Calendar.HOUR_OF_DAY,21);
		calendar.set(Calendar.MINUTE, 0);
		timeMap.put("ws", calendar.getTime());//晚上下班
		
		sentanceList.add("上班啦啦");
		sentanceList.add("继续加油哦");
		sentanceList.add("下班啦啦");
		sentanceList.add("吃饭啦啦");
		sentanceList.add("夜班哦 加油");
		sentanceList.add("终于下班啦啦");
	}
	
	public static String calcOutJobTime() {
		String statement = "";
		Date date = new Date();
		String temp = "";
		@SuppressWarnings("deprecation")
		int hour1 = date.getHours();
		if (hour1>0&&hour1<12) {
			temp = "am";
		}else if (hour1>=12&&hour1<18) {
			temp = "pm";
		}else if (hour1>18&&hour1<=24) {
			temp = "ws";
		}else {
			temp = "error";//判断上午、下午、和晚上
		}
		Date outJobTime = timeMap.get(temp);
		long value = outJobTime.getTime() - date.getTime();
		if (value>0) {
			long hour = value/(1000*3600);
			long minute = value%(1000*3600)/(1000*60);
			if (hour>0) {
				statement = statement + hour + "小时";
			}
			if (minute>0) {
				long second = (value - hour*(1000*3600) - minute*((1000*60)))/1000;
				if (second>30) {
					minute++;
				}
				statement = statement + minute + "分钟";
			}
		}
		if (!"".equals(statement)) {
			statement = "距离下班还剩" + statement;
		}
		return statement;
	}
	
	public static String getSentance() {
		String statement = "";
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		switch(hour){
		case 9:
		case 13:
		case 14:
		case 19:
			statement = sentanceList.get(0);//上班
			break;
		case 10:
		case 15:
			statement = sentanceList.get(1);//继续加油
			break;
		case 11:
			statement = sentanceList.get(3);//吃饭
			break;
		case 17:
			statement = sentanceList.get(2);//下班
			break;
		case 20:
			if (minute>30) {
				statement = sentanceList.get(5);//晚上
			}else {
				statement = sentanceList.get(4);//晚上
			}
			break;
		default:
			break;
		}
		return statement;
	}
}
