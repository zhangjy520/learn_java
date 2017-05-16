package cc.gukeer.syncdata.service.pull;

import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.datahub.persistence.dao.PlatformMapper;
import cc.gukeer.datahub.persistence.entity.Platform;
import cc.gukeer.datahub.persistence.entity.PlatformExample;
import cc.gukeer.syncdata.dataDefinition.EventData;
import cc.gukeer.syncdata.persistence.dao.*;
import cc.gukeer.syncdata.persistence.entity.*;
import cc.gukeer.syncdata.util.MD5Util;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;
import java.util.*;

@Service
public class QueueMessageListener implements SessionAwareMessageListener, Serializable {
    @Autowired
    ChangeStateRefTeacherMapper changeStateRefTeacherMapper;
    @Autowired
    ChangeStateTitleMapper changeStateTitleMapper;
    @Autowired
    ChangeStateDepartmentMapper changeStateDepartmentMapper;
    @Autowired
    ChangeStateUserMapper changeStateUserMapper;
    @Autowired
    ChangeStateTeacherMapper changeStateTeacherMapper;
    @Autowired
    ChangeStateStudentMapper changeStateStudentMapper;
    @Autowired
    ChangeStateGradeMapper changeStateGradeMapper;
    @Autowired
    ChangeStatePatriarchMapper changeStatePatriarchMapper;
    @Autowired
    ChangeStateSchoolTypeMapper changeStateSchoolTypeMapper;
    @Autowired
    ChangeStateSectionMapper changeStateSectionMapper;
    @Autowired
    ChangeStateSchoolMapper changeStateSchoolMapper;
    @Autowired
    A_ChangeStateDepartmentMapper a_changeStateDepartmentMapper;
    @Autowired
    A_ChangeStateTitleMapper a_changeStateTitleMapper;
    @Autowired
    A_ChangeStateSectionMapper a_changeStateSectionMapper;
    @Autowired
    A_ChangeStateGradeMapper a_changeStateGradeMapper;
    @Autowired
    A_ChangeStateRefTeacherMapper a_changeStateRefTeacherMapper;
    @Autowired
    A_ChangeStateSchoolMapper a_changeStateSchoolMapper;
    @Autowired
    A_ChangeStatePatriarchMapper a_changeStatePatriarchMapper;
    @Autowired
    A_ChangeStateSchoolTypeMapper a_changeStateSchoolTypeMapper;
    @Autowired
    A_ChangeStateStudentMapper a_changeStateStudentMapper;
    @Autowired
    A_ChangeStateTeacherMapper a_changeStateTeacherMapper;
    @Autowired
    A_ChangeStateUserMapper a_changeStateUserMapper;
    @Autowired
    A_MarkTimeMapper a_markTimeMapper;
    @Autowired
    MarkTimeMapper markTimeMapper;
    @Autowired
    RouteOtherMapper routeOtherMapper;
    @Autowired
    RoutePatriarchMapper routePatriarchMapper;
    @Autowired
    RouteStudentMapper routeStudentMapper;
    @Autowired
    RouteTeacherMapper routeTeacherMapper;
    @Autowired
    RouteTeacherClassMapper routeTeacherClassMapper;
    @Autowired
    PlatformMapper platformMapper;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        Destination destination;
        try {
            if (message instanceof MapMessage) {
                MapMessage tm = (MapMessage) message;
                Enumeration<String> MapNames = tm.getMapNames();
                while (MapNames.hasMoreElements()) {
                    //获取map的key
                    String mapName = MapNames.nextElement();
                    System.out.print("接收到实时同步来的数据" + mapName);
                }
                String datas = (String) tm.getObject("datas");
                String rand = (String) tm.getObject("rand");
                Long time = tm.getLong("time");
                String AGENT = tm.getString("agent");
                String ACCESSKEY = "";
                PlatformExample platformExample = new PlatformExample();
                platformExample.createCriteria().andIdentityEqualTo(AGENT);
                List<Platform> platform = platformMapper.selectByExample(platformExample);
                if (platform.size()>0 && platform!=null){
                    ACCESSKEY = platform.get(0).getPassword();
                }
                String sign = MD5Util.go(AGENT + ACCESSKEY + rand.toString() + datas.toString() + time.toString());
                String _sign = (String) tm.getObject("sign");
                if (sign.equals((String) tm.getObject("sign"))) {
                    Gson gson = new Gson();
                    EventData<?> eventObj = gson.fromJson(datas, EventData.class);
                    String objectKey = eventObj.getObjectKey();
                    if (objectKey.equals("sync_grade_class")) {
                        //将json字符串转化成List<JavaBean>对象//解析字符串
                        Gson gson1 = new Gson();
                        List<ChangeStateGrade> changeStateGradeList = gson1.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateGrade>>() {
                                }.getType());
                        List<ChangeStateGrade> changeStateGrades = new LinkedList<ChangeStateGrade>();
                        List<String> deteleflags = new ArrayList<>();
                        //设置参数存入新list集合
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateGradeList != null) {
                                for (ChangeStateGrade changeStateGrade : changeStateGradeList) {
                                    changeStateGrade.setId(PrimaryKey.get());
                                    changeStateGrade.setUpdateDate(new Date().getTime());
                                    changeStateGrade.setSource(AGENT);
                                    changeStateGrades.add(changeStateGrade);
                                    deteleflags.add(changeStateGrade.getSyncId());
                                }
                            }
                            a_changeStateGradeMapper.insertBatch(changeStateGrades);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateGrade> id = a_changeStateGradeMapper.checkBysyncId();
                            List<ChangeStateGrade> syncid = a_changeStateGradeMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateGradeMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateGradeList != null) {
                                for (ChangeStateGrade changeStateGrade : changeStateGradeList) {
                                    changeStateGrade.setUpdateDate(new Date().getTime());
                                    changeStateGrade.setSource(AGENT);
                                    changeStateGrade.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateGrade.getSyncId());
                                    int count = a_changeStateGradeMapper.checkById(changeStateGrade.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateGradeExample changeStateGradeExample = new ChangeStateGradeExample();
                                        changeStateGradeExample.createCriteria().andSyncIdEqualTo(changeStateGrade.getSyncId());
                                        changeStateGradeMapper.updateByExample(changeStateGrade, changeStateGradeExample);
                                    } else {
                                        changeStateGradeMapper.insert(changeStateGrade);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_patriarch")) {
                        Gson gson2 = new Gson();
                        List<ChangeStatePatriarch> changeStatePatriarchList = gson2.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStatePatriarch>>() {
                                }.getType());
                        List<ChangeStatePatriarch> changeStatePatriarches = new LinkedList<ChangeStatePatriarch>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStatePatriarchList != null) {
                                for (ChangeStatePatriarch changeStatePatriarch : changeStatePatriarchList) {
                                    changeStatePatriarch.setId(PrimaryKey.get());
                                    changeStatePatriarch.setUpdateDate(new Date().getTime());
                                    changeStatePatriarch.setSource(AGENT);
                                    changeStatePatriarches.add(changeStatePatriarch);
                                    deteleflags.add(changeStatePatriarch.getSyncId());
                                }
                            }
                            a_changeStatePatriarchMapper.insertBatch(changeStatePatriarches);

                            //初始化路由表
                            RoutePatriarchExample routePatriarchExample = new RoutePatriarchExample();
                            routePatriarchExample.createCriteria().andSyncIdIn(deteleflags);
                            routePatriarchMapper.deleteByExample(routePatriarchExample);
                            List<ChangeStatePatriarch> id = a_changeStatePatriarchMapper.checkBysyncId();
                            List<ChangeStatePatriarch> syncid = a_changeStatePatriarchMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStatePatriarchMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStatePatriarchList != null) {
                                for (ChangeStatePatriarch changeStatePatriarch : changeStatePatriarchList) {
                                    changeStatePatriarch.setUpdateDate(new Date().getTime());
                                    changeStatePatriarch.setSource(AGENT);
                                    changeStatePatriarch.setId(PrimaryKey.get());
                                    deteleflags.add(changeStatePatriarch.getSyncId());
                                    int count = a_changeStatePatriarchMapper.checkById(changeStatePatriarch.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStatePatriarchExample changeStatePatriarchExample = new ChangeStatePatriarchExample();
                                        changeStatePatriarchExample.createCriteria().andSyncIdEqualTo(changeStatePatriarch.getSyncId());
                                        changeStatePatriarchMapper.updateByExample(changeStatePatriarch, changeStatePatriarchExample);
                                    } else {
                                        changeStatePatriarchMapper.insert(changeStatePatriarch);
                                    }
                                }
                                //初始化路由表
                                RoutePatriarchExample routePatriarchExample = new RoutePatriarchExample();
                                routePatriarchExample.createCriteria().andSyncIdIn(deteleflags);
                                routePatriarchMapper.deleteByExample(routePatriarchExample);
                            }
                        }
                    } else if (objectKey.equals("sync_teacher_class")) {
                        Gson gson3 = new Gson();
                        List<ChangeStateRefTeacher> changeStateRefTeacherList = gson3.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateRefTeacher>>() {
                                }.getType());
                        List<ChangeStateRefTeacher> changeStateRefTeachers = new LinkedList<ChangeStateRefTeacher>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateRefTeacherList != null) {
                                for (ChangeStateRefTeacher changeStateRefTeacher : changeStateRefTeacherList) {
                                    ChangeStateRefTeacher changeStateRefTeacher1 = changeStateRefTeacher;
                                    changeStateRefTeacher.setId(PrimaryKey.get());
                                    changeStateRefTeacher.setUpdateDate(new Date().getTime());
                                    changeStateRefTeacher.setSource(AGENT);
                                    changeStateRefTeachers.add(changeStateRefTeacher);
                                    //初始化路由表
                                    RouteTeacherClassExample routeTeacherClassExample = new RouteTeacherClassExample();
                                    routeTeacherClassExample.createCriteria().andClassIdEqualTo(changeStateRefTeacher.getSyncClassId())
                                            .andTeacherIdEqualTo(changeStateRefTeacher.getSyncTeacherId());
                                    routeTeacherClassMapper.deleteByExample(routeTeacherClassExample);
                                }
                            }
                            a_changeStateRefTeacherMapper.insertBatch(changeStateRefTeachers);

                            List<ChangeStateRefTeacher> id = a_changeStateRefTeacherMapper.checkBysyncId();
                            List<ChangeStateRefTeacher> teacherId = a_changeStateRefTeacherMapper.checkTeacherId();
                            List<ChangeStateRefTeacher> classId = a_changeStateRefTeacherMapper.checkClassId();
                            if (id.size() > 0 && teacherId.size() > 0 && classId.size() > 0) {
                                a_changeStateRefTeacherMapper.deleteBatch(id, teacherId, classId);
                            }
                        } else {
                            if (changeStateRefTeacherList != null) {
                                for (ChangeStateRefTeacher changeStateRefTeacher : changeStateRefTeacherList) {
                                    changeStateRefTeacher.setUpdateDate(new Date().getTime());
                                    changeStateRefTeacher.setSource(AGENT);
                                    changeStateRefTeacher.setId(PrimaryKey.get());
                                    int count = a_changeStateRefTeacherMapper.checkById(changeStateRefTeacher.getSyncTeacherId(), AGENT, changeStateRefTeacher.getSyncClassId());
                                    if (count > 0) {
                                        ChangeStateRefTeacherExample changeStateRefTeacherExample = new ChangeStateRefTeacherExample();
                                        changeStateRefTeacherExample.createCriteria()
                                                .andSyncClassIdEqualTo(changeStateRefTeacher.getSyncClassId())
                                                .andSyncTeacherIdEqualTo(changeStateRefTeacher.getSyncTeacherId());
                                        changeStateRefTeacherMapper.updateByPrimaryKey(changeStateRefTeacher);
                                        //初始化路由表
                                        RouteTeacherClassExample routeTeacherClassExample = new RouteTeacherClassExample();
                                        routeTeacherClassExample.createCriteria().andClassIdEqualTo(changeStateRefTeacher.getSyncClassId())
                                                .andTeacherIdEqualTo(changeStateRefTeacher.getSyncTeacherId());
                                        routeTeacherClassMapper.deleteByExample(routeTeacherClassExample);
                                    } else {
                                        changeStateRefTeacherMapper.insert(changeStateRefTeacher);
                                        //初始化路由表
                                        RouteTeacherClassExample routeTeacherClassExample = new RouteTeacherClassExample();
                                        routeTeacherClassExample.createCriteria().andClassIdEqualTo(changeStateRefTeacher.getSyncClassId())
                                                .andTeacherIdEqualTo(changeStateRefTeacher.getSyncTeacherId());
                                        routeTeacherClassMapper.deleteByExample(routeTeacherClassExample);
                                    }
                                }
                            }
                        }
                    } else if (objectKey.equals("sync_org_school")) {
                        Gson gson4 = new Gson();
                        List<ChangeStateSchool> changeStateSchoolList = gson4.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateSchool>>() {
                                }.getType());
                        List<ChangeStateSchool> changeStateSchools = new LinkedList<ChangeStateSchool>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateSchoolList != null) {
                                for (ChangeStateSchool changeStateSchool : changeStateSchoolList) {
                                    changeStateSchool.setId(PrimaryKey.get());
                                    changeStateSchool.setUpdateDate(new Date().getTime());
                                    changeStateSchool.setSource(AGENT);
                                    changeStateSchools.add(changeStateSchool);
                                    deteleflags.add(changeStateSchool.getSyncId());
                                }
                            }
                            a_changeStateSchoolMapper.insertBatch(changeStateSchools);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateSchool> id = a_changeStateSchoolMapper.checkBysyncId();
                            List<ChangeStateSchool> syncid = a_changeStateSchoolMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateSchoolMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateSchoolList != null) {
                                for (ChangeStateSchool changeStateSchool : changeStateSchoolList) {
                                    changeStateSchool.setUpdateDate(new Date().getTime());
                                    changeStateSchool.setSource(AGENT);
                                    changeStateSchool.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateSchool.getSyncId());
                                    int count = a_changeStateSchoolMapper.checkById(changeStateSchool.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateSchoolExample changeStateSchoolExample = new ChangeStateSchoolExample();
                                        changeStateSchoolExample.createCriteria().andSyncIdEqualTo(changeStateSchool.getSyncId());
                                        changeStateSchoolMapper.updateByExample(changeStateSchool, changeStateSchoolExample);
                                    } else {
                                        changeStateSchoolMapper.insert(changeStateSchool);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_school_type")) {
                        Gson gson5 = new Gson();
                        List<ChangeStateSchoolType> changeStateSchoolTypeList = gson5.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateSchoolType>>() {
                                }.getType());
                        List<ChangeStateSchoolType> changeStateSchoolTypes = new LinkedList<ChangeStateSchoolType>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateSchoolTypeList != null) {
                                for (ChangeStateSchoolType changeStateSchoolType : changeStateSchoolTypeList) {
                                    changeStateSchoolType.setId(PrimaryKey.get());
                                    changeStateSchoolType.setUpdateDate(new Date().getTime());
                                    changeStateSchoolType.setSource(AGENT);
                                    changeStateSchoolTypes.add(changeStateSchoolType);
                                    deteleflags.add(changeStateSchoolType.getSyncId());
                                }
                            }
                            a_changeStateSchoolTypeMapper.insertBatch(changeStateSchoolTypes);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateSchoolType> id = a_changeStateSchoolTypeMapper.checkBysyncId();
                            List<ChangeStateSchoolType> syncid = a_changeStateSchoolTypeMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateSchoolTypeMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateSchoolTypeList != null) {
                                for (ChangeStateSchoolType changeStateSchoolType : changeStateSchoolTypeList) {
                                    changeStateSchoolType.setUpdateDate(new Date().getTime());
                                    changeStateSchoolType.setSource(AGENT);
                                    changeStateSchoolType.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateSchoolType.getSyncId());
                                    int count = a_changeStateSchoolTypeMapper.checkById(changeStateSchoolType.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateSchoolTypeExample changeStateSchoolTypeExample = new ChangeStateSchoolTypeExample();
                                        changeStateSchoolTypeExample.createCriteria().andSyncIdEqualTo(changeStateSchoolType.getSyncId());
                                        changeStateSchoolTypeMapper.updateByExample(changeStateSchoolType, changeStateSchoolTypeExample);

                                    } else {
                                        changeStateSchoolTypeMapper.insert(changeStateSchoolType);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_class_section")) {
                        Gson gson6 = new Gson();
                        List<ChangeStateSection> changeStateSectionList = gson6.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateSection>>() {
                                }.getType());
                        List<ChangeStateSection> changeStateSections = new LinkedList<ChangeStateSection>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateSectionList != null) {
                                for (ChangeStateSection changeStateSection : changeStateSectionList) {
                                    changeStateSection.setId(PrimaryKey.get());
                                    changeStateSection.setUpdateDate(new Date().getTime());
                                    changeStateSection.setSource(AGENT);
                                    changeStateSections.add(changeStateSection);
                                    deteleflags.add(changeStateSection.getSyncId());
                                }
                            }
                            a_changeStateSectionMapper.insertBatch(changeStateSections);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateSection> id = a_changeStateSectionMapper.checkBysyncId();
                            List<ChangeStateSection> syncid = a_changeStateSectionMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateSectionMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateSectionList != null) {
                                for (ChangeStateSection changeStateSection : changeStateSectionList) {
                                    changeStateSection.setUpdateDate(new Date().getTime());
                                    changeStateSection.setSource(AGENT);
                                    changeStateSection.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateSection.getSyncId());
                                    int count = a_changeStateSectionMapper.checkById(changeStateSection.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateSectionExample changeStateSectionExample = new ChangeStateSectionExample();
                                        changeStateSectionExample.createCriteria().andSyncIdEqualTo(changeStateSection.getSyncId());
                                        changeStateSectionMapper.updateByExample(changeStateSection, changeStateSectionExample);
                                    } else {
                                        changeStateSectionMapper.insert(changeStateSection);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                        }
                        }
                    } else if (objectKey.equals("sync_student")) {
                        Gson gson7 = new Gson();
                        List<ChangeStateStudent> changeStateStudentList = gson7.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateStudent>>() {
                                }.getType());
                        List<ChangeStateStudent> changeStateStudents = new LinkedList<ChangeStateStudent>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateStudentList != null) {
                                for (ChangeStateStudent changeStateStudent : changeStateStudentList) {
                                    changeStateStudent.setId(PrimaryKey.get());
                                    changeStateStudent.setUpdateDate(new Date().getTime());
                                    changeStateStudent.setSource(AGENT);
                                    changeStateStudents.add(changeStateStudent);
                                    deteleflags.add(changeStateStudent.getSyncId());
                                }
                            }
                            a_changeStateStudentMapper.insertBatch(changeStateStudents);

                            //初始化路由表
                            RouteStudentExample routeStudentExample = new RouteStudentExample();
                            routeStudentExample.createCriteria().andSyncIdIn(deteleflags);
                            routeStudentMapper.deleteByExample(routeStudentExample);
                            List<ChangeStateStudent> id = a_changeStateStudentMapper.checkBysyncId();
                            List<ChangeStateStudent> syncid = a_changeStateStudentMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateStudentMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateStudentList != null) {
                                for (ChangeStateStudent changeStateStudent : changeStateStudentList) {
                                    changeStateStudent.setUpdateDate(new Date().getTime());
                                    changeStateStudent.setSource(AGENT);
                                    changeStateStudent.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateStudent.getSyncId());
                                    int count = a_changeStateStudentMapper.checkById(changeStateStudent.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateStudentExample changeStateStudentExample = new ChangeStateStudentExample();
                                        changeStateStudentExample.createCriteria().andSyncIdEqualTo(changeStateStudent.getSyncId());

                                        changeStateStudentMapper.updateByExample(changeStateStudent, changeStateStudentExample);
                                    } else {

                                        changeStateStudentMapper.insert(changeStateStudent);
                                    }
                                }
                                //初始化路由表
                                RouteStudentExample routeStudentExample = new RouteStudentExample();
                                routeStudentExample.createCriteria().andSyncIdIn(deteleflags);
                                routeStudentMapper.deleteByExample(routeStudentExample);
                            }
                        }
                    } else if (objectKey.equals("sync_teacher")) {
                        Gson gson8 = new Gson();
                        List<ChangeStateTeacher> changeStateTeacherList = gson8.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateTeacher>>() {
                                }.getType());
                        List<ChangeStateTeacher> changeStateTeachers = new LinkedList<ChangeStateTeacher>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateTeacherList != null) {
                                for (ChangeStateTeacher changeStateTeacher : changeStateTeacherList) {
                                    changeStateTeacher.setId(PrimaryKey.get());
                                    changeStateTeacher.setUpdateDate(new Date().getTime());
                                    changeStateTeacher.setSource(AGENT);
                                    changeStateTeachers.add(changeStateTeacher);
                                    deteleflags.add(changeStateTeacher.getSyncId());
                                }
                            }
                            a_changeStateTeacherMapper.insertBatch(changeStateTeachers);

                            //初始化路由表
                            RouteTeacherExample routeTeacherExample = new RouteTeacherExample();
                            routeTeacherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeTeacherMapper.deleteByExample(routeTeacherExample);

                            List<ChangeStateTeacher> id = a_changeStateTeacherMapper.checkBysyncId();
                            List<ChangeStateTeacher> syncid = a_changeStateTeacherMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateTeacherMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateTeacherList != null) {
                                for (ChangeStateTeacher changeStateTeacher : changeStateTeacherList) {
                                    changeStateTeacher.setUpdateDate(new Date().getTime());
                                    changeStateTeacher.setSource(AGENT);
                                    changeStateTeacher.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateTeacher.getSyncId());
                                    int count = a_changeStateTeacherMapper.checkById(changeStateTeacher.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateTeacherExample changeStateTeacherExample = new ChangeStateTeacherExample();
                                        changeStateTeacherExample.createCriteria().andSyncIdEqualTo(changeStateTeacher.getSyncId());
                                        changeStateTeacherMapper.updateByExample(changeStateTeacher, changeStateTeacherExample);
                                    } else {
                                        changeStateTeacherMapper.insert(changeStateTeacher);
                                    }
                                }
                                //初始化路由表
                                RouteTeacherExample routeTeacherExample = new RouteTeacherExample();
                                routeTeacherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeTeacherMapper.deleteByExample(routeTeacherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_user")) {
                        Gson gson9 = new Gson();
                        List<ChangeStateUser> changeStateUserList = gson9.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateUser>>() {
                                }.getType());
                        List<ChangeStateUser> changeStateUsers = new LinkedList<ChangeStateUser>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateUserList != null) {
                                for (ChangeStateUser changeStateUser : changeStateUserList) {
                                    changeStateUser.setId(PrimaryKey.get());
                                    changeStateUser.setUpdateDate(new Date().getTime());
                                    changeStateUser.setSource(AGENT);
                                    changeStateUsers.add(changeStateUser);
                                    deteleflags.add(changeStateUser.getSyncId());
                                }
                            }
                            a_changeStateUserMapper.insertBatch(changeStateUsers);
                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateUser> id = a_changeStateUserMapper.checkBysyncId();
                            List<ChangeStateUser> syncid = a_changeStateUserMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateUserMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateUserList != null) {
                                for (ChangeStateUser changeStateUser : changeStateUserList) {
                                    changeStateUser.setUpdateDate(new Date().getTime());
                                    changeStateUser.setSource(AGENT);
                                    changeStateUser.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateUser.getSyncId());
                                    int count = a_changeStateUserMapper.checkById(changeStateUser.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateUserExample changeStateUserExample = new ChangeStateUserExample();
                                        changeStateUserExample.createCriteria().andSyncIdEqualTo(changeStateUser.getSyncId());
                                        changeStateUserMapper.updateByExample(changeStateUser, changeStateUserExample);
                                    } else {

                                        changeStateUserMapper.insert(changeStateUser);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_department")) {
                        Gson gson10 = new Gson();
                        List<ChangeStateDepartment> changeStateDepartmentList = gson10.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateDepartment>>() {
                                }.getType());
                        List<ChangeStateDepartment> changeStateDepartments = new LinkedList<ChangeStateDepartment>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateDepartmentList != null) {
                                for (ChangeStateDepartment changeStateDepartment : changeStateDepartmentList) {
                                    changeStateDepartment.setId(PrimaryKey.get());
                                    changeStateDepartment.setUpdateDate(new Date().getTime());
                                    changeStateDepartment.setSource(AGENT);
                                    changeStateDepartments.add(changeStateDepartment);
                                    deteleflags.add(changeStateDepartment.getSyncId());
                                }
                            }
                            a_changeStateDepartmentMapper.insertBatch(changeStateDepartments);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateDepartment> id = a_changeStateDepartmentMapper.checkBysyncId();
                            List<ChangeStateDepartment> syncid = a_changeStateDepartmentMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateDepartmentMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateDepartmentList != null) {
                                for (ChangeStateDepartment changeStateDepartment : changeStateDepartmentList) {
                                    changeStateDepartment.setUpdateDate(new Date().getTime());
                                    changeStateDepartment.setSource(AGENT);
                                    changeStateDepartment.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateDepartment.getSyncId());
                                    int count = a_changeStateDepartmentMapper.checkById(changeStateDepartment.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateDepartmentExample changeStateDepartmentExample = new ChangeStateDepartmentExample();
                                        changeStateDepartmentExample.createCriteria().andSyncIdEqualTo(changeStateDepartment.getSyncId());
                                        changeStateDepartmentMapper.updateByExample(changeStateDepartment, changeStateDepartmentExample);
                                    } else {
                                        changeStateDepartmentMapper.insert(changeStateDepartment);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    } else if (objectKey.equals("sync_title")) {
                        Gson gson11 = new Gson();
                        List<ChangeStateTitle> changeStateTitleList = gson11.fromJson(gson.toJson(eventObj.getDataList()),
                                new TypeToken<List<ChangeStateTitle>>() {
                                }.getType());
                        List<ChangeStateTitle> changeStateTitles = new LinkedList<ChangeStateTitle>();
                        List<String> deteleflags = new ArrayList<>();
                        if (eventObj.getEvent().name().equals("INSERT")) {
                            if (changeStateTitleList != null) {
                                for (ChangeStateTitle changeStateTitle : changeStateTitleList) {
                                    changeStateTitle.setId(PrimaryKey.get());
                                    changeStateTitle.setUpdateDate(new Date().getTime());
                                    changeStateTitle.setSource(AGENT);
                                    changeStateTitles.add(changeStateTitle);
                                    deteleflags.add(changeStateTitle.getSyncId());
                                }
                            }
                            a_changeStateTitleMapper.insertBatch(changeStateTitles);

                            //初始化路由表
                            RouteOtherExample routeOtherExample = new RouteOtherExample();
                            routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                            routeOtherMapper.deleteByExample(routeOtherExample);

                            List<ChangeStateTitle> id = a_changeStateTitleMapper.checkBysyncId();
                            List<ChangeStateTitle> syncid = a_changeStateTitleMapper.selectSyncId();
                            if (id.size() > 0 && syncid.size() > 0) {
                                a_changeStateTitleMapper.deleteBatch(id, syncid);
                            }
                        } else {
                            if (changeStateTitleList != null) {
                                for (ChangeStateTitle changeStateTitle : changeStateTitleList) {
                                    changeStateTitle.setUpdateDate(new Date().getTime());
                                    changeStateTitle.setSource(AGENT);
                                    changeStateTitle.setId(PrimaryKey.get());
                                    deteleflags.add(changeStateTitle.getSyncId());
                                    int count = a_changeStateTitleMapper.checkById(changeStateTitle.getSyncId(), AGENT);
                                    if (count > 0) {
                                        ChangeStateTitleExample changeStateTitleExample = new ChangeStateTitleExample();
                                        changeStateTitleExample.createCriteria().andSyncIdEqualTo(changeStateTitle.getSyncId());
                                        changeStateTitleMapper.updateByExample(changeStateTitle, changeStateTitleExample);
                                    } else {
                                        changeStateTitleMapper.insert(changeStateTitle);
                                    }
                                }
                                //初始化路由表
                                RouteOtherExample routeOtherExample = new RouteOtherExample();
                                routeOtherExample.createCriteria().andSyncIdIn(deteleflags);
                                routeOtherMapper.deleteByExample(routeOtherExample);
                            }
                        }
                    }
                }
            } else {
                System.out.print("error");
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}

