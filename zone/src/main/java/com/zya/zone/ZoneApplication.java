package com.zya.zone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ZoneApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZoneApplication.class).web(true).run(args);
	}
	
	/**
	 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
	 */
	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // 注意这里要指向原先用main方法执行的Application启动类
	        return builder.sources(ZoneApplication.class);
	    }
}
