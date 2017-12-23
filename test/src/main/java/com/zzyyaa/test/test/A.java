package com.zzyyaa.test.test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 重新对象的equals方法，实现效果：
 * 当对象中primaryKey的值一样，对象即为相等
 * @author biuiuiu
 * */
public class A {
	private String primaryKey;
	private String primaryValue;
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getPrimaryValue() {
		return primaryValue;
	}
	public void setPrimaryValue(String primaryValue) {
		this.primaryValue = primaryValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			return false;
		}
		if (!(obj.getClass() == this.getClass())) {
			return false;
		}
		if (this.primaryKey.equals((String)getValue(obj))) {
			return true;
		}
		return false;
	}
	
	private Object getValue(Object o){
		if (o == null) {
			return null;
		}
		if (o == this) {
			return true;
		}
		try {
			Method method = o.getClass().getMethod("getPrimaryKey");
			Object oo = method.invoke(o);
			return oo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		A a = new A();
		a.setPrimaryKey("A");
		a.setPrimaryValue("88");
		A b = new A();
		b.setPrimaryKey("A");
		b.setPrimaryValue("90");
		A c = new A ();
		c.setPrimaryKey("A");
		c.setPrimaryValue("100");
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(b.equals(c));
	}
	
	
	/**
	 * 单例模式
	 * 构造方法私有化
	 * 通过暴露的一个实例化方法获取对象实例
	 * */
	private A(){
	}
	
	private static A a = new A();
	
	public static A getInstance() {
		return a;
	}
}
