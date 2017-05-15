package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.ReflectionUtil;

/**
 * 获取指定应用包名下所有的bean<br>
 * 原理：<br>
 *　　1、先调用ClassHelper的getBeanClassSet获取所有的bean类集合（此时还没有实例化）。<br>
 *　　2、循环bean类集合，调用ReflectionUtil的newInstance方法来实例化。<br>
 *　　3、每次循环创建的对象都存在一个map中（Map<Class<?>,Object>）
 * @author Administrator
 *
 */
public final class BeanHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);
	
	/**
	 * 定义Bean的映射关系（用于存放bean类与bean类实例的映射关系）
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static{
		System.out.println("BeanHelper loading ... ");
		//获取所有的bean类
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanCls : beanClassSet){
			//循环实例化
			Object obj = ReflectionUtil.newInstance(beanCls);
			BEAN_MAP.put(beanCls, obj);
		}
	}
	
	/**
	 * 获取Bean映射
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 根据bean类来获取它对应的实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			LOGGER.error("没有找到【"+cls+"】bean类的实例");
			throw new RuntimeException("没有找到【"+cls+"】bean类的实例");
		}
		LOGGER.info("bean类【"+cls+"】");
		return (T) BEAN_MAP.get(cls);
	}
	
}
