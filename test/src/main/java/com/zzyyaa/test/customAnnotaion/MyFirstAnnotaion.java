package com.zzyyaa.test.customAnnotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 注解作用的目标位置，类、方法、字段、参数等
@Retention(RetentionPolicy.RUNTIME) // 注解保留的位置，只保留源代码中、编译文件中、运行时可获取
@Documented // 此注解会被包含在Javadoc中
@Inherited // 子类可以继承父类的注解
public @interface MyFirstAnnotaion {
	
	String type();

	boolean multi() default false;
}
