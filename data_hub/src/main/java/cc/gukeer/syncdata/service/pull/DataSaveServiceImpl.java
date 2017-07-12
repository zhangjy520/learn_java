package cc.gukeer.syncdata.service.pull;


import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.syncdata.persistence.dao.*;
import cc.gukeer.syncdata.persistence.entity.*;
import cc.gukeer.syncdata.service.DataSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataSaveServiceImpl implements DataSaveService {

    @Autowired
    ChangeStateGradeMapper changeStateGradeMapper;

    @Autowired
    A_ChangeStateTeachTaskMapper teachTaskMapper;

    @Autowired
    RouteOtherMapper routeOtherMapper;

    @Autowired
    RoutePatriarchMapper routePatriarchMapper;

    @Autowired
    RouteTeacherMapper routeTeacherMapper;

    @Autowired
    RouteStudentMapper routeStudentMapper;

    @Autowired
    ChangeStateCycleMapper cycleMapper;

    @Autowired
    ChangeStateClassRoomMapper classRoomMapper;

    @Autowired
    ChangeStateCourseManageMapper courseManageMapper;

    @Autowired
    ChangeStateClassRoomTypeMapper classRoomTypeMapper;

    @Autowired
    ChangeStateTeachMangeMapper teachMangeMapper;

    @Autowired
    ChangeStateCourseMapper courseMapper;

    @Autowired
    ChangeStateCourseTypeMapper courseTypeMapper;

    @Autowired
    ChangeStateStandardCourseMapper stateStandardCourseMapper;

    @Autowired
    ChangeStateDailyHourMapper dailyHourMapper;

    @Autowired
    ChangeStatePatriarchMapper patriarchMapper;

    @Autowired
    ChangeStateSchoolMapper schoolMapper;

    @Autowired
    ChangeStateSchoolTypeMapper schoolTypeMapper;

    @Autowired
    ChangeStateSectionMapper sectionMapper;

    @Autowired
    ChangeStateDepartmentMapper departmentMapper;

    @Autowired
    ChangeStateTitleMapper titleMapper;

    @Autowired
    ChangeStateUserMapper userMapper;

    @Autowired
    ChangeStateStudentMapper studentMapper;

    @Autowired
    ChangeStateTeacherMapper teacherMapper;

    @Autowired
    A_RouteTeacherClassMapper a_routeTeacherClassMapper;

    @Autowired
    RouteTeacherClassMapper teacherClassMapper;

    @Autowired
    ChangeStateRefTeacherMapper refTeacherMapper;

    @Autowired
    ChangeStateCourseNodeMapper courseNodeMapper;

    @Autowired
    ChangeStateCourseNodeInitMapper courseNodeInitMapper;

    @Override
    public int routeInit(List<? extends Object> viewList, Integer type) {

        List<String> syncIds = new ArrayList<>();

        for (Object obj : viewList) {
            syncIds.add(getSyncId(obj));
        }
        if (0 == type) {

            RouteOtherExample routeOtherExample = new RouteOtherExample();
            routeOtherExample.createCriteria().andSyncIdIn(syncIds);
            routeOtherMapper.deleteByExample(routeOtherExample);

        } else if (1 == type) {

            RoutePatriarchExample routePatriarchExample = new RoutePatriarchExample();
            routePatriarchExample.createCriteria().andSyncIdIn(syncIds);
            routePatriarchMapper.deleteByExample(routePatriarchExample);

        } else if (2 == type) {

            RouteTeacherExample routeTeacherExample = new RouteTeacherExample();
            routeTeacherExample.createCriteria().andSyncIdIn(syncIds);
            routeTeacherMapper.deleteByExample(routeTeacherExample);

        } else if (3 == type) {

            RouteStudentExample routeStudentExample = new RouteStudentExample();
            routeStudentExample.createCriteria().andSyncIdIn(syncIds);
            routeStudentMapper.deleteByExample(routeStudentExample);

        }
        return syncIds.size();

    }

    @Override
    public int routeInit(List<ChangeStateRefTeacher> viewList) {
        for (ChangeStateRefTeacher teacherClass : viewList) {
            RouteTeacherClassExample example = new RouteTeacherClassExample();
            example.createCriteria().andClassIdEqualTo(teacherClass.getSyncClassId())
                    .andTeacherIdEqualTo(teacherClass.getSyncTeacherId());

            teacherClassMapper.deleteByExample(example);
        }
        return viewList.size();
    }

    @Override
    public int batchInsertCycle(List<ChangeStateCycle> list, String source) {

        for (ChangeStateCycle cycle : list) {
            cycle.setId(PrimaryKey.get());
            cycle.setUpdateDate(new Date().getTime());
            cycle.setSource(source);
        }
        teachTaskMapper.batchInsertCycle(list);
        return list.size();
    }

    @Override
    public int batchDeleteCycle(List<ChangeStateCycle> list) {

        List<String> idList = new ArrayList<>();
        for (ChangeStateCycle cycle : list) {
            idList.add(cycle.getSyncId());
        }
        ChangeStateCycleExample example = new ChangeStateCycleExample();
        example.createCriteria().andSyncIdIn(idList);

        return cycleMapper.deleteByExample(example);
    }

    @Override
    public int updateCycle(ChangeStateCycle cycle, String source) {

        ChangeStateCycleExample example = new ChangeStateCycleExample();
        example.createCriteria().andSyncIdEqualTo(cycle.getSyncId());

        cycle.setId(PrimaryKey.get());
        cycle.setSource(source);
        cycle.setUpdateDate(new Date().getTime());

        List<ChangeStateCycle> res = cycleMapper.selectByExample(example);
        if (res.size() == 0)
            return cycleMapper.insert(cycle);

        return cycleMapper.updateByExample(cycle, example);
    }

    @Override
    public int batchInsertClassRoom(List<ChangeStateClassRoom> list, String source) {

        for (ChangeStateClassRoom room : list) {
            room.setId(PrimaryKey.get());
            room.setUpdateDate(new Date().getTime());
            room.setSource(source);
        }
        teachTaskMapper.batchInsertClassRoom(list);
        return list.size();
    }

    @Override
    public int batchDeleteClassRoom(List<ChangeStateClassRoom> list) {

        List<String> idList = new ArrayList<>();
        for (ChangeStateClassRoom cycle : list) {
            idList.add(cycle.getSyncId());
        }
        ChangeStateClassRoomExample example = new ChangeStateClassRoomExample();
        example.createCriteria().andSyncIdIn(idList);

        return classRoomMapper.deleteByExample(example);
    }

    @Override
    public int updateClassRoom(ChangeStateClassRoom classRoom, String source) {
        ChangeStateClassRoomExample example = new ChangeStateClassRoomExample();
        example.createCriteria().andSyncIdEqualTo(classRoom.getSyncId());

        classRoom.setId(PrimaryKey.get());
        classRoom.setSource(source);
        classRoom.setUpdateDate(new Date().getTime());

        List<ChangeStateClassRoom> res = classRoomMapper.selectByExample(example);
        if (res.size() == 0)
            return classRoomMapper.insert(classRoom);

        return classRoomMapper.updateByExample(classRoom, example);
    }

    @Override
    public int batchInsertCourseManage(List<ChangeStateCourseManage> list, String source) {
        for (ChangeStateCourseManage courseManage : list) {
            courseManage.setId(PrimaryKey.get());
            courseManage.setUpdateDate(new Date().getTime());
            courseManage.setSource(source);
        }
        teachTaskMapper.batchInsertCourseManage(list);
        return list.size();
    }

    @Override
    public int batchDeleteCourseManage(List<ChangeStateCourseManage> list) {

        List<String> idList = new ArrayList<>();
        for (ChangeStateCourseManage courseManage : list) {
            idList.add(courseManage.getSyncId());
        }
        ChangeStateCourseManageExample example = new ChangeStateCourseManageExample();
        example.createCriteria().andSyncIdIn(idList);

        return courseManageMapper.deleteByExample(example);
    }

    @Override
    public int updateCourseManage(ChangeStateCourseManage courseManage, String source) {
        ChangeStateCourseManageExample example = new ChangeStateCourseManageExample();
        example.createCriteria().andSyncIdEqualTo(courseManage.getSyncId());

        courseManage.setId(PrimaryKey.get());
        courseManage.setSource(source);
        courseManage.setUpdateDate(new Date().getTime());

        List<ChangeStateCourseManage> res = courseManageMapper.selectByExample(example);
        if (res.size() == 0)
            return courseManageMapper.insert(courseManage);

        return courseManageMapper.updateByExample(courseManage, example);
    }

    @Override
    public int batchInsertRoomType(List<ChangeStateClassRoomType> list, String source) {
        for (ChangeStateClassRoomType classRoomType : list) {
            classRoomType.setId(PrimaryKey.get());
            classRoomType.setUpdateDate(new Date().getTime());
            classRoomType.setSource(source);
        }
        teachTaskMapper.batchInsertRoomType(list);
        return list.size();
    }

    @Override
    public int batchDeleteRoomType(List<ChangeStateClassRoomType> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateClassRoomType roomType : list) {
            idList.add(roomType.getSyncId());
        }
        ChangeStateClassRoomTypeExample example = new ChangeStateClassRoomTypeExample();
        example.createCriteria().andSyncIdIn(idList);

        return classRoomTypeMapper.deleteByExample(example);
    }

    @Override
    public int updateRoomType(ChangeStateClassRoomType roomType, String source) {
        ChangeStateClassRoomTypeExample example = new ChangeStateClassRoomTypeExample();
        example.createCriteria().andSyncIdEqualTo(roomType.getSyncId());

        roomType.setId(PrimaryKey.get());
        roomType.setSource(source);
        roomType.setUpdateDate(new Date().getTime());

        List<ChangeStateClassRoomType> res = classRoomTypeMapper.selectByExample(example);
        if (res.size() == 0)
            return classRoomTypeMapper.insert(roomType);

        return classRoomTypeMapper.updateByExample(roomType, example);
    }

    @Override
    public int batchInsertTeachMange(List<ChangeStateTeachMange> list, String source) {
        for (ChangeStateTeachMange teachMange : list) {
            teachMange.setId(PrimaryKey.get());
            teachMange.setUpdateDate(new Date().getTime());
            teachMange.setSource(source);
        }
        teachTaskMapper.batchInsertTeachManage(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeachMange(List<ChangeStateTeachMange> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateTeachMange teachMange : list) {
            idList.add(teachMange.getSyncId());
        }
        ChangeStateTeachMangeExample example = new ChangeStateTeachMangeExample();
        example.createCriteria().andSyncIdIn(idList);

        return teachMangeMapper.deleteByExample(example);
    }

    @Override
    public int updateTeachMange(ChangeStateTeachMange teachMange, String source) {
        ChangeStateTeachMangeExample example = new ChangeStateTeachMangeExample();
        example.createCriteria().andSyncIdEqualTo(teachMange.getSyncId());

        teachMange.setId(PrimaryKey.get());
        teachMange.setSource(source);
        teachMange.setUpdateDate(new Date().getTime());

        List<ChangeStateTeachMange> res = teachMangeMapper.selectByExample(example);
        if (res.size() == 0)
            return teachMangeMapper.insert(teachMange);

        return teachMangeMapper.updateByExample(teachMange, example);
    }

    @Override
    public int batchInsertTeachCourse(List<ChangeStateCourse> list, String source) {
        for (ChangeStateCourse teachCourse : list) {
            teachCourse.setId(PrimaryKey.get());
            teachCourse.setUpdateDate(new Date().getTime());
            teachCourse.setSource(source);
        }
        teachTaskMapper.batchInsertCourse(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeachCourse(List<ChangeStateCourse> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateCourse teachCourse : list) {
            idList.add(teachCourse.getSyncId());
        }
        ChangeStateCourseExample example = new ChangeStateCourseExample();
        example.createCriteria().andSyncIdIn(idList);

        return courseMapper.deleteByExample(example);
    }

    @Override
    public int updateTeachCourse(ChangeStateCourse teachCourse, String source) {
        ChangeStateCourseExample example = new ChangeStateCourseExample();
        example.createCriteria().andSyncIdEqualTo(teachCourse.getSyncId());

        teachCourse.setId(PrimaryKey.get());
        teachCourse.setSource(source);
        teachCourse.setUpdateDate(new Date().getTime());

        List<ChangeStateCourse> res = courseMapper.selectByExample(example);
        if (res.size() == 0)
            return courseMapper.insert(teachCourse);

        return courseMapper.updateByExample(teachCourse, example);
    }

    @Override
    public int batchInsertTeachCourseType(List<ChangeStateCourseType> list, String source) {
        for (ChangeStateCourseType teachCourseType : list) {
            teachCourseType.setId(PrimaryKey.get());
            teachCourseType.setUpdateDate(new Date().getTime());
            teachCourseType.setSource(source);
        }
        teachTaskMapper.batchInsertCourseType(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeachCourseType(List<ChangeStateCourseType> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateCourseType teachCourseType : list) {
            idList.add(teachCourseType.getSyncId());
        }
        ChangeStateCourseTypeExample example = new ChangeStateCourseTypeExample();
        example.createCriteria().andSyncIdIn(idList);

        return courseTypeMapper.deleteByExample(example);
    }

    @Override
    public int updateTeachCourseType(ChangeStateCourseType teachCourseType, String source) {
        ChangeStateCourseTypeExample example = new ChangeStateCourseTypeExample();
        example.createCriteria().andSyncIdEqualTo(teachCourseType.getSyncId());

        teachCourseType.setId(PrimaryKey.get());
        teachCourseType.setSource(source);
        teachCourseType.setUpdateDate(new Date().getTime());

        List<ChangeStateCourseType> res = courseTypeMapper.selectByExample(example);
        if (res.size() == 0)
            return courseTypeMapper.insert(teachCourseType);

        return courseTypeMapper.updateByExample(teachCourseType, example);
    }

    @Override
    public int batchInsertTeachStandardCourse(List<ChangeStateStandardCourse> list, String source) {
        for (ChangeStateStandardCourse stateStandardCourse : list) {
            stateStandardCourse.setId(PrimaryKey.get());
            stateStandardCourse.setUpdateDate(new Date().getTime());
            stateStandardCourse.setSource(source);
        }
        teachTaskMapper.batchInsertStandardCourse(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeachStandardCourse(List<ChangeStateStandardCourse> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateStandardCourse teachCourseType : list) {
            idList.add(teachCourseType.getSyncId());
        }
        ChangeStateStandardCourseExample example = new ChangeStateStandardCourseExample();
        example.createCriteria().andSyncIdIn(idList);

        return stateStandardCourseMapper.deleteByExample(example);
    }

    @Override
    public int updateTeachStandardCourse(ChangeStateStandardCourse standardCourse, String source) {
        ChangeStateStandardCourseExample example = new ChangeStateStandardCourseExample();
        example.createCriteria().andSyncIdEqualTo(standardCourse.getSyncId());

        standardCourse.setId(PrimaryKey.get());
        standardCourse.setSource(source);
        standardCourse.setUpdateDate(new Date().getTime());

        List<ChangeStateStandardCourse> res = stateStandardCourseMapper.selectByExample(example);
        if (res.size() == 0)
            return stateStandardCourseMapper.insert(standardCourse);

        return stateStandardCourseMapper.updateByExample(standardCourse, example);
    }

    @Override
    public int batchInsertTeachDailyHour(List<ChangeStateDailyHour> list, String source) {
        for (ChangeStateDailyHour dailyHour : list) {
            dailyHour.setId(PrimaryKey.get());
            dailyHour.setUpdateDate(new Date().getTime());
            dailyHour.setSource(source);
        }
        teachTaskMapper.batchInsertDailyHour(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeachDailyHour(List<ChangeStateDailyHour> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateDailyHour dailyHour : list) {
            idList.add(dailyHour.getSyncId());
        }
        ChangeStateDailyHourExample example = new ChangeStateDailyHourExample();
        example.createCriteria().andSyncIdIn(idList);

        return dailyHourMapper.deleteByExample(example);
    }

    @Override
    public int updateTeachDailyHour(ChangeStateDailyHour dailyHour, String source) {
        ChangeStateDailyHourExample example = new ChangeStateDailyHourExample();
        example.createCriteria().andSyncIdEqualTo(dailyHour.getSyncId());

        dailyHour.setId(PrimaryKey.get());
        dailyHour.setSource(source);
        dailyHour.setUpdateDate(new Date().getTime());

        List<ChangeStateDailyHour> res = dailyHourMapper.selectByExample(example);
        if (res.size() == 0)
            return dailyHourMapper.insert(dailyHour);

        return dailyHourMapper.updateByExample(dailyHour, example);
    }

    @Override
    public int batchInsertGradeClass(List<ChangeStateGrade> list, String source) {
        for (ChangeStateGrade gradeClass : list) {
            gradeClass.setId(PrimaryKey.get());
            gradeClass.setUpdateDate(new Date().getTime());
            gradeClass.setSource(source);
        }
        teachTaskMapper.batchInsertGradeClass(list);
        return list.size();
    }

    @Override
    public int batchDeleteGradeClass(List<ChangeStateGrade> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateGrade gradeClass : list) {
            idList.add(gradeClass.getSyncId());
        }
        ChangeStateGradeExample example = new ChangeStateGradeExample();
        example.createCriteria().andSyncIdIn(idList);

        return changeStateGradeMapper.deleteByExample(example);
    }

    @Override
    public int updateGradeClass(ChangeStateGrade gradeClass, String source) {
        ChangeStateGradeExample example = new ChangeStateGradeExample();
        example.createCriteria().andSyncIdEqualTo(gradeClass.getSyncId());

        gradeClass.setId(PrimaryKey.get());
        gradeClass.setSource(source);
        gradeClass.setUpdateDate(new Date().getTime());

        List<ChangeStateGrade> res = changeStateGradeMapper.selectByExample(example);
        if (res.size() == 0)
            return changeStateGradeMapper.insert(gradeClass);

        return changeStateGradeMapper.updateByExample(gradeClass, example);
    }

    @Override
    public int batchInsertParent(List<ChangeStatePatriarch> list, String source) {
        for (ChangeStatePatriarch parent : list) {
            parent.setId(PrimaryKey.get());
            parent.setUpdateDate(new Date().getTime());
            parent.setSource(source);
        }
        teachTaskMapper.batchInsertParent(list);
        return list.size();
    }

    @Override
    public int batchDeleteParent(List<ChangeStatePatriarch> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStatePatriarch parent : list) {
            idList.add(parent.getSyncId());
        }
        ChangeStatePatriarchExample example = new ChangeStatePatriarchExample();
        example.createCriteria().andSyncIdIn(idList);

        return patriarchMapper.deleteByExample(example);
    }

    @Override
    public int updateParent(ChangeStatePatriarch parent, String source) {
        ChangeStatePatriarchExample example = new ChangeStatePatriarchExample();
        example.createCriteria().andSyncIdEqualTo(parent.getSyncId());

        parent.setId(PrimaryKey.get());
        parent.setSource(source);
        parent.setUpdateDate(new Date().getTime());

        List<ChangeStatePatriarch> res = patriarchMapper.selectByExample(example);
        if (res.size() == 0)
            return patriarchMapper.insert(parent);

        return patriarchMapper.updateByExample(parent, example);
    }

    @Override
    public int batchInsertUser(List<ChangeStateUser> list, String source) {
        for (ChangeStateUser user : list) {
            user.setId(PrimaryKey.get());
            user.setUpdateDate(new Date().getTime());
            user.setSource(source);
        }
        teachTaskMapper.batchInsertUser(list);
        return list.size();
    }

    @Override
    public int batchDeleteUser(List<ChangeStateUser> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateUser user : list) {
            idList.add(user.getSyncId());
        }
        ChangeStateUserExample example = new ChangeStateUserExample();
        example.createCriteria().andSyncIdIn(idList);

        return userMapper.deleteByExample(example);
    }

    @Override
    public int updateUser(ChangeStateUser user, String source) {
        ChangeStateUserExample example = new ChangeStateUserExample();
        example.createCriteria().andSyncIdEqualTo(user.getSyncId());

        user.setId(PrimaryKey.get());
        user.setSource(source);
        user.setUpdateDate(new Date().getTime());

        List<ChangeStateUser> res = userMapper.selectByExample(example);
        if (res.size() == 0)
            return userMapper.insert(user);

        return userMapper.updateByExample(user, example);
    }

    @Override
    public int batchInsertTeacher(List<ChangeStateTeacher> list, String source) {
        for (ChangeStateTeacher teacher : list) {
            teacher.setId(PrimaryKey.get());
            teacher.setUpdateDate(new Date().getTime());
            teacher.setSource(source);
        }
        teachTaskMapper.batchInsertTeacher(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeacher(List<ChangeStateTeacher> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateTeacher teacher : list) {
            idList.add(teacher.getSyncId());
        }
        ChangeStateTeacherExample example = new ChangeStateTeacherExample();
        example.createCriteria().andSyncIdIn(idList);

        return teacherMapper.deleteByExample(example);
    }

    @Override
    public int updateTeacher(ChangeStateTeacher teacher, String source) {
        ChangeStateTeacherExample example = new ChangeStateTeacherExample();
        example.createCriteria().andSyncIdEqualTo(teacher.getSyncId());

        teacher.setId(PrimaryKey.get());
        teacher.setSource(source);
        teacher.setUpdateDate(new Date().getTime());

        List<ChangeStateTeacher> res = teacherMapper.selectByExample(example);
        if (res.size() == 0)
            return teacherMapper.insert(teacher);

        return teacherMapper.updateByExample(teacher, example);
    }

    @Override
    public int batchInsertStudent(List<ChangeStateStudent> list, String source) {
        for (ChangeStateStudent student : list) {
            student.setId(PrimaryKey.get());
            student.setUpdateDate(new Date().getTime());
            student.setSource(source);
        }
        teachTaskMapper.batchInsertStudent(list);
        return list.size();
    }

    @Override
    public int batchDeleteStudent(List<ChangeStateStudent> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateStudent student : list) {
            idList.add(student.getSyncId());
        }
        ChangeStateStudentExample example = new ChangeStateStudentExample();
        example.createCriteria().andSyncIdIn(idList);

        return studentMapper.deleteByExample(example);
    }

    @Override
    public int updateStudent(ChangeStateStudent student, String source) {
        ChangeStateStudentExample example = new ChangeStateStudentExample();
        example.createCriteria().andSyncIdEqualTo(student.getSyncId());

        student.setId(PrimaryKey.get());
        student.setSource(source);
        student.setUpdateDate(new Date().getTime());

        List<ChangeStateStudent> res = studentMapper.selectByExample(example);
        if (res.size() == 0)
            return studentMapper.insert(student);

        return studentMapper.updateByExample(student, example);
    }

    @Override
    public int batchInsertSchool(List<ChangeStateSchool> list, String source) {
        for (ChangeStateSchool school : list) {
            school.setId(PrimaryKey.get());
            school.setUpdateDate(new Date().getTime());
            school.setSource(source);
        }
        teachTaskMapper.batchInsertSchool(list);
        return list.size();
    }

    @Override
    public int batchDeleteSchool(List<ChangeStateSchool> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateSchool school : list) {
            idList.add(school.getSyncId());
        }
        ChangeStateSchoolExample example = new ChangeStateSchoolExample();
        example.createCriteria().andSyncIdIn(idList);

        return schoolMapper.deleteByExample(example);
    }

    @Override
    public int updateSchool(ChangeStateSchool school, String source) {
        ChangeStateSchoolExample example = new ChangeStateSchoolExample();
        example.createCriteria().andSyncIdEqualTo(school.getSyncId());

        school.setId(PrimaryKey.get());
        school.setSource(source);
        school.setUpdateDate(new Date().getTime());

        List<ChangeStateSchool> res = schoolMapper.selectByExample(example);
        if (res.size() == 0)
            return schoolMapper.insert(school);

        return schoolMapper.updateByExample(school, example);
    }

    @Override
    public int batchInsertSchoolType(List<ChangeStateSchoolType> list, String source) {
        for (ChangeStateSchoolType schoolType : list) {
            schoolType.setId(PrimaryKey.get());
            schoolType.setUpdateDate(new Date().getTime());
            schoolType.setSource(source);
        }
        teachTaskMapper.batchInsertSchoolType(list);
        return list.size();
    }

    @Override
    public int batchDeleteSchoolType(List<ChangeStateSchoolType> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateSchoolType school : list) {
            idList.add(school.getSyncId());
        }
        ChangeStateSchoolTypeExample example = new ChangeStateSchoolTypeExample();
        example.createCriteria().andSyncIdIn(idList);

        return schoolTypeMapper.deleteByExample(example);
    }

    @Override
    public int updateSchoolType(ChangeStateSchoolType schoolType, String source) {
        ChangeStateSchoolTypeExample example = new ChangeStateSchoolTypeExample();
        example.createCriteria().andSyncIdEqualTo(schoolType.getSyncId());

        schoolType.setId(PrimaryKey.get());
        schoolType.setSource(source);
        schoolType.setUpdateDate(new Date().getTime());

        List<ChangeStateSchoolType> res = schoolTypeMapper.selectByExample(example);
        if (res.size() == 0)
            return schoolTypeMapper.insert(schoolType);

        return schoolTypeMapper.updateByExample(schoolType, example);
    }

    @Override
    public int batchInsertSection(List<ChangeStateSection> list, String source) {
        for (ChangeStateSection section : list) {
            section.setId(PrimaryKey.get());
            section.setUpdateDate(new Date().getTime());
            section.setSource(source);
        }
        teachTaskMapper.batchInsertSection(list);
        return list.size();
    }

    @Override
    public int batchDeleteSection(List<ChangeStateSection> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateSection section : list) {
            idList.add(section.getSyncId());
        }
        ChangeStateSectionExample example = new ChangeStateSectionExample();
        example.createCriteria().andSyncIdIn(idList);

        return sectionMapper.deleteByExample(example);
    }

    @Override
    public int updateSection(ChangeStateSection section, String source) {
        ChangeStateSectionExample example = new ChangeStateSectionExample();
        example.createCriteria().andSyncIdEqualTo(section.getSyncId());

        section.setId(PrimaryKey.get());
        section.setSource(source);
        section.setUpdateDate(new Date().getTime());

        List<ChangeStateSection> res = sectionMapper.selectByExample(example);
        if (res.size() == 0)
            return sectionMapper.insert(section);

        return sectionMapper.updateByExample(section, example);
    }

    @Override
    public int batchInsertDepartment(List<ChangeStateDepartment> list, String source) {
        for (ChangeStateDepartment department : list) {
            department.setId(PrimaryKey.get());
            department.setUpdateDate(new Date().getTime());
            department.setSource(source);
        }
        teachTaskMapper.batchInsertDepartment(list);
        return list.size();
    }

    @Override
    public int batchDeleteDepartment(List<ChangeStateDepartment> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateDepartment department : list) {
            idList.add(department.getSyncId());
        }
        ChangeStateDepartmentExample example = new ChangeStateDepartmentExample();
        example.createCriteria().andSyncIdIn(idList);

        return departmentMapper.deleteByExample(example);
    }

    @Override
    public int updateDepartment(ChangeStateDepartment department, String source) {
        ChangeStateDepartmentExample example = new ChangeStateDepartmentExample();
        example.createCriteria().andSyncIdEqualTo(department.getSyncId());

        department.setId(PrimaryKey.get());
        department.setSource(source);
        department.setUpdateDate(new Date().getTime());

        List<ChangeStateDepartment> res = departmentMapper.selectByExample(example);
        if (res.size() == 0)
            return departmentMapper.insert(department);

        return departmentMapper.updateByExample(department, example);
    }

    @Override
    public int batchInsertTitle(List<ChangeStateTitle> list, String source) {
        for (ChangeStateTitle title : list) {
            title.setId(PrimaryKey.get());
            title.setUpdateDate(new Date().getTime());
            title.setSource(source);
        }
        teachTaskMapper.batchInsertTitle(list);
        return list.size();
    }

    @Override
    public int batchDeleteTitle(List<ChangeStateTitle> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateTitle title : list) {
            idList.add(title.getSyncId());
        }
        ChangeStateTitleExample example = new ChangeStateTitleExample();
        example.createCriteria().andSyncIdIn(idList);

        return titleMapper.deleteByExample(example);
    }

    @Override
    public int updateTitle(ChangeStateTitle title, String source) {
        ChangeStateTitleExample example = new ChangeStateTitleExample();
        example.createCriteria().andSyncIdEqualTo(title.getSyncId());

        title.setId(PrimaryKey.get());
        title.setSource(source);
        title.setUpdateDate(new Date().getTime());

        List<ChangeStateTitle> res = titleMapper.selectByExample(example);
        if (res.size() == 0)
            return titleMapper.insert(title);

        return titleMapper.updateByExample(title, example);
    }

    @Override
    public int batchInsertTeacherClass(List<ChangeStateRefTeacher> list, String source) {
        for (ChangeStateRefTeacher teacherClass : list) {
            teacherClass.setId(PrimaryKey.get());
            teacherClass.setUpdateDate(new Date().getTime());
            teacherClass.setSource(source);
        }
        teachTaskMapper.batchInsertRefTeacher(list);
        return list.size();
    }

    @Override
    public int batchDeleteTeacherClass(List<ChangeStateRefTeacher> list) {

        for (ChangeStateRefTeacher teacherClass : list) {
            ChangeStateRefTeacherExample example = new ChangeStateRefTeacherExample();
            example.createCriteria().andSyncClassIdEqualTo(teacherClass.getSyncClassId())
                    .andSyncTeacherIdEqualTo(teacherClass.getSyncTeacherId());
            refTeacherMapper.deleteByExample(example);
        }

        return list.size();
    }

    @Override
    public int updateTeacherClass(ChangeStateRefTeacher teacherClass, String source) {
        teacherClass.setUpdateDate(new Date().getTime());
        teacherClass.setSource(source);
        teacherClass.setId(PrimaryKey.get());

        ChangeStateRefTeacherExample changeStateRefTeacherExample = new ChangeStateRefTeacherExample();
        changeStateRefTeacherExample.createCriteria()
                .andSyncClassIdEqualTo(teacherClass.getSyncClassId())
                .andSyncTeacherIdEqualTo(teacherClass.getSyncTeacherId());

        List<ChangeStateRefTeacher> res = refTeacherMapper.selectByExample(changeStateRefTeacherExample);
        if (res.size() == 0)
            return refTeacherMapper.insert(teacherClass);

        return refTeacherMapper.updateByExample(teacherClass, changeStateRefTeacherExample);
    }

    @Override
    public int batchInsertCourseNode(List<ChangeStateCourseNode> list, String source) {
        for (ChangeStateCourseNode courseNode : list) {
            courseNode.setId(PrimaryKey.get());
            courseNode.setSource(source);
        }
        teachTaskMapper.batchInsertCourseNode(list);
        return list.size();
    }

    @Override
    public int batchDeleteCourseNode(List<ChangeStateCourseNode> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateCourseNode courseNode : list) {
            idList.add(courseNode.getSyncId());
        }
        ChangeStateCourseNodeExample example = new ChangeStateCourseNodeExample();
        example.createCriteria().andSyncIdIn(idList);

        return courseNodeMapper.deleteByExample(example);
    }

    @Override
    public int updateCourseNode(ChangeStateCourseNode courseNode, String source) {
        ChangeStateCourseNodeExample example = new ChangeStateCourseNodeExample();
        example.createCriteria().andSyncIdEqualTo(courseNode.getSyncId());

        courseNode.setId(PrimaryKey.get());
        courseNode.setSource(source);

        List<ChangeStateCourseNode> res = courseNodeMapper.selectByExample(example);
        if (res.size() == 0)
            return courseNodeMapper.insert(courseNode);

        return courseNodeMapper.updateByExample(courseNode, example);
    }

    @Override
    public int batchInsertCourseNodeInit(List<ChangeStateCourseNodeInit> list, String source) {
        for (ChangeStateCourseNodeInit courseNodeInit : list) {
            courseNodeInit.setId(PrimaryKey.get());
            courseNodeInit.setSource(source);
        }
        teachTaskMapper.batchInsertCourseNodeInit(list);
        return list.size();
    }

    @Override
    public int batchDeleteCourseNodeInit(List<ChangeStateCourseNodeInit> list) {
        List<String> idList = new ArrayList<>();
        for (ChangeStateCourseNodeInit courseNodeInit : list) {
            idList.add(courseNodeInit.getSyncId());
        }
        ChangeStateCourseNodeInitExample example = new ChangeStateCourseNodeInitExample();
        example.createCriteria().andSyncIdIn(idList);

        return courseNodeInitMapper.deleteByExample(example);
    }

    @Override
    public int updateCourseNodeInit(ChangeStateCourseNodeInit courseNodeInit, String source) {
        ChangeStateCourseNodeInitExample example = new ChangeStateCourseNodeInitExample();
        example.createCriteria().andSyncIdEqualTo(courseNodeInit.getSyncId());

        courseNodeInit.setId(PrimaryKey.get());
        courseNodeInit.setSource(source);

        List<ChangeStateCourseNodeInit> res = courseNodeInitMapper.selectByExample(example);
        if (res.size() == 0)
            return courseNodeInitMapper.insert(courseNodeInit);

        return courseNodeInitMapper.updateByExample(courseNodeInit, example);
    }


    public static String getSyncId(Object obj) {
        String res = null;
        try {
            Class<?> cla = obj.getClass();
            Method method = cla.getMethod("getSyncId");
            res = method.invoke(obj).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
