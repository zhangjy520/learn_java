package cc.gukeer.support.update.persistence.dao;

import cc.gukeer.support.update.persistence.entity.Upgrade;
import cc.gukeer.support.update.persistence.entity.UpgradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpgradeMapper {
    int deleteByExample(UpgradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Upgrade record);

    int insertSelective(Upgrade record);

    List<Upgrade> selectByExample(UpgradeExample example);

    Upgrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Upgrade record, @Param("example") UpgradeExample example);

    int updateByExample(@Param("record") Upgrade record, @Param("example") UpgradeExample example);

    int updateByPrimaryKeySelective(Upgrade record);

    int updateByPrimaryKey(Upgrade record);
}