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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zzyyaa.test.Utils.CommonUtils;
import com.zzyyaa.test.entity.User;
import com.zzyyaa.test.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Path("/demo")
@Api(value = "demo")
@Produces({"application/json;charset=utf-8"})
@Consumes({"application/json;charset=utf-8"})
public class WebRequestDemo {
	
	@Autowired
	private UserService service;
	
	@Value("${com.zzyyaa.test.author}")
	private String author;
	
	@Value("${com.zzyyaa.test.group}")
	private String test;
	
	@Value("${com.zzyyaa.test.environment}")
	private String environment;
			
	@GET
	@Path(value = "/hhhh")
	@ApiOperation(value = "测试获取配置文件信息")
	public String index(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ss = sdf.format(date);
		return ss.concat(author).concat(CommonUtils.ENTER_DELIMITER).concat(test).concat(CommonUtils.ENTER_DELIMITER).concat(environment);
	}
	@GET
	@Path(value = "/hello/{name}")
	@ApiOperation(value = "测试路径传入参数")
	public String reString(@PathParam("name") String name) {
		return "hello".concat(name).concat("!");
	}
	
	@GET
	@Path(value = "/list")
	@ApiOperation(value = "测试获取数据库信息")
	public String getAllUser() {
		List<User> list = service.getAllUser();
		return JSONObject.toJSONString(list);
	}
}
