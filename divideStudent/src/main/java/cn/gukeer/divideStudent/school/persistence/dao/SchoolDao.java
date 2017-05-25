/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.persistence.dao;

import cn.gukeer.common.persistence.CrudDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.school.persistence.entity.School;

/**
 * 学校DAO接口
 * @author xiangfusheng
 * @version 2016-06-14
 */
@MyBatisDao
public interface SchoolDao extends CrudDao<School> {

	
}