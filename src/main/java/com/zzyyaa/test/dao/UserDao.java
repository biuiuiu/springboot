package com.zzyyaa.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zzyyaa.test.entity.User;
@Mapper
public interface UserDao {
	
	public List<User> getAllUser();
//	public User getAllUser();
}
