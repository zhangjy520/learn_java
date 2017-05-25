/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.sys.utils;



import cn.gukeer.common.utils.SpringContextHolder;
import cn.gukeer.divideStudent.sys.persistence.dao.AreaDao;
import cn.gukeer.divideStudent.sys.persistence.entity.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户工具类
 * @author xiangfusheng
 * @version 2013-12-05
 */
public class AreaUtils {


	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

	/**
	 * 通过area获取
	 * @param area
	 * @return
	 */
	public static List<Area> getAreasByEntity(Area area){
		return areaDao.findByEntity(area);
	}
	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Area> getAreasByIds(String ids){
		String idsArr[] = ids.split(",");
		List<Area> areas = new ArrayList<Area>();
		if(idsArr.length == 3){
			for(String id:idsArr){
				areas.add(areaDao.get(id));
			}
		}
		return areas;
	}
}
