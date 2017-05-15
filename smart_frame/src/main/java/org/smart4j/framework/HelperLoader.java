package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * 初始化加载类<br>
 * ClassHelper、BeanHelper、ControllerHepler、IocHepler
 * @author Administrator
 *
 */
public final class HelperLoader{
	
//	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	public static void init() {
		Class<?>[] classList = {
			ClassHelper.class,
			BeanHelper.class,
			IocHelper.class,
			ControllerHelper.class
		};
		for(Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName());
		}
		
	}

//	/**
//	 * 加载时候要执行的
//	 */
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		LOGGER.info("初始化加载类...");
//		HelperLoader.init();
//	}
//	
//	/**
//	 * 毁灭时候要执行的
//	 */
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//	}

}
