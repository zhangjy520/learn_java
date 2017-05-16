package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.smartRing.persistence.dao.*;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.service.SyncDataService;
import cc.gukeer.smartRing.syncdata.beanViews.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SyncDataServiceImpl implements SyncDataService {

    @Autowired
    ClassSectionMapper classSectionMapper;
    @Autowired
    GradeClassMapper gradeClassMapper;
    @Autowired
    TeacherClassMapper teacherClassMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    A_SyncDataExtensionMapper syncDataExtensionMapper;

    @Override
    public int batchInsertClassSection(List<ClassSectionView> list) {
        int flag = syncDataExtensionMapper.batchInsertClassSection(list);
        return flag;
    }

    @Override
    public int batchDeleteClassSection(List<ClassSectionView> list) {
        int flag = syncDataExtensionMapper.batchDeleteClassSection(list);
        return flag;
    }

    @Override
    public int updateClassSection(ClassSectionView classSection) {
        classSection.setId(classSection.getSyncId());
        int flag = 0;
        ClassSection exist = classSectionMapper.selectByPrimaryKey(classSection.getSyncId());
        if (GukeerStringUtil.isNullOrEmpty(exist)) {
            //为空，执行插入操作
            flag = classSectionMapper.insertSelective(classSection);
        } else {
            //不为空，执行更新操作
            flag = classSectionMapper.updateByPrimaryKeySelective(classSection);
        }
        return flag;
    }

    @Override
    public int batchInsertGradeClass(List<GradeClassView> list) {
        int flag = syncDataExtensionMapper.batchInsertGradeClass(list);
        return flag;
    }

    @Override
    public int batchDeleteGradeClass(List<GradeClassView> list) {
        int flag = syncDataExtensionMapper.batchDeleteGradeClass(list);
        return flag;
    }

    @Override
    public int updateGradeClass(GradeClassView gradeClass) {
        gradeClass.setId(gradeClass.getSyncId());
        int flag = 0;
        GradeClass exist = gradeClassMapper.selectByPrimaryKey(gradeClass.getSyncId());
        if (GukeerStringUtil.isNullOrEmpty(exist)) {
            //为空，执行插入操作
            flag = gradeClassMapper.insertSelective(gradeClass);
        } else {
            //不为空，执行更新操作
            flag = gradeClassMapper.updateByPrimaryKeySelective(gradeClass);
        }
        return flag;
    }


    @Override
    public int batchInsertTeacherClass(List<TeacherClassView> list) {
        List<TeacherClass> param = new ArrayList<TeacherClass>();
        for (TeacherClassView view : list) {
            TeacherClass teacherClass = new TeacherClass();
            teacherClass.setClassId(view.getSyncClassId());
            teacherClass.setTeacherId(view.getSyncTeacherId());
            param.add(teacherClass);
        }
        int flag = syncDataExtensionMapper.batchInsertTeacherClass(param);
        return flag;
    }

    @Override
    public int deleteTeacherClass(String teacherId, String classId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId).andClassIdEqualTo(classId);
        int flag = teacherClassMapper.deleteByExample(example);
        return flag;
    }

    @Override
    public int batchInsertTeacher(List<TeacherView> list) {
        int flag = syncDataExtensionMapper.batchInsertTeacher(list);
        return flag;
    }

    @Override
    public int batchDeleteTeacher(List<TeacherView> list) {
        int flag = syncDataExtensionMapper.batchDeleteTeacher(list);
        return flag;
    }

    @Override
    public int updateTeacher(TeacherView teacher) {
        teacher.setId(teacher.getSyncId());
        int flag = 0;
        Teacher exist = teacherMapper.selectByPrimaryKey(teacher.getSyncId());
        if (GukeerStringUtil.isNullOrEmpty(exist)) {
            //为空，执行插入操作
            flag = teacherMapper.insertSelective(teacher);
        } else {
            //不为空，执行更新操作
            flag = teacherMapper.updateByPrimaryKeySelective(teacher);
        }
        return flag;
    }


    @Override
    public int batchInsertStudent(List<StudentView> list) {
        int flag = syncDataExtensionMapper.batchInsertStudent(list);
        return flag;
    }

    @Override
    public int batchDeleteStudent(List<StudentView> list) {
        int flag = syncDataExtensionMapper.batchDeleteStudent(list);
        return flag;
    }

    @Override
    public int updateStudent(StudentView student) {
        student.setId(student.getSyncId());
        int flag = 0;
        Student exist = studentMapper.selectByPrimaryKey(student.getSyncId());
        if (GukeerStringUtil.isNullOrEmpty(exist)) {
            //为空，执行插入操作
            flag = studentMapper.insertSelective(student);
        } else {
            //不为空，执行更新操作
            flag = studentMapper.updateByPrimaryKeySelective(student);
        }
        return flag;
    }

    @Override
    public int batchInsertUser(List<UserView> list) {
        int flag = syncDataExtensionMapper.batchInsertUser(list);
        return flag;
    }

    @Override
    public int batchDeleteUser(List<UserView> list) {
        int flag = syncDataExtensionMapper.batchDeleteUser(list);
        return flag;
    }

    @Override
    public int updateUser(UserView user) {
        user.setId(user.getSyncId());
        int flag = 0;
        User exist = userMapper.selectByPrimaryKey(user.getSyncId());
        if (GukeerStringUtil.isNullOrEmpty(exist)) {
            //为空，执行插入操作
            flag = userMapper.insertSelective(user);
        } else {
            //不为空，执行更新操作
            flag = userMapper.updateByPrimaryKeySelective(user);
        }
        return flag;
    }


}
