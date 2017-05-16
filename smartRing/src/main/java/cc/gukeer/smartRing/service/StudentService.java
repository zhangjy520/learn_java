package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.Student;
import cc.gukeer.smartRing.persistence.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student selectStudentByMac(String mac);

    Student selectByPrimaryKey(String studentId);

    Student selectByXh(String schoolId,String xh);

    List<Map<String, String>> selectNoBoundStudentBySchoolIdAndClassId(String schoolId, String classId);

    List<Student> getAllStudent(User user);

    //添加学生的所有界面
    PageInfo checkAllStudent(Map map);
    //得到选课学生
    List<Map> getClassStudent(String classId);

    List<Map> selectStudentByAttribute(Map map);

    //查询视图v_student_ring_classinfo得到所有信息
    List<Map> selectAllStudent(User user);

    //单独删除学生
    int deleteStudent(String classId,String studentxh);
}
