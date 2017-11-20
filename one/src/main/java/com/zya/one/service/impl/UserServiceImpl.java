package com.zya.one.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zya.one.dao.UserDao;
import com.zya.one.entity.User;
import com.zya.one.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;
	
	public List<User> getAllUser(){
		return dao.getAllUser();
	}

	@Override
	public User printState(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
