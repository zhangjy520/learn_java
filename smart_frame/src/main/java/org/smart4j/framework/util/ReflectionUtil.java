package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类<br>
 * 用于实例化类<br>
 * 以及获取方法、成员变量
 * @author Administrator
 *
 */
public final class ReflectionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 创建实例
	 */
	public static Object newInstance(Class<?> cls){
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			LOGGER.error("创建类的实例化失败", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return instance;
	}
	
	/**
	 * 调用方法
	 */
	public static Object invokeMethod(Object obj, Method method, Object ... args){
		Object result;
		try {
			//忽略访问权限的限制(override的限制)  ture为可以访问
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("类的方法调用失败", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * 设置成员变量的值
	 */
	public static void setField(Object obj, Field field, Object value){
		try {
			System.out.println("<<<<>>>>  "+field.getName());
			//忽略访问权限的限制 ture为可以访问
			field.setAccessible(true);
			field.set(obj, value);
			System.out.println("<<<<>>>>  "+field.getType());
		} catch (Exception e) {
			LOGGER.error("类的变量赋值失败", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
