package com.zzyyaa.test.Utils;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DateAroundAspect implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before");
		Date beginDate = new Date();
		Object result = invocation.proceed();
		Date endDate = new Date();
		System.out.println(beginDate.getTime() - endDate.getTime());
		System.out.println("after");
		return result;
	}
	
}
