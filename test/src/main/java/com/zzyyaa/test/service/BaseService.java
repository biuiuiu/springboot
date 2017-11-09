package com.zzyyaa.test.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.zzyyaa.test.dao.BaseDao;

@Transactional
public interface BaseService <T,PK extends Serializable>{
	
	public BaseDao<T, PK> getDao();
	
	public T getAll(PK e);
	
	public T addT(T t);
}
