package cc.gukeer.syncdata.service.push;


import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.datahub.persistence.dao.*;
import cc.gukeer.datahub.persistence.entity.*;
import cc.gukeer.syncdata.dataDefinition.EventData;
import cc.gukeer.syncdata.dataDefinition.EventType;
import cc.gukeer.syncdata.modeView.ObjDetail;
import cc.gukeer.syncdata.modeView.ObjInfo;
import cc.gukeer.syncdata.persistence.dao.*;
import cc.gukeer.syncdata.persistence.entity.*;
import cc.gukeer.syncdata.util.Gziputil;
import cc.gukeer.syncdata.util.LineToCameUtil;
import cc.gukeer.syncdata.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lx on 2017/2/7.
 */
@Service
public class SyncMian {
    @Autowired
    SyncBaseMapper syncBaseMapper;
    @Autowired
    AppMapper appMapper;
    @Autowired
    RefPlatformAppMapper refPlatformAppMapper;
    @Autowired
    PlatformMapper platformMapper;
    @Autowired
    RefQueueObjMapper refQueueObjMapper;
    @Autowired
    PushObjMapper pushObjMapper;
    @Autowired
    DetailObjMapper detailObjMapper;
    @Autowired
    DetailObjColumnMapper detailObjColumnMapper;
    @Autowired
    RouteOtherMapper routeOtherMapper;
    @Autowired
    RoutePatriarchMapper routePatriarchMapper;
    @Autowired
    A_RoutePatriarchMapper a_routePatriarchMapper;
    @Autowired
    RouteStudentMapper routeStudentMapper;
    @Autowired
    RouteTeacherMapper routeTeacherMapper;
    @Autowired
    RouteTeacherClassMapper routeTeacherClassMapper;
    @Autowired
    A_RouteStudentMapper a_routeStudentMapper;
    @Autowired
    A_RouteTeacherMapper a_routeTeacherMapper;
    @Autowired
    A_RouteTeacherClassMapper a_routeTeacherClassMapper;
    @Autowired
    A_RouteOtherMapper a_routeOtherMapper;

