package com.zzyyaa.test.Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class DynamicProxy implements InvocationHandler {
	private static Date beginDate;
	private static Date endDate;
	private Object target;

	public DynamicProxy(Object target) {
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		beginDate = new Date();
		Object result = method.invoke(target, args);
		endDate = new Date();
		System.out.println(endDate.getTime() - beginDate.getTime());
		return result;
	}

}
