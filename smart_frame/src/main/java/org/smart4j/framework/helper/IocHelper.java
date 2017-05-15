package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.service.HelloService;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * ����ע��������<br>
 * ������<br>
 *����1����ȡ���е�bean����bean��ʵ����ӳ���ϵ(���beanMap)<br>
 *����2��ѭ��beanMap����ȡbean������ʵ�����Ķ��󣬲���ȡĳ���������ֶ�(���beanField)<br>
 *����3����ѭ��beanField���жϵ�ǰ��ĵ�ǰ�ֶ��Ƿ���ĳ��ע���ע<br>
 *����4������У���ͨ�������@Injectע���ע�ı�����ֵ(����ע��)
 * @author Administrator
 *
 */
public final class IocHelper {

	static{
		System.out.println("IocHelper loading ... ");
		//��ȡ����bean����bean��ʵ��֮���ӳ���ϵ
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			//���� Bean Map
			for(Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
				//��beanMap�л�ȡbean����bean��ʵ��
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				
				//��ȡbean�������е��ֶ�
				Field[] beanFields = beanClass.getDeclaredFields();
				
				if(ArrayUtils.isNotEmpty(beanFields)){
					//����BeanField
					for(Field beanField : beanFields){
						//�жϵ�ǰ�ֶ��Ƿ���Injectע���ע
						if(beanField.isAnnotationPresent(Inject.class)){
							System.out.println("<<< "+beanField.getName()+" >>>");
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							Object beanFieldInstance2 = beanMap.get(beanFieldClass);
							((HelloService)beanFieldInstance2).eat();
							if(null!=beanFieldInstance){
								//ͨ�������ʼ��BeanField��ֵ
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
