package com.zzyyaa.test.demoUi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.zzyyaa.test.Utils.BeanUtils;
import com.zzyyaa.test.Utils.CommonUtils;
import com.zzyyaa.test.Utils.DateAroundAspect;
import com.zzyyaa.test.Utils.DynamicProxy;
import com.zzyyaa.test.entity.User;
import com.zzyyaa.test.service.UserService;
import com.zzyyaa.test.service.impl.UserServiceImpl;

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
		logger.warn("test:wwwwwwwwwwwwwwwww");
		logger.error("11");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ss = sdf.format(date);
		return ss.concat(author).concat(CommonUtils.ENTER_DELIMITER).concat(test).concat(CommonUtils.ENTER_DELIMITER).concat(environment);
	}
	@GET
	@Path(value = "/hello/{name}")
	@ApiOperation(value = "测试路径传入参数", notes="测试接口详细描述")
	public String reString(@PathParam("name") String name) {
		return "hello".concat(name).concat("!");
	}
	
	@GET
	@Path(value = "/list")
	@ApiOperation(value = "测试获取数据库信息")
	public List<User> getAllUser() throws Exception {
		UserService aservice = BeanUtils.getBean(UserService.class);
		List<User> list = aservice.getAllUser();
		list = AnnotationTest.testAnnotation(list);
		return list;
	}
	/**
	 * JDK提供的动态代理实现调用一个方法的时候在方法前后添加其他方法，本例计算方法用时
	 * */
	@GET
	@Path(value = "/getById/{id}")
	public User getById(@PathParam("id") long id){
		DynamicProxy dynamicProxy = new DynamicProxy(new UserServiceImpl());
		UserService sUserService = dynamicProxy.getProxy();
		sUserService.printState(id);
		return null;
	}
	/**
	 * spring提供的AOP，环绕增强
	 * */
	@GET
	@Path(value = "/testAspect/{id}")
	public String testAspect(@PathParam("id") long id){
		ProxyFactory proxyFactory = new ProxyFactory();//创建代理工厂
		proxyFactory.setTarget(new UserServiceImpl());//添加目标类对象
		proxyFactory.addAdvice(new DateAroundAspect());//添加环绕增强类
		UserService userService = (UserService)proxyFactory.getProxy();//从代理工厂中获取代理
		String ss = userService.printState(id);//调用代理的方法
		return ss;
	}
	
	
	@POST
	@Path(value = "/add")
	public User addUser(User user){
		if (user == null) {
			logger.debug("Error:no param");
		}
		if (user.getCreateDate() == null) 
			user.setCreateDate(new Date());
		if (user.getUpdateDate() == null) {
			user.setUpdateDate(new  Date());
		}
		User user2 =  service.addT(user);
		return user2;
	}
}
