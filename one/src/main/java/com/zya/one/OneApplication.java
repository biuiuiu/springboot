package com.zya.one;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@EnableDiscoveryClient
public class OneApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(OneApplication.class).web(true).run(args);
	}
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		//定义一个converter转换消息的对象
		FastJsonHttpMessageConverter fastconverter = new FastJsonHttpMessageConverter();
		//添加fastjson的配置信息，比如：是否需要格式化返回的json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//在converter中添加配置信息
		fastconverter.setFastJsonConfig(fastJsonConfig);
		//将converter添加到converters中
		converters.add(fastconverter);
		super.configureMessageConverters(converters);
	}

}
