package com.zzyyaa.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class SwaggerRedirect extends WebMvcConfigurerAdapter{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		System.out.println("111");
		registry.addResourceHandler("/demo/**").addResourceLocations("file:D:\\Source\\workspace-cus\\test\\springboot\\src\\main\\resources\\static\\index.html");
		super.addResourceHandlers(registry);
	}
}
