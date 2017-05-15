package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.DeviceRing;
import cc.gukeer.smartBoard.persistence.entity.DeviceRingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by conn on 16-10-11.
 */
public interface A_DeviceExtensionMapper {

    List<DeviceRing> selectLastRingRecord(DeviceRing deviceRing);

    List<DeviceRing> findDistinctRingsByExample(DeviceRingExample deviceRingExample);

    List<DeviceRing> findRingListByScreenMac(@Param("screenMac") String screenMac);

    List<DeviceRing> findEmptyRingByScreenMac(@Param("screenMac") String screenMac);
}
