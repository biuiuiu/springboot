package com.zzyyaa.test.Utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.zzyyaa.test.service.BaseService;

@Component
public class SetTypeInit extends WebApplicationObjectSupport{
	
	private Map<String, Object> map = new HashMap<>();
	
	@Override
	protected void initApplicationContext(ApplicationContext context) {
		super.initApplicationContext(context);
		String[] arg1 = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, BaseService.class);
		for (int i = 0; i < arg1.length; i++) {
			map.put(arg1[i], context.getBean(arg1[i]));
		}
	}
	
	public Map<String, Object> getMap(){
		return map;
	}
}
