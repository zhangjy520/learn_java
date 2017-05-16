package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.utils.Gziputil;
import cc.gukeer.common.utils.MD5Util;
import cc.gukeer.common.utils.PropertiesUtil;
import cc.gukeer.smartRing.persistence.dao.SchoolMapper;
import cc.gukeer.smartRing.syncdata.EventData;
import cc.gukeer.smartRing.syncdata.EventType;
import cc.gukeer.smartRing.syncdata.beanViews.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

@Service
public class QueueMessageListenerServiceImpl extends SyncDataServiceImpl implements SessionAwareMessageListener, Serializable {

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    private Destination destination;

    @Transactional
    @Override
    public void onMessage(Message message, Session session) throws JMSException {

        try {
            if (message instanceof MapMessage) {
                MapMessage tm = (MapMessage) message;
                Enumeration<String> MapNames = tm.getMapNames();
                while (MapNames.hasMoreElements()) {
                    System.out.print("接收到实时同步来的数据" + MapNames.nextElement());
                }
                Properties props = PropertiesUtil.getProperties("/sync.properties");
                byte[] _datas = (byte[]) tm.getObject("datas");
                String datas = Gziputil.unCompress(_datas);
                String rand = (String) tm.getObject("rand");
                Long time = tm.getLong("time");
                String AGENT = tm.getString("agent");
                String ACCESSKEY = props.getProperty("accesskey");
                String sign = MD5Util.go(AGENT + ACCESSKEY + rand.toString() + datas.toString() + time.toString());
                String _sign = (String) tm.getObject("sign");
                if (sign.equals(_sign)) {
                    Gson gson = new Gson();
                    EventData<?> res = gson.fromJson(datas, EventData.class);
                    execute(res);
                }
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }
    }

    public void execute(EventData eventData) {
        List dataList = eventData.getDataList();
        String tableName = eventData.getObjectKey();
        EventType eventType = eventData.getEvent();

       /* DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);*/

        try {
            if ("change_state_org_class_section".equals(tableName)) {
                Gson gson = new Gson();
                List<ClassSectionView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<ClassSectionView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertClassSection(viewList);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (ClassSectionView classSectionView : viewList) {
                        this.updateClassSection(classSectionView);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteClassSection(viewList);
                }
            }

            if ("change_state_grade_class".equals(tableName)) {
                Gson gson = new Gson();
                List<GradeClassView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<GradeClassView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertGradeClass(viewList);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (GradeClassView gradeClassView : viewList) {
                        this.updateGradeClass(gradeClassView);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteGradeClass(viewList);
                }
            }

            if ("change_state_ref_teacher_class".equals(tableName)) {
                Gson gson = new Gson();
                List<TeacherClassView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<TeacherClassView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeacherClass(viewList);
                }
                if (EventType.DELETE.equals(eventType)) {
                    for (TeacherClassView teacherClass : viewList) {
                        this.deleteTeacherClass(teacherClass.getSyncTeacherId(), teacherClass.getSyncClassId());
                    }
                }
            }

            if ("change_state_user_student".equals(tableName)) {
                Gson gson = new Gson();
                List<StudentView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<StudentView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertStudent(viewList);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (StudentView studentView : viewList) {
                        this.updateStudent(studentView);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteStudent(viewList);
                }
            }

            if ("change_state_user_teacher".equals(tableName)) {
                Gson gson = new Gson();
                List<TeacherView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<TeacherView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertTeacher(viewList);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (TeacherView teacherView : viewList) {
                        this.updateTeacher(teacherView);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteTeacher(viewList);
                }
            }

           /* if ("change_state_sys_user".equals(tableName)) {
                Gson gson = new Gson();
                List<UserView> viewList = gson.fromJson(gson.toJson(dataList),
                        new TypeToken<List<UserView>>() {
                        }.getType());

                if (EventType.INSERT.equals(eventType)) {
                    this.batchInsertUser(viewList);
                }
                if (EventType.MODIFY.equals(eventType)) {
                    for (UserView userView : viewList) {
                        this.updateUser(userView);
                    }
                }
                if (EventType.DELETE.equals(eventType)) {
                    this.batchDeleteUser(viewList);
                }
            }*/

            // transactionManager.commit(status);
        } catch (Exception ex) {
            // transactionManager.rollback(status);
        }


    }


    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
