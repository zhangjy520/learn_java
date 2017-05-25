/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.sys.persistence.dao;

import cn.gukeer.common.persistence.TreeDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.sys.persistence.entity.Area;

import java.util.List;

/**
 * 区域DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	public List<Area> findByEntity(Area area);
}
