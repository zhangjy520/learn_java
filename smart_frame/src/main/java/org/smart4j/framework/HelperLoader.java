package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * ��ʼ��������<br>
 * ClassHelper��BeanHelper��ControllerHepler��IocHepler
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
//	 * ����ʱ��Ҫִ�е�
//	 */
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		LOGGER.info("��ʼ��������...");
//		HelperLoader.init();
//	}
//	
//	/**
//	 * ����ʱ��Ҫִ�е�
//	 */
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//	}

}
