package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.service.HelloService;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;


/**
 * 依赖注入助手类<br>
 * 做法：<br>
 *　　1、获取所有的bean类与bean类实例的映射关系(简称beanMap)<br>
 *　　2、循环beanMap，获取bean类与其实例化的对象，并获取某个类所有字段(简称beanField)<br>
 *　　3、再循环beanField，判断当前类的当前字段是否有某个注解标注<br>
 *　　4、如果有，则通过反射给@Inject注解标注的变量赋值(依赖注入)
 * @author Administrator
 *
 */
public final class IocHelper {

	static{
		System.out.println("IocHelper loading ... ");
		//获取所有bean类与bean类实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			//遍历 Bean Map
			for(Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()){
				//从beanMap中获取bean类与bean的实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				
				//获取bean类中所有的字段
				Field[] beanFields = beanClass.getDeclaredFields();
				
				if(ArrayUtils.isNotEmpty(beanFields)){
					//遍历BeanField
					for(Field beanField : beanFields){
						//判断当前字段是否有Inject注解标注
						if(beanField.isAnnotationPresent(Inject.class)){
							System.out.println("<<< "+beanField.getName()+" >>>");
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							Object beanFieldInstance2 = beanMap.get(beanFieldClass);
							((HelloService)beanFieldInstance2).eat();
							if(null!=beanFieldInstance){
								//通过反射初始化BeanField的值
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
