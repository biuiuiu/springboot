package com.zzyyaa.test.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zzyyaa.test.Utils.SetTypeInit;
import com.zzyyaa.test.dao.BaseDao;
import com.zzyyaa.test.service.BaseService;

public abstract class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T, PK>{
	
	public abstract BaseDao<T, PK> getDao();
	
	@Autowired
	private SetTypeInit setTypeInit;
	
	@Override
	public T getAll(PK pk) {
		// TODO Auto-generated method stub
		Map<String, Object> map = setTypeInit.getMap();
		System.out.println(map);
		return this.getDao().getAll(pk);
	}
	
	@Override
	public T addT(T t){
		getDao().addT(t);
		return t;
	}
	
}
