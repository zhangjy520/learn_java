package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.MarkTime;
import cc.gukeer.syncdata.persistence.entity.MarkTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarkTimeMapper {
    int deleteByExample(MarkTimeExample example);

    int deleteByPrimaryKey(String id);

    int insert(MarkTime record);

    int insertSelective(MarkTime record);

    List<MarkTime> selectByExample(MarkTimeExample example);

    MarkTime selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MarkTime record, @Param("example") MarkTimeExample example);

    int updateByExample(@Param("record") MarkTime record, @Param("example") MarkTimeExample example);

    int updateByPrimaryKeySelective(MarkTime record);

    int updateByPrimaryKey(MarkTime record);
}