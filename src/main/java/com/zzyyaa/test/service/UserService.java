package com.zzyyaa.test.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzyyaa.test.entity.User;
@Transactional
public interface UserService {
	
	public List<User> getAllUser();
//	public User getAllUser();
}
