package com.zzyyaa.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzyyaa.test.dao.UserDao;
import com.zzyyaa.test.entity.User;
import com.zzyyaa.test.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{
	
	@Autowired
	private UserDao dao;
	
	public List<User> getAllUser(){
		return dao.getAllUser();
	}
	
	public User printState(long id) {
		System.out.println("ebfor"+ id);
		return dao.getAll(id);
	}
	
	@Override
	public UserDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
