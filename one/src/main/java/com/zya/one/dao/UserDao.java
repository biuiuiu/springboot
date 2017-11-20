package com.zya.one.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zya.one.entity.User;
@Mapper
public interface UserDao {
	
	public List<User> getAllUser();
//	public User getAllUser();
}
