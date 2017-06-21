package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCourse;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCourseMapper {
    int countByExample(ChangeStateCourseExample example);

    int deleteByExample(ChangeStateCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCourse record);

    int insertSelective(ChangeStateCourse record);

    List<ChangeStateCourse> selectByExample(ChangeStateCourseExample example);

    ChangeStateCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCourse record, @Param("example") ChangeStateCourseExample example);

    int updateByExample(@Param("record") ChangeStateCourse record, @Param("example") ChangeStateCourseExample example);

    int updateByPrimaryKeySelective(ChangeStateCourse record);

    int updateByPrimaryKey(ChangeStateCourse record);
}