    //入口方法
    //需要加事务
    public void execute() {
        Set<List<RefPlatformApp>> parms = getAppPushParms(null);
        Iterator<List<RefPlatformApp>> iterator = parms.iterator();
        while (iterator.hasNext()) {
            List<RefPlatformApp> appParms = iterator.next();
            for (RefPlatformApp appParm : appParms) {
                String source = platformMapper.selectByPrimaryKey(appParm.getPlatformId()).getIdentity();
                String appId = appParm.getAppId();
                String refPlatAppId = appParm.getId();

                //获取推送表id
                List<ObjInfo> objInfos = getTableNamesByRef(refPlatAppId);
                if (objInfos == null) {
                    continue;
                }
//                List<String> tableNames = getTableNames(schema, tableNamePrefix);
                for (ObjInfo objInfo : objInfos) {
                    //查询数据
                    String pushId = objInfo.getPushObjId();
                    PushObj pushObj = pushObjMapper.selectByPrimaryKey(pushId);
                    String tableName = pushObj.getObjTableName();
                    String __mark = appParm.getQueues();
                    String mark = "";
                    List<ObjDetail> objDetails = objInfo.getObjDetail();
                    for (ObjDetail objDetail : objDetails) {
                        if (objDetail.getObjNmae() != null) {
                            mark = __mark + "." + objDetail.getObjNmae();
                        }
                        //获取数据
                        List<Map<String, Object>> datas = getDatas(tableName, mark, source, objInfo);

                        if (datas.size() == 0) {
                            continue;
                        }
                        //获取数据的id集合
                        List<String> ids = new ArrayList<>();

                        if (!tableName.equals("change_state_ref_teacher_class")){
                            for (Map<String, Object> data : datas) {
                                if (data.get("syncId") != null)
                                    ids.add(data.get("syncId").toString());
                            }
                        }



                        //序列化数据
                        Map<String, Object> datasJson = formatDatas(datas, tableName);

                        //推送数据
                        try {
                            if (!datasJson.get("insert").equals("false")) {
                                pushdata(appId, datasJson.get("insert"), appParm.getQueues(), "High");
                            }
                            if (!datasJson.get("modify").equals("false")) {
                                pushdata(appId, datasJson.get("modify"), appParm.getQueues(), "normal");
                            }
                            if (!datasJson.get("delete").equals("false")) {
                                pushdata(appId, datasJson.get("delete"), appParm.getQueues(), "normal");
                            }
                        } catch (Exception e) {
                            //如果失败，则不标记同步过的数据，直接返回
                            break;
                        }
                        //标记已经同步过的数据
                        try {
                            //可以优化
                            if (tableName.equals("change_state_user_patriarch")) {
                                List<RoutePatriarch> list = new ArrayList<RoutePatriarch>();
                                for (String id : ids) {
                                    RoutePatriarch routePatriarch = new RoutePatriarch();
                                    routePatriarch.setId(PrimaryKey.get());
                                    routePatriarch.setSyncId(id);
                                    routePatriarch.setSyncDelFlag(mark);
                                    list.add(routePatriarch);
                                }
                                a_routePatriarchMapper.insertBatch(list);
                            } else if (tableName.equals("change_state_user_student")) {
                                List<RouteStudent> list = new ArrayList<RouteStudent>();
                                for (String id : ids) {
                                    RouteStudent routeStudent = new RouteStudent();
                                    routeStudent.setId(PrimaryKey.get());
                                    routeStudent.setSyncId(id);
                                    routeStudent.setSyncDelFlag(mark);
                                    list.add(routeStudent);
                                }
                                a_routeStudentMapper.insertBatch(list);

                            } else if (tableName.equals("change_state_user_teacher")) {
                                List<RouteTeacher> list = new ArrayList<RouteTeacher>();
                                for (String id : ids) {
                                    RouteTeacher routeTeacher = new RouteTeacher();
                                    routeTeacher.setId(PrimaryKey.get());
                                    routeTeacher.setSyncId(id);
                                    routeTeacher.setSyncDelFlag(mark);
                                    list.add(routeTeacher);
                                }
                                a_routeTeacherMapper.insertBatch(list);
                            } else if (tableName.equals("change_state_ref_teacher_class")) {
                                List<RouteTeacherClass> list = new ArrayList<RouteTeacherClass>();
                                for (Map<String, Object> data : datas) {
                                    RouteTeacherClass routeTeacherClass = new RouteTeacherClass();
                                    routeTeacherClass.setId(PrimaryKey.get());
                                    routeTeacherClass.setClassId(data.get("syncClassId").toString());
                                    routeTeacherClass.setTeacherId(data.get("syncTeacherId").toString());
                                    routeTeacherClass.setSyncDelFlag(mark);
                                    list.add(routeTeacherClass);
                                }
                                a_routeTeacherClassMapper.insertBatch(list);
                            } else {
                                List<RouteOther> list = new ArrayList<RouteOther>();
                                for (String id : ids) {
                                    RouteOther routeOther = new RouteOther();
                                    routeOther.setId(PrimaryKey.get());
                                    routeOther.setSyncId(id);
                                    routeOther.setSyncDelFlag(mark);
                                    list.add(routeOther);
                                }
                                a_routeOtherMapper.insertBatch(list);
                            }
                            //标记数据的逻辑
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    }
                }
            }
        }
    }

    //初始化时使用
    public void init(String _id) {
        Set<List<RefPlatformApp>> parms = getAppPushParms(_id);
        Iterator<List<RefPlatformApp>> iterator = parms.iterator();
        while (iterator.hasNext()) {
            List<RefPlatformApp> appParms = iterator.next();
            for (RefPlatformApp appParm : appParms) {
                String source = platformMapper.selectByPrimaryKey(appParm.getPlatformId()).getIdentity();
                String appId = appParm.getAppId();
                String refPlatAppId = appParm.getId();

                //获取推送表id
                List<ObjInfo> objInfos = getTableNamesByRef(refPlatAppId);
                if (objInfos == null) {
                    continue;
                }
//                List<String> tableNames = getTableNames(schema, tableNamePrefix);
                for (ObjInfo objInfo : objInfos) {
                    //查询数据
                    String pushId = objInfo.getPushObjId();
                    PushObj pushObj = pushObjMapper.selectByPrimaryKey(pushId);
                    String tableName = pushObj.getObjTableName();
                    String __mark = appParm.getQueues();
                    String mark = "";
                    List<ObjDetail> objDetails = objInfo.getObjDetail();
                    for (ObjDetail objDetail : objDetails) {
                        if (objDetail.getObjNmae() != null) {
                            mark = __mark + "." + objDetail.getObjNmae();
                        }
                        int pageNum = 0;
                        int pageSize = 10;
                        while (true) {
                            List<Map<String, Object>> datas = getInitDatas(tableName, source, objInfo, pageNum, pageSize);
                            pageNum = pageNum + pageSize;
                            //获取数据的id集合
                            List<String> ids = new ArrayList<>();
                            for (Map<String, Object> data : datas) {
                                ids.add(data.get("syncId").toString());
                            }
                            if (datas.size() == 0) {
                                continue;
                            }
                            //序列化数据
                            Map<String, Object> datasJson = formatDatas(datas, tableName);
                            //推送数据
                            try {
                                if (!datasJson.get("insert").equals("false")) {
                                    pushdata(appId, datasJson.get("insert"), appParm.getQueues(), "High");
                                }
                                if (!datasJson.get("modify").equals("false")) {
                                    pushdata(appId, datasJson.get("modify"), appParm.getQueues(), "normal");
                                }
                                if (!datasJson.get("delete").equals("false")) {
                                    pushdata(appId, datasJson.get("delete"), appParm.getQueues(), "normal");
                                }
                            } catch (Exception e) {
                                //如果失败，则不标记同步过的数据，直接返回
                                break;
                            }
                            //标记已经同步过的数据
                            try {
                                //可以优化批量****
                                if (tableName.equals("change_state_user_patriarch")) {
                                    List<RoutePatriarch> list = new ArrayList<RoutePatriarch>();
                                    for (String id : ids) {
                                        RoutePatriarch routePatriarch = new RoutePatriarch();
                                        routePatriarch.setId(PrimaryKey.get());
                                        routePatriarch.setSyncId(id);
                                        routePatriarch.setSyncDelFlag(mark);
                                        list.add(routePatriarch);
                                    }
                                    a_routePatriarchMapper.insertBatch(list);
                                } else if (tableName.equals("change_state_user_student")) {
                                    List<RouteStudent> list = new ArrayList<RouteStudent>();
                                    for (String id : ids) {
                                        RouteStudent routeStudent = new RouteStudent();
                                        routeStudent.setId(PrimaryKey.get());
                                        routeStudent.setSyncId(id);
                                        routeStudent.setSyncDelFlag(mark);
                                        list.add(routeStudent);
                                    }
                                    a_routeStudentMapper.insertBatch(list);

                                } else if (tableName.equals("change_state_user_teacher")) {
                                    List<RouteTeacher> list = new ArrayList<RouteTeacher>();
                                    for (String id : ids) {
                                        RouteTeacher routeTeacher = new RouteTeacher();
                                        routeTeacher.setId(PrimaryKey.get());
                                        routeTeacher.setSyncId(id);
                                        routeTeacher.setSyncDelFlag(mark);
                                        list.add(routeTeacher);
                                    }
                                    a_routeTeacherMapper.insertBatch(list);
                                } else if (tableName.equals("change_state_ref_teacher_class")) {
                                    List<RouteTeacherClass> list = new ArrayList<RouteTeacherClass>();
                                    for (Map<String, Object> data : datas) {
                                        RouteTeacherClass routeTeacherClass = new RouteTeacherClass();
                                        routeTeacherClass.setId(PrimaryKey.get());
                                        routeTeacherClass.setClassId(data.get("syncClassId").toString());
                                        routeTeacherClass.setTeacherId(data.get("syncTeacherId").toString());
                                        routeTeacherClass.setSyncDelFlag(mark);
                                        list.add(routeTeacherClass);
                                    }
                                    a_routeTeacherClassMapper.insertBatch(list);
                                } else {
                                    List<RouteOther> list = new ArrayList<RouteOther>();
                                    for (String id : ids) {
                                        RouteOther routeOther = new RouteOther();
                                        routeOther.setId(PrimaryKey.get());
                                        routeOther.setSyncId(id);
                                        routeOther.setSyncDelFlag(mark);
                                        list.add(routeOther);
                                    }
                                    a_routeOtherMapper.insertBatch(list);
                                }
                                //如果数据量少于2000 则退出
                                if (datas.size() < pageSize - 1) {
                                    break;
                                }
                                //标记数据的逻辑
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
    }

    //获取需要同步的表名
    public List<String> getTableNames(String schema, String tableNamePrefix) {
        List<String> tableNames = syncBaseMapper.getTableNames(schema, tableNamePrefix);
        return tableNames;
    }

    public List<ObjInfo> getTableNamesByRef(String refPlatAppId) {
        RefQueueObjExample refQueueObjExample = new RefQueueObjExample();
        refQueueObjExample.createCriteria().andPlatAppIdEqualTo(refPlatAppId);
        List<RefQueueObj> refQueueObj = refQueueObjMapper.selectByExample(refQueueObjExample);
        if (refQueueObj.size() == 0) {
            return null;
        }
        List<String> detailObjIds = new ArrayList<>();
        //遍历该队列和对象的关系
        for (RefQueueObj queueObj : refQueueObj) {
            detailObjIds.add(queueObj.getObjId());
        }
        DetailObjExample detailObjExample = new DetailObjExample();
        detailObjExample.createCriteria().andIdIn(detailObjIds);
        List<DetailObj> detailObjs = detailObjMapper.selectByExample(detailObjExample);
        if (detailObjs == null || detailObjIds.size() == 0) {
            return null;
        }
        //已选对象下的表的id集合
        Set<String> pushObjIds = new HashSet<>();
        for (DetailObj detailObj : detailObjs) {
            pushObjIds.add(detailObj.getPushObjId());
        }
        //表和对象的树形结构
        List<ObjInfo> objInfoList = new ArrayList<>();
        for (String pushObjId : pushObjIds) {
            ObjInfo objInfo = new ObjInfo();
            objInfo.setPushObjId(pushObjId);
            List<ObjDetail> objDetails = new ArrayList<>();
            for (DetailObj detailObj : detailObjs) {
                ObjDetail objDetail = new ObjDetail();
                if (pushObjId.equals(detailObj.getPushObjId())) {
                    objDetail.setObjId(detailObj.getId());
                    objDetail.setObjNmae(detailObj.getName());
                    String detailObjId = detailObj.getId();
                    List<String> columnsNames = syncBaseMapper.getColumns(detailObjId);
                    objDetail.setColumnNames(columnsNames);
                    objDetails.add(objDetail);
                }
            }
            objInfo.setObjDetail(objDetails);
            objInfoList.add(objInfo);
        }


        return objInfoList;
    }


    //根据表名获取数据
    public List<Map<String, Object>> getDatas(String tableName, String mark, String source, ObjInfo objInfo) {
        //********需要优化 直接查出sync的集合
        List<String> ids = new ArrayList<>();
        List<RouteTeacherClass> routeTeacherClasses = null;
        if (tableName.equals("change_state_user_student")) {
            ids = a_routeStudentMapper.selectId(mark);
        } else if (tableName.equals("change_state_user_teacher")) {
            ids = a_routeTeacherMapper.selectId(mark);
        } else if (tableName.equals("change_state_user_patriarch")) {
            ids = a_routePatriarchMapper.selectId(mark);
            //先不用管
        } else if (tableName.equals("change_state_ref_teacher_class")) {
            //ids = null;
        } else {
            ids = a_routeOtherMapper.selectId(mark);
        }
        List<Map<String, Object>> _datas = null;
        if (ids == null || ids.size() == 0) {
            ids.add("init");
            if (tableName.equals("change_state_ref_teacher_class")) {
                _datas = syncBaseMapper.getRefData(tableName, source, mark);
            } else {
                _datas = syncBaseMapper.getDatas(tableName, ids, source);
            }

        } else {
            _datas = syncBaseMapper.getDatas(tableName, ids, source);
        }
        List<Map<String, Object>> datas = new ArrayList<>();
        for (Map<String, Object> data : _datas) {
            Map<String, Object> columnMap = new HashMap<>();
            Set<String> keySet = data.keySet();
            Iterator<String> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                String _column = keyIterator.next();
                String column = LineToCameUtil.underlineToCamel2(_column);
                List<ObjDetail> objDetails = objInfo.getObjDetail();
                for (ObjDetail objDetail : objDetails) {
                    List<String> columns = objDetail.getColumnNames();
                    for (String s : columns) {
                        if (s.equals(_column)) {
                            columnMap.put(column, data.get(_column));
                        }
                    }
                }
            }
            columnMap.put("syncId", data.get("sync_id"));
            columnMap.put("event", data.get("event"));
            datas.add(columnMap);
        }
        return datas;
    }

    //init时根据表名获取数据
    public List<Map<String, Object>> getInitDatas(String tableName, String source, ObjInfo objInfo, int pageNum, int pageSize) {
        //********需要优化 直接查出sync的集合
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> _datas = syncBaseMapper.getInitDatas(tableName, source);
        List<Map<String, Object>> datas = new ArrayList<>();
        for (Map<String, Object> data : _datas) {
            Map<String, Object> columnMap = new HashMap<>();
            Set<String> keySet = data.keySet();
            Iterator<String> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                String _column = keyIterator.next();
                String column = LineToCameUtil.underlineToCamel2(_column);
                List<ObjDetail> objDetails = objInfo.getObjDetail();
                for (ObjDetail objDetail : objDetails) {
                    List<String> columns = objDetail.getColumnNames();
                    for (String s : columns) {
                        if (s.equals(column)) {
                            columnMap.put(column, data.get(_column));
                        }
                    }
                }
            }
            columnMap.put("syncId", data.get("sync_id"));
            columnMap.put("event", data.get("event"));
            datas.add(columnMap);
        }
        return datas;
    }

    //序列化数据
    public Map<String, Object> formatDatas(List<Map<String, Object>> datas, String tableName) {
        //封装插入数据
        List<Map<String, Object>> insertDatas = new ArrayList<>();
        //封装修改数据
        List<Map<String, Object>> modifyDatas = new ArrayList<>();
        //封装删除数据
        List<Map<String, Object>> deleteDatas = new ArrayList<>();

        //将datas按照事件拆分为三种不同的list
        for (Map<String, Object> data : datas) {
            data.remove("source");
            data.remove("syncDelFlag");
            data.remove("syncDate");
            if (data.get("event").equals(EventType.INSERT.name())) {
                data.remove("event");
                insertDatas.add(data);
                continue;
            }
            if (data.get("event").equals(EventType.MODIFY.name())) {
                data.remove("event");
                modifyDatas.add(data);
                continue;
            }
            if (data.get("event").equals(EventType.DELETE.name())) {
                data.remove("event");
                deleteDatas.add(data);
                continue;
            }
        }
        Map<String, Object> datasJson = new HashMap<>();
        //封装插入数据
        if (insertDatas.size() != 0) {
            EventData insertEventData = new EventData();
            insertEventData.setDataList(insertDatas);
            insertEventData.setObjectKey(tableName);
            insertEventData.setEvent(EventType.INSERT);
            String insertDatasJson = eventDataToJson(insertEventData);
            datasJson.put("insert", insertDatasJson);
        } else {
            datasJson.put("insert", "false");
        }
        //封装修改数据
        if (modifyDatas.size() != 0) {
            EventData modifyEventData = new EventData();
            modifyEventData.setDataList(modifyDatas);
            modifyEventData.setObjectKey(tableName);
            modifyEventData.setEvent(EventType.MODIFY);
            String modifyDatasJson = eventDataToJson(modifyEventData);
            datasJson.put("modify", modifyDatasJson);
        } else {
            datasJson.put("modify", "false");
        }
        // 封装删除数据
        if (deleteDatas.size() != 0) {
            EventData deleteEventData = new EventData();
            deleteEventData.setDataList(deleteDatas);
            deleteEventData.setObjectKey(tableName);
            deleteEventData.setEvent(EventType.DELETE);
            String deleteDatasJson = eventDataToJson(deleteEventData);
            datasJson.put("delete", deleteDatasJson);
        } else {
            datasJson.put("delete", "false");
        }
        return datasJson;
    }

    //转json
    public String eventDataToJson(EventData eventData) {
       /* Gson gson = new GsonBuilder().disableHtmlEscaping().create();*/
        Gson gson = new Gson();
        String datasJson = gson.toJson(eventData);
        return datasJson;
    }

    //标记已经同步过的数据
    public void identityDeletion(String tableName, String mark, List<String> ids, String notLikeMark) {
        syncBaseMapper.identityDeletion(tableName, mark, ids, notLikeMark);
    }

    //转化时间
    public String makeTime(Long time) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String timeString = fmt.format(time);
        return timeString;
    }

    //推送数据
    public String pushdata(String appId, Object messageContent, String queuesName, String Priority) throws IOException {
        App app = appMapper.selectByPrimaryKey(appId);
        String AGENT = queuesName;
        String ACCESSKEY = app.getAppSecret();
        String property = "userId:1";
        Map<String, Object> generateSignParams = new HashMap<String, Object>();
        generateSignParams.put("agent", AGENT);
        /*generateSignParams.put("accesskey", ACCESSKEY);*/
        generateSignParams.put("rand", RandomStringUtils.randomAlphanumeric(8));
        generateSignParams.put("time", new Date().getTime());
        generateSignParams.put("data", messageContent);
        //生成签名
        String sign = MD5Util.go(AGENT + ACCESSKEY + generateSignParams.get("rand").toString() + messageContent.toString() + generateSignParams.get("time").toString());
        System.out.println("签名是+++++++++++" + sign);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rand", generateSignParams.get("rand"));
        params.put("sign", sign);
        params.put("time", generateSignParams.get("time"));
        params.put("property", property);
        params.put("agent", generateSignParams.get("agent"));
        byte[] messageContentZip = Gziputil.compress(messageContent.toString());
        params.put("datas", messageContentZip);
        if (Priority.equals("High")) {
            sendPriorityHigh(params, queuesName);
        } else {
            send(params, queuesName);
        }
        return null;
    }

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendPriorityHigh(final Map<String, Object> message, String queuesName) {
        jmsTemplate.convertAndSend(queuesName, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                message.setJMSCorrelationID("gkpltaform");
                message.setJMSPriority(9);
                return message;
            }
        });
    }

    public void send(final Map<String, Object> message, String queuesName) {
        jmsTemplate.convertAndSend(queuesName, message, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                message.setJMSCorrelationID("gkpltaform");
                message.setJMSPriority(3);
                return message;
            }
        });
    }

    public Set<List<RefPlatformApp>> getAppPushParms(String id) {
        Set<List<RefPlatformApp>> parms = new HashSet<>();
        RefPlatformAppExample refPlatExample = new RefPlatformAppExample();
        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        if (id != null) {
            refPlatExample.createCriteria().andIdEqualTo(id);
        } else {
            refPlatExample.createCriteria().andAppStatusIn(statusList).andOptStatusEqualTo(1).andSyncStatusEqualTo(1).andDataStatusEqualTo(1);
        }
        List<RefPlatformApp> refPlatformApps = refPlatformAppMapper.selectByExample(refPlatExample);

        parms.add(refPlatformApps);
        return parms;
    }

}
