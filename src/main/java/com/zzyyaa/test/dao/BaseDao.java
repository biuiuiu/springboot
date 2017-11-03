package com.zzyyaa.test.dao;

import java.io.Serializable;

public interface BaseDao<T ,PK>{
	
	public T getAll(PK pk);
	
	public void addT(T t);
}
