package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.DeviceStation;
import cc.gukeer.smartRing.persistence.entity.DeviceStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceStationMapper {
    int deleteByExample(DeviceStationExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeviceStation record);

    int insertSelective(DeviceStation record);

    List<DeviceStation> selectByExample(DeviceStationExample example);

    DeviceStation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeviceStation record, @Param("example") DeviceStationExample example);

    int updateByExample(@Param("record") DeviceStation record, @Param("example") DeviceStationExample example);

    int updateByPrimaryKeySelective(DeviceStation record);

    int updateByPrimaryKey(DeviceStation record);
}