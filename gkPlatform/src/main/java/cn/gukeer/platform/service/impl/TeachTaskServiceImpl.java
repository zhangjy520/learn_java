
package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.BZRView;
import cn.gukeer.platform.modelView.ClassView;
import cn.gukeer.platform.modelView.CourseClassView;
import cn.gukeer.platform.modelView.RefClassRoomView;
import cn.gukeer.platform.persistence.dao.*;
import cn.gukeer.platform.persistence.entity.*;

import cn.gukeer.platform.service.TeachTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
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
    A_Teach_CourseMapper a_teach_courseMapper;

    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    GradeClassMapper gradeClassMapper;

    @Autowired
    A_ClassRoomExtensionMapper classRoomExtensionMapper;

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

        if (pageSize != -1 || pageSize != 0) {
            PageHelper.startPage(pageNum, pageSize);
        }
        TeachCycleExample teachCycleExample = new TeachCycleExample();
        TeachCycleExample.Criteria criteria = teachCycleExample.createCriteria();
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        teachCycleExample.setOrderByClause("create_date desc");
        List<TeachCycle> list = teachCycleMapper.selectByExample(teachCycleExample);
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
        example.setOrderByClause("create_date DESC");
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
        return null;
    }

    @Override
    public PageInfo<ClassRoom> getClassRoomList(Map param) {

        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String schoolId = getValue(param, "schoolId").toString();
        String cycleId = getValue(param, "cycleId").toString();

        pageSize = (pageSize == 0 ? 10 : pageSize);
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        ClassRoomExample example = new ClassRoomExample();
        example.setOrderByClause("create_date DESC");
        ClassRoomExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(0);
        if (StringUtil.isNotEmpty(schoolId)) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (StringUtil.isNotEmpty(cycleId)) {
            List<String> list = getRoomIdList(cycleId);
            if (list.size()>0)
                criteria.andRoomIdIn(list);
            else return new PageInfo<>(new ArrayList<ClassRoom>());
        }


        List<ClassRoom> roomList = classRoomMapper.selectByExample(example);
        PageInfo<ClassRoom> pageInfo = new PageInfo<ClassRoom>(roomList);

        return pageInfo;
    }

    @Override
    public int saveClassRoom(ClassRoom classRoom, String pri) {
        int flag = 0;
        if (StringUtil.isEmpty(classRoom.getRoomId())) {
            //执行新增操作
            classRoom.setRoomId(pri);
            flag = classRoomMapper.insertSelective(classRoom);
        } else {
            //执行修改操作
            flag = classRoomMapper.updateByPrimaryKeySelective(classRoom);
        }
        return flag;
    }

    @Transactional
    @Override
    public int batchInsertClassRoom(List<ClassRoom> list) {
        classRoomExtensionMapper.insertClassRoomBatch(list);
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
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("create_date desc");
        List<CourseType> list = courseTypeMapper.selectByExample(example);
        PageInfo pageInfo = null;
        if (list.size() > 0 && list != null) {
            pageInfo = new PageInfo<>(list);
            return pageInfo;
        }
        return null;
    }

    public Course getCourseByPrimaryKey(String id) {
        CourseExample example = new CourseExample();
        example.createCriteria().andIdEqualTo(id);
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
    public PageInfo<Course> getAllCourseListByParam(Map param) {

        String schoolId = getValue(param, "schoolId").toString();
        int pageNum = NumberConvertUtil.convertS2I(getValue(param, "pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(getValue(param, "pageSize").toString());
        String cycleId = getValue(param, "cycleId").toString();

        CourseExample example = new CourseExample();
        PageHelper.startPage(pageNum, pageSize);
        example.createCriteria().andSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0).andCycleIdEqualTo(cycleId);

        List<Course> list = courseMapper.selectByExample(example);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<ClassSection> findAllXd(String schoolId) {
        ClassSectionExample example = new ClassSectionExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        List<ClassSection> list = classSectionMapper.selectByExample(example);
        if (list.size() > 0) {
            return list;
        }
        return null;
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
        CourseExample example = new CourseExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andCycleIdEqualTo(cycleId);
        List<Course> courseList = courseMapper.selectByExample(example);
        return courseList;
    }

    @Override
    public void batchInsertCourseClass(List<CourseClass> courseClassList) {
        a_courseClassMapper.insertCourseClassBatch(courseClassList);
    }

    @Override
    public PageInfo<BZRView> findAllCourseTeacherBycourseClassList(List<CourseClass> courseClassList, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> bzrViewList = a_masterMapper.findAllCourseTeacherBycourseClassList(courseClassList);
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
        return null;
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
        return null;
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

    @Override
    public Course findCourseBySchoolIdAndCycleIdAndName(String id, String name, String schoolId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andCycleIdEqualTo(id).andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(name);
        List<Course> courseList = courseMapper.selectByExample(example);
        if (courseList.size() > 0) {
            return courseList.get(0);
        }
        return null;
    }


    public CourseType findCourseTypeByName(String oneCourse, String schoolId) {
        CourseTypeExample example = new CourseTypeExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId).andNameEqualTo(oneCourse);
        List<CourseType> courseTypeList = courseTypeMapper.selectByExample(example);
        if (courseTypeList.size() > 0) {
            return courseTypeList.get(0);
        }
        return null;
    }

    @Override
    public List<CourseType> findAllCourseTypeBySchoolId(String schoolId) {
        CourseTypeExample example = new CourseTypeExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);
        List<CourseType> courseTypeList = courseTypeMapper.selectByExample(example);
        if (courseTypeList.size() > 0) {
            return courseTypeList;
        }
        return null;
    }


    @Override
    public PageInfo<TeacherClass> findMasterByClassIdListAndType(List<String> list, int pageNum, int pageSize, String cycleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherClass> TeacherClassList = a_refTeacherClassMapper.findMasterByClassIdListAndType(list, cycleId);
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
    public TeacherClass findTeacherClassByClassIdCycleIdTeacherId(String classId, String cycleId, String teacherId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andClassIdEqualTo(classId).andTeacherIdEqualTo(teacherId).andCycleIdEqualTo(cycleId);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        if (teacherClasses.size() > 0) {
            return teacherClasses.get(0);
        }
        return null;
    }

    @Override
    public List<TeacherClass> findAllClassIdByCycleId(String cycleId) {
        TeacherClassExample example = new TeacherClassExample();
        example.createCriteria().andCycleIdEqualTo(cycleId);
        List<TeacherClass> teacherClasses = teacherClassMapper.selectByExample(example);
        return teacherClasses;
    }

    @Override
    public PageInfo<BZRView> getAllMasterByGradeClassIds(List<String> classIdList, int pageNum, int pageSize, String xdId, int nj) {
        PageHelper.startPage(pageNum, pageSize);
        List<BZRView> list = a_masterMapper.getAllMasterByGradeClassIds(classIdList, xdId, nj);
        PageInfo<BZRView> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<BZRView> findMasterByCycleId(String cycleId) {
        return null;
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
        if (list.size()>0)
            criteria.andRoomIdIn(list);
        else
            return null;

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
        return null;
    }

    @Override
    public List<RefClassRoom> findAllRefClassRoomByClassRoomId(List<ClassRoom> classRoomListByschoolId) {
        List<RefClassRoom> refClassRoomList = a_refClassRoomMapper.findAllRefClassRoomByClassRoomId(classRoomListByschoolId);
        if (refClassRoomList.size() > 0) {
            return refClassRoomList;
        }
        return null;
    }

    @Override
    public void batchInsertRefClassRoom(List<RefClassRoom> correctRefClassRoomList) {
        a_refClassRoomMapper.batchInsertRefClassRoom(correctRefClassRoomList);
    }

    @Override
    public PageInfo<RefClassRoomView> getRefClassRoomList(int pageNum, int pageSize, String schoolId, String cycleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<RefClassRoomView> list = a_refClassRoomMapper.getRefClassRoomList(schoolId, cycleId);
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

    public RefClassRoom findRefClassRoomByKey(String refId) {
        return refClassRoomMapper.selectByPrimaryKey(refId);
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


    public List<ClassRoom> findAllTeachBuilding(String schoolId) {
        List<ClassRoom> classRoomList = a_classRoomMapper.findAllTeachBuilding(schoolId);
        return classRoomList;
    }

    @Override
    public RefClassRoomView findRefClassRoomViewByRefId(String refId) {
        RefClassRoomView classRoomView = a_refClassRoomMapper.findRefClassRoomViewByRefId(refId);
        return classRoomView;
    }

    @Override
    public List<ClassRoom> findBuildingBySchoolTypeList(List<SchoolType> schoolTypeList) {
        List<ClassRoom> classRoomList = a_classRoomMapper.findBuildingBySchoolTypeList(schoolTypeList);
        return classRoomList;
    }

    @Override
    public List<ClassRoom> findBuildingByschoolTypeId(String schoolTypeId) {
        List<ClassRoom> list = a_classRoomMapper.findBuildingByschoolTypeId(schoolTypeId);
        return list;
    }

    @Override
    public List<CourseClassView> findRefCourseClassByCycleIdCourseId(String cycleId, String courseId) {
        List<CourseClassView> courseClassViewList = a_courseClassMapper.findRefCourseClassByCycleIdCourseId(cycleId,courseId);
        return courseClassViewList;
    }

    @Override
    public List<GradeClass> findGradeClassBySectionIdAndNj(List<A_CourseClassHour> a_courseClassHourArrayList) {
        List<GradeClass> gradeClassList = a_gradeClassMapper.findGradeClassBySectionIdAndNj(a_courseClassHourArrayList);
        return gradeClassList;
    }

    @Override
    public void updateCourseClassByList(List<CourseClass> courseClassList) {
        a_courseClassMapper.updateCourseClassByList(courseClassList);
    }

    @Override
    public CourseClass findCourseClassByClassIdAndCourseId(String courseId, String id) {
        CourseClassExample example = new CourseClassExample();
        example.createCriteria().andClassIdEqualTo(id).andCourseIdEqualTo(courseId);
        List<CourseClass> courseClasses = courseClassMapper.selectByExample(example);
        if (courseClasses.size()>0){
            return courseClasses.get(0);
        }
        return null;
    }

    @Override
    public List<ClassRoom> findRooomsBySchoolTypeIdAndBuilding(String schoolTypeId, String building) {
        ClassRoomExample example = new ClassRoomExample();
        example.createCriteria().andSchoolTypeEqualTo(schoolTypeId).andTeachBuildingEqualTo(building);
        List<ClassRoom> rooms = classRoomMapper.selectByExample(example);
        return rooms;
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


}
