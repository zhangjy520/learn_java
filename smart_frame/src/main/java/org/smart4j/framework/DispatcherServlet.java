package org.smart4j.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// ��ʼ�������
		HelperLoader.init();
		// ��ȡServletContext ��������ע��Servlet��
		ServletContext servletContext = servletConfig.getServletContext();
		// ע�ᴦ��JSP��Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// ע�ᴦ��̬��Դ��Ĭ��Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		System.out.println(ConfigHelper.getAppAssetPath());
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ����ʽ������·��
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		// ��ȡAction������
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(null != handler){
			// ��ȡController�༰��beanʵ��
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			// ���������������
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String[] params = StringUtil.splitString(body, "&");
				if(ArrayUtils.isNotEmpty(params)){
					for(String param : params){
						String[] array = StringUtil.splitString(param, "=");
						if(ArrayUtils.isNotEmpty(array) && array.length == 2){
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			// ����Action����
			Method actionMethod = handler.getActionMethod();
			//Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			Object[] paramA ={request,response};
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, paramA);
			// ����Action��������ֵ
			if(result instanceof View){ // �������ͼ
				// ����JSPҳ��
				View view  = (View) result;
				String path = view.getPath();
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){
						System.out.println(request.getContextPath());
//						response.sendRedirect(request.getContextPath() + path);
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
					} else {
						Map<String, Object> model = view.getModel();
						for(Map.Entry<String, Object> entry : model.entrySet()){
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
					}
				}
			} else if(result instanceof Data) {
				// ����JSON����
				Data data = (Data) result;
				Object model = data.getModel();
				if(null != model){
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
	}
}
