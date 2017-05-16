package cc.gukeer.attendance.persistence.dao;

import java.util.List;
import java.util.Map;

public interface A_CourseMapper {
    //得到该上课的学生
    List getStudentClass(Long bTime,Long eTime);
    //得到该上课的学生日志
    List getStudentClassLog(Long btime,Long etime,Long timebegin,Long timeend, String studentId);
    //获得上课时间
    List getClassTime();
    //获得班级统计的数据(迟到多少人)
    List getClassStatisticsData(String teacherId,Long monday,Long saturday);
    //获得该班学生的信息（查看课结果）
    List getClassStudentInfomation(String teacherId ,String courseId);
    //查看某节课的某个学生的具体信息（可以查看教师备注）
    List getStudentInfomation(String teacherId,String courseId,String studentId);
    //获得某节课的统计信息
    Map getSingleClassStatistics(String teacherId, String courseId);
    //获得本学期的时间
    List getStudentTime();
}