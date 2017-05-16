package cc.gukeer.attendance.controller;

import cc.gukeer.attendance.persistence.entity.RefCoursesStudent;
import cc.gukeer.attendance.service.CourseService;
import cc.gukeer.attendance.service.RefCoursesStudentService;
import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.smartRing.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pc-daisike on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/attendancestatistics")
public class AttendanceStatisticsController extends BasicController {
    @Autowired
    CourseService courseService;
    @Autowired
    RefCoursesStudentService refCoursesStudentService;
    //计算每节课的每个学生的最终信息
//    @RequestMapping (value = "/insert/data")
//    public String getStudentInsertStatistics  (HttpServletRequest request, Model model){
//        User user = getLoginUser();
//        //得到本周的时间(周一24:00到周六24:00)
//        SimpleDateFormat sdf =  new SimpleDateFormat( "yyyyMMdd" );
//        Calendar cal=new GregorianCalendar();
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        cal.setTime(new Date());
//        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
//        Date first=cal.getTime();
//        String monday = sdf.format(first)+"000000";
//        cal.setFirstDayOfWeek(Calendar.SATURDAY);
//        cal.setTime(new Date());
//        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
//        Date first1=cal.getTime();
//        String saturday = sdf.format(first1)+"000000";
//        //旷课
//        int absenteeism =0;
//        //迟到
//        int late = 0;
//        //早退
//        int leaveEarly = 0;
//        //外出
//        int goOut = 0;
//        //获取该授课教师的课表
////        List<Map> classTableList=courseService.getClassTable(user.getId(),user.getSchoolId(),Long.parseLong(monday),Long.parseLong(saturday));
////        for (Map time : classTableList) {
////            String upClass = time.get("class_begin").toString();
////            String downClass = time.get("class_end").toString();
//            //获得该节课应该上该教师课的学生
//            List<Map> getClassStudentList = courseService.getClassStudent(Long.parseLong(monday),Long.parseLong(saturday));
//            for (Map student : getClassStudentList) {
//                //不相等
//                RefCoursesStudent refCoursesStudent = new RefCoursesStudent();
//                refCoursesStudent.setStudentId(student.get("id").toString());
//                refCoursesStudent.setCourseIds(student.get("courseId").toString());
//                int uneq = 0;
//                //迟到早退标记
//                int lateflag = 0;
//                int leaveflag = 0;
////                int outflag = 0;
//                List<Map> studentLogList = courseService.getClassStudentLog(Long.parseLong(monday),Long.parseLong(saturday),Long.parseLong(monday),Long.parseLong(saturday),student.get("id").toString());
//                if (studentLogList == null) {
//                    absenteeism += 1;
//                } else {
//                    for (Map studentLog : studentLogList) {
//                        if (!studentLog.get("stationMac").equals(studentLog.get("classPlaceId"))) {
//                            uneq += 1;
//                            if (!studentLog.get("logUpdata").equals(monday)){
//                                lateflag+=1;
//                            }
//                            if (!studentLog.get("logUpdata").equals(saturday)){
//                                leaveflag+=1;
//                            }
////                            if (!studentLog.get("last_update").equals(downClass)&&!studentLog.get("last_update").equals(upClass)){
////                                if (lateflag==0){
////                                    outflag+=1;
////                                }
////                            }
//                        }
//                    }
//                }
//                if (uneq<studentLogList.size()&&uneq>=1){
//                    if (leaveflag !=0){
//                        leaveEarly +=1;
//                        refCoursesStudent.setStatus(2);
//                    }
//                    if (lateflag!=0){
//                        late +=1;
//                        refCoursesStudent.setStatus(1);
//                    }
//                    if (leaveflag==0&&lateflag==0) {
//                        goOut += 1;
//                    }
//                    if (lateflag!=0&&leaveflag!=0){
//                        refCoursesStudent.setStatus(3);
//                    }
//
//                } else if (uneq == studentLogList.size()) {
//                    absenteeism += 1;
//                    refCoursesStudent.setStatus(0);
//                }else{
//                    refCoursesStudent.setStatus(4);
//                }
//                refCoursesStudentService.save(refCoursesStudent);
//            }
//
//        return null;
//    }
    //前端请求查看所有班级
    @RequestMapping (value = "/get/data")
    public String getStudentStatistics  (HttpServletRequest request){
        //课程的id
        String classId = getParamVal(request,"classId");
        String bTime = getParamVal(request,"bTime");
        String eTime = getParamVal(request,"eTime");
        //本周的时间
        User user = getLoginUser();
        if (classId ==""||classId==null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = new GregorianCalendar();
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.setTime(new Date());
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            Date first = cal.getTime();
            bTime = sdf.format(first) + "000000";
            cal.setFirstDayOfWeek(Calendar.SATURDAY);
            cal.setTime(new Date());
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            Date first1 = cal.getTime();
            eTime = sdf.format(first1) + "000000";
        }
        courseService.getClassStatisticsData(user.getId(),Long.parseLong(bTime),Long.parseLong(eTime));
        return null;
    }

    //查看某个班级的学生信息
    @RequestMapping(value = "/select/class/student")
    public ResultEntity selectClassInfo(HttpServletRequest request){
        String courseId = getParamVal(request,"courseId");
        User user = getLoginUser();
        Map statistic = courseService.getSingleClassStatistics(user.getRefId(),courseId);
        List<Map> studentList =courseService.getClassStudentInfomation(user.getRefId(),courseId);
        Map map = new HashMap();
        map.put("statistic",statistic);
        map.put("studentList",studentList);
        return ResultEntity.newResultEntity(map);
    }
    //更新某节课学生的信息
    @RequestMapping (value = "/updata/student/data")
    public ResultEntity updateStudentData  (HttpServletRequest request, Model model){
        String coursePId = getParamVal(request,"coursePId");
        String studentId = getParamVal(request,"studentId");
        String remarks = getParamVal(request,"remarks");
        int status = Integer.parseInt(getParamVal(request,"status"));
        RefCoursesStudent refCoursesStudent = new RefCoursesStudent();
        refCoursesStudent.setStatus(status);
        refCoursesStudent.setRemarks(remarks);
        refCoursesStudent.setStudentId(studentId);
        refCoursesStudent.setCourseIds(coursePId);
        refCoursesStudentService.update(refCoursesStudent);
        return ResultEntity.newResultEntity("修改成功");
    }
//    public int detail(List<LogDetailView> detailViews, int i) {
//        if (detailViews.size() == 0) return i;
//        if (i == detailViews.size() - 1) return i;
//        LogDetailView detailView = detailViews.get(i);
//        Long startTime = detailView.getLastUpdate();
//        Long cycle = detailView.getCycle();
//        Long endTime = getEndTime(startTime, cycle);
//        if (endTime + 500 > detailViews.get(i + 1).getLastUpdate() && endTime - 500 < detailViews.get(i + 1).getLastUpdate()) {  //浮动5秒
//            detail(detailViews, i + 1);
//        }
//        return i;
//    }
//    public Long getEndTime(Long startTime, Long cycle) {
//        long end = 0l;
//        try {
//            if (startTime == null || cycle == null) {
//                return end;
//            } else {
//                String str = String.valueOf(startTime);
//                long cyc = cycle * 1000;
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//                long millionSeconds = sdf.parse(str).getTime();//毫秒
//                long sfds = millionSeconds + cyc;
//                return Long.valueOf(sdf.format(sfds));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return end;
//    }
}
