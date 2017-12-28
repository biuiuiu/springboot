package com.zzyyaa.test.test;

public class ReplaceTest {
	public static void main(String[] args) {
		String testString = "abcdTestString";
		testString = testString.replaceAll("[A-Z]", "_$0");
		System.out.println(testString);
		String tt = testString.replaceAll("(\\_)([A-Za-z])", "$2".toLowerCase());
		System.out.println(tt);
		
	}
	
	public static  String translateTo_String(String ss) {
		if (ss == null)
			return null;
		
		for (int i = 0; i < ss.length(); i++) {
			if (Character.isUpperCase(ss.charAt(i))) {
				ss = ss.replace(String.valueOf(ss.charAt(i)), "_"+String.valueOf(ss.charAt(i)).toLowerCase());
			}
		}
		return  ss;
	}
}
