package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.Upgrade;
import cc.gukeer.open.persistence.entity.UpgradeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpgradeMapper {
    int deleteByExample(UpgradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Upgrade record);

    int insertSelective(Upgrade record);

    List<Upgrade> selectByExample(UpgradeExample example);

    Upgrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Upgrade record, @Param("example") UpgradeExample example);

    int updateByExample(@Param("record") Upgrade record, @Param("example") UpgradeExample example);

    int updateByPrimaryKeySelective(Upgrade record);

    int updateByPrimaryKey(Upgrade record);
}