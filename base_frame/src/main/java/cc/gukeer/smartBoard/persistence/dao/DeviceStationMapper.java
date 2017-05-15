package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.DeviceStation;
import cc.gukeer.smartBoard.persistence.entity.DeviceStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceStationMapper {
    int deleteByExample(DeviceStationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceStation record);

    int insertSelective(DeviceStation record);

    List<DeviceStation> selectByExample(DeviceStationExample example);

    DeviceStation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceStation record, @Param("example") DeviceStationExample example);

    int updateByExample(@Param("record") DeviceStation record, @Param("example") DeviceStationExample example);

    int updateByPrimaryKeySelective(DeviceStation record);

    int updateByPrimaryKey(DeviceStation record);
}