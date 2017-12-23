package com.zzyyaa.test.demoUi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zzyyaa.test.Utils.RedisClient;

@Controller
@Path("/demo")
@Produces({ "application/json;charset=utf-8" })
@Consumes({ "application/json;charset=utf-8" })
public class RedisOperationTest {

	@Autowired
	private RedisClient redisClient;

	@GET
	@Path(value = "/getRedisByKey/{key}")
	public String getRedisByKey(@PathParam("key") String key) {
		redisClient.set("CUS:services:Smart:User:id:test123", "zya");
		String value = redisClient.get("CUS:services:Smart:User:id:test123");
		redisClient.del("CUS:services:Smart:User:id:test123");
		return JSONObject.toJSONString(value);
	}
}
