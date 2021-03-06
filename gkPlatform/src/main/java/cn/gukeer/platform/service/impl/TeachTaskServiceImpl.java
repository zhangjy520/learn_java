
package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;

import cn.gukeer.platform.persistence.entity.extention.GradeClassExtention;
import cn.gukeer.platform.service.TeachTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeachTaskServiceImpl extends BasicService implements TeachTaskService {

    @Autowired
    RefRoomCycleMapper refRoomCycleMapper;

    @Autowired
    TeachCycleMapper teachCycleMapper;

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_CourseClassMapper a_courseClassMapper;

    @Autowired
    ClassSectionMapper classSectionMapper;

    @Autowired
    A_MasterMapper a_masterMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    A_ClassViewMapper a_classViewMapper;

    @Autowired
    A_TeacherExtensionMapper a_teacherExtensionMapper;

    @Autowired
    CourseClassMapper courseClassMapper;

    @Autowired
    A_RefTeacherClassMapper a_refTeacherClassMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Autowired
    A_CourseMapper a_courseMapper;

    @Autowired
    SchoolTypeMapper schoolTypeMapper;

    @Autowired
    A_RefClassRoomMapper a_refClassRoomMapper;

    @Autowired
    RefClassRoomMapper refClassRoomMapper;

    @Autowired
    A_ClassRoomMapper a_classRoomMapper;

    @Autowired
    A_GradeClassMapper a_gradeClassMapper;

    @Autowired
    StandardCourseMapper standardCourseMapper;

    @Autowired
    A_StandardCourseMapper a_standardCourseMapper;

    @Autowired
    DailyHourMapper dailyHourMapper;

    @Autowired
    A_DailyHoureMapper a_dailyHoureMapper;

    @Autowired
    CourseNodeMapper courseNodeMapper;

    @Autowired
    A_CourseNodeMapper a_courseNodeMapper;

    @Autowired
    CourseNodeInitMapper courseNodeInitMapper;

    @Autowired
    A_RefRoomCycleMapper a_refRoomCycleMapper;

    @Override
    public int saveRefRoomCycle(RefRoomCycle roomCycle) {
        return refRoomCycleMapper.insertSelective(roomCycle);
    }

    @Override
    public int batchSaveRefRoomCycle(List<RefRoomCycle> list) {
        return a_classRoomMapper.batchSaveRefRoomCycle(list);
    }

    public PageInfo<TeachCycle> getTeachCycle(Map param) {
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();

        PageHelper.startPage(pageNum, pageSize);
        TeachCycleExample example = new TeachCycleExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("create_date desc");
        List<TeachCycle> list = teachCycleMapper.selectByExample(example);
        PageInfo<TeachCycle> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int saveTeachCycle(TeachCycle teachCycle) {
        int flag = 0;
        if (StringUtil.isEmpty(teachCycle.getId())) {
            //执行新增操作
            teachCycle.setCreateDate(System.currentTimeMillis());
            teachCycle.setDelFlag(0);
            teachCycle.setId(PrimaryKey.get());
            flag = teachCycleMapper.insertSelective(teachCycle);
        } else {
            //执行修改操作
            teachCycle.setUpdateDate(System.currentTimeMillis());
            flag = teachCycleMapper.updateByPrimaryKeySelective(teachCycle);
        }
        return flag;
    }


    @Override
    public TeachCycle selectByKey(String cycleId) {
        TeachCycle teachCycle = teachCycleMapper.selectByPrimaryKey(cycleId);
        return teachCycle;
    }


    @Override
    public ClassRoom getRoomByPri(String pri) {
        return classRoomMapper.selectByPrimaryKey(pri);
    }

    public List<TeachCycle> getCycleList(String schoolId) {

        TeachCycleExample example = new TeachCycleExample();
        example.setOrderByClause("create_date desc");
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);

        return teachCycleMapper.selectByExample(example);
    }

    @Override
    public TeachCycle getCycleByYearSemester(String year, Integer semester, String schoolId) {

        TeachCycleExample example = new TeachCycleExample();
        TeachCycleExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("cycle_semester");
        criteria.andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).
                andCycleYearEqualTo(year);
//        if (semester != 0)
        criteria.andCycleSemesterEqualTo(semester);
        List<TeachCycle> res = teachCycleMapper.selectByExample(example);
        if (res.size() > 0)
            return res.get(0);
        return new TeachCycle();
    }

    @Override
    public PageInfo<ClassRoom> getClassRoomList(Map param) {

        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();
        String cycleId = getValue(param, "cycleId").toString();


        ClassRoomExample example = new ClassRoomExample();
        example.setOrderByClause("create_date DESC");
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        if (StringUtil.isNotEmpty(schoolId)) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (StringUtil.isNotEmpty(cycleId)) {
            List<String> list = getRoomIdList(cycleId);
            if (list.size() > 0)
                criteria.andIdIn(list);
            else return new PageInfo<>(new ArrayList<ClassRoom>());
        }

        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassRoom> roomList = classRoomMapper.selectByExample(example);
        PageInfo<ClassRoom> pageInfo = new PageInfo<ClassRoom>(roomList);

        return pageInfo;
    }

    @Override
    public int saveClassRoom(ClassRoom classRoom, String pri) {
        int flag = 0;
        if (StringUtil.isEmpty(classRoom.getId())) {
            //执行新增操作
            classRoom.setId(pri);
            flag = classRoomMapper.insertSelective(classRoom);
        } else {
            //执行修改操作
            flag = classRoomMapper.updateByPrimaryKeySelective(classRoom);
        }
        return flag;
    }

    @Transactional
    public int insertClassRoomBatch(List<ClassRoom> list) {
        a_classRoomMapper.insertClassRoomBatch(list);
        return list.size();
    }


    @Override
    public List<RoomType> roomTypeList(String schoolId) {
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        return roomTypeMapper.selectByExample(example);
    }

    public PageInfo<CourseType> findAllCourseType(Map param) {
        CourseTypeExample example = new CourseTypeExample();
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();
        PageHelper.startPage(pageNum, pageSize);
//        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");
        List<CourseType> list = courseTypeMapper.selectByExample(example);
        PageInfo pageInfo = null;
        if (list.size() > 0 && list != null) {
            pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
        return new PageInfo<CourseType>(new ArrayList<CourseType>());
    }

    public Course getCourseByPrimaryKey(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    public List<GradeClass> findBjList(Integer nj, String xd, String schoolId) {
        GradeClassExample gradeClassExample = new GradeClassExample();
        gradeClassExample.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andNjEqualTo(nj).andXdEqualTo(xd);
        gradeClassExample.setOrderByClause("NAME+\"\"");
        List<GradeClass> list = gradeClassMapper.selectByExample(gradeClassExample);

        return list;
    }

    @Override
    public int saveCourseType(CourseType courseType) {
        int flag = 0;
        if (StringUtil.isEmpty(courseType.getId())) {
            //执行新增操作
            courseType.setId(PrimaryKey.get());
            courseType.setCreateDate(System.currentTimeMillis());
            flag = courseTypeMapper.insertSelective(courseType);
        } else {
            //执行修改操作
            flag = courseTypeMapper.updateByPrimaryKeySelective(courseType);
        }
        return flag;
    }

    @Override
    public CourseType selectCourseTypeByKey(String id) {
        CourseType courseType = courseTypeMapper.selectByPrimaryKey(id);
        return courseType;
    }


    @Override
    public PageInfo<CourseView> getAllCourseListByParam(Map param) {

        String schoolId = getValue(param, "schoolId").toString();
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String cycleId = getValue(param, "cycleId").toString();
        PageHelper.startPage(pageNum, pageSize);
        List<CourseView> courseViewList = a_courseMapper.findCourseBySchoolIdAndCycleId(schoolId, cycleId);
        PageInfo<CourseView> pageInfo = new PageInfo<>(courseViewList);
        return pageInfo;
    }

    @Override
    public List<ClassSection> findAllXd(String schoolId) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("section_year asc");
        example.setOrderByClause("limit_age");
        List<ClassSection> list = classSectionMapper.selectByExample(example);
//        List<ClassSection> list1 = new ArrayList<>();
//        if (list.size() > 0) {
//            for (ClassSection classSection : list) {
//                if (classSection.getName().equals("小学") && list.size() >= 1) {
//                    list1.add(0, classSection);
//                } else if (classSection.getName().equals("初中") && list.size() >= 2) {
//                    list1.add(1, classSection);
//                } else if (classSection.getName().equals("高中") && list.size() >= 3) {
//                    list1.add(2, classSection);
//                }
//            }
//            return list1;
//        }
        return list;
    }

    public ClassView findClassNameByXdAndClassId(String classId, String xd) {
        ClassView classView = a_classViewMapper.findClassNameByXdAndClassId(classId, xd);
        return classView;
    }

    @Override
    public PageInfo<BZRView> findteacherByNameAndSchoolICycleId(String cycleId, String schoolId, String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrViewList = a_masterMapper.findteacherByNameAndSchoolICycleId(cycleId, schoolId, name);
        PageInfo<BZRView> pageInfo = new PageInfo<>(bzrViewList);
        return pageInfo;
    }

    @Override
    public List<Course> findAllCourseBySchoolIdAndCycleId(String schoolId, String cycleId) {
        schoolId = (schoolId == null ? "" : schoolId);
        cycleId = (cycleId == null ? "" : cycleId);
        CourseExample example = new CourseExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId);
        List<Course> courseList = courseMapper.selectByExample(example);
        return courseList;
    }

    @Transactional
    public void batchInsertCourseClass(List<CourseClass> courseClassList) {
        a_courseClassMapper.batchInsertCourseClass(courseClassList);
    }

    @Override
    public PageInfo<BZRView> findAllCourseTeacherBycourseClassList(List<CourseClass> courseClassList, int pageNum, int pageSize, String cycleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrViewList = a_masterMapper.findAllCourseTeacherBycourseClassList(courseClassList, cycleId);
        PageInfo<BZRView> pageInfo = new PageInfo<>(bzrViewList);
        return pageInfo;
    }

    @Override
    public List<CourseClass> findAllCourseClassByCourseId(String courseId) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        if (courseClasses != null) {
            return courseClasses;
        }
        return new ArrayList<CourseClass>();
    }

    @Override
    public int updateCourseClassByPrimareyKey(CourseClass courseClass) {
        int succ = courseClassMapper.updateByPrimaryKeySelective(courseClass);
        return succ;
    }

    @Override
    public void insertBatchTeacherClass(List<TeacherClass> correctTeacherClassList) {
        a_masterMapper.insertBatchTeacherClass(correctTeacherClassList);
    }


    @Override
    public List<CourseClassView> findAllCourseTeacherBySchoolId(String schoolId) {
        List<CourseClassView> courseClassViewList = a_courseClassMapper.findAllCourseTeacherBySchoolId(schoolId);
        return courseClassViewList;
    }

    @Override
    public List<RoomType> getAllRoomTypeBySchoolId(String schoolId) {
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<RoomType> list = roomTypeMapper.selectByExample(example);
        if (list != null) {
            return list;
        }
        return new ArrayList<RoomType>();
    }

    @Override
    public List<CourseClass> findClassIdByCourseId(String courseId) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        return courseClasses;
    }

    public List<Course> findAllCourseBySchoolId(String schoolId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<Course> courseList = courseMapper.selectByExample(example);
        return courseList;
    }


    public CourseType findCourseTypeByName(String oneCourse, String schoolId) {
        CourseTypeExample example = new CourseTypeExample();
        example.createCriteria().andDelFlagEqualTo(0).andNameEqualTo(oneCourse);
//        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(oneCourse);
        List<CourseType> courseTypeList = courseTypeMapper.selectByExample(example);
        if (courseTypeList.size() > 0) {
            return courseTypeList.get(0);
        }
        return new CourseType();
    }

    public PageInfo<TeacherClass> findMasterByClassIdListAndType(List<String> list, int pageNum, int pageSize, String cycleId,int nj) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherClass> TeacherClassList = a_refTeacherClassMapper.findMasterByClassIdListAndType(list, cycleId,nj);
        PageInfo<TeacherClass> pageInfo = new PageInfo<>(TeacherClassList);
        return pageInfo;

    }

    @Override
    public int deleteTeacherClassByClassId(String classId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andClassIdEqualTo(classId);
        int succ = teacherClassMapper.deleteByExample(example);
        return succ;
    }

    @Override
    public CourseClass selectCourseClassByKey(String courseClassId) {
        CourseClass courseClass = courseClassMapper.selectByPrimaryKey(courseClassId);
        return courseClass;
    }

    @Override
    public List<BZRView> findCourseTeacherByCycleIdAndSchoolIdAndName(String schoolId, String cycleId, String teacherName) {
        List<BZRView> bzrViewList = a_masterMapper.findCourseTeacherByCycleIdAndSchoolIdAndName(cycleId, schoolId, teacherName);
        return bzrViewList;
    }

    @Override
    public TeacherClass findTeacherClassByClassIdCycleIdTeacherId(String classId, String cycleId, String teacherId, int i) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andClassIdEqualTo(classId).andTeacherIdEqualTo(teacherId).andCycleIdEqualTo(cycleId).andTypeEqualTo(i);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        if (teacherClasses.size() > 0) {
            return teacherClasses.get(0);
        }
        return new TeacherClass();
    }

    @Override
    public List<TeacherClass> findAllClassIdByCycleId(String cycleId) {
        if (cycleId == null)
            cycleId = "";
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andCycleIdEqualTo(cycleId);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        return teacherClasses;
    }

    @Override
    public PageInfo<BZRView> getAllMasterByGradeClassIds(List<String> classIdList, int pageNum, int pageSize, String xdId, int nj, String cycleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> list = a_masterMapper.getAllMasterByGradeClassIds(classIdList, xdId, nj,cycleId);
        PageInfo<BZRView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<TeacherClass> findLastMasterByPreCycleId(String preCycleId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        return teacherClasses;
    }


    @Override
    public List<BZRView> findLastCourseClassByPreCycleId(String preCycleId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId);
        List<Course> courseList = courseMapper.selectByExample(example);
        List<String> courseIdList = new ArrayList<>();
        for (Course course : courseList) {
            courseIdList.add(course.getId());
        }
        List<BZRView> bzrViewList = a_masterMapper.findAllCourseTeacherBycourseClassIdList(courseIdList);
        return bzrViewList;
    }

    @Override
    public void batchInsertCourse(List<Course> courseList) {
        a_courseMapper.batchInsertCourse(courseList);
    }

    @Override
    public List<ClassRoom> findAllClassRoomByCycleId(String cycleId) {
        ClassRoomExample example = new ClassRoomExample();
        ClassRoomExample.Criteria criteria = example.createCriteria();

        List<String> list = getRoomIdList(cycleId);
        if (list.size() > 0)
            criteria.andIdIn(list);
        else
            return new ArrayList<ClassRoom>();

        List<ClassRoom> classRoomList = classRoomMapper.selectByExample(example);
        return classRoomList;
    }


    @Override
    public List<ClassRoom> findAllClassRoomBySchoolId(String schoolId) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);
        List<ClassRoom> classRoomList = classRoomMapper.selectByExample(example);
        if (classRoomList.size() > 0) {
            return classRoomList;
        }
        return new ArrayList<ClassRoom>();
    }

    @Override
    public List<RefClassRoom> findAllRefClassRoomByClassRoomId(List<ClassRoom> classRoomListByschoolId) {
        List<RefClassRoom> refClassRoomList = a_refClassRoomMapper.findAllRefClassRoomByClassRoomId(classRoomListByschoolId);
        if (refClassRoomList.size() > 0) {
            return refClassRoomList;
        }
        return new ArrayList<RefClassRoom>();
    }

    @Override
    public void batchInsertRefClassRoom(List<RefClassRoom> correctRefClassRoomList) {
        a_refClassRoomMapper.batchInsertRefClassRoom(correctRefClassRoomList);
    }

    @Override
    public PageInfo<RefClassRoomView> getRefClassRoomList(int pageNum, int pageSize, String schoolId, String cycleId, int nj, String xdId) {
        PageHelper.startPage(pageNum, pageSize);
        List<RefClassRoomView> list = a_refClassRoomMapper.getRefClassRoomList(schoolId, cycleId,nj,xdId);
        PageInfo<RefClassRoomView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<SchoolType> findAllSchoolTypeBySchoolId(String schoolId) {
        SchoolTypeExample example = new SchoolTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolType> schoolTypeList = schoolTypeMapper.selectByExample(example);
        return schoolTypeList;
    }

    @Override
    public int updateRefClassRoomByKey(RefClassRoom refClassRoom) {
        return refClassRoomMapper.updateByPrimaryKeySelective(refClassRoom);
    }

    public List<String> getRoomIdList(String cycleId) {
        RefRoomCycleExample example = new RefRoomCycleExample();
        example.createCriteria().andCycleIdEqualTo(cycleId);

        List<RefRoomCycle> list = refRoomCycleMapper.selectByExample(example);

        List<String> roomIdList = new ArrayList<>();
        for (RefRoomCycle refRoomCycle : list) {
            roomIdList.add(refRoomCycle.getRoomId());
        }

        return roomIdList;
    }

    @Override
    public RefClassRoomView findRefClassRoomViewByRefId(String refId) {
        RefClassRoomView classRoomView = a_refClassRoomMapper.findRefClassRoomViewByRefId(refId);
        return classRoomView;
    }

    @Override
    public List<ClassRoom> findBuildingByschoolTypeId(String schoolTypeId) {
        List<ClassRoom> list = a_classRoomMapper.findBuildingByschoolTypeId(schoolTypeId);
        return list;
    }

    @Override
    public List<CourseClassView> findRefCourseClassByCycleIdCourseId(String cycleId, String courseId) {
        List<CourseClassView> courseClassViewList = a_courseClassMapper.findRefCourseClassByCycleIdCourseId(cycleId, courseId);
        return courseClassViewList;
    }

    @Override
    public List<GradeClass> findGradeClassBySectionIdAndNj(List<A_CourseClassHour> a_courseClassHourArrayList) {
        List<GradeClass> gradeClassList = a_gradeClassMapper.findGradeClassBySectionIdAndNj(a_courseClassHourArrayList);
        return gradeClassList;
    }

    @Override
    public CourseClass findCourseClassByClassIdAndCourseId(String courseId, String id) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andClassIdEqualTo(id).andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        if (courseClasses.size() > 0) {
            return courseClasses.get(0);
        }
        return new CourseClass();
    }

    @Override
    public List<ClassRoom> findRooomsBySchoolTypeIdAndBuilding(String schoolTypeId, String building) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andSchoolTypeEqualTo(schoolTypeId).andTeachBuildingEqualTo(building);
        List<ClassRoom> rooms = classRoomMapper.selectByExample(example);
        return rooms;
    }

    @Override
    public List<RefClassRoom> findRefClassRoomByCycleId(String preCycleId) {
        RefClassRoomExample example = new RefClassRoomExample();
        example.createCriteria().andCycleIdEqualTo(preCycleId);
        return refClassRoomMapper.selectByExample(example);
    }

    @Override
    public List<CourseClass> findAllCourseClassByCourseList(List<Course> coursesListPre) {
        CourseClassExample example = new CourseClassExample();
        List<String> courseIdList = new ArrayList<>();

        if (coursesListPre.size() > 0) {
            for (Course course : coursesListPre) {
                courseIdList.add(course.getId());
            }
        }
        example.createCriteria().andCourseIdIn(courseIdList);
        return courseClassMapper.selectByExample(example);
    }

    @Override
    public List<GradeClassExtention> findAllGradeClassBySchoolId(String schoolId) {
        List<GradeClassExtention> gradeClassExtentionList = a_gradeClassMapper.findAllGradeClassBySchoolId(schoolId);
        return gradeClassExtentionList;
    }

    @Override
    public void saveStandardCourse(StandardCourse standardCourse) {

        standardCourse.setUpdateDate(System.currentTimeMillis());
        standardCourse.setDelFlag(0);
        if (standardCourse.getIsDictionary() != null && standardCourse.getIsDictionary().equals('1')) {
            standardCourse.setCourseTypeId(null);
        }
        if (StringUtils.isNotBlank(standardCourse.getId())) {

            standardCourseMapper.updateByPrimaryKeySelective(standardCourse);
        } else {
            standardCourse.setId(PrimaryKey.get());
            standardCourse.setCreateDate(System.currentTimeMillis());
            standardCourseMapper.insert(standardCourse);
        }

    }

    @Override
    public List<StandardCourse> findAllStandardCourseBySchoolId(String schoolId) {
        StandardCourseExample example = new StandardCourseExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);

        return standardCourseMapper.selectByExample(example);
    }

    @Override
    public StandardCourse findStandardCourseById(String id) {
        return standardCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delStandardCourseById(String id) {
        if (id != null) {
            standardCourseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void saveDailyHour(DailyHour dailyHour) {
        dailyHour.setDelFlag(0);
        dailyHour.setUpdateTime(System.currentTimeMillis());
        if (null == dailyHour.getId()) {
            dailyHour.setId(PrimaryKey.get());
            dailyHour.setCreateTime(System.currentTimeMillis());
            dailyHourMapper.insert(dailyHour);
        } else {
            dailyHourMapper.updateByPrimaryKeySelective(dailyHour);
        }
    }

    @Override
    public void batchInsertDailyHour(List<DailyHour> dailyHourList) {
        a_dailyHoureMapper.batchInsertDailyHour(dailyHourList);

    }

    @Override
    public PageInfo<DailyHourView> findDailyHourByXdAndCycleIdAndNj(String schoolId, String xdId, String cycleId, String nj, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DailyHourView> dailyHourViewList = a_dailyHoureMapper.findDailyHourByXdAndCycleIdAndNj(schoolId, xdId, cycleId, nj);
        PageInfo<DailyHourView> pageInfo = new PageInfo<>(dailyHourViewList);
        return pageInfo;
    }

    @Override
    public DailyHour findDailyHourById(String dailyHourId) {
        return dailyHourMapper.selectByPrimaryKey(dailyHourId);
    }

    @Override
    public void delDailyHourById(String dailyId) {
        dailyHourMapper.deleteByPrimaryKey(dailyId);
    }


//    @Override
//    public void batchSaveCourseNode(List<CourseNode> courseNodeList) {
//        a_courseNodeMapper.batchSaveCourseNode(courseNodeList);
//    }

    @Override
    public CourseNodeInit findCourNodeInitByCycleIdAndSchoolIdAndTimeSection(String schoolId, String cycleId, String time_section) {
//        CourseNodeInitExample example = new CourseNodeInitExample();
//        example.createCriteria().andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId).andMonthStartEndEqualTo(time_section);
//        List<CourseNodeInit> courseNodeInitList = courseNodeInitMapper.selectByExample(example);
//        if (courseNodeInitList.size() > 0) {
//            return courseNodeInitList.get(0);
//        }
        return new CourseNodeInit();
    }

    @Transactional
    public void saveCourseNodeInit(CourseNodeInit courseNodeInit, List<CourseNode> courseNodeList) {
        int succ = 0;
        courseNodeInit.setCreateTime(System.currentTimeMillis());
        courseNodeInit.setDelFlag(0);
        //根据initId查询是否已存在
        CourseNodeInit courseNodeInitDB = courseNodeInitMapper.selectByPrimaryKey(courseNodeInit.getId());
        if (courseNodeInitDB == null) {
            succ = courseNodeInitMapper.insert(courseNodeInit);
        }else {
            succ = courseNodeInitMapper.updateByPrimaryKeySelective(courseNodeInit);
            CourseNodeExample example = new CourseNodeExample();
            example.createCriteria().andCourseNodeInitIdEqualTo(courseNodeInit.getId());
            succ = courseNodeMapper.deleteByExample(example);
        }

        if (succ > 0) {
            a_courseNodeMapper.batchSaveCourseNode(courseNodeList);
        }
    }

    public PageInfo<CourseNodeInit> findCourseNodeInitBySchoolId(String schoolId, Integer pageNum, Integer pageSize, String cycleId) {
        schoolId = (schoolId == null ? "" : schoolId);
        cycleId = (cycleId == null ? "" : cycleId);

        CourseNodeInitExample example = new CourseNodeInitExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId).andDelFlagEqualTo(0);
        PageHelper.startPage(pageNum, pageSize);
        List<CourseNodeInit> courseNodeInitList = courseNodeInitMapper.selectByExample(example);
        PageInfo<CourseNodeInit> pageInfo = new PageInfo<>(courseNodeInitList);
        return pageInfo;
    }

    @Override
    public CourseNodeInit findCourseNodeInitById(String nodeId) {
        return courseNodeInitMapper.selectByPrimaryKey(nodeId);
    }

    @Override
    public List<CourseNode> findCourseNodeByNodeId(String nodeId) {
        nodeId = (nodeId == null ? "" : nodeId);

        CourseNodeExample example = new CourseNodeExample();
        example.createCriteria().andCourseNodeInitIdEqualTo(nodeId);
        example.setOrderByClause("start_time");
        return courseNodeMapper.selectByExample(example);
    }

    @Transactional
    public void delCourseNodeInit(String nodeId) {
        courseNodeInitMapper.deleteByPrimaryKey(nodeId);

        CourseNodeExample example = new CourseNodeExample();
        example.createCriteria().andCourseNodeInitIdEqualTo(nodeId);
        courseNodeMapper.deleteByExample(example);

    }


    @Override
    public int updateCourseNodeInitById(CourseNodeInit courseNodeInit) {
        return courseNodeInitMapper.updateByPrimaryKeySelective(courseNodeInit);

    }

    @Override
    public void updateCourseClassByCourseIdAndClassId(CourseClass courseClass) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andCourseIdEqualTo(courseClass.getCourseId()).andClassIdEqualTo(courseClass.getClassId());
        courseClassMapper.updateByExampleSelective(courseClass, example);
    }

    @Override
    public int saveRoomType(RoomType roomType, User user) {
        roomType.setUpdateBy(user.getId());
        int succ = 0;
        if (StringUtils.isNotEmpty(roomType.getId())) {
            roomType.setUpdateDate(System.currentTimeMillis());
            succ = roomTypeMapper.updateByPrimaryKeySelective(roomType);
        } else {
            roomType.setCreateBy(user.getId());
            roomType.setCreateDate(System.currentTimeMillis());
            roomType.setId(PrimaryKey.get());
            roomType.setDelFlag(0);//0显示 1隐藏
            roomType.setSchoolId(user.getSchoolId());
            succ = roomTypeMapper.insert(roomType);
        }
        return succ;
    }

    public void batchDelCourseClass(String courseId) {
        a_courseClassMapper.batchDelByCourseId(courseId);
    }

    @Override
    public PageInfo<StandardCourse> findAllStandardCourseBySchoolIdAndPageNum(String schoolId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StandardCourse> standardCourseList = a_standardCourseMapper.findAllStandardCourseBySchoolIdAndPageNum(schoolId);
        PageInfo<StandardCourse> pageInfo = new PageInfo<>(standardCourseList);
        return pageInfo;
    }


    public int saveCourse(Course course, User user) {
        int flag = 0;
        course.setSchoolId(user.getSchoolId());
        if (StringUtil.isEmpty(course.getId())) {
            //执行新增操作
            course.setId(PrimaryKey.get());
            course.setDelFlag(0);
            flag = courseMapper.insertSelective(course);
        } else {
            //执行修改操作
            flag = courseMapper.updateByPrimaryKeySelective(course);
        }
        return flag;
    }

    public List<CourseNodeInit> findCourseNodeInitByCycleId(String cycleId) {
        CourseNodeInitExample example = new CourseNodeInitExample();
        example.createCriteria().andCycleIdEqualTo(cycleId).andDelFlagEqualTo(0);
        example.setOrderByClause("end_week");
        return courseNodeInitMapper.selectByExample(example);
    }

        public void batchInsertRefRoomCycle(List<RefRoomCycle> refRoomCycleList) {
            a_refRoomCycleMapper.batchInsertRefRoomCycle(refRoomCycleList);
        }

    @Override
    public List<GradeClass> getAllClassBySchoolIdAndNj(String schoolId, String nj,String xdId) {
        GradeClassExample example = new GradeClassExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId).andNjEqualTo(NumberConvertUtil.convertS2I(nj)).andXdEqualTo(xdId);
        example.setOrderByClause("nj");
        return  gradeClassMapper.selectByExample(example);
    }


}
