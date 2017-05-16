package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.TeacherClass;
import cc.gukeer.smartRing.syncdata.beanViews.*;

import java.util.List;

/**
 * Created by conn on 16-10-11.
 */
public interface A_SyncDataExtensionMapper {

    //学段表
    int batchInsertClassSection(List<ClassSectionView> list);//批量插入

    int batchDeleteClassSection(List<ClassSectionView> list);//批量删除

    //班级表
    int batchInsertGradeClass(List<GradeClassView> list);//批量插入

    int batchDeleteGradeClass(List<GradeClassView> list);//批量删除

    //教师班级表
    int batchInsertTeacherClass(List<TeacherClass> list);//批量插入

    //教师表
    int batchInsertTeacher(List<TeacherView> list);//批量插入

    int batchDeleteTeacher(List<TeacherView> list);//批量删除

    //学生表
    int batchInsertStudent(List<StudentView> list);//批量插入

    int batchDeleteStudent(List<StudentView> list);//批量删除


    //学生表
    int batchInsertUser(List<UserView> list);//批量插入

    int batchDeleteUser(List<UserView> list);//批量删除

}
