package cc.gukeer.syncdata.service;

import cc.gukeer.syncdata.persistence.entity.*;

import java.util.List;

//教务基础数据入库保存
public interface DataSaveService {

    // 初始化路由表
    int routeInit(List<? extends Object> viewList,Integer type);

    //初始化 ref_teach_class路由表 route_ref_teacher_class
    int routeInit(List<ChangeStateRefTeacher> viewList);

    //教学周期信息表 change_state_teach_cycle
    int batchInsertCycle(List<ChangeStateCycle> list, String source);//批量插入

    int batchDeleteCycle(List<ChangeStateCycle> list);//批量删除

    int updateCycle(ChangeStateCycle cycle, String source);//修改


    // 教室信息表 change_state_teach_class_room
    int batchInsertClassRoom(List<ChangeStateClassRoom> list, String source);//批量插入

    int batchDeleteClassRoom(List<ChangeStateClassRoom> list);//批量删除

    int updateClassRoom(ChangeStateClassRoom classRoom, String source);//修改


    // 课程管理信息表 change_state_teach_course_manage
    int batchInsertCourseManage(List<ChangeStateCourseManage> list, String source);//批量插入

    int batchDeleteCourseManage(List<ChangeStateCourseManage> list);//批量删除

    int updateCourseManage(ChangeStateCourseManage courseManage, String source);//修改


    // 教室类型表 change_state_teach_room_type
    int batchInsertRoomType(List<ChangeStateClassRoomType> list, String source);//批量插入

    int batchDeleteRoomType(List<ChangeStateClassRoomType> list);//批量删除

    int updateRoomType(ChangeStateClassRoomType roomType, String source);//修改


    // 教师授课安排信息 change_state_teach_teach_class
    int batchInsertTeachMange(List<ChangeStateTeachMange> list, String source);//批量插入

    int batchDeleteTeachMange(List<ChangeStateTeachMange> list);//批量删除

    int updateTeachMange(ChangeStateTeachMange teachMange, String source);//修改


    //课程表 change_state_teach_course
    int batchInsertTeachCourse(List<ChangeStateCourse> list, String source);//批量插入

    int batchDeleteTeachCourse(List<ChangeStateCourse> list);//批量删除

    int updateTeachCourse(ChangeStateCourse teachCourse, String source);//修改


    //科目字典表 change_state_teach_course_type
    int batchInsertTeachCourseType(List<ChangeStateCourseType> list, String source);//批量插入

    int batchDeleteTeachCourseType(List<ChangeStateCourseType> list);//批量删除

    int updateTeachCourseType(ChangeStateCourseType teachCourseType, String source);//修改


    //标准课程信息表
    int batchInsertTeachStandardCourse(List<ChangeStateStandardCourse> list, String source);//批量插入

    int batchDeleteTeachStandardCourse(List<ChangeStateStandardCourse> list);//批量删除

    int updateTeachStandardCourse(ChangeStateStandardCourse standardCourse, String source);//修改


    //班级日常课时表 change_state_teach_daily_hour
    int batchInsertTeachDailyHour(List<ChangeStateDailyHour> list, String source);//批量插入

    int batchDeleteTeachDailyHour(List<ChangeStateDailyHour> list);//批量删除

    int updateTeachDailyHour(ChangeStateDailyHour dailyHour, String source);//修改


    //班级 change_state_org_grade_class
    int batchInsertGradeClass(List<ChangeStateGrade> list, String source);//批量插入

    int batchDeleteGradeClass(List<ChangeStateGrade> list);//批量删除

    int updateGradeClass(ChangeStateGrade gradeClass, String source);//修改


    //家长 change_state_user_patriarch
    int batchInsertParent(List<ChangeStatePatriarch> list, String source);//批量插入

    int batchDeleteParent(List<ChangeStatePatriarch> list);//批量删除

    int updateParent(ChangeStatePatriarch parent, String source);//修改


    //用户change_state_sys_user
    int batchInsertUser(List<ChangeStateUser> list, String source);//批量插入

    int batchDeleteUser(List<ChangeStateUser> list);//批量删除

    int updateUser(ChangeStateUser user, String source);//修改

    //用户change_state_user_teacher
    int batchInsertTeacher(List<ChangeStateTeacher> list, String source);//批量插入

    int batchDeleteTeacher(List<ChangeStateTeacher> list);//批量删除

    int updateTeacher(ChangeStateTeacher teacher, String source);//修改


    //用户change_state_user_Student
    int batchInsertStudent(List<ChangeStateStudent> list, String source);//批量插入

    int batchDeleteStudent(List<ChangeStateStudent> list);//批量删除

    int updateStudent(ChangeStateStudent student, String source);//修改


    //机构 change_state_org_school
    int batchInsertSchool(List<ChangeStateSchool> list, String source);//批量插入

    int batchDeleteSchool(List<ChangeStateSchool> list);//批量删除

    int updateSchool(ChangeStateSchool school, String source);//修改


    //校区change_state_org_school_type
    int batchInsertSchoolType(List<ChangeStateSchoolType> list, String source);//批量插入

    int batchDeleteSchoolType(List<ChangeStateSchoolType> list);//批量删除

    int updateSchoolType(ChangeStateSchoolType schoolType, String source);//修改


    //学段change_state_org_class_section
    int batchInsertSection(List<ChangeStateSection> list, String source);//批量插入

    int batchDeleteSection(List<ChangeStateSection> list);//批量删除

    int updateSection(ChangeStateSection section, String source);//修改


    //部门change_state_org_department
    int batchInsertDepartment(List<ChangeStateDepartment> list, String source);//批量插入

    int batchDeleteDepartment(List<ChangeStateDepartment> list);//批量删除

    int updateDepartment(ChangeStateDepartment department, String source);//修改


    //职务change_state_org_title
    int batchInsertTitle(List<ChangeStateTitle> list, String source);//批量插入

    int batchDeleteTitle(List<ChangeStateTitle> list);//批量删除

    int updateTitle(ChangeStateTitle title, String source);//修改


    //职务change_state_ref_teacher_class
    int batchInsertTeacherClass(List<ChangeStateRefTeacher> list, String source);//批量插入

    int batchDeleteTeacherClass(List<ChangeStateRefTeacher> list);//批量删除

    int updateTeacherClass(ChangeStateRefTeacher teacherClass, String source);//修改

}
