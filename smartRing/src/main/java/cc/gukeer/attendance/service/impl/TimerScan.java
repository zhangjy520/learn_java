package cc.gukeer.attendance.service.impl;

import cc.gukeer.attendance.persistence.entity.RefCoursesStudent;
import cc.gukeer.attendance.service.CourseService;
import cc.gukeer.attendance.service.RefCoursesStudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pc-daisike on 2017/5/15.
 */
public class TimerScan {
    @Autowired
    CourseService courseService;
    @Autowired
    RefCoursesStudentService refCoursesStudentService;

    public void timerList() {
        //得到本周的时间(周一24:00到周六24:00)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
        Date time = new Date();

//        String nowTime = sdf.format(time);
        List<Map> typeTimeList = courseService.getTime();

        Long nowTime = Long.parseLong(sdf.format(time));
        for (Map map : typeTimeList) {
            Long bTime = Long.parseLong(map.get("classBegin").toString());
            Long eTime = Long.parseLong(map.get("classEnd").toString());
            if (bTime < nowTime && nowTime < eTime) {
                if (nowTime == eTime) {

                    //获得该节课应该上该教师课的学生
                    List<Map> getClassStudentList = courseService.getClassStudent(bTime, eTime);
                    for (Map student : getClassStudentList) {
                        //不相等
                        RefCoursesStudent refCoursesStudent = new RefCoursesStudent();
                        refCoursesStudent.setStudentId(student.get("id").toString());
                        refCoursesStudent.setCourseIds(student.get("courseId").toString());
                        int uneq = 0;
                        int lateflag = 0;
                        int leaveflag = 0;
                        List<Map> studentLogList = courseService.getClassStudentLog(bTime, eTime, bTime, eTime, student.get("id").toString());
                        if (studentLogList == null) {
                            refCoursesStudent.setStatus(0);
                        } else {
                            for (Map studentLog : studentLogList) {
                                if (!studentLog.get("stationMac").equals(studentLog.get("classPlaceId"))) {
                                    uneq += 1;
                                    if (!studentLog.get("logUpdata").equals(nowTime)) {
                                        lateflag += 1;
                                    }
                                    if (!studentLog.get("logUpdata").equals(nowTime)) {
                                        leaveflag += 1;
                                    }
                                }
                            }
                        }
                        if (uneq < studentLogList.size() && uneq >= 1) {
                            if (leaveflag != 0) {
                                refCoursesStudent.setStatus(2);
                            }
                            if (lateflag != 0) {
                                refCoursesStudent.setStatus(1);
                            }
                            if (lateflag != 0 && leaveflag != 0) {
                                refCoursesStudent.setStatus(3);
                            }

                        } else if (uneq == studentLogList.size()) {
                            refCoursesStudent.setStatus(0);
                        } else if(uneq==0){
                            refCoursesStudent.setStatus(5);
                        }
                        refCoursesStudentService.save(refCoursesStudent);
                    }
                }
            }
        }
    }
}
