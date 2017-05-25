/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.common.utils.excel.fieldtype;

import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.divideStudent.sys.persistence.entity.Area;
import cn.gukeer.divideStudent.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author jeeplus
 * @version 2013-03-10
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
