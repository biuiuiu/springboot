package com.zya.one.utils;

import java.text.NumberFormat;

public class CommonUtil {

	private static final String[] NUM_CHINESE = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	private static final String[] NUM_CHINESE_COMPLEX = { "零", "壹", "貳", "叁", "肆", "伍", "陸", "柒", "捌", "玖" };
	private static final String[] UNIT_CHINESE = { "", "十", "百", "千", "万", "亿" };
	private static final String[] UNIT_CHINESE_COMPLEX = { "", "拾", "佰", "仟", "萬", "億" };
	/**
	 * 输入number类型，转化为对应的汉字
	 * @param number
	 * @return string
	 * */
	public static String tranNum2Chinese(Number number) {
		if (number == null)
			return null;
		String categorty = number.getClass().getSimpleName();
		if ("Integer".equals(categorty) || "Long".equals(categorty)) {
			return tranInt2Chinese(number.longValue());
		}else if ("Float".equals(categorty) || "Double".equals(categorty)) {
			return tranDouble2Chinese(number.doubleValue());
		}else {
			System.out.println("Error:不支持類型！");
			return null;
		}
	}
	/**
	 * 输入小数类型，转化为对应的汉字
	 * @param number
	 * @return string
	 * */
	private static String tranDouble2Chinese(Double doubleparam){
		if (doubleparam == null )
			return null;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);//去除分隔符
		String sDouble = nf.format(doubleparam);//格式化小数，去除多余的0
		String result;
		if (sDouble.indexOf('.')>0 && (sDouble.indexOf('.')==sDouble.lastIndexOf('.'))) {
			String[] array = sDouble.split("\\.");
			result = tranInt2Chinese(Long.valueOf(array[0]))+"点"+tranSecondPart2Chinese(array[1]);
		}else {
			result = tranInt2Chinese(Long.valueOf(sDouble));
		}
		if (result.endsWith("点")) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	/**
	 * 输入整数类型，转化为对应的汉字
	 * @param number
	 * @return string
	 * */
	private static String tranInt2Chinese(Long longparam){
		String sInteger = longparam.toString();
		int length = sInteger.length();
		String result ; 
		if (length > 0 && length < 6) {
			result = getChinese(sInteger);
		}else if (length >= 6 && length < 9) {
			result = getChinese(sInteger.substring(0,length-4)).concat("萬").concat(getChinese(sInteger.substring(length-4)));
		}else {
			String temp = sInteger.substring(0,length-8);
			String aa = tranInt2Chinese(Long.valueOf(temp));
			result = aa +"億" + getChinese(sInteger.substring(length-8,length-4)).concat("萬").concat(getChinese(sInteger.substring(length-4)));
		}
		return result;
	}
	/**
	 * 输入string类型，转化为对应的汉字
	 * @param number
	 * @return string
	 * */
	private static String getChinese(String ss) {
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for (int i = 0 ,j = ss.length(); i < j; i++) {
			int index = ss.charAt(i) - '0';//'1'转化成1
			String temp = NUM_CHINESE_COMPLEX[index];
			String unitTemp = UNIT_CHINESE_COMPLEX[j - i - 1];
			if (index == 0 && flag == true) {
				sb.append(temp);
				flag = false;
			}
			if (index != 0) {
				sb.append(temp).append(unitTemp);
			}
		}
		if (sb.toString().endsWith("零")) {
			return sb.toString().substring(0,sb.toString().length()-1);
		}//去除最后一位0
		return sb.toString();
	}
	/**
	 * 小数部分转化为对应的汉字
	 * @param number
	 * @return string
	 * */
	private static String tranSecondPart2Chinese(String ss) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0,j=ss.length(); i < j; i++) {
			sb.append(NUM_CHINESE_COMPLEX[Character.getNumericValue(ss.charAt(i))]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(tranNum2Chinese(101));
	}
}
