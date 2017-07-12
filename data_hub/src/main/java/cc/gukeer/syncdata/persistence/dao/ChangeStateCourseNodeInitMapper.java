package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseNodeInit;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseNodeInitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCourseNodeInitMapper {
    int countByExample(ChangeStateCourseNodeInitExample example);

    int deleteByExample(ChangeStateCourseNodeInitExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCourseNodeInit record);

    int insertSelective(ChangeStateCourseNodeInit record);

    List<ChangeStateCourseNodeInit> selectByExample(ChangeStateCourseNodeInitExample example);

    ChangeStateCourseNodeInit selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCourseNodeInit record, @Param("example") ChangeStateCourseNodeInitExample example);

    int updateByExample(@Param("record") ChangeStateCourseNodeInit record, @Param("example") ChangeStateCourseNodeInitExample example);

    int updateByPrimaryKeySelective(ChangeStateCourseNodeInit record);

    int updateByPrimaryKey(ChangeStateCourseNodeInit record);
}