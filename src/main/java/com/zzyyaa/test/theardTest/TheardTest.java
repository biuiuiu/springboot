package com.zzyyaa.test.theardTest;

import java.util.concurrent.Callable;

public class TheardTest implements Callable<String>{

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		for (int i = 0; i < 99; i++) {
			System.out.println(i);
		}
		return "success";
	}
	
}
