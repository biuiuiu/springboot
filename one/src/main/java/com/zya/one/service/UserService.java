package com.zya.one.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zya.one.entity.User;
@Transactional
public interface UserService {
	public User printState(long id) ;
	public List<User> getAllUser();
}
