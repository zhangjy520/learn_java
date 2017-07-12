package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseNode;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCourseNodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCourseNodeMapper {
    int countByExample(ChangeStateCourseNodeExample example);

    int deleteByExample(ChangeStateCourseNodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCourseNode record);

    int insertSelective(ChangeStateCourseNode record);

    List<ChangeStateCourseNode> selectByExample(ChangeStateCourseNodeExample example);

    ChangeStateCourseNode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCourseNode record, @Param("example") ChangeStateCourseNodeExample example);

    int updateByExample(@Param("record") ChangeStateCourseNode record, @Param("example") ChangeStateCourseNodeExample example);

    int updateByPrimaryKeySelective(ChangeStateCourseNode record);

    int updateByPrimaryKey(ChangeStateCourseNode record);
}