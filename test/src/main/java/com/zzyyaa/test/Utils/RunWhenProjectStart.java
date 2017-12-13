package com.zzyyaa.test.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zzyyaa.test.entity.User;
import com.zzyyaa.test.service.UserService;

/**
 * 此类继承ApplicationRunner，目的是启动该服务的时候直接运行此重写的run方法。
 * 根据order注解实现排序
 * */
@Component
@Order(value = 1)//启动时运行此方法顺序，也可实现order接口实现
public class RunWhenProjectStart implements ApplicationRunner{

	@Autowired
	private UserService service;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(args);
		User user = new User();
		user.setState("1");
		user.setUserName("A");
		user.setUserAge(25);
		service.addT(user);
		System.out.println("success");
	}

}
