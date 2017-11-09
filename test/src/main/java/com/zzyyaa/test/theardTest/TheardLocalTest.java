package com.zzyyaa.test.theardTest;

import java.util.HashMap;
import java.util.Map;

public class TheardLocalTest implements Runnable {
	/**
	 * 用static无法保证线程安全，采用threadlocal线程局部变量确保每个线程变量隔离，，避免线程之间的相互影响
	 * 初始化值重写方法initialValue
	 * */
	private ThreadLocal<Map<Integer, Character>> threadLocal = new ThreadLocal<Map<Integer, Character>>() {
		@Override
		protected Map<Integer, Character> initialValue() {
			// TODO Auto-generated method stub
			Map<Integer, Character> map = new HashMap<>();
			map.put(0,'a');
			return map;
		}
	};

	protected char getNum(Integer aa) {
		char b = threadLocal.get().get(aa);
		Map<Integer, Character> map = new HashMap<>();
		map.put(aa+1,(char)(b+1));//ASCII码值转char可直接得到对应的字母
		threadLocal.set(map);
		return b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0, j = i + 10; i < 3; i++,j++) {
			System.out.println("i=" + i + "j=" + j);
			System.out.println(Thread.currentThread().getName() + "---" + getNum(i));
		}
	}

}
