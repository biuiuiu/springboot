package com.zzyyaa.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zzyyaa.test.entity.Lookup;
@Mapper
public interface LookupDao extends BaseDao<Lookup, Long>{
	List<Lookup> getLookupByGroupCode(@Param("groupcode") String groupcode);
}
