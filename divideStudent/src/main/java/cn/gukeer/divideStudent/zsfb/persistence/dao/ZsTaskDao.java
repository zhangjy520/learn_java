/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.zsfb.persistence.dao;

import cn.gukeer.common.persistence.CrudDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.zsfb.persistence.entity.ZsTask;

/**
 * 分班任务DAO接口
 * @author xiangfusheng
 * @version 2016-07-11
 */
@MyBatisDao
public interface ZsTaskDao extends CrudDao<ZsTask> {

}