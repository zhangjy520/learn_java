package cc.gukeer.datahub.persistence.dao;

import cc.gukeer.datahub.persistence.entity.PushObj;
import cc.gukeer.datahub.persistence.entity.PushObjExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PushObjMapper {
    int countByExample(PushObjExample example);

    int deleteByExample(PushObjExample example);

    int deleteByPrimaryKey(String id);

    int insert(PushObj record);

    int insertSelective(PushObj record);

    List<PushObj> selectByExample(PushObjExample example);

    PushObj selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PushObj record, @Param("example") PushObjExample example);

    int updateByExample(@Param("record") PushObj record, @Param("example") PushObjExample example);

    int updateByPrimaryKeySelective(PushObj record);

    int updateByPrimaryKey(PushObj record);


}