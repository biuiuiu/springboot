package com.zzyyaa.test.demoUi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.zzyyaa.test.Utils.BeanUtils;
import com.zzyyaa.test.customAnnotaion.MyFirstAnnotaion;
import com.zzyyaa.test.dao.LookupDao;
import com.zzyyaa.test.entity.Lookup;
import com.zzyyaa.test.entity.User;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;

@Component
public class AnnotationTest {
	
	public static <T> List<T> testAnnotation(List<T> list) throws Exception {
		if (list == null || list.size() == 0) 
			return list;
		List<T> finalList = new ArrayList<>();
		Class<?> class1 = list.get(0).getClass();
		Field[] fields = class1.getDeclaredFields();//获取类的所有注解字段
		Set<String> despSet = new HashSet<>();//需要加desp的字段
		Set<String> addSet = new HashSet<>();//需要扩展的字段
		for (Field field : fields) {
			if (field.isAnnotationPresent(MyFirstAnnotaion.class)) {
				String temp = field.getName();
				String despField = temp.concat("Desp");
				despSet.add(despField);
			}
		}
		Iterator<String> despIt = despSet.iterator();
		while (despIt.hasNext()) {
			boolean flag = false;
			String string =  despIt.next();
			for (Field field : fields) {
				if (string.equals(field.getName())) {
					flag = true;
				}
			}
			if (!flag)
				addSet.add(string);
		}
		User user = new User();
		System.out.println(user);
		Class<?> class2 = addField(class1.getName(), addSet);
 		for (T t : list) {
			Object t2 = class2.newInstance();
			for (Field field : fields) {
				String temp = field.getName();
				Method getMethod = class1.getMethod("get"+temp.substring(0,1).toUpperCase()+temp.substring(1),new Class[]{});
				Object value = getValueByObj(t,getMethod);
				if (field.isAnnotationPresent(MyFirstAnnotaion.class)) {
					String vString = translateObj2Str(value,getMethod.getGenericReturnType());
					MyFirstAnnotaion myFirstAnnotaion = field.getAnnotation(MyFirstAnnotaion.class);//获取某个字段的注解及参数内容
					String type = myFirstAnnotaion.type();
					LookupDao dao = BeanUtils.getBean(LookupDao.class);
					List<Lookup> lookupList = dao.getLookupByGroupCode(type);
					String despField = temp.concat("Desp");
					for (Lookup lookup : lookupList) {
						if (vString.equals(lookup.getCode())) {
							vString = lookup.getDesp();
						}
					}
					setFieldValue(t2, despField, vString);
				}else {
					setFieldValue(t2, temp, value);
				}
			}
			finalList.add((T)t2);
		}
		return finalList;
	}
	/**
	 * 根据对象和方法获取方法对应的返回值
	 * @
	 * */
	private static Object getValueByObj(Object object , Method method) throws Exception{
		if (object == null || method == null )
			return null;
		try {
			Object obj = method.invoke(object, new Object[]{});
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("没有此方法");
			return null;
		}
	}
	/**
	 * 把object的类型转换为string类型
	 * @
	 * */
	private static String translateObj2Str(Object object,Type type) {
		if (object == null || type == null) 
			return null;
		String temp = type.getTypeName();
		String reString = null;
		switch (temp) {
		case "java.lang.String":
			reString = (String) object;
			break;
		case "long":
			reString = String.valueOf(object);
		default:
			break;
		}
		return reString;
	}
	/**
	 * 根据对象和方法设置属性的值
	 * @
	 * */
	private static void setValueByObj(Object object,Method method,String val) {
		if (object == null || method == null )
			return ;
		try {
			method.invoke(object, val);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("方法不存在");
		}
		
	}
	
	/**
	 * 动态添加属性及赋值
	 * @
	 * */
	private static Class<?> addField(String className,Set<String> set) throws Exception{
		ClassPool pool = ClassPool.getDefault();//获取Javaassist类池
		CtClass ctClass = pool.get(className);
		ctClass.defrost();//字节文件“解冻”，即可多次修改，不然执行toclass方法之后不可更改
		// 为创建的类ctClass添加属性
		Iterator<String> it = set.iterator();
		Class<?> class1 = Class.forName(className);
		while(it.hasNext()){
			String fieldName = it.next();
			String filedType = fieldName.getClass().getName();//设置字段的类型，此为string
			CtField ctField = new CtField(pool.get(filedType), fieldName,ctClass);
			ctField.setModifiers(Modifier.PRIVATE);
			CtField[] ctFields = ctClass.getDeclaredFields();
			boolean b = false;
			for (CtField item : ctFields) {
				if (item.getName().contains(fieldName))
					b = true;
			}
			if (!b){
				 ctClass.addField(ctField);
				 class1 = ctClass.toClass();
			}
		}
		return class1;
	}
	
	/**
	 * 动态添加属性及赋值
	 * @
	 * */
	private static Object addField(String className,Map<String, Object> fieldMap) throws Exception{
		ClassPool pool = ClassPool.getDefault();//获取Javaassist类池
		CtClass ctClass = pool.get(className);
		ctClass.defrost();
		// 为创建的类ctClass添加属性
		Iterator<Entry<String, Object>> it = fieldMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			String fieldName = entry.getKey();
			Object value = entry.getValue();
			String filedType = value.getClass().getName();
			CtField ctField = new CtField(pool.get(filedType), fieldName,ctClass);
			ctField.setModifiers(Modifier.PRIVATE);
			ctClass.addField(ctField);
		}
		Class<?> class1 = ctClass.toClass();
		Object object = class1.newInstance();
		// 为创建的类newObject属性赋值
        it = fieldMap.entrySet().iterator();
        while (it.hasNext()) {  // 遍历所有的属性
        	Entry<String, Object> entry = it.next();
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            // 为属性赋值
            setFieldValue(object,fieldName,fieldValue);
        }
        return object;
		
		
	}
	
    /**
     * 给对象属性赋值
     * @param dObject
     * @param fieldName
     * @param val
     * @return
     */
    public static Object setFieldValue(Object dObject, String fieldName, Object val) {
//        Object result = null;
        try {
          Field fu = dObject.getClass().getDeclaredField(fieldName); // 获取对象的属性域
            try {
                fu.setAccessible(true); // 设置对象属性域的访问属性
                fu.set(dObject,val); // 设置对象属性域的属性值
//                result = fu.get(dObject); // 获取对象属性域的属性值
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return dObject;
    }
	
    
    
    
}
