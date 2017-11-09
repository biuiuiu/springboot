package com.zzyyaa.test.theardTest;

import java.io.IOException;
import java.net.InetAddress;
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
	
	//通过地址查找主机ip
	public static void main(String[] args) throws IOException {
		System.out.println(InetAddress.getLocalHost());	
	}
}
