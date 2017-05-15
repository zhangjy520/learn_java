package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * ������������
 * @author Administrator
 *
 */
public final class ControllerHelper {
	
	/**
	 * ���ڴ�������봦������ӳ���ϵ��Action Map��
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static {
		
		System.out.println("ControllerHelper loading ... ");
		
		// ��ȡ���е� Controller ��
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		
		if(CollectionUtil.isNotEmpty(controllerClassSet)) {
			// ������Щ Controller ��
			for(Class<?> controllerClass : controllerClassSet) {
				// ��ȡ Controller ���ж���ķ���
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtils.isNotEmpty(methods)){
					// ������Щ Controller ���еķ���
					for(Method method : methods){
						// �жϵ�ǰ�����Ƿ���� Action ע��
						if(method.isAnnotationPresent(Action.class)){
							// �� Action ע���л�ȡURLӳ�����
							Action action = method.getAnnotation(Action.class);
							String mapping  = action.value();
							// ��֤URLӳ�����
							//if(mapping.matches("\\w+:/\\w")){
								String[] array = mapping.split(":");
								if(ArrayUtils.isNotEmpty(array) && array.length == 2){
									// ��ȡ���󷽷�������·��
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									// ��ʼ��ActionMap
									ACTION_MAP.put(request, handler);
								}
							//}
						}
					}
				}
			}
		}
	}
	
	/**
	 * ��ȡ Handler
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}

	public static void pring(){
		Set set = ACTION_MAP.entrySet();
		for (Object a :set) {
			System.out.println(ACTION_MAP.get(a));
		}
	}
	public static void main(String[] args) {
		String pat = "test:/hello";
		 Map<Request, Handler> dd = new HashMap<Request, Handler>();
		dd.put(new Request("test","/hello"),new Handler(null,null));

		Request re = new Request("test","/hello");
		Handler ddd  = dd.get(re);
		System.out.println(pat.matches("\\w+:/\\w"));
	}
	
}
