package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateCycle;
import cc.gukeer.syncdata.persistence.entity.ChangeStateCycleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateCycleMapper {
    int countByExample(ChangeStateCycleExample example);

    int deleteByExample(ChangeStateCycleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateCycle record);

    int insertSelective(ChangeStateCycle record);

    List<ChangeStateCycle> selectByExample(ChangeStateCycleExample example);

    ChangeStateCycle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateCycle record, @Param("example") ChangeStateCycleExample example);

    int updateByExample(@Param("record") ChangeStateCycle record, @Param("example") ChangeStateCycleExample example);

    int updateByPrimaryKeySelective(ChangeStateCycle record);

    int updateByPrimaryKey(ChangeStateCycle record);
}