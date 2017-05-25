/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.sys.persistence.dao;



import cn.gukeer.common.persistence.CrudDao;
import cn.gukeer.common.persistence.annotation.MyBatisDao;
import cn.gukeer.divideStudent.sys.persistence.entity.Dict;

import java.util.List;

/**
 * 字典DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
