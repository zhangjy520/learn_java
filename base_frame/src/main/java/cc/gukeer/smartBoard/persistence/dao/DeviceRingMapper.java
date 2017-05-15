package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.DeviceRing;
import cc.gukeer.smartBoard.persistence.entity.DeviceRingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceRingMapper {
    int deleteByExample(DeviceRingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceRing record);

    int insertSelective(DeviceRing record);

    List<DeviceRing> selectByExample(DeviceRingExample example);

    DeviceRing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceRing record, @Param("example") DeviceRingExample example);

    int updateByExample(@Param("record") DeviceRing record, @Param("example") DeviceRingExample example);

    int updateByPrimaryKeySelective(DeviceRing record);

    int updateByPrimaryKey(DeviceRing record);
}