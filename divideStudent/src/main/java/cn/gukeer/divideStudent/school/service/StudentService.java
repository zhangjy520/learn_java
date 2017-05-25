/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.divideStudent.school.service;

import cn.gukeer.common.persistence.Page;
import cn.gukeer.common.service.CrudService;
import cn.gukeer.divideStudent.school.persistence.dao.StudentDao;
import cn.gukeer.divideStudent.school.persistence.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生管理Service
 * @author xiangfusheng
 * @version 2016-06-29
 */
@Service
@Transactional(readOnly = true)
public class StudentService extends CrudService<StudentDao, Student> {

	public Student get(String id) {
		return super.get(id);
	}
	
	public List<Student> findList(Student student) {
		return super.findList(student);
	}
	
	public Page<Student> findPage(Page<Student> page, Student student) {
		return super.findPage(page, student);
	}
	
	@Transactional(readOnly = false)
	public void save(Student student) {
		super.save(student);
	}
	
	@Transactional(readOnly = false)
	public void delete(Student student) {
		super.delete(student);
	}
	
	
	
	
}