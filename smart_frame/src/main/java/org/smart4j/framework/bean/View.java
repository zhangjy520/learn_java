package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ���ص���ͼ����
 * @author Administrator
 *
 */
public class View {
	
	/**
	 * ��ͼ·��
	 */
	private String path;
	
	/**
	 * ģ������
	 */
	private Map<String, Object> model;
	
	public View(String path) {
		this.path = path;
		model = new HashMap<String, Object>();
	}
	
	public View addModel(String key, Object value) {
		model.put(key, value);
		return this;
	}
	
	public String getPath() {
		return path;
	}
	
	public Map<String, Object> getModel() {
		return model;
	}
	
}
