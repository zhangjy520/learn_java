package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.SportClass;
import cc.gukeer.smartRing.persistence.entity.SportClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SportClassMapper {
    int deleteByExample(SportClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(SportClass record);

    int insertSelective(SportClass record);

    List<SportClass> selectByExample(SportClassExample example);

    SportClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SportClass record, @Param("example") SportClassExample example);

    int updateByExample(@Param("record") SportClass record, @Param("example") SportClassExample example);

    int updateByPrimaryKeySelective(SportClass record);

    int updateByPrimaryKey(SportClass record);
}