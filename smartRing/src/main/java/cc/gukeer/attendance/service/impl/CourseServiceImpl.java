package cc.gukeer.attendance.service.impl;

import cc.gukeer.attendance.persistence.dao.A_CourseMapper;
import cc.gukeer.attendance.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/5/12.
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    A_CourseMapper a_courseMapper;
    @Override
    public List<Map> getClassStudent(Long bTime,Long eTime) {
        return  a_courseMapper.getStudentClass(bTime,eTime);
    }

    @Override
    public List<Map> getClassStudentLog(Long btime,Long etime,Long timebegin,Long timeend, String studentId) {
        List<Map> mapList=a_courseMapper.getStudentClassLog(btime,etime,timebegin,timeend,studentId);

        if (mapList.size()==0){
            mapList=null;
        }
        return mapList;
    }

    @Override
    public List<Map> getClassTable(String teacherId,String schoolId,Long monday,Long saturday) {
        return a_courseMapper.getClassTime();
    }

    @Override
    public List<Map> getClassStudentInfomation(String teacherId,String courseId) {
        return a_courseMapper.getClassStudentInfomation(teacherId,courseId);
    }

    @Override
    public List<Map> getClassStatisticsData(String teacherId,Long monday,Long saturday) {
        return a_courseMapper.getClassStatisticsData(teacherId,monday,saturday);
    }

    @Override
    public List<Map> getStudentInfomation(String teacherId,String courseId,String studentId) {
        return a_courseMapper.getStudentInfomation(teacherId,courseId,studentId);
    }

    @Override
    public List<Map> getTime() {
        return a_courseMapper.getStudentTime();
    }

    @Override
    public Map getSingleClassStatistics(String teacherId, String courseId) {

        return a_courseMapper.getSingleClassStatistics(teacherId,courseId);
    }


}
