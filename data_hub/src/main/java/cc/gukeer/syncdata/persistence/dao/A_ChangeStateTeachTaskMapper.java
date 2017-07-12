package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.*;

import java.util.List;

public interface A_ChangeStateTeachTaskMapper {

    //教学周期信息表  change_state_teach_cycle
    int batchInsertCycle(List<ChangeStateCycle> list);//批量插入

    //教室信息表   change_state_teach_class_room
    int batchInsertClassRoom(List<ChangeStateClassRoom> list);

    //课程管理信息表 change_state_teach_course_manage
    int batchInsertCourseManage(List<ChangeStateCourseManage> list);

    // 教室类型表 change_state_teach_room_type
    int batchInsertRoomType(List<ChangeStateClassRoomType> list);

    //教师授课安排信息表 change_state_teach_teach_class
    int batchInsertTeachManage(List<ChangeStateTeachMange> list);

    //课程表 change_state_teach_course
    int batchInsertCourse(List<ChangeStateCourse> list);

    //科目类型字典表 change_state_teach_course_type
    int batchInsertCourseType(List<ChangeStateCourseType> list);

    //标准课程信息表 change_state_teach_standard_course
    int batchInsertStandardCourse(List<ChangeStateStandardCourse> list);

    //班级日常课时表 change_state_teach_daily_hour
    int batchInsertDailyHour(List<ChangeStateDailyHour> list);

    //部门表 change_state_org_department
    int batchInsertDepartment(List<ChangeStateDepartment> list);

    //班级表 change_state_org_grade_class
    int batchInsertGradeClass(List<ChangeStateGrade> list);

    //家长表 change_state_user_patriarch
    int batchInsertParent(List<ChangeStatePatriarch> list);

    //教师班级关联表 change_state_ref_teacher_class
    int batchInsertRefTeacher(List<ChangeStateRefTeacher> list);

    //机构表 change_state_org_school
    int batchInsertSchool(List<ChangeStateSchool> list);

    //校区表 change_state_org_school_type
    int batchInsertSchoolType(List<ChangeStateSchoolType> list);

    //学段表 change_state_org_class_section
    int batchInsertSection(List<ChangeStateSection> list);

    //学生表 change_state_user_student
    int batchInsertStudent(List<ChangeStateStudent> list);

    //教职工表 change_state_user_teacher
    int batchInsertTeacher(List<ChangeStateTeacher> list);

    //职位表 change_state_org_title
    int batchInsertTitle(List<ChangeStateTitle> list);

    //用户表 change_state_sys_user
    int batchInsertUser(List<ChangeStateUser> list);

    //课节表change_state_course_node
    int batchInsertCourseNode(List<ChangeStateCourseNode> list);

    //课节表change_state_course_node_init
    int batchInsertCourseNodeInit(List<ChangeStateCourseNodeInit> list);

}
