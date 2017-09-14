package com.zzyyaa.test.demoUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zzyyaa.test.entity.User;
import com.zzyyaa.test.service.UserService;

@Controller
@Path("/demo")
@Produces({"application/json;charset=utf-8"})
@Consumes({"application/json;charset=utf-8"})
public class WebRequestDemo {
	@Autowired
	private UserService service;
	
	@GET
	@Path(value = "/hhhh")
	public String index(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ss = sdf.format(date);
		return ss;
	}
	@GET
	@Path(value = "/hello/{name}")
	public String reString(@PathParam("name") String name) {
		return "hello".concat(name).concat("!");
	}
	
	@GET
	@Path(value = "/list")
	public String getAllUser() {
		List<User> list = service.getAllUser();
		return JSONObject.toJSONString(list);
	}
}
