package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.BZRView;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_TeacherExtensionMapper {

    int createAccount(@Param(value = "schoolId") String schoolId);

    List<Map<String, Object>> selectTeacherViewByParam(Map<Object, Object> paramMap);

    List<Teacher> selectByClassId(@Param("classId") String classId, @Param("type") Integer type);

    int saveTeacherClass(@Param("teacherId") String teacherId, @Param("classId") String classId, @Param("type") Integer type);

    int delTeacherClass(@Param("teacherId") String teacherId, @Param("classId") String classId, @Param("type") Integer type);

    int insertTeacherBatch(List<Teacher> teacherList);

    List<Teacher> selectTeacherNameLike(@Param("teacherName") String teacherName, @Param("schoolId") String schoolId);

    List<Teacher> selectBatchTeachers(@Param("idList") List<String> teacherList, @Param("schoolId") String schoolId);

    List<Map> teacherListView(@Param("currentSchoolId") String currentSchoolId, @Param("schoolId") String schoolId, @Param("teacherName") String teacherName);

    List<Teacher> selectByByTYSC(Map param);

    //区平台id，子学校+平台
    Map teacherReport(@Param("loginSchoolId") String loginSchoolId,
                      @Param("allSchool") List<School> allSchool);

    List<Teacher> findTeacherByName(@Param("name") String name, @Param("schoolId")String schoolId);
}
