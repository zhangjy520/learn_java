package cc.gukeer.attendance.service;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/5/12.
 */

public interface CourseService {
//  获得本段时间该上课的学生
    public List<Map> getClassStudent(Long monday,Long saturday);
    //得到该上课的学生的日志
    public List<Map> getClassStudentLog(Long btime,Long etime,Long timebegin,Long timeend, String studentId);
    //获取上课的课表
    public List<Map> getClassTable(String teacherId,String schoolId,Long monday,Long saturday);
    //统计本周课学生每节课状态
    List<Map> getClassStudentInfomation(String teacherId,String courseId);
    //统计本周的统计课程信息
    List<Map> getClassStatisticsData(String teacherId,Long monday,Long saturday);
    //获得某个学生的具体信息
    List<Map> getStudentInfomation(String teacherId,String courseId,String studentId);
    //获得本学期的时间
    List<Map> getTime();
    //获得某个班级的具体信息
    Map getSingleClassStatistics(String teacherId ,String courseId);
}
