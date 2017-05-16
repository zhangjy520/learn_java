package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.persistence.entity.PlatformExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformMapper {
    int countByExample(PlatformExample example);

    int deleteByExample(PlatformExample example);

    int deleteByPrimaryKey(String id);

    int insert(Platform record);

    int insertSelective(Platform record);

    List<Platform> selectByExampleWithBLOBs(PlatformExample example);

    List<Platform> selectByExample(PlatformExample example);

    Platform selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Platform record, @Param("example") PlatformExample example);

    int updateByExampleWithBLOBs(@Param("record") Platform record, @Param("example") PlatformExample example);

    int updateByExample(@Param("record") Platform record, @Param("example") PlatformExample example);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKeyWithBLOBs(Platform record);

    int updateByPrimaryKey(Platform record);
}