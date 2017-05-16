package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.StationArea;
import cc.gukeer.smartRing.persistence.entity.StationAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationAreaMapper {
    int deleteByExample(StationAreaExample example);

    int deleteByPrimaryKey(String id);

    int insert(StationArea record);

    int insertSelective(StationArea record);

    List<StationArea> selectByExample(StationAreaExample example);

    StationArea selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StationArea record, @Param("example") StationAreaExample example);

    int updateByExample(@Param("record") StationArea record, @Param("example") StationAreaExample example);

    int updateByPrimaryKeySelective(StationArea record);

    int updateByPrimaryKey(StationArea record);
}