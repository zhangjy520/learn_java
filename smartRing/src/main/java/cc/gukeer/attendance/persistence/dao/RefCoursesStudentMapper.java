package cc.gukeer.attendance.persistence.dao;

import cc.gukeer.attendance.persistence.entity.RefCoursesStudent;
import cc.gukeer.attendance.persistence.entity.RefCoursesStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefCoursesStudentMapper {
    int deleteByExample(RefCoursesStudentExample example);

    int insert(RefCoursesStudent record);

    int insertSelective(RefCoursesStudent record);

    List<RefCoursesStudent> selectByExample(RefCoursesStudentExample example);

    int updateByExampleSelective(@Param("record") RefCoursesStudent record, @Param("example") RefCoursesStudentExample example);

    int updateByExample(@Param("record") RefCoursesStudent record, @Param("example") RefCoursesStudentExample example);
}