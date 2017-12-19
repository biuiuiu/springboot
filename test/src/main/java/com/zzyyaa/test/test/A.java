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
		System.out.println(a.equals(b));
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		Set<A> set = new HashSet<>();
		set.add(a);
		set.add(b);
		System.out.println(set);
	}
}
