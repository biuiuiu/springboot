package com.zzyyaa.test.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.zzyyaa.test.entity.ServletEntity;
@Component
public class dispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127430761547382067L;
	
	/**
	 * 需要在启动类添加servlet，指定servlet匹配路径
	 * 不知道怎么在doget中发送到UI对应方法
	 * */
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ServletEntity.init(request, response);
		try {
			super.service(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			ServletEntity.destory();
		}
	}
	
}
