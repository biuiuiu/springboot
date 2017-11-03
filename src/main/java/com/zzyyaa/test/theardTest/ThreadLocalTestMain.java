package com.zzyyaa.test.theardTest;

public class ThreadLocalTestMain {
	public static void main(String[] args) {
		TheardLocalTest target = new TheardLocalTest();
		Thread thread = new Thread(target);
		Thread thread1 = new Thread(target);
		Thread thread2 = new Thread(target);
		thread.start();
		thread1.start();
		thread2.start();
	}
}
