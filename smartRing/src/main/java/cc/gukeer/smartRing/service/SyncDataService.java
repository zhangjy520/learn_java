package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.syncdata.beanViews.*;

import java.util.List;

public interface SyncDataService {
    //学段表
    int batchInsertClassSection(List<ClassSectionView> list);//批量插入

    int batchDeleteClassSection(List<ClassSectionView> list);//批量删除

    int updateClassSection(ClassSectionView classSection);

    //班级表
    int batchInsertGradeClass(List<GradeClassView> list);//批量插入

    int batchDeleteGradeClass(List<GradeClassView> list);//批量删除

    int updateGradeClass(GradeClassView gradeClass);

    //教师班级表
    int batchInsertTeacherClass(List<TeacherClassView> list);//批量插入

    int deleteTeacherClass(String teacherId, String classId);//删除

    //教师表
    int batchInsertTeacher(List<TeacherView> list);//批量插入

    int batchDeleteTeacher(List<TeacherView> list);//批量删除

    int updateTeacher(TeacherView teacher);//修改teacher

    //学生表
    int batchInsertStudent(List<StudentView> list);//批量插入

    int batchDeleteStudent(List<StudentView> list);//批量删除

    int updateStudent(StudentView student);//修改学生

    //用户表
    int batchInsertUser(List<UserView> list);

    int batchDeleteUser(List<UserView> list);

    int updateUser(UserView user);
}
