package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseType;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCourseTypeMapper {
    int countByExample(ChangeStateCourseTypeExample example);

    int deleteByExample(ChangeStateCourseTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCourseType record);

    int insertSelective(ChangeStateCourseType record);

    List<ChangeStateCourseType> selectByExample(ChangeStateCourseTypeExample example);

    ChangeStateCourseType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCourseType record, @Param("example") ChangeStateCourseTypeExample example);

    int updateByExample(@Param("record") ChangeStateCourseType record, @Param("example") ChangeStateCourseTypeExample example);

    int updateByPrimaryKeySelective(ChangeStateCourseType record);

    int updateByPrimaryKey(ChangeStateCourseType record);
}