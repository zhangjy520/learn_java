package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.smartRing.persistence.dao.TeacherMapper;
import cc.gukeer.smartRing.persistence.entity.Teacher;
import cc.gukeer.smartRing.persistence.entity.TeacherExample;
import cc.gukeer.smartRing.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class TeacherServiceImpl extends BasicService implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public PageInfo<Teacher> findAllList(int pageNum, int pageSize) {

        TeacherExample example = new TeacherExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.selectByExample(example);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);

        return pageInfo;
    }

    @Override
    public Teacher findTeacherById(String id) {

        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andIdEqualTo(teacher.getId());
        int count = teacherMapper.updateByExampleSelective(teacher, example);
        return count;
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        int count = teacherMapper.insertSelective(teacher);
        return count;
    }

    @Override
    public Teacher findTeacherByNo(String schoolId, String no) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNoEqualTo(no);
        List<Teacher> teachers = teacherMapper.selectByExample(example);
        if(teachers.size()>0){
            return teachers.get(0);
        }
        else return null;
    }
    @Override
    public List<Teacher> checkTeacherByName(String teacherName){
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andNameEqualTo(teacherName);
        return teacherMapper.selectByExample(teacherExample);
    }

    @Override
    public List<Teacher> checkTeacherByNo(String teacherNo){
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andNoEqualTo(teacherNo);
        return teacherMapper.selectByExample(teacherExample);
    }

    public List<Teacher> getAllTeacher(String schoolId){
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andSchoolIdEqualTo(schoolId);
        return teacherMapper.selectByExample(teacherExample);
    }
}
