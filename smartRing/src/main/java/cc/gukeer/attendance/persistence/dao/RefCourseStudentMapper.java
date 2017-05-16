package cc.gukeer.attendance.persistence.dao;

import cc.gukeer.attendance.persistence.entity.RefCourseStudent;
import cc.gukeer.attendance.persistence.entity.RefCourseStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefCourseStudentMapper {
    int deleteByExample(RefCourseStudentExample example);

    int insert(RefCourseStudent record);

    int insertSelective(RefCourseStudent record);

    List<RefCourseStudent> selectByExample(RefCourseStudentExample example);

    int updateByExampleSelective(@Param("record") RefCourseStudent record, @Param("example") RefCourseStudentExample example);

    int updateByExample(@Param("record") RefCourseStudent record, @Param("example") RefCourseStudentExample example);
}