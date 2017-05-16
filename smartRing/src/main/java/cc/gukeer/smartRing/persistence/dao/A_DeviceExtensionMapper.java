package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.DeviceRing;
import cc.gukeer.smartRing.persistence.entity.DeviceRingExample;
import cc.gukeer.smartRing.persistence.entity.extension.RingView;
import cc.gukeer.smartRing.persistence.entity.extension.StationView;
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

    List<RingView> findAllRingWhoUsedLeft(@Param("schoolId") String schoolId,@Param("status") Integer status,@Param("type") Integer type);

    List<RingView> findAllRingWhoUsedInner(@Param("schoolId") String schoolId,@Param("search") String search,@Param("status") Integer status,@Param("type") Integer type);

    List<StationView> findAllStationBySchoolId(@Param("schoolId") String schoolId,@Param("status") Integer status);
}
