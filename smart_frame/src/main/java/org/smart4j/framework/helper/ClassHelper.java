package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * �����������<br>
 * �ܻ�ȡָ��Ӧ�ð��������е���<br>
 * <B>ע���ܻ�ȡbean�࣬���޷�ʵ����<br>
 * <B>ʵ������Ҫ����ReflectionUtil.java
 * @author Administrator
 */
public final class ClassHelper {

	/**
	 * ������ļ��ϣ����ڴ�������ص��ࣩ
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		System.out.println("ClassHelper loading ... ");
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * ��ȡӦ�ð��������е���
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * ��ȡӦ�ð��������е�service��
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
	 * ��ȡӦ�ð��������е�Controller��
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
	 * ��ȡӦ�ð��������е�bean��<br>
	 * ����controller��service
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}


	/*��ȡӦ�ð�����ĳ�����ӿڵ��������� ��ʵ����*/
	public static  Set<Class<?>> getClassSetBySuper(Class<?> superClass){
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls: CLASS_SET) {
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls))
				classSet.add(cls);
		}
		return classSet;
	}
}
