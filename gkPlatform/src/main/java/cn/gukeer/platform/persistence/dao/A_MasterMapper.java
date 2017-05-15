package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.BZRView;
import cn.gukeer.platform.modelView.ClassView;
import cn.gukeer.platform.persistence.entity.CourseClass;
import cn.gukeer.platform.persistence.entity.GradeClass;
import cn.gukeer.platform.persistence.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LL on 2017/4/18.
 */
public interface A_MasterMapper {
    List<BZRView> finaAllMasterByParam(@Param("schoolId")String schoolId, @Param("njId")String njId);

    List<BZRView> findteacherByNameAndSchoolICycleId(@Param("cycleId")String cycleId,@Param("schoolId") String schoolId,@Param("name") String name);

    void insertBatchTeacherClass(@Param("correctTeacherClassList")List<TeacherClass> correctTeacherClassList);

    List<BZRView> getAllMasterByGradeClassIds(@Param("classIdList") List<String> gradeClassList,@Param("xdId") String xdId, @Param("nj")int nj);

    void batchUpdateTeacherClass(@Param("TeacherClassList") List<TeacherClass> TeacherClassList,@Param("classId") String classId);

    List<BZRView> findAllCourseTeacherBycourseClassList(@Param("courseClassList")List<CourseClass> courseClassList);

    List<BZRView> selectCourseTeacherBycourseIdListAndName(@Param("courseIdList")List<String> courseIdList, @Param("teacherName")String teacherName);

    List<BZRView> findCourseTeacherByCycleIdAndSchoolIdAndName(@Param("cycleId")String cycleId,@Param("schoolId") String schoolId,@Param("name") String name);

    List<BZRView> findAllCourseTeacherBycourseClassIdList(@Param("courseIdList")List<String> courseIdList);
}
