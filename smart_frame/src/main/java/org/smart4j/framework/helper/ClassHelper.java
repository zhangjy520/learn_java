package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类<br>
 * 能获取指定应用包名下所有的类<br>
 * <B>注：能获取bean类，但无法实例化<br>
 * <B>实例化需要调用ReflectionUtil.java
 * @author Administrator
 */
public final class ClassHelper {

	/**
	 * 定义类的集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		System.out.println("ClassHelper loading ... ");
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取应用包名下所有的类
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获取应用包名下所有的service类
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Service.class))
			classSet.add(cls);
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有的Controller类
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class))
			classSet.add(cls);
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有的bean类<br>
	 * 包括controller与service
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}


	/*获取应用包名下某父类或接口的所有子类 或实现类*/
	public static  Set<Class<?>> getClassSetBySuper(Class<?> superClass){
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls: CLASS_SET) {
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls))
				classSet.add(cls);
		}
		return classSet;
	}
}
