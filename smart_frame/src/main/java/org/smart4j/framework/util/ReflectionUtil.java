package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ���乤����<br>
 * ����ʵ������<br>
 * �Լ���ȡ��������Ա����
 * @author Administrator
 *
 */
public final class ReflectionUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * ����ʵ��
	 */
	public static Object newInstance(Class<?> cls){
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			LOGGER.error("�������ʵ����ʧ��", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return instance;
	}
	
	/**
	 * ���÷���
	 */
	public static Object invokeMethod(Object obj, Method method, Object ... args){
		Object result;
		try {
			//���Է���Ȩ�޵�����(override������)  tureΪ���Է���
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("��ķ�������ʧ��", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	
	/**
	 * ���ó�Ա������ֵ
	 */
	public static void setField(Object obj, Field field, Object value){
		try {
			System.out.println("<<<<>>>>  "+field.getName());
			//���Է���Ȩ�޵����� tureΪ���Է���
			field.setAccessible(true);
			field.set(obj, value);
			System.out.println("<<<<>>>>  "+field.getType());
		} catch (Exception e) {
			LOGGER.error("��ı�����ֵʧ��", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
