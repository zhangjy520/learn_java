package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.ReflectionUtil;

/**
 * ��ȡָ��Ӧ�ð��������е�bean<br>
 * ԭ��<br>
 *����1���ȵ���ClassHelper��getBeanClassSet��ȡ���е�bean�༯�ϣ���ʱ��û��ʵ��������<br>
 *����2��ѭ��bean�༯�ϣ�����ReflectionUtil��newInstance������ʵ������<br>
 *����3��ÿ��ѭ�������Ķ��󶼴���һ��map�У�Map<Class<?>,Object>��
 * @author Administrator
 *
 */
public final class BeanHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);
	
	/**
	 * ����Bean��ӳ���ϵ�����ڴ��bean����bean��ʵ����ӳ���ϵ��
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static{
		System.out.println("BeanHelper loading ... ");
		//��ȡ���е�bean��
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanCls : beanClassSet){
			//ѭ��ʵ����
			Object obj = ReflectionUtil.newInstance(beanCls);
			BEAN_MAP.put(beanCls, obj);
		}
	}
	
	/**
	 * ��ȡBeanӳ��
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * ����bean������ȡ����Ӧ��ʵ��
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			LOGGER.error("û���ҵ���"+cls+"��bean���ʵ��");
			throw new RuntimeException("û���ҵ���"+cls+"��bean���ʵ��");
		}
		LOGGER.info("bean�ࡾ"+cls+"��");
		return (T) BEAN_MAP.get(cls);
	}
	
}
