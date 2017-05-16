package cc.gukeer.smartRing.persistence.dao;

import java.util.List;
import java.util.Map;

public interface A_StudentMapper {

    List<Map> selectStudent(String schoolId);
    //通过属性查询学生
    List<Map> selectStudentByCondition(Map map);

    List<Map> getAllStudent(String schoolId);
    //获得选择体育课的信息
    List<Map> getClassStudent(String classId);
}