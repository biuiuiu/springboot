package com.zya.one.ui;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zya.one.entity.User;
import com.zya.one.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Path("/demo")
@Produces({"application/json;charset=utf-8"})
@Consumes({"application/json;charset=utf-8"})
@Api(value = "test")
public class WebRequestDemo {
		
	private final Logger logger = LoggerFactory.getLogger(WebRequestDemo.class);
	
	@Autowired
	private UserService service;
	
	@GET
	@Path(value = "/hello/{name}")
	@ApiOperation(value = "测试路径传入参数", notes="测试接口详细描述")
	public String reString(@PathParam("name") String name) {
		return "hello".concat(name).concat("!");
	}
	
	@GET
	@Path(value = "/getUserInfo")
	public List<User> getUserInfo(){
		List<User> users = service.getAllUser();
		logger.warn("Warn");
		return users;
	}
	
}
