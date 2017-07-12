package cc.gukeer.syncdata.service.pull;

import cc.gukeer.common.utils.GsonUtil;
import cc.gukeer.datahub.persistence.dao.PlatformMapper;
import cc.gukeer.datahub.persistence.entity.Platform;
import cc.gukeer.datahub.persistence.entity.PlatformExample;
import cc.gukeer.syncdata.dataDefinition.EventData;
import cc.gukeer.syncdata.dataDefinition.EventType;
import cc.gukeer.syncdata.persistence.dao.*;
import cc.gukeer.syncdata.persistence.entity.*;
import cc.gukeer.syncdata.util.MD5Util;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;

@Service
public class QueueMessageListener extends DataSaveServiceImpl implements SessionAwareMessageListener, Serializable {
    @Autowired
    A_MarkTimeMapper a_markTimeMapper;
    @Autowired
    MarkTimeMapper markTimeMapper;
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
                if (platform.size() > 0 && platform != null) {
                    ACCESSKEY = platform.get(0).getPassword();
                }
                String sign = MD5Util.go(AGENT + ACCESSKEY + rand.toString() + datas.toString() + time.toString());
                if (sign.equals((String) tm.getObject("sign"))) {
                    Gson gson = new Gson();
                    EventData<?> eventObj = gson.fromJson(datas, EventData.class);

                    dataSaveToMysql(eventObj, AGENT);

                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dataSaveToMysql(EventData eventData, String source) {
        List dataList = eventData.getDataList();
        String tableName = eventData.getObjectKey();
        EventType eventType = eventData.getEvent();

        try {
            if ("v_teach_cycle".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCycle> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCycle>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertCycle(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCycle cycleView : viewList) {
                        this.updateCycle(cycleView, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteCycle(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }
            if ("v_teach_class_room".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateClassRoom> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateClassRoom>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertClassRoom(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateClassRoom roomView : viewList) {
                        this.updateClassRoom(roomView, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteClassRoom(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_course_manage".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCourseManage> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCourseManage>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertCourseManage(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCourseManage courseManage : viewList) {
                        this.updateCourseManage(courseManage, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteCourseManage(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_room_type".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateClassRoomType> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateClassRoomType>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertRoomType(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateClassRoomType roomType : viewList) {
                        this.updateRoomType(roomType, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteRoomType(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_teach_class".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateTeachMange> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateTeachMange>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeachMange(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateTeachMange teachMange : viewList) {
                        this.updateTeachMange(teachMange, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeachMange(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_course".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCourse> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCourse>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeachCourse(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCourse teachCourse : viewList) {
                        this.updateTeachCourse(teachCourse, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeachCourse(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_course_type".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCourseType> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCourseType>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeachCourseType(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCourseType teachCourseType : viewList) {
                        this.updateTeachCourseType(teachCourseType, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeachCourseType(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_standard_course".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateStandardCourse> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateStandardCourse>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeachStandardCourse(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateStandardCourse standardCourse : viewList) {
                        this.updateTeachStandardCourse(standardCourse, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeachStandardCourse(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("v_teach_daily_hour".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateDailyHour> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateDailyHour>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeachDailyHour(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateDailyHour dailyHour : viewList) {
                        this.updateTeachDailyHour(dailyHour, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeachDailyHour(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_grade_class".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateGrade> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateGrade>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertGradeClass(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateGrade gradeClass : viewList) {
                        this.updateGradeClass(gradeClass, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteGradeClass(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_org_school".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateSchool> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateSchool>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertSchool(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateSchool school : viewList) {
                        this.updateSchool(school, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteSchool(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_school_type".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateSchoolType> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateSchoolType>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertSchoolType(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateSchoolType schoolType : viewList) {
                        this.updateSchoolType(schoolType, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteSchoolType(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_class_section".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateSection> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateSection>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertSection(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateSection section : viewList) {
                        this.updateSection(section, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteSection(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_department".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateDepartment> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateDepartment>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertDepartment(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateDepartment department : viewList) {
                        this.updateDepartment(department, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteDepartment(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_title".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateTitle> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateTitle>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTitle(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateTitle title : viewList) {
                        this.updateTitle(title, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTitle(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_user".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateUser> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateUser>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertUser(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateUser user : viewList) {
                        this.updateUser(user, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteUser(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_teach_course_node".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCourseNode> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCourseNode>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertCourseNode(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCourseNode courseNode : viewList) {
                        this.updateCourseNode(courseNode, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteCourseNode(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_teach_course_node_init".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateCourseNodeInit> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateCourseNodeInit>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertCourseNodeInit(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateCourseNodeInit courseNodeInit : viewList) {
                        this.updateCourseNodeInit(courseNodeInit, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteCourseNodeInit(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 0);
            }

            if ("sync_patriarch".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStatePatriarch> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStatePatriarch>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertParent(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStatePatriarch gradeClass : viewList) {
                        this.updateParent(gradeClass, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteParent(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 1);
            }

            if ("sync_teacher".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateTeacher> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateTeacher>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeacher(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateTeacher teacher : viewList) {
                        this.updateTeacher(teacher, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeacher(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 2);
            }

            if ("sync_student".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateStudent> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateStudent>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertStudent(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateStudent student : viewList) {
                        this.updateStudent(student, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteStudent(viewList);
                }
                //初始化路由表
                this.routeInit(viewList, 3);
            }

            if ("sync_teacher_class".equals(tableName)) {
                Gson gson = GsonUtil.noneIntDouble();
                List<ChangeStateRefTeacher> viewList = gson.fromJson(gson.toJson(dataList),
                        new com.google.gson.reflect.TypeToken<List<ChangeStateRefTeacher>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeacherClass(viewList, source);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ChangeStateRefTeacher refTeacher : viewList) {
                        this.updateTeacherClass(refTeacher, source);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeacherClass(viewList);
                }
                //初始化路由表
                this.routeInit(viewList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

