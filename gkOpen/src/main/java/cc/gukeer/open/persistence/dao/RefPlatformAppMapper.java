package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.RefPlatformApp;
import cc.gukeer.open.persistence.entity.RefPlatformAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefPlatformAppMapper {
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