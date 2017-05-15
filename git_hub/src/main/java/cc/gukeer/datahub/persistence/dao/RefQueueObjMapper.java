package cc.gukeer.datahub.persistence.dao;

import cc.gukeer.datahub.persistence.entity.RefQueueObj;
import cc.gukeer.datahub.persistence.entity.RefQueueObjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefQueueObjMapper {
    int countByExample(RefQueueObjExample example);

    int deleteByExample(RefQueueObjExample example);

    int deleteByPrimaryKey(String id);

    int insert(RefQueueObj record);

    int insertSelective(RefQueueObj record);

    List<RefQueueObj> selectByExample(RefQueueObjExample example);

    RefQueueObj selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RefQueueObj record, @Param("example") RefQueueObjExample example);

    int updateByExample(@Param("record") RefQueueObj record, @Param("example") RefQueueObjExample example);

    int updateByPrimaryKeySelective(RefQueueObj record);

    int updateByPrimaryKey(RefQueueObj record);
}