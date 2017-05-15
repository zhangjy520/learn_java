package cc.gukeer.datahub.persistence.dao;

import cc.gukeer.datahub.persistence.entity.RefPlatformApp;
import cc.gukeer.datahub.persistence.entity.RefPlatformAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefPlatformAppMapper {
    int countByExample(RefPlatformAppExample example);

    int deleteByExample(RefPlatformAppExample example);

    int deleteByPrimaryKey(String id);

    int insert(RefPlatformApp record);

    int insertSelective(RefPlatformApp record);

    List<RefPlatformApp> selectByExample(RefPlatformAppExample example);

    RefPlatformApp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RefPlatformApp record, @Param("example") RefPlatformAppExample example);

    int updateByExample(@Param("record") RefPlatformApp record, @Param("example") RefPlatformAppExample example);

    int updateByPrimaryKeySelective(RefPlatformApp record);

    int updateByPrimaryKey(RefPlatformApp record);
}