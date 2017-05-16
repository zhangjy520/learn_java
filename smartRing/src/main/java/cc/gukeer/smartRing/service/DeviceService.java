package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.modelView.CascadingView;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.persistence.entity.extension.RingView;
import cc.gukeer.smartRing.persistence.entity.extension.StationView;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeviceService {
    DeviceStation findStationByMac(String mac);

    DeviceRing findRingByMac(String mac);

    int saveStation(DeviceStation station, String optId);

    int updateStation( DeviceStation station, DeviceStationExample example);

    int saveRingByMac(DeviceRing ring);

    int updateRing(DeviceRing ring);

    List<DeviceRing> selectByExample(DeviceRingExample example);

    List<DeviceRing> selectLastRecord(DeviceRing deviceRing);

    List<DeviceRing> findRingsByExample(DeviceRingExample deviceRingExample);

    List<DeviceRing> findDistinctByExample(DeviceRingExample deviceRingExample);

    PageInfo<DeviceStation> findAllDeviceStaion(int pageNum, int pageSize);

    List<DeviceStation> findStationByExample(DeviceStationExample example);

    int deleteStationByMac(String mac);

    DeviceRing findRingByNum(int no);

    int deleteRingByMac(DeviceRing ring);

    List<DeviceRing> findRingByScreenMac(String screenMac);

    List<DeviceRing> findEmptyRingByScreenMac(String screenMac);

    int saveScanLog(ScanLog scanLog);

    List<ScanLog> finScanLogByExample(ScanLogExample scanLogExample);

    //  2016/12/9
    List<RingView> findAllWhoUsedBySchoolId(String schoolId, String search, Integer status, Integer type);

    PageInfo<RingView> findRingBySchoolId(String schoolId, String search, Integer status, Integer type, int pageNum, int pageSize);

    int report(String ringId, Integer targetStatus, String userId);

    List<DeviceRing> findRingByStudentIdNotStatus(String studentId, Integer type, Integer notStatus);

    List<DeviceRing> findRingByStudentId(String studentId, Integer type, Integer status);

    DeviceRing findRingById(String ringId);

    int saveRing(DeviceRing deviceRing);

    List<DeviceRing> findRingByMacNotLostNotFree(String mac, String schoolId, Integer type);

    List<DeviceRing> findRingByMacBySchoolId(String mac, String schoolId, Integer type, Integer status);

    List<DeviceRing> findRingByMacsNotLost(List<String> macs, String schoolId);

    List<CascadingView> getCascadingView(String schoolId);

    List<CascadingView> getCascadingViewByTeacherId(String schoolId, String teacherId);

    List<StationView> findStationBySchoolId(String schoolId, Integer status);

    PageInfo<StationView> findStationBySchoolId(String schoolId, Integer status, int pageNum, int pageSize);

    List<StationArea> findAreaBySchoolId(String schoolId);

    StationArea findAreaById( String id);

    int findAreaByName( String areaName, String schoolId);

    StationArea saveArea( StationArea stationArea);

    List<DeviceRing> findRingByStationMac(String stationMac);

    int saveRingMessage(RingMessage ringMessage);

    PageInfo ringList(String schoolId, Integer pageSize,String timeStamp);
    PageInfo stationList(String schoolId, Integer pageSize,String timeStamp);

}
