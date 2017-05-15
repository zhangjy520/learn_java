package org.smart4j.framework;

import com.sun.org.glassfish.gmbal.Description;

/**
 * 配置文件中的常量
 * @author Administrator
 */
public interface ConfigConstant {
	
	@Description("文件名称")
	String CONFIG_FILE = "smart.properties";
	
	@Description("JDBC-驱动")
	String JDBC_DRIVER = "smart.framework.jdbc.driver";
	
	@Description("JDBC-URL")
	String JDBC_URL = "smart.framework.jdbc.url";

	@Description("JDBC-USERNAME")
	String JDBC_USERNAME = "smart.framework.jdbc.username";

	@Description("JDBC-PASSWORD")
	String JDBC_PASSWORD = "smart.framework.jdbc.password";

	@Description("项目基础包名")
	String APP_BASE_PACKAGE = "smart.framework.app.base_package";

	@Description("JSP页面路径")
	String APP_JSP_PATH = "smart.framework.app.jsp_path";

	@Description("静态文件路径")
	String APP_ASSET_PATH = "smart.framework.app.asset_path";



}
