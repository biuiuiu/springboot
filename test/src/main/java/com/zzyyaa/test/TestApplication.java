package com.zzyyaa.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zzyyaa.test.Utils.dispatcherServlet;
import com.zzyyaa.test.customAnnotaion.MyFirstAnnotationAspect;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EnableScheduling//注解开启spring定时任务
public class TestApplication extends WebMvcConfigurerAdapter{
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
}

	/**
	 * 代码添加对应的servlet
	 * */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new dispatcherServlet(), "/ownservlet/*");// ServletName默认值为首字母小写，即myServlet
    }
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
	    //1.需要定义一个convert转换消息的对象;
		System.out.println("aaa");
	    FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
	    //2:添加fastJson的配置信息;
	    FastJsonConfig fastJsonConfig = new FastJsonConfig();
	    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	    //3处理中文乱码问题
	    List<MediaType> fastMediaTypes = new ArrayList<>();
	    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
	    //4.在convert中添加配置信息.
	    fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
	    fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
	    HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
	    return new HttpMessageConverters(converter);

	}
	@Override
    public void addFormatters(FormatterRegistry registry) {
		MyFirstAnnotationAspect myFirstAnnotaion = new MyFirstAnnotationAspect();
        registry.addFormatterForFieldAnnotation(myFirstAnnotaion);
    }
	
}
