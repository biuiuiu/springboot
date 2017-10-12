package com.zzyyaa.test.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseService <T,PK extends Serializable>{
	public T getAll(PK e);
	
	public T addT(T t);
}
