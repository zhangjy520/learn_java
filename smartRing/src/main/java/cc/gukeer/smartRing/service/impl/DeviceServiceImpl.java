package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.config.ConfigData;
import cc.gukeer.common.service.BasicService;
import cc.gukeer.common.utils.ConstantUtil;
import cc.gukeer.common.utils.DateUtils;
import cc.gukeer.smartRing.common.RStatusType;
import cc.gukeer.smartRing.modelView.CascadingView;
import cc.gukeer.smartRing.persistence.dao.*;
import cc.gukeer.smartRing.persistence.entity.*;
import cc.gukeer.smartRing.persistence.entity.extension.RingView;
import cc.gukeer.smartRing.persistence.entity.extension.StationView;
import cc.gukeer.smartRing.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl extends BasicService implements DeviceService {

    @Autowired
    DeviceRingMapper ringMapper;

    @Autowired
    DeviceStationMapper stationMapper;

    @Autowired
    StationAreaMapper stationAreaMapper;

    @Autowired
    A_DeviceExtensionMapper deviceExtensionMapper;

    @Autowired
    A_ScanLogExtensionMapper scanLogExtensionMapper;

    @Autowired
    ScanLogMapper scanLogMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;
    @Autowired
    A_ClassExtensionMapper a_classExtensionMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    DeviceStationMapper deviceStationMapper;

    @Autowired
    RingMessageMapper ringMessageMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Override
    public DeviceStation findStationByMac(String mac) {

        DeviceStationExample example = new DeviceStationExample();
        example.createCriteria().andStationMacEqualTo(mac);

        List<DeviceStation> stations = stationMapper.selectByExample(example);

        DeviceStation station = null;
        if (null != stations && stations.size() > 0) {
            station = stations.get(0);
        }

        return station;
    }

    @Override
    public DeviceRing findRingByMac(String mac) {

        DeviceRingExample example = new DeviceRingExample();
        example.createCriteria().andMacEqualTo(mac).andDelFlagEqualTo(0);

        List<DeviceRing> rings = ringMapper.selectByExample(example);

        DeviceRing ring = null;
        if (null != rings && rings.size() > 0) {
            ring = rings.get(0);
        }

        return ring;
    }

    @Override
    public int saveStation(DeviceStation station, String optId) {

        int count = 0;

        if (null != station.getId() && !("").equals(station.getId())) {
            DeviceStationExample example = new DeviceStationExample();
            example.createCriteria().andStationMacEqualTo(station.getStationMac());
            station.setUpdateBy(optId);
            station.setUpdateDate(System.currentTimeMillis());
            count = stationMapper.updateByExampleSelective(station, example);
        } else {
            station.setId( ConstantUtil.getPrimaryKey());
            station.setCreateBy(optId);
            station.setCreateDate(System.currentTimeMillis());
            count = stationMapper.insertSelective(station);
        }

        return count;
    }

    @Override
    public int updateStation( DeviceStation station, DeviceStationExample example) {
        int count = 0;
        count = stationMapper.updateByExampleSelective( station, example);
        return count;
    }

    @Override
    public int saveRingByMac(DeviceRing ring) {

        int count = 0;

        if (null != findRingByMac(ring.getMac())) {
            DeviceRingExample example = new DeviceRingExample();
            example.createCriteria().andMacEqualTo(ring.getMac());
            ring.setLastUpdate(System.currentTimeMillis());
            count = ringMapper.updateByExampleSelective(ring, example);
        } else {
            ring.setLastUpdate(System.currentTimeMillis());
            count = ringMapper.insertSelective(ring);
        }
        return count;
    }

    @Override
    public int updateRing(DeviceRing ring) {
        return ringMapper.updateByPrimaryKey(ring);
    }

    @Override
    public List<DeviceRing> selectByExample(DeviceRingExample example) {
        return ringMapper.selectByExample(example);
    }

    @Override
    public List<DeviceRing> selectLastRecord(DeviceRing deviceRing) {
        return deviceExtensionMapper.selectLastRingRecord(deviceRing);
    }

    @Override
    public List<DeviceRing> findRingsByExample(DeviceRingExample deviceRingExample) {
        return ringMapper.selectByExample(deviceRingExample);
    }

    @Override
    public List<DeviceRing> findDistinctByExample(DeviceRingExample deviceRingExample) {
        return deviceExtensionMapper.findDistinctRingsByExample(deviceRingExample);
    }

    @Override
    public PageInfo<DeviceStation> findAllDeviceStaion(int pageNum, int pageSize) {

        DeviceStationExample example = new DeviceStationExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<DeviceStation> list = stationMapper.selectByExample(example);
        PageInfo<DeviceStation> pageInfo = new PageInfo<DeviceStation>(list);

        return pageInfo;
    }

    @Override
    public List<DeviceStation> findStationByExample(DeviceStationExample example) {
        List<DeviceStation> list = stationMapper.selectByExample(example);
        return list;
    }

    @Override
    public int deleteStationByMac(String mac) {

        int count = 0;
        if (null != findStationByMac(mac)) {
            DeviceStation station = new DeviceStation();
            station.setDelFlag(1);
            DeviceStationExample example = new DeviceStationExample();
            example.createCriteria().andStationMacEqualTo(mac);

            count = stationMapper.updateByExampleSelective(station, example);
        }

        return count;
    }

    @Override
    public DeviceRing findRingByNum(int no) {

        DeviceRingExample example = new DeviceRingExample();
        example.createCriteria().andRingNumEqualTo(no);

        List<DeviceRing> rings = ringMapper.selectByExample(example);

        DeviceRing ring = null;
        if (null != rings && rings.size() > 0) {
            ring = rings.get(0);
        }

        return ring;
    }

    @Override
    public int deleteRingByMac(DeviceRing ring) {

        DeviceRingExample example = new DeviceRingExample();
        example.createCriteria().andMacEqualTo(ring.getMac());
        int count = ringMapper.deleteByExample(example);

        if (count > 0) {

            ScanLogExample scanLogExample = new ScanLogExample();
            scanLogExample.createCriteria().andMacEqualTo(ring.getMac());
            int delCount = scanLogMapper.deleteByExample(scanLogExample);
            logger.info("====== delete scanLog with ring mac : {} ======", delCount);
        }

        return count;
    }

    @Override
    public List<DeviceRing> findRingByScreenMac(String screenMac) {

        List<DeviceRing> list = deviceExtensionMapper.findRingListByScreenMac(screenMac);

        return list;
    }

    @Override
    public List<DeviceRing> findEmptyRingByScreenMac(String screenMac) {

        List<DeviceRing> list = deviceExtensionMapper.findEmptyRingByScreenMac(screenMac);

        return list;
    }

    @Override
    public List<ScanLog> finScanLogByExample(ScanLogExample scanLogExample) {

        List<ScanLog> list = scanLogMapper.selectByExample(scanLogExample);

        return list;
    }

    @Override
    public int saveScanLog(ScanLog scanLog) {

        List<ScanLog> logs = scanLogExtensionMapper.selectScanLogOrderByDate(scanLog);
        int count = 0;
        if (null == logs || logs.size() == 0) { // 直接插入

            scanLog.setId(ConstantUtil.getPrimaryKey());
            count = scanLogMapper.insertSelective(scanLog);

        } else { // 根据 固定时间[ConfigData.RING_SCAN_INTERVAL] 更新一次

            ScanLog tmp = logs.get(0);
            long timestamp = tmp.getLastUpdate();
            long minutes = DateUtils.getDeltaInMinutes(timestamp, scanLog.getLastUpdate());
            if (Math.abs(minutes) > ConfigData.RING_SCAN_INTERVAL) { // 超过 固定时间段 重新插入
                scanLog.setId(ConstantUtil.getPrimaryKey());
                count = scanLogMapper.insertSelective(scanLog);
            } else {
                scanLog.setId(tmp.getId());
                count = scanLogMapper.updateByPrimaryKeySelective(scanLog);
            }
        }

        return count;
    }

    //*****

    @Override
    public List<RingView> findAllWhoUsedBySchoolId(String schoolId, String search, Integer status, Integer type) {
        if (search != "%%") {
            return deviceExtensionMapper.findAllRingWhoUsedInner(schoolId, search, status, type);
        } else {
            return deviceExtensionMapper.findAllRingWhoUsedLeft(schoolId, status, type);
        }
    }

    @Override
    public PageInfo<RingView> findRingBySchoolId(String schoolId, String search, Integer status, Integer type, int pageNum, int pageSize) {
        List<RingView> ringViewList = new ArrayList<RingView>();
        PageHelper.startPage( pageNum,pageSize);
//        if (("%%").equals(search)) {
//            ringViewList = deviceExtensionMapper.findAllRingWhoUsedLeft(schoolId, status, type);
//        } else {
//            ringViewList = deviceExtensionMapper.findAllRingWhoUsedInner(schoolId, search, status, type);
//        }
        ringViewList = deviceExtensionMapper.findAllRingWhoUsedInner(schoolId, search, status, type);
        PageInfo<RingView> ringViewPage = new PageInfo<RingView>(ringViewList);
        return ringViewPage;
    }

    /**
     * 挂失、改变状态
     * 0：空闲 1:使用  2：挂失    3：丢失
     * 原来：0:使用  1：挂失 2：丢失    3：空闲
     *
     * @param ringId
     * @param targetStatus
     * @param userId
     * @return
     */
    @Override
    public int report(String ringId, Integer targetStatus, String userId) {
        int count = 0;
        if (StringUtils.isBlank(ringId)) {
            return count;
        }
        DeviceRing ring = ringMapper.selectByPrimaryKey(ringId);
        if (ring == null) {
            return count;
        }

        //将临时手环置于空闲状态时，配对的学生取消
        if (ring.getType() == RStatusType.TYPE_TEMP &&
                (targetStatus == RStatusType.STATUS_UNUSED || targetStatus == RStatusType.STATUS_LOST)) {
            ring.setStudentId(null);
        }

        //将个人手环置于使用中,需确认学生的曾使用的临时手环状态
        // 个人手环，未使用，学生ID存在
        if (ring.getType() == RStatusType.TYPE_PERSONAL && targetStatus == RStatusType.STATUS_USED && StringUtils.isNotBlank(ring.getStudentId())) {
            //学生配对的临时手环
            List<DeviceRing> tempRings = findRingByStudentIdNotStatus(ring.getStudentId(),
                    RStatusType.TYPE_TEMP, RStatusType.STATUS_LOSING);
            if (tempRings.size() > 0) {   //存在则返回-1： 需跳转页面
                return -1;
            }

            //学生配对的个人手环，将其之前的个人手环 置于已丢失状态
            List<DeviceRing> personalRings = findRingByStudentIdNotStatus(ring.getStudentId(),
                    RStatusType.TYPE_PERSONAL, RStatusType.STATUS_LOST);
            if (personalRings.size() > 0) {
                for (DeviceRing deviceRing : personalRings) {
                    if (StringUtils.equals(deviceRing.getId(), ringId)) {
                        ring.setStatus(RStatusType.STATUS_LOST);
                        ring.setUpdateBy(userId);
                        ring.setUpdateDate(Long.valueOf(DateUtils.getCurrentTime()));
                        ringMapper.updateByPrimaryKey(deviceRing);
                    }
                }
            }
        }

        ring.setStatus(targetStatus);
        ring.setUpdateBy(userId);
        ring.setUpdateDate(Long.valueOf(DateUtils.getCurrentTime()));
        count = ringMapper.updateByPrimaryKey(ring);

        return count;
    }

    @Override
    public List<DeviceRing> findRingByStudentIdNotStatus(String studentId, Integer type, Integer notStatus) {  //  type可为空
        DeviceRingExample example = new DeviceRingExample();
        DeviceRingExample.Criteria temp = example.createCriteria();
        temp.andDelFlagEqualTo(0).andStudentIdEqualTo(studentId);

        if (type != null) {
            temp.andTypeEqualTo(type);
        }
        if (notStatus != null) {
            temp.andStatusNotEqualTo(notStatus);
        }

        return ringMapper.selectByExample(example);
    }

    @Override
    public List<DeviceRing> findRingByStudentId(String studentId, Integer type, Integer status) {  //  type可为空
        DeviceRingExample example = new DeviceRingExample();
        DeviceRingExample.Criteria temp = example.createCriteria();
        temp.andDelFlagEqualTo(0).andStudentIdEqualTo(studentId);

        if (type != null) {
            temp.andTypeEqualTo(type);
        }
        if (status != null) {
            temp.andStatusEqualTo(status);
        }

        return ringMapper.selectByExample(example);
    }


    @Override
    public DeviceRing findRingById(String ringId) {
        return ringMapper.selectByPrimaryKey(ringId);
    }

    @Override
    public int saveRing(DeviceRing deviceRing) {
        if (deviceRing == null) return 0;
        if (StringUtils.isBlank(deviceRing.getId())) {
            deviceRing.setId(ConstantUtil.getPrimaryKey());
            return ringMapper.insertSelective(deviceRing);
        } else {
            return ringMapper.updateByPrimaryKeySelective(deviceRing);
        }
    }

    @Override
    public List<DeviceRing> findRingByMacNotLostNotFree(String mac, String schoolId, Integer type) {
        DeviceRingExample example = new DeviceRingExample();
        DeviceRingExample.Criteria criteria = example.createCriteria();
        criteria.andMacEqualTo(mac).andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andStatusNotEqualTo(2).andStatusNotEqualTo(3);
        if (type != null) criteria.andTypeEqualTo(type);
        return ringMapper.selectByExample(example);
    }

    @Override
    public List<DeviceRing> findRingByMacBySchoolId(String mac, String schoolId, Integer type, Integer status) {
        DeviceRingExample example = new DeviceRingExample();
        DeviceRingExample.Criteria criteria = example.createCriteria();
        criteria.andMacEqualTo(mac).andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        if (type != null) criteria.andTypeEqualTo(type);
        if (status != null) criteria.andStatusEqualTo(status);
        return ringMapper.selectByExample(example);
    }

    @Override
    public List<DeviceRing> findRingByMacsNotLost(List<String> macs, String schoolId) {
        DeviceRingExample example = new DeviceRingExample();
        DeviceRingExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andStatusNotEqualTo(2);
        criteria.andMacIn(macs);
        return ringMapper.selectByExample(example);
    }

    @Override
    public List<CascadingView> getCascadingView(String schoolId) {
        List<CascadingView> finalView = new ArrayList<CascadingView>();
//        ClassSectionExample sectionExample = new ClassSectionExample();
//        sectionExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
//        List<ClassSection> allSection = classSectionMapper.selectByExample(sectionExample);
        List<ClassSection> allSection = a_classExtensionMapper.getSessionByGradeClassAndSchoolId( schoolId);
        if (allSection.size() != 0) {
            GradeClassExample gradeClassExample = new GradeClassExample();
            gradeClassExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
            List<GradeClass> allClass = gradeClassMapper.selectByExample(gradeClassExample);
            for (ClassSection classSection : allSection) {
                String xdName = classSection.getName();
                String xd = classSection.getId().toString();
                if (classSection.getSectionYear() > 0) {
                    for (int i = 1; i <= classSection.getSectionYear(); i++) {
                        CascadingView njView = new CascadingView();
                        njView.setId(xd + "_" + i);
                        njView.setName(xdName + ConstantUtil.getGradeNj(i));
                        njView.setPid("-10");
                        finalView.add(njView);
                        if (allClass.size() != 0) {
                            for (GradeClass gradeClass : allClass) {
                                if (StringUtils.equals(gradeClass.getXd(), classSection.getId()) && gradeClass.getNj() == i) {
                                    CascadingView classView = new CascadingView();
                                    classView.setPid(njView.getId());
                                    classView.setId(gradeClass.getId().toString());
                                    classView.setName(gradeClass.getName());
                                    finalView.add(classView);
                                }
                            }
                        }
                    }
                }
            }
        }
        return finalView;
    }

    @Override
    public List<CascadingView> getCascadingViewByTeacherId(String schoolId, String teacherId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId);
        List<TeacherClass> teacherClasses =  teacherClassMapper.selectByExample(example);

        List<String> classIds = new ArrayList<String>();
        for (TeacherClass teacherClass : teacherClasses) {
            classIds.add(teacherClass.getClassId());
        }
        if (classIds.size() == 0) {
            return null;
        }

        List<CascadingView> finalView = new ArrayList<CascadingView>();
        ClassSectionExample sectionExample = new ClassSectionExample();
        sectionExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<ClassSection> allSection = classSectionMapper.selectByExample(sectionExample);
        if (allSection.size() != 0) {
            GradeClassExample gradeClassExample = new GradeClassExample();
            gradeClassExample.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andIdIn(classIds);
            List<GradeClass> allClass = gradeClassMapper.selectByExample(gradeClassExample);
            for (ClassSection classSection : allSection) {
                String xdName = classSection.getName();
                String xd = classSection.getId().toString();
                if (classSection.getSectionYear() > 0) {
                    for (int i = 1; i <= classSection.getSectionYear(); i++) {
                        CascadingView njView = new CascadingView();
                        njView.setId(xd + "_" + i);
                        njView.setName(xdName + ConstantUtil.getGradeNj(i));
                        njView.setPid("-10");
//                        finalView.add(njView);
                        boolean flag = false;
                        if (allClass.size() != 0) {
                            for (GradeClass gradeClass : allClass) {
                                if (StringUtils.equals(gradeClass.getXd(), classSection.getId()) && gradeClass.getNj() == i) {
                                    CascadingView classView = new CascadingView();
                                    classView.setPid(njView.getId());
                                    classView.setId(gradeClass.getId().toString());
                                    classView.setName(gradeClass.getName());
                                    finalView.add(classView);
                                    flag = true;
                                }
                            }
                            if (flag == true) {
                                finalView.add(njView);
                            }
                        }
                    }
                }
            }
        }
        return finalView;
    }

    @Override
    public List<StationView> findStationBySchoolId(String schoolId, Integer status){

        List<StationView> stationViews = deviceExtensionMapper.findAllStationBySchoolId(schoolId, status);
        for ( StationView stationView:stationViews) {
            if (null != stationView.getClassId() && !("").equals(stationView.getClassId())) {
                stationView.setClassName( ConstantUtil.getValueByKeyAndFlag( Integer.parseInt(stationView.getXd()), "xd")+
                        ConstantUtil.getValueByKeyAndFlag( stationView.getNj(),"nj")+stationView.getBj());

            }
        }
        return stationViews;
    }


    @Override
    public PageInfo<StationView> findStationBySchoolId(String schoolId, Integer status, int pageNum, int pageSize){

        PageHelper.startPage( pageNum, pageSize);
        List<StationView> stationViews = deviceExtensionMapper.findAllStationBySchoolId(schoolId, status);
        for ( StationView stationView:stationViews) {
            if (null != stationView.getClassId() && !("").equals(stationView.getClassId())) {
                stationView.setClassName( ConstantUtil.getValueByKeyAndFlag( Integer.parseInt(stationView.getXd()), "xd")+
                        ConstantUtil.getValueByKeyAndFlag( stationView.getNj(),"nj")+stationView.getBj());

            }
        }
        PageInfo<StationView> stationViewPage = new PageInfo<StationView>( stationViews);
        return stationViewPage;
    }

    @Override
    public List<StationArea> findAreaBySchoolId(String schoolId) {
        StationAreaExample example = new StationAreaExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        return stationAreaMapper.selectByExample(example);
    }

    @Override
    public StationArea findAreaById( String id) {
        return stationAreaMapper.selectByPrimaryKey( id);
    }

    @Override
    public int findAreaByName( String areaName, String schoolId) {

        StationAreaExample example = new StationAreaExample();
        example.createCriteria().andDelFlagEqualTo( 0).andSchoolIdEqualTo(schoolId).andAreaNameEqualTo( areaName);
        List< StationArea> areaList = stationAreaMapper.selectByExample( example);
        int count = areaList.size();
        return count;
    }

    @Override
    public StationArea saveArea( StationArea stationArea) {
        if ( null != stationArea.getId() && !("").equals( stationArea.getId())) {
            stationAreaMapper.updateByPrimaryKeySelective( stationArea);
        } else {
            stationArea.setId( ConstantUtil.getPrimaryKey());
            stationAreaMapper.insertSelective( stationArea);
        }
        return stationArea;
    }

    @Override
    public List<DeviceRing> findRingByStationMac(String stationMac) {
        DeviceRingExample example = new DeviceRingExample();
        example.createCriteria().andDelFlagEqualTo(0).andStatusEqualTo(0).andStationMacEqualTo(stationMac);
        return ringMapper.selectByExample(example);
    }

    @Override
    public int saveRingMessage(RingMessage ringMessage) {
        if (ringMessage == null) {
            return 0;
        }
        DeviceRing ring = findRingByMac(ringMessage.getRingMac());
        if (ring == null || StringUtils.isBlank(ring.getStudentId())) {
            return 0;
        }

        ringMessage.setStudentId(ring.getStudentId());

        long startTime = DateUtils.dayStartTimestamp(ringMessage.getInfoDate());
        long endTime = DateUtils.dayEndTimestamp(ringMessage.getInfoDate());

        RingMessageExample example = new RingMessageExample();
        RingMessageExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0).andRingMacEqualTo(ringMessage.getRingMac()).andStudentIdEqualTo(ring.getStudentId());
        criteria.andInfoDateBetween(startTime, endTime);

        List<RingMessage> existMessage = ringMessageMapper.selectByExample(example);
        if (existMessage.size() > 0) {
            ringMessage.setId(existMessage.get(0).getId());
            return ringMessageMapper.updateByPrimaryKeySelective(ringMessage);
        } else {
            ringMessage.setId(ConstantUtil.getPrimaryKey());
            ringMessage.setCreateDate(Long.valueOf(DateUtils.getCurrentTime()));
            return ringMessageMapper.insertSelective(ringMessage);
        }
    }

    /**
     * 按照分页获取手环数据
     *
     * @param schoolId
     * @param pageSize
     * @param timeStamp
     * @return
     */
    @Override
    public PageInfo ringList(String schoolId, Integer pageSize,String timeStamp) {

        DeviceRingExample example = new DeviceRingExample();
        example.setOrderByClause("last_update desc");

        DeviceRingExample.Criteria criteria = example.createCriteria();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);

        if(!StringUtils.isEmpty(timeStamp)){
            criteria.andLastUpdateGreaterThan(Long.valueOf(timeStamp));
        }

        PageHelper.startPage(0, pageSize);
        List<DeviceRing> ringList = ringMapper.selectByExample(example);
        if (ringList.size() <= 0) {
            return null;
        } else {
            PageInfo<DeviceRing> pageInfo = new PageInfo<DeviceRing>(ringList);
            return pageInfo;
        }
    }

    /**
     * 按照分页获取基站数据
     *
     * @param schoolId
     * @param pageSize
     * @param timeStamp
     * @return
     */
    @Override
    public PageInfo stationList(String schoolId, Integer pageSize, String timeStamp) {
        DeviceRingExample example = new DeviceRingExample();
        example.setOrderByClause("last_update desc");

        DeviceRingExample.Criteria criteria = example.createCriteria();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);

        if(!StringUtils.isEmpty(timeStamp)){
            criteria.andLastUpdateGreaterThan(Long.valueOf(timeStamp));
        }

        PageHelper.startPage(0, pageSize);
        List<DeviceRing> ringList = ringMapper.selectByExample(example);
        if (ringList.size() <= 0) {
            return null;
        } else {
            PageInfo<DeviceRing> pageInfo = new PageInfo<DeviceRing>(ringList);
            return pageInfo;
        }
    }


}
