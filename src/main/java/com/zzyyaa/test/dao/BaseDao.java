package com.zzyyaa.test.dao;

import java.io.Serializable;

public interface BaseDao<T ,PK extends Serializable>{
	
	public T getAll(PK pk);
	
	public void addT(T t);
}
