package com.zzyyaa.test.customAnnotaion;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/*@Aspect//注解这个类是AOP切面
@Component*/
public class MyFirstAnnotationAspect extends EmbeddedValueResolutionSupport implements AnnotationFormatterFactory<MyFirstAnnotaion>{
	
	
	@Override
	public Set<Class<?>> getFieldTypes() {
		// TODO Auto-generated method stub
		Set<Class<?>> set = new HashSet<>();
		set.add(String.class);
		return set;
	}

	@Override
	public Parser<?> getParser(MyFirstAnnotaion arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		Parser<String> parser = name(arg0, arg1);
		return parser;
	}

	@Override
	public Printer<?> getPrinter(MyFirstAnnotaion arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		Parser<String> parser = name(arg0, arg1);
		return (Printer<?>) parser;
	}
	
	private Parser<String> name(MyFirstAnnotaion arg0, Class<?> arg1) {
		Map<String, String> map = new HashMap<>();
		map.put("1", "壹");
		map.put("2", "贰");
		map.put("3", "叁");
		map.put("4", "肆");
		Parser<String> parser = new Parser<String>() {
			@Override
			public String parse(String text, Locale locale) throws ParseException {
				// TODO Auto-generated method stub
				return map.get(arg0);
			}
		};
		return parser;
	}
}
