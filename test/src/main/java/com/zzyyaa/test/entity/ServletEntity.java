package com.zzyyaa.test.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEntity {

	private static ThreadLocal<ServletEntity> threadLocal = new ThreadLocal<>();

	private HttpServletRequest request;
	private HttpServletResponse response;

	private ServletEntity(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static void init(HttpServletRequest request, HttpServletResponse response) {
		threadLocal.set(new ServletEntity(request, response));
	}

	public static void destory() {
		threadLocal.remove();
	}
	
	public static ServletEntity getEntity() {
		return threadLocal.get();
	}
	
}
