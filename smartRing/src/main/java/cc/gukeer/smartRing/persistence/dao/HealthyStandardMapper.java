package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.HealthyStandard;
import cc.gukeer.smartRing.persistence.entity.HealthyStandardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HealthyStandardMapper {
    int deleteByExample(HealthyStandardExample example);

    int deleteByPrimaryKey(String id);

    int insert(HealthyStandard record);

    int insertSelective(HealthyStandard record);

    List<HealthyStandard> selectByExample(HealthyStandardExample example);

    HealthyStandard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HealthyStandard record, @Param("example") HealthyStandardExample example);

    int updateByExample(@Param("record") HealthyStandard record, @Param("example") HealthyStandardExample example);

    int updateByPrimaryKeySelective(HealthyStandard record);

    int updateByPrimaryKey(HealthyStandard record);
}