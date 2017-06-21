package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateStandardCourse;
import cc.gukeer.syncdata.persistence.entity.ChangeStateStandardCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateStandardCourseMapper {
    int countByExample(ChangeStateStandardCourseExample example);

    int deleteByExample(ChangeStateStandardCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateStandardCourse record);

    int insertSelective(ChangeStateStandardCourse record);

    List<ChangeStateStandardCourse> selectByExample(ChangeStateStandardCourseExample example);

    ChangeStateStandardCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateStandardCourse record, @Param("example") ChangeStateStandardCourseExample example);

    int updateByExample(@Param("record") ChangeStateStandardCourse record, @Param("example") ChangeStateStandardCourseExample example);

    int updateByPrimaryKeySelective(ChangeStateStandardCourse record);

    int updateByPrimaryKey(ChangeStateStandardCourse record);
}