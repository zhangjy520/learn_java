package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.Course;
import cn.gukeer.platform.persistence.entity.TeachCycle;
import cn.gukeer.platform.persistence.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by LL on 2017/4/14.
 */
public interface A_Teach_CourseMapper {
    List<Course> getAllCourseListByCycleIds(@Param("idList") List<String> list,@Param("schoolId") String schoolId);
}
