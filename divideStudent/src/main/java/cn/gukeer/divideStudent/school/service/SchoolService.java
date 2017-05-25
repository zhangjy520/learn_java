/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.service;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.service.CrudService;
import cn.gukeer.divideStudent.school.persistence.dao.SchoolDao;
import cn.gukeer.divideStudent.school.persistence.entity.School;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学校Service
 * @author xiangfusheng
 * @version 2016-06-14
 */
@Service
@Transactional(readOnly = true)
public class SchoolService extends CrudService<SchoolDao, School> {

	public School get(String id) {
		return super.get(id);
	}
	
	public List<School> findList(School school) {
		return super.findList(school);
	}
	
	public Page<School> findPage(Page<School> page, School school) {
		return super.findPage(page, school);
	}
	
	@Transactional(readOnly = false)
	public void save(School school) {
		super.save(school);
	}
	
	@Transactional(readOnly = false)
	public void delete(School school) {
		super.delete(school);
	}
	
	
	
	
}