package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;

import java.util.Map;

public class Param {
	
	private Map<String, Object> paramMap;
	
	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**
	 * ���ݲ�������ȡlong�Ͳ���ֵ
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * ��ȡ�����ֶ���Ϣ
	 * @return
	 */
	public Map<String, Object> getMap() {
		return paramMap;
	}
	
}
