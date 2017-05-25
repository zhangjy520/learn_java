/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.persistence.dao;

import cn.gukeer.common.persistence.CrudDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.school.persistence.entity.Student;

/**
 * 学生管理DAO接口
 * @author xiangfusheng
 * @version 2016-06-29
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {

	
}