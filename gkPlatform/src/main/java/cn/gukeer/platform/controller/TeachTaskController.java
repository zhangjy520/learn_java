package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.exception.ErrcodeException;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.GsonUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.modelView.importExport.IOCCourseTeacherView;
import cn.gukeer.platform.modelView.importExport.IOCMasterView;
import cn.gukeer.platform.modelView.importExport.IOCRefClassRoomView;
import cn.gukeer.platform.modelView.importExport.IOClassRoomView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/teach/task")
public class TeachTaskController extends BasicController {
    @Autowired
    TeachTaskService teachTaskService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ClassService classService;


    /**
     * 教务管理-课程管理
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/course/index")
    public String courseIndex(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();
        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", schoolId);
        param.put("cycleYear", cycleYear);
        param.put("cycleId", teachCycle.getId());
        //查询所有的课程
        PageInfo<CourseView> coursePageInfo = teachTaskService.getAllCourseListByParam(param);

        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("coursePageInfo", coursePageInfo);
        return "teachTask/course";
    }

    //课程编辑、增加的弹窗
    @RequestMapping(value = "/course/pop")
    public String courseAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String courseId = getParamVal(request, "id");
        String cycleId = getParamVal(request, "cycleId");

        //查询所有的标准课程类型
        List<StandardCourse> standardCourseList = teachTaskService.findAllStandardCourseBySchoolId(schoolId);

        //查询所有的教室类型
        List<RoomType> roomTypeList = teachTaskService.getAllRoomTypeBySchoolId(schoolId);
        //根据学校的查询所有的教学周期
        List<TeachCycle> teachCycleList = teachTaskService.getCycleList(schoolId);

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        TeachCycle teachCycle = new TeachCycle();
        if (GukeerStringUtil.isNullOrEmpty(cycleId)) {
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
        } else {
            teachCycle = teachTaskService.selectByKey(cycleId);
        }

        List<TeachCycle> semesterList = new ArrayList<>();
        String cycleYear = "";
        if (!GukeerStringUtil.isNullOrEmpty(teachCycle))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Course course = null;
        if (courseId != "") {
            course = teacherService.findCourseById(courseId);
        }

        model.addAttribute("standardCourseList", standardCourseList);
        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("teachCycleList", teachCycleList);

        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", teachCycle.getCycleSemester());
        model.addAttribute("course", course);
        model.addAttribute("yearmap", yearMap.keySet());
        return "teachTask/pop/courseAddPop";
    }

    // 增加授课班级的弹窗
    @RequestMapping(value = "/course/class/pop")
    public String addCourseClassPop(HttpServletRequest request, Model model) {
        String courseId = request.getParameter("id");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        if (courseId != "" && courseId != null) {
            model.addAttribute("courseId", courseId);
        }
        //查询所有的班级
        //首先查询所有的学段信息
        List<ClassSection> sectionList = classService.getAllClassSectionBySchoolId(schoolId);
        List<GradeClass> njList = classService.getAllClassBySchoolId(schoolId);

        for (int k = 0; k < njList.size() - 1; k++) {
            System.out.println(njList.size());
            for (int l = njList.size() - 1; l > k; l--) {
                System.out.println(njList.size());
                if (njList.get(l).getNj().equals(njList.get(k).getNj()) && njList.get(l).getXd().equals(njList.get(k).getXd())) {
                    njList.remove(l);
                }
            }
        }
        List<GradeClass> gradeClassList = classService.getAllClassBySchoolId(schoolId);

        //根据courseId,查询course_classs表，作为页面checkbox默认选中的判断条件
        List<CourseClass> courseClassList = teachTaskService.findClassIdByCourseId(courseId);
        for (GradeClass gradeClass : gradeClassList) {
            for (CourseClass courseClass : courseClassList) {
                if (courseClass.getClassId().equals(gradeClass.getId()))
                    gradeClass.setSelectId(gradeClass.getId());
            }
        }

        model.addAttribute("sectionList", sectionList);
        model.addAttribute("sectionListSize", sectionList.size());
        model.addAttribute("njList", njList);
        model.addAttribute("gradeClassList", gradeClassList);
        return "teachTask/pop/courseClassPop";
    }

    // 增加授课班级
    @RequestMapping(value = "/course/class/add", method = RequestMethod.POST)
    public String courseClass(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String classIds = getParamVal(request, "classIds");

        String[] idsArray = classIds.split(",");
        List<CourseClass> courseClasses = new ArrayList<>();

        teachTaskService.batchDelCourseClass(courseId);

        if (idsArray.length > 0) {
            for (int i = 0; i < idsArray.length; i++) {
                CourseClass courseClass = new CourseClass();
                if (idsArray[i] != "" && idsArray[i] != null) {
                    courseClass.setId(PrimaryKey.get());
                    courseClass.setClassId(idsArray[i]);
                    courseClass.setCourseId(courseId);
                }
                courseClasses.add(courseClass);
            }
            //先根据courseId查询所有的数据，做一个删除操作，再插入
            teachTaskService.batchInsertCourseClass(courseClasses);

        }
        return "teachTask/course";
    }

    //课程的增删改
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public String updateCourse(HttpServletRequest request, Model model, Course course) {
        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String id = getParamVal(request, "id");
        String cycleYear = getParamVal(request, "cycleYear");
        String _semester = request.getParameter("semester");

        TeachCycle teachCycle = null;
        //根据schoolId和学期学年数据查询cycleId
        if (cycleYear != "" && _semester != "") {
            teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(_semester), user.getSchoolId());
        } else {
            teachCycle = getLatestTeachCycle(user.getSchoolId());
        }

        if (id != "") {
            course.setId(id);
            if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                course.setDelFlag(1);
            }
            teachTaskService.saveCourse(course, user);
        } else if (ConstantUtil.INSERT.equalsIgnoreCase(type.trim())) {
            //根据cycleId name schoolId查询该课程是否已存在\
            String cycleId = "";

            if (teachCycle != null) {
                System.out.println(teachCycle.getId() + "oooooooo");
            }


            if (teachCycle.getId() != null) {
                course.setCycleId(teachCycle.getId());
            }
            teachTaskService.saveCourse(course, user);
        }
        return "redirect:/teach/task/course/pop";
    }

    //标准课程增加、编辑的弹窗
    @RequestMapping(value = "/course/standard/pop", method = RequestMethod.GET)
    public String addStandardCoursePop(HttpServletRequest request, Model model) {
        //查询所有的课程类型
        String id = request.getParameter("id");

        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Map param = new HashedMap();
        param.put("schoolId", schoolId);
        PageInfo<CourseType> courseTypePageInfo = teachTaskService.findAllCourseType(param);

        model.addAttribute("courseTypePageInfo", courseTypePageInfo);
        Course course = null;
        if (id != null) {
            StandardCourse standardCourse = teachTaskService.findStandardCourseById(id);
            model.addAttribute("standardCourse", standardCourse);
        }
        return "teachTask/pop/standardCoursePop";
    }


    //标准课程增加
    @RequestMapping(value = "/course/standard/add", method = RequestMethod.POST)
    public String addStandardCourse(HttpServletRequest request, Model model, StandardCourse standardCourse) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String id = standardCourse.getId();
        standardCourse.setSchoolId(schoolId);
        String courseTypeId = standardCourse.getCourseTypeId();
        if (StringUtils.isEmpty(courseTypeId)) {
            standardCourse.setIsDictionary(1);//1表示自定义学科
        } else {
            standardCourse.setIsDictionary(0);//0表示字典学科
        }
        teachTaskService.saveStandardCourse(standardCourse);
        return "teachTask/course";
    }

    //标准课程删除
    @RequestMapping(value = "/course/standard/del", method = RequestMethod.POST)
    public String delStandardCourse(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        //直接删掉，不做逻辑删除
        teachTaskService.delStandardCourseById(id);
        return "teachTask/course";
    }

    //标准课程首页
    @RequestMapping(value = "/course/standard/index", method = RequestMethod.GET)
    public String standardCourseIndex(HttpServletRequest request, Model model, StandardCourse standardCourse) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        //根据标准的课程名称查询的是否已经存在
        //根据schoolId查询所有的标准课程
        PageInfo<StandardCourse> pageInfo = teachTaskService.findAllStandardCourseBySchoolIdAndPageNum(schoolId, pageNum, pageSize);
        List<StandardCourse> standardCourseList = pageInfo.getList();
        if (standardCourseList.size() > 0) {

        }
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/standardCourse";
    }


    /**
     * 教务管理-课程类型管理
     *
     * @param request
     * @param model
     * @return
     */
    //   课程类型管理的弹窗
    @RequestMapping(value = "/course/type/pop", method = RequestMethod.GET)
    public String courseCategory(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        Map param = new HashMap();
        param.put("schoolId", schoolId);

        PageInfo<CourseType> pageInfo = teachTaskService.findAllCourseType(param);

        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/pop/courseCategoryPop";
    }

    @ResponseBody
    @RequestMapping(value = "/course/type/update/one", method = RequestMethod.POST)
    public ResultEntity courseCategoryAdd(HttpServletRequest request) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String oneCourse = request.getParameter("oneCourse");
        String typeId = request.getParameter("typeId");
        //根据名字是否已经存在
        if (typeId != null) {
            CourseType courseType = new CourseType();
            courseType.setId(typeId);
            courseType.setUpdateDate(new Date().getTime());
            courseType.setUpdateBy(user.getId());
            courseType.setName(oneCourse);
            courseType.setDelFlag(0);
            int succ = teachTaskService.saveCourseType(courseType);
            return ResultEntity.newResultEntity("修改成功", "teachTask/pop/courseCategoryPop");
        } else {
            if (oneCourse != null) {
                CourseType courseTypeFromDB = teachTaskService.findCourseTypeByName(oneCourse, schoolId);
                if (courseTypeFromDB.getId() != null) {
                    return ResultEntity.newResultEntity("课程已经存在,已经执行了更新操作", "teachTask/pop/courseCategoryPop");
                } else {
                    CourseType courseType = new CourseType();
                    courseType.setCreateBy(user.getId());
                    courseType.setDelFlag(0);
                    courseType.setName(oneCourse);
                    courseType.setSchoolId(schoolId);
                    int succ = teachTaskService.saveCourseType(courseType);
                    return ResultEntity.newResultEntity("创建成功", "teachTask/pop/courseCategoryPop");
                }
            }
        }
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/course/type/update", method = RequestMethod.POST)
    public String courseCategoryUpdate(@ModelAttribute CourseType courseType, HttpServletRequest request, Model model) {
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        CourseType courseTypeDB = null;
        if (id != "" && id != null) {
            courseTypeDB = teachTaskService.selectCourseTypeByKey(id);
        }
        if (courseType != null) {
            if (ConstantUtil.UPDATE.equalsIgnoreCase(type.trim())) {
                courseType.setDelFlag(0);
                courseType.setCreateBy(user.getId());
                courseType.setUpdateDate(System.currentTimeMillis());
                int succ = teachTaskService.saveCourseType(courseType);
            } else if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                courseTypeDB.setDelFlag(1);
                teachTaskService.saveCourseType(courseTypeDB);
            }
        }
        return "teachTask/courseCategoryPop";
    }


    /**
     * 教务管理-教学周期
     *
     * @param request
     * @param model
     * @return
     */

    //   周期管理首页
    @RequiresPermissions("teach:task:base")
    @RequestMapping(value = "/cycle/index")
    public String cycleIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());

        PageInfo<TeachCycle> pageInfo = teachTaskService.getTeachCycle(param);
        List<TeachCycle> list = pageInfo.getList();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("list", list);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("teachTaskId", getParamVal(request, "appId"));

        return "teachTask/cycleIndex";
    }


    //教学周期 - 增加的弹窗
    @RequestMapping(value = "/cycle/add/pop")
    public String cycleAddPop(HttpServletRequest request, Model model) {
        return "teachTask/pop/cycleAddPop";
    }

    //教学周期 - 编辑的弹窗
    @RequestMapping(value = "/cycle/edit/pop")
    public String cycleEditPop(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        TeachCycle teachCycle = null;
        if (id != null && id != "") {
            teachCycle = teachTaskService.selectByKey(id);
        }
        model.addAttribute("teachCycle", teachCycle);
        return "teachTask/pop/cycleEditPop";
    }

    //教学周期 - 增加教学周期
    @ResponseBody
    @RequestMapping(value = "/cycle/do", method = RequestMethod.POST)
    public ResultEntity cycleAdd(HttpServletRequest request, Model model) {
        String cycleId = request.getParameter("id");
        String type = request.getParameter("type");
        String cycleYear = request.getParameter("cycleYear");
        String cycleSemester = request.getParameter("cycleSemester");
        String _endDate = request.getParameter("endDate");
        String _beginDate = request.getParameter("beginDate");
        String weekCount = request.getParameter("weekCount");
        String _termsStart = getParamVal(request, "termBeginTime");
        User user = getLoginUser();
        Long termsStart = 0L;
        TeachCycle teachCycleDB = null;
        String schoolId = user.getSchoolId();
        try {
            termsStart = stringToLong(_termsStart, "YYYY-MM-DD");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(cycleId)) {
            teachCycleDB = teachTaskService.selectByKey(cycleId);
            if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
                teachCycleDB.setDelFlag(1);
                teachTaskService.saveTeachCycle(teachCycleDB);
                return ResultEntity.newResultEntity("删除成功", "teachTask/cycleIndex");
            } else if (ConstantUtil.UPDATE.equalsIgnoreCase(type.trim())) {
                teachCycleDB.setCreateBy(user.getId());
                teachCycleDB.setDelFlag(0);
                teachCycleDB.setCreateDate(System.currentTimeMillis());
                teachCycleDB.setCycleYear(cycleYear);
                teachCycleDB.setCycleSemester(NumberConvertUtil.convertS2I(cycleSemester));
                teachCycleDB.setBeginDate(Long.parseLong(_beginDate));
                teachCycleDB.setEndDate(Long.parseLong(_endDate));
                teachCycleDB.setSchoolId(schoolId);
                teachCycleDB.setTermBeginTime(termsStart);
                teachCycleDB.setWeekCount(NumberConvertUtil.convertS2I(weekCount));
                teachTaskService.saveTeachCycle(teachCycleDB);
                return ResultEntity.newResultEntity("修改成功", "teachTask/cycleIndex");
            }
        } else {
            //根据学年 学期  学校Id判断该学期是否已经存在
            TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
//                System.out.println(teachCycle.getId()+" iiii ii ");
            if (teachCycle.getId() != null) {
                return ResultEntity.newResultEntity("该学期已经存在，无需再创建", "teachTask/cycleIndex");
            } else {
                TeachCycle teachCycleNew = new TeachCycle();
                teachCycleNew.setCreateBy(user.getId());
                teachCycleNew.setDelFlag(0);
                teachCycleNew.setCreateDate(System.currentTimeMillis());
                teachCycleNew.setCycleYear(cycleYear);
                teachCycleNew.setCycleSemester(NumberConvertUtil.convertS2I(cycleSemester));
                teachCycleNew.setBeginDate(Long.parseLong(_beginDate));
                teachCycleNew.setEndDate(Long.parseLong(_endDate));
                teachCycleNew.setSchoolId(schoolId);
                teachCycleNew.setTermBeginTime(termsStart);

                teachCycleNew.setWeekCount(NumberConvertUtil.convertS2I(weekCount));
                teachTaskService.saveTeachCycle(teachCycleNew);
                //插入之后紧接着查询cycleId
                TeachCycle teachCycleThis = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
                String thisCycleId = teachCycleThis.getId();
                Map param = new HashMap<>();
                String preCycleId = null;
                TeachCycle teachCycle1 = null;
                if (NumberConvertUtil.convertS2I(cycleSemester) == 2) {
                    cycleSemester = "1";
                    param.put("schoolId", schoolId);
                    param.put("cycleYear", cycleYear);
                    param.put("cycleSemester", cycleSemester);
                    teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
                    if (teachCycle1.getId() != null) {
                        preCycleId = teachCycle1.getId();
                    }
                } else if (NumberConvertUtil.convertS2I(cycleSemester) == 1) {
                    String[] arr = cycleYear.split("-");
                    Integer before = Integer.parseInt(arr[0]) - 1;
                    Integer after = Integer.parseInt(arr[1]) - 1;
                    String preCycleYear = before.toString() + "-" + after.toString();
                    teachCycle1 = teachTaskService.getCycleByYearSemester(preCycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);

                    if (teachCycle1.getId() != null) {
                        preCycleId = teachCycle1.getId();
                    }
                }
                //根据preCycleId获取上一学期数据

                //获取上一学期课程
                List<Course> courseList = null;

                //获取上一学期教室信息
                List<ClassRoom> classRoomList = null;

                //获取上一学期班主任信息
                List<TeacherClass> teacherClassList = null;

                //获取上一学期任课教师信息
                List<BZRView> courseClassList = null;
                List<CourseClass> courseClassList1 = new ArrayList<>();
                //获取要同步的信息
                String synInfo = request.getParameter("synInfo");
                String[] infoArray = synInfo.split(",");
                if (infoArray.length > 0) {
                    for (int i = 0; i < infoArray.length; i++) {
                        if (preCycleId != null) {
                            String oneInfo = infoArray[i];
                            if (oneInfo.equals("教室管理")) {
                                classRoomList = teachTaskService.findAllClassRoomByCycleId(preCycleId);
                                List<RefRoomCycle> cycleList = new ArrayList<>();
                                if (classRoomList != null) {
                                    for (ClassRoom classRoom : classRoomList) {
                                        String roomId = PrimaryKey.get();
                                        classRoom.setId(roomId);

                                        //教室周期关联
                                        RefRoomCycle cycle = new RefRoomCycle();
                                        cycle.setCycleId(thisCycleId);
                                        cycle.setRoomId(roomId);
                                        cycle.setId(PrimaryKey.get());
                                        cycleList.add(cycle);
                                    }
                                }
                                if (classRoomList != null && classRoomList.size() > 0) {
                                    teachTaskService.insertClassRoomBatch(classRoomList);
                                    teachTaskService.batchSaveRefRoomCycle(cycleList);
                                }
                            } else if (oneInfo.equals("班主任安排")) {

                                teacherClassList = teachTaskService.findLastMasterByPreCycleId(preCycleId);
                                for (TeacherClass teacherClass : teacherClassList) {
                                    teacherClass.setCycleId(thisCycleId);
                                    //aaaaaaaaaaaaaaaaaaa teacherClass.setId(PrimaryKey.get());
                                }
                                if (teacherClassList != null && teacherClassList.size() > 0)
                                    teachTaskService.insertBatchTeacherClass(teacherClassList);
                            } else if (oneInfo.equals("任课教师安排")) {
                                //先去查一下这些
                                courseClassList = teachTaskService.findLastCourseClassByPreCycleId(preCycleId);
                                if (courseClassList != null && courseClassList.size() > 0) {
                                    for (BZRView bzrView : courseClassList) {
                                        CourseClass courseClass = new CourseClass();
                                        courseClass.setId(PrimaryKey.get());
                                        courseClass.setTeacherId(bzrView.getTeacherId());
                                        courseClass.setClassId(bzrView.getClassId());
                                        courseClass.setCourseId(bzrView.getCourseId());
                                        courseClassList1.add(courseClass);
                                    }
                                    teachTaskService.batchInsertCourseClass(courseClassList1);
                                }
                            } else if (oneInfo.equals("课程安排")) {

                                courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, preCycleId);
                                if (courseList != null && courseList.size() > 0) {
                                    for (Course course : courseList) {
                                        course.setId(PrimaryKey.get());
                                        course.setCycleId(thisCycleId);
                                    }
                                    teachTaskService.batchInsertCourse(courseList);
                                }
                            } else if (oneInfo.equals("班级教室安排")) {
                                //查询上一学期班级教室安排
                                List<RefClassRoom> refClassRoomList = teachTaskService.findRefClassRoomByCycleId(preCycleId);
                                List<RefClassRoom> refClassRoomListNew = new ArrayList<>();
                                if (refClassRoomList.size() > 0) {
                                    for (RefClassRoom refClassRoom : refClassRoomList) {
                                        RefClassRoom refClassRoomNew = new RefClassRoom();
                                        refClassRoomNew.setId(PrimaryKey.get());
                                        refClassRoomNew.setRoomId(refClassRoom.getRoomId());
                                        refClassRoomNew.setSchoolTypeId(refClassRoom.getSchoolTypeId());
                                        refClassRoomNew.setGradeClass(refClassRoom.getGradeClass());
                                        refClassRoomNew.setCycleId(thisCycleId);
                                        refClassRoomListNew.add(refClassRoomNew);
                                    }
                                }
                                //批量插入班级教室
                                if (refClassRoomListNew != null && refClassRoomListNew.size() > 0)
                                    teachTaskService.batchInsertRefClassRoom(refClassRoomListNew);
                            } else if (oneInfo.equals("科目课时安排")) {
                                //首先要根据周期查询上一学期的课程list
                                List<Course> coursesListPre = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, preCycleId);
                                //查询上一学期科目查询所有的科目课时
                                List<CourseClass> courseClassListPre = teachTaskService.findAllCourseClassByCourseList(coursesListPre);
                                List<CourseClass> courseClassListNew = new ArrayList<>();
                                if (courseClassListPre.size() > 0) {
                                    for (CourseClass courseClass : courseClassListPre) {
                                        CourseClass courseClassNew = new CourseClass();
                                        courseClassNew.setId(PrimaryKey.get());
                                        courseClassNew.setCourseHour(courseClass.getCourseHour());
                                        courseClassNew.setTeacherId(courseClass.getTeacherId());
                                        courseClassNew.setClassId(courseClass.getClassId());
                                        courseClassNew.setCourseId(courseClass.getCourseId());
                                        courseClassListNew.add(courseClassNew);
                                    }
                                    if (courseClassListNew != null && courseClassListNew.size() > 0)
                                        teachTaskService.batchInsertCourseClass(courseClassListNew);
                                }
                            }
                        }
                    }
                }
                return ResultEntity.newResultEntity("成功创建教学周期", "teachTask/cycleIndex");
            }
        }
        return null;
    }

    @RequestMapping(value = "/cycle/syn/pop")
    public String synPop(HttpServletRequest request, Model model) {
        String cycleId = request.getParameter("cycleId");
        model.addAttribute("cycleId", cycleId);
        return "teachTask/pop/syn";
    }

    //教学周期 - 同步数据
    @RequestMapping(value = "/cycle/syn")
    public String syn(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //获取教学周期id
        String cycleId = request.getParameter("cycleId");
        TeachCycle teachCycle = null;
        if (cycleId != null) {
            teachCycle = teachTaskService.selectByKey(cycleId);
        }
        int cycleSemester = 0;
        String cycleYear = null;
        if (teachCycle != null) {
            cycleSemester = teachCycle.getCycleSemester();
            cycleYear = teachCycle.getCycleYear();
        }
        Map param = new HashMap<>();
        String preCycleId = null;
        TeachCycle teachCycle1 = null;
        if (cycleSemester == 2) {
            cycleSemester = 1;
            param.put("schoolId", schoolId);
            param.put("cycleYear", cycleYear);
            param.put("cycleSemester", cycleSemester);
            teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
            preCycleId = teachCycle1.getId();
        } else if (cycleSemester == 1) {
            String[] arr = cycleYear.split("-");
            Integer before = Integer.parseInt(arr[0]) - 1;
            Integer after = Integer.parseInt(arr[1]) - 1;
            String preCycleYear = before.toString() + "-" + after.toString();
            teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
            preCycleId = teachCycle1.getId();
        }
        //根据preCycleId获取上一学期数据

        //获取上一学期课程
        List<Course> courseList = null;

        //获取上一学期教室信息
        List<ClassRoom> classRoomList = null;

        //获取上一学期班主任信息
        List<TeacherClass> teacherClassList = null;

        //获取上一学期任课教师信息
        List<BZRView> courseClassList = null;
        List<CourseClass> courseClassList1 = new ArrayList<>();
        //获取要同步的信息
        String synInfo = request.getParameter("synInfo");
        String[] infoArray = synInfo.split(",");
        if (infoArray.length > 0) {
            for (int i = 0; i < infoArray.length; i++) {
                String oneInfo = infoArray[i];
                if (oneInfo.equals("教室管理")) {
                    List<ClassRoom> classRoomList1 = teachTaskService.findAllClassRoomByCycleId(cycleId);
                    classRoomList = teachTaskService.findAllClassRoomByCycleId(preCycleId);

                    List<RefRoomCycle> cycleList = new ArrayList<>();
                    for (ClassRoom classRoom : classRoomList) {
                        String roomId = PrimaryKey.get();
                        classRoom.setId(roomId);

                        //教室，周期关联
                        RefRoomCycle cycle = new RefRoomCycle();
                        cycle.setId(PrimaryKey.get());
                        cycle.setCycleId(cycleId);
                        cycle.setRoomId(roomId);

                        cycleList.add(cycle);
                    }
                    teachTaskService.insertClassRoomBatch(classRoomList);
                    teachTaskService.batchSaveRefRoomCycle(cycleList);//教室周期关联
                } else if (oneInfo.equals("班主任安排")) {

                    teacherClassList = teachTaskService.findLastMasterByPreCycleId(preCycleId);
                    for (TeacherClass teacherClass : teacherClassList) {
                        teacherClass.setCycleId(cycleId);
                        //aaaaaaaaaaaaaaaaaaaa    teacherClass.setId(PrimaryKey.get());
                    }
                    teachTaskService.insertBatchTeacherClass(teacherClassList);
                } else if (oneInfo.equals("任课教师安排")) {
                    //先去查一下这些
                    courseClassList = teachTaskService.findLastCourseClassByPreCycleId(preCycleId);
                    for (BZRView bzrView : courseClassList) {
                        CourseClass courseClass = new CourseClass();
                        courseClass.setId(PrimaryKey.get());
                        courseClass.setTeacherId(bzrView.getTeacherId());
                        courseClass.setClassId(bzrView.getClassId());
                        courseClass.setCourseId(bzrView.getCourseId());
                        courseClassList1.add(courseClass);
                    }
                    teachTaskService.batchInsertCourseClass(courseClassList1);
                } else if (oneInfo.equals("课程安排")) {
                    courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, preCycleId);
                    for (Course course : courseList) {
                        course.setId(PrimaryKey.get());
                        course.setCycleId(cycleId);
                    }
                    teachTaskService.batchInsertCourse(courseList);
                }
            }
        }
        return "teachTask/cycleIndex";
    }

    //---------------------------------------------------begin-----------------------------------------------------

    //教室管理，主页
    @RequestMapping(value = "/room/index")
    public String classRoomManage(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String year = getParamVal(request, "year");
        String semester = getParamVal(request, "semester");
        User user = getLoginUser();

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(year, NumberConvertUtil.convertS2I(semester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(year))
            year = teachCycle.getCycleYear();

        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (year.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());
        param.put("cycleId", teachCycle.getId());

        PageInfo<ClassRoom> pageInfo = teachTaskService.getClassRoomList(param);
        List<ClassRoom> list = pageInfo.getList();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("chooseYear", year);
        model.addAttribute("chooseSemester", semester);

        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            request.getSession().setAttribute("teachTaskId", getParamVal(request, "appId"));

        return "teachTask/teachTaskRoom";
    }


    //修改，新增，页面
    @RequestMapping(value = "/room/update/index")
    public String roomIndex(HttpServletRequest request, Model model) {
        String roomId = getParamVal(request, "roomId");
        User user = getLoginUser();

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());
        ClassRoom room = teachTaskService.getRoomByPri(roomId);
        model.addAttribute("room", room);
        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("schoolTypeList", schoolTypeList);

        return "teachTask/pop/classRoomAdd";
    }

    //教室 修改，新增
    @RequestMapping(value = "/room/update", method = RequestMethod.POST)
    public String updateRoom(@ModelAttribute("inputForm") ClassRoom room, HttpServletRequest request) {

        User user = getLoginUser();

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());

        String pri = PrimaryKey.get();
        room.setSchoolId(user.getSchoolId());
        if (StringUtil.isNotEmpty(room.getId())) {

            room.setUpdateBy(user.getId());
            room.setUpdateDate(System.currentTimeMillis());

        } else if (StringUtil.isEmpty(room.getId())) {

            room.setCreateBy(user.getId());
            room.setCreateDate(System.currentTimeMillis());

            //周期教室关联
            RefRoomCycle cycle = new RefRoomCycle();
            cycle.setRoomId(pri);
            cycle.setCycleId(cycleLatest.getId());
            cycle.setId(PrimaryKey.get());
            teachTaskService.saveRefRoomCycle(cycle);
        }
        teachTaskService.saveClassRoom(room, pri);
        return "redirect:/teach/task/room/update/index?roomId=" + room.getId();
    }

    //教室，删除，批量删除
    @RequestMapping(value = "/room/delete", method = RequestMethod.POST)
    public void deleteRoom(HttpServletRequest request) {
        String roomIds = getParamVal(request, "roomId");
        List<String> roomIdList = ConstantUtil.splitWithOutNull(roomIds);

        for (String roomId : roomIdList) {
            ClassRoom room = new ClassRoom();
            room.setDelFlag(1);
            room.setId(roomId);
            teachTaskService.saveClassRoom(room, null);
        }
    }

    //教室导入模板
    @RequestMapping(value = "/room/download")
    public void exportRoomTemplate(HttpServletResponse response) {
        try {
            String fileName = "教室导入模板.xlsx";
            String anno = "注释：所有字段均为必填项\n" +
                    "          1.校区名称需和学籍管理中统一，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            new ExportExcel(false, "教室导入模板", IOClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入教室
    @ResponseBody
    @RequestMapping(value = "/room/import")
    public ResponseEntity importRoom(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        Long begin = System.currentTimeMillis();
        List<IOClassRoomView> errorRoomList = new ArrayList<IOClassRoomView>();
        List<ClassRoom> correctRoomList = new ArrayList<ClassRoom>();
        IOClassRoomView errorRoom = new IOClassRoomView();

        //获取最新的教学周期
        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());

        //翻译教室类型
        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());
        Map roomTypeMap = new HashMap();
        for (RoomType roomType : roomTypeList) {
            roomTypeMap.put(roomType.getName(), roomType.getId());//config_key冗余
        }

        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        Map schoolTypeMap = new HashMap();
        for (SchoolType schoolType : schoolTypeList) {
            schoolTypeMap.put(schoolType.getName(), schoolType.getId());
        }

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOClassRoomView> list = importExcel.getDataList(IOClassRoomView.class);
        List<RefRoomCycle> refRoomCycleList = new ArrayList<>();
        for (IOClassRoomView roomView : list) {
            try {
                errorRoom = roomView;
                ClassRoom room = new ClassRoom();
                String roomId = PrimaryKey.get();
                room.setId(roomId);
                //room.setRoomName(roomView.getRoomName());
                room.setTeachBuilding(roomView.getTeachBuilding());
                room.setRoomTypeName(roomView.getRoomTypeName());
                if (GukeerStringUtil.isNullOrEmpty(roomTypeMap.get(roomView.getRoomTypeName()))) {
                    throw new ErrcodeException("教室类型不存在");
                }
                room.setRoomType(roomTypeMap.get(roomView.getRoomTypeName()).toString());

                room.setSchoolTypeName(roomView.getSchoolTypeName());
                if (GukeerStringUtil.isNullOrEmpty(schoolTypeMap.get(roomView.getSchoolTypeName()))) {
                    throw new ErrcodeException("校区不存在");
                }
                room.setSchoolType(schoolTypeMap.get(roomView.getSchoolTypeName()).toString());
                room.setRoomNum(roomView.getRoomNum());
                room.setSchoolId(user.getSchoolId());
                room.setFloor(roomView.getFloor());
                room.setCount(roomView.getCount());
                room.setAvailableSeat(roomView.getAvailableSeat());
                room.setExamSeat(roomView.getExamSeat());
                room.setCourseSelect(ConstantUtil.getKeyByValueAndFlag(roomView.getCourseSelect(), "yorn"));
                room.setRemarks(roomView.getRemarks());
                room.setCreateBy(user.getId());
                room.setCreateDate(System.currentTimeMillis());

                //关联教室，周期
                RefRoomCycle cycle = new RefRoomCycle();
                cycle.setId(PrimaryKey.get());
                cycle.setRoomId(roomId);
                cycle.setCycleId(cycleLatest.getId());
                refRoomCycleList.add(cycle);

                correctRoomList.add(room);
            } catch (Exception e) {
                errorRoomList.add(errorRoom);
                continue;
            }
        }
        if (correctRoomList.size() > 0) {
            teachTaskService.insertClassRoomBatch(correctRoomList);
            //同时也要在ref_room_cycle表中插入这些数据
            teachTaskService.batchInsertRefRoomCycle(refRoomCycleList);
        }


        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correctRoomList.size() + "条成功，" + errorRoomList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorRoomList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //下载导入失败的classRoom列表
    @RequestMapping(value = "/room/error/export", method = RequestMethod.POST)
    public void errorRoom(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：所有字段均为必填项\n" +
                    "          1.校区一栏填写:主校区、西校区，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOClassRoomView> exportFile = new ArrayList<IOClassRoomView>();
            for (JsonElement jsonElement : jsonArray) {
                IOClassRoomView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOClassRoomView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "教室信息", IOClassRoomView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //角色分配
    @RequiresPermissions("renShi:role:view")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");

        HttpSession session = request.getSession();
        Object appId = session.getAttribute("teachTaskId");

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());//。。。。人事管理

        List<String> idList = new ArrayList<String>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }

        PageHelper.startPage(pageNum, pageSize);
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        if (idList.size() > 0) {
            Role role = new Role();
            role.setId(roleId);
            userRoleList = userService.findUserRoleByCriteria(idList, role, getLoginUser().getSchoolId());//List<Integer> ids, Role role,String schoolId
        }
        if (userRoleList.size() > 0) {
            List<String> userIds = new ArrayList<String>();
            for (UserRole userRole : userRoleList) {
                userIds.add(userRole.getUserId());
            }
            List<User> userList = userService.selectUsersInIds(userIds);
            List<String> teacherIdList = new ArrayList<>();
            List<Teacher> teacherList = new ArrayList<Teacher>();
            for (User user : userList) {
                teacherIdList.add(user.getRefId());
            }
            teacherList = teacherService.selectBatchTeachers(teacherIdList, getLoginUser().getSchoolId());
            PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("pageInfo", pageInfo);
        }

        if (roleId.equals("") && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }
        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "teachTask/roleFp";
    }

    //---------------------------------------------------end-----------------------------------------------------


    public TeachCycle getLatestTeachCycle(String schoolId) {
        List<TeachCycle> cycleList = teachTaskService.getCycleList(schoolId);
        TeachCycle cycleLatest = new TeachCycle();
        if (cycleList.size() > 0)
            cycleLatest = cycleList.get(0);
        return cycleLatest;
    }

    /**
     * 教务管理-班主任管理
     *
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = "/master/index")
    public String masterTeacherIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "sectionId");
        String nj = getParamVal(request, "nj");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        Map yearMap = new TreeMap();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            if (cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
        }

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }

        String cycleId = teachCycle.getId();
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }


        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = teachTaskService.findAllXd(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }

        if (nj == "" || nj == null) {
            nj = "1";
        }

        //获取所有的classId
        List<TeacherClass> teacherClasses = teachTaskService.findAllClassIdByCycleId(cycleId);
        List<String> classIdList = new ArrayList<>();
        if (teacherClasses != null) {
            for (TeacherClass teacherClass : teacherClasses) {
                classIdList.add(teacherClass.getClassId());
            }
        }
        //根据正班班主任分页 根据classIdList和type=1 去中间表插叙查询班主任 此次查询仅仅作为分页的存在意义
        PageInfo<TeacherClass> teacherClassPageInfo = null;
//        if (teacherClasses.size() > 0) {
        teacherClassPageInfo = teachTaskService.findMasterByClassIdListAndType(classIdList, pageNum, pageSize, cycleId, NumberConvertUtil.convertS2I(nj));
//        }
        model.addAttribute("teacherClassPageInfo", teacherClassPageInfo);
        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("chooseYear", cycleYear);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("nj", nj);
        if (teacherClasses == null || teacherClasses.size() == 0) {
            return "teachTask/masterIndex";
        }
        //根据所有的classId查询中间表 得到所有的班主任
        PageInfo<BZRView> pageInfo = teachTaskService.getAllMasterByGradeClassIds(classIdList, pageNum, pageSize, xdId, NumberConvertUtil.convertS2I(nj), cycleId);
        List<BZRView> bzrViewList = pageInfo.getList();
        List<BZRView> bzrViewPageList = new ArrayList<>();
        //两个list分别是班主任和副班主任
        List<BZRView> bzrViewNew = new ArrayList<>();
        List<BZRView> deputyBzrViewNew = new ArrayList<>();
        for (BZRView bzrView : bzrViewList) {
            if (bzrView.getType() == 1) {
                bzrViewNew.add(bzrView);
            } else if (bzrView.getType() == 2) {
                deputyBzrViewNew.add(bzrView);
            }
        }

        //循环两个list合并 放到最后的list中
        for (BZRView bzrView : bzrViewNew) {
            BZRView bzrView2 = new BZRView();
            bzrView2.setMasterName(bzrView.getTeacherName());
            bzrView2.setTeacherId(bzrView.getTeacherId());
            bzrView2.setClassName(bzrView2.getClassName());
            bzrView2.setSectionId(bzrView.getSectionId());
            bzrView2.setXdName(bzrView.getXdName());
            bzrView2.setClassId(bzrView.getClassId());
            bzrView2.setClassName(bzrView.getClassName());
            bzrView2.setCycleId(bzrView.getCycleId());
            String deputyIds = "";
            String deputyNames = "";
            for (BZRView bzrView1 : deputyBzrViewNew) {
                if (bzrView.getClassId().equals(bzrView1.getClassId())) {
                    String oneDeputyId = bzrView1.getTeacherId();
                    deputyIds += "," + oneDeputyId;
                    String oneDuputyName = bzrView1.getTeacherName();
                    deputyNames += "," + oneDuputyName;
                }
            }
            bzrView2.setDeputyIds(deputyIds);
            if (deputyNames.length() > 1) {
                bzrView2.setDeputymasterName(deputyNames.substring(1, deputyNames.length()));
            }
            bzrViewPageList.add(bzrView2);
        }


        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("bzrViewPageList", bzrViewPageList);
        model.addAttribute("cycleSemester", cycleSemester);

        return "teachTask/masterIndex";
    }


    //班主任编辑
    @RequestMapping(value = "/master/edit", method = RequestMethod.POST)
    public String masterEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherIdFromHouTai = request.getParameter("teacherIdFromHouTai");
        String teacherId = request.getParameter("teacherId");
        String deputyIds = request.getParameter("tempTeacherId");
        String classId = request.getParameter("classId");
        String cycleId = request.getParameter("cycleId");

        List<TeacherClass> TeacherClassList = new ArrayList<>();
        //全部根据classId进行批量更新
        if (classId != null) {
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.deleteTeacherClassByClassId(classId);
        }

        TeacherClass teacherClass = new TeacherClass();
        teacherClass.setType(1);
        teacherClass.setClassId(classId);
        teacherClass.setCycleId(cycleId);
        if (teacherId == "") {
            teacherClass.setTeacherId(teacherIdFromHouTai);
        } else {
            teacherClass.setTeacherId(teacherId);
        }
        TeacherClassList.add(teacherClass);
        String[] deputyIdArray = deputyIds.split(",");
        for (int i = 1; i < deputyIdArray.length; i++) {
            String oneDeputyId = deputyIdArray[i];
            TeacherClass teacherClass1 = new TeacherClass();
            teacherClass1.setClassId(classId);
            teacherClass1.setTeacherId(oneDeputyId);
            teacherClass1.setType(2);
            teacherClass1.setCycleId(cycleId);
            TeacherClassList.add(teacherClass1);
        }
        //批量插入
        teachTaskService.insertBatchTeacherClass(TeacherClassList);
        return "teachTask/masterIndex";
    }

    //班主任编辑的弹窗
    @RequestMapping(value = "/master/edit/pop", method = RequestMethod.GET)
    public String masterEditPop(HttpServletRequest request, Model model) {
        //根据
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        String classId = getParamVal(request, "classId");
        String _deputyName = getParamVal(request, "deputyName");
        String _master = getParamVal(request, "master");
        String teacherId = getParamVal(request, "teacherId");
        String deputyIds = getParamVal(request, "deputyIds");
        String cycleId = getParamVal(request, "cycleId");
        String deputyName = null;
        String master = null;
        try {
            deputyName = URLDecoder.decode(_deputyName, "UTF-8");
            master = URLDecoder.decode(_master, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //根据classId查询得到班级的名字
        GradeClass gradeClass = classService.selectClassById(classId);

        String[] arrDeputyIds = null;
        List<String> deputyIdsList = new ArrayList<>();
        if (deputyIds != null) {
            arrDeputyIds = deputyIds.split(",");
            if (arrDeputyIds != null && arrDeputyIds.length > 1) {
                for (int i = 1; i < arrDeputyIds.length; i++)
                    deputyIdsList.add(arrDeputyIds[i]);
            }
        }

//        //根据schoolId 所有老师的名字
        List<Teacher> _teacherList = teacherService.findAllTeacher(schoolId);
        JSON teacherList = (JSON) JSON.toJSON(_teacherList);
        String xd = gradeClass.getXd();
        //查询年级班级名字
        ClassView classView = teachTaskService.findClassNameByXdAndClassId(classId, xd);
        model.addAttribute("classId", classId);
        model.addAttribute("classView", classView);
        model.addAttribute("gradeClass", gradeClass);
        model.addAttribute("deputyName", deputyName);
        model.addAttribute("master", master);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("teacherIdFromHouTai", teacherId);
        model.addAttribute("deputyIds", deputyIds);
        model.addAttribute("deputyIdsList", deputyIdsList);
        model.addAttribute("cycleId", cycleId);
        return "teachTask/pop/masterEditPop";
    }


    //班主任搜索
    @RequestMapping(value = "/master/search")
    public String rsIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = request.getParameter("cycleYear");
        String cycleSemester = request.getParameter("cycleSemester");
        int cycleSemster1 = 0;
        if (cycleSemester != null && cycleSemester != "") {
            cycleSemster1 = Integer.parseInt(cycleSemester);
        }
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherName = request.getParameter("name");

        //查询cycleId
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (cycleList.size() > 0) {
            //教学周期去重
            for (int k = 0; k < cycleList.size() - 1; k++) {
                for (int l = cycleList.size() - 1; l > k; l--) {
                    if (cycleList.get(l).getCycleYear().equals(cycleList.get(k).getCycleYear())) {
                        cycleList.remove(l);
                    }
                }
            }
        }
        if (GukeerStringUtil.isNullOrEmpty(teachCycle)) {
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);
            cycleYear = teachCycle.getCycleYear();
            cycleSemster1 = teachCycle.getCycleSemester();
        }

        String cycleId = teachCycle.getId();
        try {
            if (teacherName != null) {
                teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
            } else {
                //根据cycleId查询所有的班主任
                model.addAttribute("cycleYear", cycleYear);
                model.addAttribute("cycleSemester", cycleSemester);
                model.addAttribute("cycleList", cycleList);
                //为空的话则根据cycleId查询虽有的班级，进而关联teacher_class表查询所有
                return "teachTask/masterSearch";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        teacherName = "%" + teacherName + "%";
        PageInfo<BZRView> pageInfo = teachTaskService.findteacherByNameAndSchoolICycleId(cycleId, schoolId, teacherName, pageNum, pageSize);
        List<BZRView> bzrViewList = pageInfo.getList();
        HttpSession session = request.getSession();
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            session.setAttribute("teachTask", getParamVal(request, "appId"));//将当前应用id存到session中
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("bzrViewList", bzrViewList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/masterSearch";
    }

    //班主任导入
    @ResponseBody
    @RequestMapping(value = "/master/import")
    public ResponseEntity importMaster(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        List<IOCMasterView> errorIOCMasterView = new ArrayList<IOCMasterView>();
        List<TeacherClass> correctTeacherClassList = new ArrayList<TeacherClass>();

        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName(), classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd(), banji);
        }

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        Map<String, Teacher> teacherMapName = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMapName.put(teacher.getName() + schoolId, teacher);
            teacherMap.put(teacher.getNo() + schoolId, teacher);
        }

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCMasterView> list = importExcel.getDataList(IOCMasterView.class);
        Map res = new HashMap();

        //去除名字一样的
        Integer count = 0;
        if (list.size() > 0) {
            System.out.println("开始===================================================================="+System.currentTimeMillis());
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = list.size() - 1; j > i; j--) {
                    if (list.get(i).getMasterName().equals(list.get(j).getMasterName())) {
                        errorIOCMasterView.add(list.get(j));
                        list.remove(j);
                        count++;
                        System.out.println(count);
                    }
                }
            }
            System.out.println("结束***************************************************************************"+System.currentTimeMillis());
        }

        for (IOCMasterView iocMasterView : list) {
            try {

                System.out.println(ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj"));
                TeacherClass ref1 = new TeacherClass();
                if (ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj") == 0) {
                    errorIOCMasterView.add(iocMasterView);
                    continue;
                }
                GradeClass gradeClass = (GradeClass) classMap.get(iocMasterView.getBj() + ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj") + classSectionMap.get(iocMasterView.getXdName()));
                String classId = "";
                if (gradeClass != null) {
                    classId = gradeClass.getId();
                }
                //根据学期和学年导入数据时候首先获取cycleId
                String cycleYear = iocMasterView.getCycleYear();
                int cycleSemester = iocMasterView.getCycleSemester();
                //获取cycleId
                TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, cycleSemester, schoolId);
                String cycleId = null;
                if (teachCycle.getId() != null) {
                    cycleId = teachCycle.getId();
                }
                if (gradeClass.getNj().equals(ConstantUtil.getKeyByValueAndFlag(iocMasterView.getNj(), "nj")))
                    if (gradeClass.getXd().equals(classSectionMap.get(iocMasterView.getXdName())))
                        ref1.setClassId(gradeClass.getId());
                ref1.setCycleId(cycleId);

                //班主任
                //中英文符号的替换
                String _masterName = iocMasterView.getMasterName();
                String amasterName = _masterName.replace("（", "(");
                String masterName = amasterName.replace("）", ")");
                String[] masterNoArray = masterName.split("\\(");
                String teacherId = "";

                //查询导入的班主任的id
                if (masterNoArray.length >= 2) {
                    String masterNo = masterNoArray[1].substring(0, masterNoArray[1].length() - 1);
                    if (masterNo.equals(teacherMap.get(masterNo + schoolId).getNo())) {
                        Teacher teacher = teacherMap.get(masterNo + schoolId);
                        teacherId = teacher.getId();
                        ref1.setTeacherId(teacherMap.get(masterNo + schoolId).getId());
                    }
                } else if (masterName.equals(teacherMapName.get(masterName + schoolId).getName())) {
                    Teacher teacher = teacherMapName.get(masterName + schoolId);
                    teacherId = teacher.getId();
                    ref1.setTeacherId(teacherId);
                } else {
                    System.out.println(teacherMapName.get(masterName + schoolId).getName() + "-----------------");
                    throw new ErrcodeException("该教师信息不存在");
                }
                ref1.setType(1);

                TeacherClass teacherClass = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherId, 1);

                if (teacherClass.getClassId() == null)
                    correctTeacherClassList.add(ref1);
                //副班主任

                //中英文符号替换
                String _deputyNoStr = iocMasterView.getDeputymasterName();
                if (_deputyNoStr != null) {
                    String douhaoDeputyNoStr = _deputyNoStr.replace("；", ";");
                    String aDeputyNoStr = douhaoDeputyNoStr.replace("（", "(");
                    String deputyNoStr = aDeputyNoStr.replace("）", ")");
                    if (deputyNoStr != null) {
                        String[] deputyNoArray = deputyNoStr.split(";");

                        for (int i = 0; i < deputyNoArray.length; i++) {
                            String _deputyNo = deputyNoArray[i];
                            String[] _deputyArray = _deputyNo.split("\\(");
                            TeacherClass ref2 = new TeacherClass();
                            ref2.setClassId(gradeClass.getId());

                            if (_deputyArray.length >= 2) {
                                System.out.println(_deputyArray[1]);
                                System.out.println();
                                String deputyNo = _deputyArray[1].substring(0, _deputyArray[1].length() - 1);
                                if (deputyNo.equals(teacherMap.get(deputyNo + schoolId).getNo())) {
                                    Teacher teacher = teacherMap.get(deputyNo + schoolId);
                                    teacherId = teacher.getId();
                                    ref2.setTeacherId(teacherId);
                                }
                            } else if (_deputyNo.equals(teacherMapName.get(_deputyNo + schoolId).getName())) {
                                Teacher teacher = teacherMapName.get(_deputyNo + schoolId);
                                teacherId = teacher.getId();
                                ref2.setTeacherId(teacherId);
                            } else {
                                System.out.println(teacherMapName.get(_deputyNo + schoolId).getName() + "-----------------");
                                throw new ErrcodeException("该教师信息不存在");
                            }
                            ref2.setType(2);
                            ref2.setCycleId(cycleId);

                            TeacherClass teacherClass1 = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherId, 2);
                            if (teacherClass1.getTeacherId() == null) {
                                correctTeacherClassList.add(ref2);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorIOCMasterView.add(iocMasterView);
                continue;
            }
        }

        if (correctTeacherClassList.size() > 0)
            teachTaskService.insertBatchTeacherClass(correctTeacherClassList);
        Long end = System.currentTimeMillis();

        res.put("msg", "导入完成，共" + correctTeacherClassList.size() + "条成功，" + errorIOCMasterView.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorIOCMasterView);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 教务管理-任课教师管理
     *
     * @param request
     * @param model
     * @return
     */

//任课教师首页
    @RequestMapping(value = "/course/teacher/index")
    public String courseTeacherIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String courseId = getParamVal(request, "courseId");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        Map yearMap = new TreeMap();
        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            if (cycleList.size() > 0) {
                teachCycle = cycleList.get(0);
            }
        }

        if (cycleList.size() > 0) {
            for (TeachCycle cycle : cycleList) {
                if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                    semesterList.add(cycle);
                yearMap.put(cycle.getCycleYear(), cycle);
            }
        }

        if (StringUtil.isEmpty(cycleYear))
            if (!GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
                cycleYear = teachCycle.getCycleYear();

        //根据cycleId、schoolId查询存在的课程
        String str = "";
        if (!GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            str = teachCycle.getId();

        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, str);

        String courseName = null;
        if (StringUtil.isEmpty(courseId) || courseId.equals("undefined")) {
            if (courseList != null && courseList.size() > 0) {
                courseId = courseList.get(0).getId();
                courseName = courseList.get(0).getName();
            }
        }

        //根据courseId查询课程班级关联表
        List<CourseClass> courseClassList = teachTaskService.findAllCourseClassByCourseId(courseId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        //根据classIdList和teacherIdList查询所有的任课老师
        model.addAttribute("courseName", courseName);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseId", courseId);
        if (courseClassList.size() == 0) {
            return "teachTask/courseTeacher";
        }

        PageInfo<BZRView> pageInfo = teachTaskService.findAllCourseTeacherBycourseClassList(courseClassList, pageNum, pageSize, teachCycle.getId());
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/courseTeacher";
    }

    //任课教师编辑的弹窗
    @RequestMapping(value = "/course/teacher/edit/pop")
    public String courseTeacherEditPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String courseClassId = request.getParameter("refId");
        String classId = request.getParameter("classId");
        String teacherId = request.getParameter("teacherId");
        String courseId = request.getParameter("courseId");
        String nj = request.getParameter("nj");
        String bj = request.getParameter("bj");
        String xd = request.getParameter("xd");
        String className = request.getParameter("className");
        String courseName = request.getParameter("courseName");
        String teacherName = request.getParameter("teacherName");
        //根据schoolId查询所有的老师
        List<Teacher> _teacherList = teacherService.findAllTeacher(schoolId);
        try {
            bj = new String(bj.getBytes("iso8859-1"), "utf-8");
            xd = new String(xd.getBytes("iso8859-1"), "utf-8");
            teacherName = new String(teacherName.getBytes("iso8859-1"), "utf-8");
            courseName = new String(courseName.getBytes("iso8859-1"), "utf-8");
            className = new String(className.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JSON teacherList = (JSON) JSON.toJSON(_teacherList);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("classId", classId);
        model.addAttribute("xd", xd);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("nj", nj);
        model.addAttribute("bj", bj);
        model.addAttribute("courseName", courseName);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("className", className);
        model.addAttribute("courseClassId", courseClassId);
        return "teachTask/pop/courseTeacherEdit";
    }


    @RequestMapping(value = "/course/teacher/edit")
    public String courseTeacherEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String courseClassId = request.getParameter("courseClassId");
        String classId = request.getParameter("classId");
        String courseId = request.getParameter("courseId");
        String teacherIdFromHouTai = request.getParameter("teacherIdFromHouTai");
        String teacherId = request.getParameter("teacherId");
        if (courseClassId != null) {
            CourseClass courseClass = teachTaskService.selectCourseClassByKey(courseClassId);
            if (courseClass != null) {
                if (teacherId != "") {
                    courseClass.setTeacherId(teacherId);
                } else {
                    courseClass.setTeacherId(teacherIdFromHouTai);
                }
                int succ = teachTaskService.updateCourseClassByPrimareyKey(courseClass);
            }
        }
        return "teachTask/courseTeacher";
    }


    //导入任课教师
    @ResponseBody
    @RequestMapping(value = "/course/teacher/import")
    public ResponseEntity importExcel(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();
        List<IOCCourseTeacherView> errorIOCCourseTeacherViewList = new ArrayList<IOCCourseTeacherView>();
        List<CourseClass> correcCourseClassList = new ArrayList<CourseClass>();
        IOCCourseTeacherView errCourse = new IOCCourseTeacherView();
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCCourseTeacherView> list = importExcel.getDataList(IOCCourseTeacherView.class, 1);

        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName(), classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd(), banji);
        }

        List<Teacher> teacherLlist = teacherService.findAllTeacher(schoolId);
        Map<String, Teacher> teacherMap = new HashedMap();
        for (Teacher teacher : teacherLlist) {
            teacherMap.put(teacher.getNo(), teacher);
        }

        //查询所有课程的名字
        Map courseNameMap = new HashMap();
        List<Course> courseList = teachTaskService.findAllCourseBySchoolId(schoolId);
        for (Course course : courseList) {
            courseNameMap.put(course.getCycleId() + course.getName(), course.getId());
        }

        for (IOCCourseTeacherView iocCourseTeacherView : list) {
            try {
                //gradeclassId、courseId
                errCourse = iocCourseTeacherView;
                String gradeClassId = "";
                GradeClass gradeClass = (GradeClass) classMap.get(iocCourseTeacherView.getBj() + iocCourseTeacherView.getNj() + classSectionMap.get(iocCourseTeacherView.getXdName()));

                //拿到对应的classId
                if (gradeClass.getXd().equals(classSectionMap.get(iocCourseTeacherView.getXdName()))) {
                    if (gradeClass.getNj().equals(iocCourseTeacherView.getNj())) {
                        if (gradeClass.getName().equals(iocCourseTeacherView.getBj())) {
                            gradeClassId = gradeClass.getId();
                        }
                    }
                }

                //获取最新的教学周期
                TeachCycle teachCycle = getLatestTeachCycle(schoolId);
                String cycleId = teachCycle.getId();
                String courseId = (String) courseNameMap.get(cycleId + iocCourseTeacherView.getCourse());
                //????????????????????????????????????老师姓名+（编号）
                String _courseTeacher = iocCourseTeacherView.getCourseTeacher();
                String acourseTeacher = _courseTeacher.replace("（", "(");
                String courseTeacher = acourseTeacher.replace("）", ")");
                String[] courseteachArray = courseTeacher.split("\\(");
                String courseTeacherNo = courseteachArray[1].substring(0, courseteachArray[1].length() - 1);
                //通过老师的在职编号查询老师的id
                String teacherId = "";
                if (courseTeacherNo.equals(teacherMap.get(courseTeacherNo).getNo())) {
                    teacherId = teacherMap.get(courseTeacherNo).getId();
                }
                //此时classId 、courseId、 teacherId都有了 插入中间表 导入成功
                CourseClass courseClass = new CourseClass();
                courseClass.setTeacherId(teacherId);
                courseClass.setClassId(gradeClassId);
                if (courseId == null) {
                    throw new ErrcodeException(iocCourseTeacherView.getCourse() + "课程不存在，请先到课程管理目录创建该课程");
                }
                courseClass.setCourseId(courseId);
                correcCourseClassList.add(courseClass);
            } catch (Exception e) {
                errorIOCCourseTeacherViewList.add(errCourse);
                e.printStackTrace();
                continue;
            }
        }
        if (correcCourseClassList.size() > 0) {
            //执行批量更新，因为course和class已经有这条数据，只能是更新
            for (CourseClass courseClass : correcCourseClassList) {
                teachTaskService.updateCourseClassByCourseIdAndClassId(courseClass);
            }
        }
//            teachTaskService.batchInsertCourseClass(correcCourseClassList);
//        teacherService.insertTeacherBatch(correcCourseTeacherList);//批量插入正确验证的数据
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correcCourseClassList.size() + "条成功，" + errorIOCCourseTeacherViewList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorIOCCourseTeacherViewList);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    //任课教师下载导入模板
    @ResponseBody
    @RequestMapping(value = "/course/teacher/moban/download")
    public void exportCourseTeacher(HttpServletResponse response) {
        try {
            String fileName = "任课教师导入模板.xlsx";
            String anno = "注释：所有字段均为必填项\n" +
                    "          1.任课教师一栏请按例子格式填写 XXX(教师在校编号)\n" +
                    "          2.年级一栏填写数字，如1表示一年级，初中一年级也填写1;\n" +
                    "          2.学期字段填写1或者2，1表示第一学期\n";

            new ExportExcel(false, "任课教师导入模板", IOCCourseTeacherView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //查看所有任课教师安排
    @RequestMapping(value = "/course/teacher/all")
    public String courseTeacherAll(HttpServletRequest request, Model model) {

        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //查询所有的任课教师安排
        List<CourseClassView> coureClassViewList = teachTaskService.findAllCourseTeacherBySchoolId(schoolId);
        List<IOCCourseTeacherView> iocCourseTeacherViews = new ArrayList<>();
        if (coureClassViewList != null) {
            for (CourseClassView courseClassView : coureClassViewList) {
                IOCCourseTeacherView iocCourseTeacherView = new IOCCourseTeacherView();
                iocCourseTeacherView.setBj(courseClassView.getClassName());
                iocCourseTeacherView.setCourse(courseClassView.getCourseName());
                iocCourseTeacherView.setCourseTeacher(courseClassView.getTeacherName());
                iocCourseTeacherView.setNj(courseClassView.getNj());
                iocCourseTeacherView.setXdName(courseClassView.getClassSection());
                iocCourseTeacherViews.add(iocCourseTeacherView);
            }
        }
        JSON json = (JSON) JSON.toJSON(iocCourseTeacherViews);
        model.addAttribute("coureClassViewList", coureClassViewList);
        model.addAttribute("json", json);
        return "teachTask/pop/allCourseTeacher";
    }


    //任课教师搜索
    @RequestMapping(value = "/course/teacher/search")
    public String courseTeacherSearch(HttpServletRequest request, Model model) {
        //在页面获取所有的查询条件
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherName = request.getParameter("name");

        ////解决teacherName乱码问题。
        try {
            if (teacherName != null)
                teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        String cycleId = teachCycle.getId();
        teacherName = "%" + teacherName + "%";
        //根据cycleId和schoolId查询courseIdList
        List<BZRView> courseTeacherList = teachTaskService.findCourseTeacherByCycleIdAndSchoolIdAndName(schoolId, cycleId, teacherName);

        HttpSession session = request.getSession();
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            session.setAttribute("teachTask", getParamVal(request, "appId"));//将当前应用id存到session中

        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("courseTeacherList", courseTeacherList);

        return "teachTask/courseTeacherSearch";
    }

    //班主任 导入模板下载
    @ResponseBody
    @RequestMapping(value = "/master/moban/download")
    public void exportMaster(HttpServletResponse response) {
        try {
            String fileName = "班主任导入模板.xlsx";
            String anno = "注释：除副班主任一栏可以为空外，其余所有字段均为必填项\n" +
                    "          1.学年、学期、学段、年级、班级信息都需与教务管理和学籍管理中的信息相符才能导入成功\n" +
                    "          2.班主任和副班主任按照以下格式填写：姓名（教师编号）；姓名（教师编号），若只有一人末尾分号不能省略\n" +
                    "          3.年级一栏如初中一年级，请填写数字1\n";
            new ExportExcel(false, "班主任", IOCMasterView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的CourseClass表
    @RequestMapping(value = "/master/error/export", method = RequestMethod.POST)
    public void errorMaster(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：所有字段均为必填项\n" +
                    "          1.班主任一栏请按例子格式填写 XXX(教师在校编号)\n" +
                    "          2.副班主任一栏，若副班主任有一人请填写 XXX(教师编号);末尾分号不能省略\n" +
                    "          3.所有数据存在时才会导入成功，班级或者年级等数据不存在时可能导入失败\n" +
                    "          4.年级一栏只填写数字，班级如一年级一班，请填写：1班\n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCMasterView> exportFile = new ArrayList<IOCMasterView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCMasterView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCMasterView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "班主任错误信息列表", IOCMasterView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //下载所有任课教师的安排列表
    @RequestMapping(value = "/course/teacher/export", method = RequestMethod.POST)
    public void courseClassExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "所有任课教师安排列表.xlsx";
            String anno = "请确保您的校区，教室类型等字段填写合格";
            String courseClassViews = getParamVal(request, "courseClassViews");
            JsonArray jsonArray = new JsonParser().parse(courseClassViews).getAsJsonArray();
            List<IOCCourseTeacherView> exportFile = new ArrayList<IOCCourseTeacherView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCCourseTeacherView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCCourseTeacherView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "任课教师安排信息", IOCCourseTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的CourseClass表
    @RequestMapping(value = "/course/teacher/error/export", method = RequestMethod.POST)
    public void errorCourseClass(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "请确保您的学段、年级、班级等字段的对应";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCCourseTeacherView> exportFile = new ArrayList<IOCCourseTeacherView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCCourseTeacherView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCCourseTeacherView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "任课教师", IOCCourseTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // string类型转换为long类型
// strTime要转换的String类型的时间
// formatType时间格式
// strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // string类型转换为date类型
// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
// HH时mm分ss秒，
// strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date类型转换为long类型
// date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    // long类型转换为String类型
// currentTime要转换的long类型的时间
// formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // long转换为Date类型
// currentTime要转换的long类型的时间
// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // date类型转换为String类型
// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
// data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


//综合管理

    /**
     * 教务管理-班级教室管理
     *
     * @param request
     * @param model
     * @return
     */

//班级教室首页
    @RequestMapping(value = "/ref/class/room/index", method = RequestMethod.GET)
    public String refClassRoomIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "xdId");
        String nj = getParamVal(request, "nj");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", user.getSchoolId());
        param.put("cycleId", teachCycle.getId());
        String cycleId = teachCycle.getId();
        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = teachTaskService.findAllXd(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }

        if (nj == "") {
            nj = "1";
        }

        PageInfo<RefClassRoomView> pageInfo = teachTaskService.getRefClassRoomList(pageNum, pageSize, schoolId, cycleId, NumberConvertUtil.convertS2I(nj), xdId);

        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("nj", nj);
        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/refClassRoom";

    }


    //下载教室班级模板
    @ResponseBody
    @RequestMapping(value = "/ref/class/room/download")
    public void exportRefClassRoom(HttpServletResponse response) {
        try {
            String fileName = "班级教室导入模板.xlsx";
            String anno = "注释：所有字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n" +
                    "          2.学期项:数字1表示第一学期,数字2表示第二学期\n";

            new ExportExcel(false, "班级教室", IOCRefClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //班级教室导入
    @ResponseBody
    @RequestMapping(value = "/ref/class/room/import")
    public ResponseEntity importRefClassRoom(@RequestParam(value = "file") MultipartFile file) throws Exception {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Long begin = System.currentTimeMillis();


        List<ClassSection> classSectionList = classService.getAllClassSectionBySchoolId(user.getSchoolId());
        Map classSectionMap = new HashedMap();
        for (ClassSection classSection : classSectionList) {
            classSectionMap.put(classSection.getName(), classSection.getId());
        }

        List<GradeClass> classList = classService.getAllClassBySchoolId(user.getSchoolId());
        Map classMap = new HashedMap();
        for (GradeClass banji : classList) {
            classMap.put(banji.getName() + banji.getNj() + banji.getXd(), banji);
        }

        List<IOCRefClassRoomView> errorRefClassRoomList = new ArrayList<IOCRefClassRoomView>();
        List<RefClassRoom> correctRefClassRoomList = new ArrayList<RefClassRoom>();
        IOCRefClassRoomView errRefClassRoom = new IOCRefClassRoomView();

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCRefClassRoomView> list = importExcel.getDataList(IOCRefClassRoomView.class, 1);


        List<SchoolType> schoolTypeList = schoolService.selectSchoolTypeBySchoolId(user.getSchoolId());
        Map schoolTypeMap = new HashMap();
        for (SchoolType schoolType : schoolTypeList) {
            schoolTypeMap.put(schoolType.getName(), schoolType.getId());
        }

        //首先根据schoolId查询出所有的classRoomId
        List<ClassRoom> classRoomListByschoolId = teachTaskService.findAllClassRoomBySchoolId(schoolId);
        //查询所有的班级教室信息
        List<RefClassRoom> allRefClassRoomList = null;
        if (classRoomListByschoolId != null) {
            allRefClassRoomList = teachTaskService.findAllRefClassRoomByClassRoomId(classRoomListByschoolId);
        }
        Map refClassRoomMap = new HashMap();
        if (allRefClassRoomList != null && allRefClassRoomList.size() > 0) {
            for (RefClassRoom refClassRoom : allRefClassRoomList) {
                refClassRoomMap.put(refClassRoom.getCycleId() + refClassRoom.getGradeClass() + refClassRoom.getRoomId(), refClassRoom);
            }
        }

        //查询所有教室信息
        Map classRoomMap = new HashMap();
        for (ClassRoom classRoom : classRoomListByschoolId) {
            classRoomMap.put(classRoom.getSchoolType() + classRoom.getTeachBuilding() + classRoom.getRoomNum(), classRoom);
        }
        //查询所有的教学周期
        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        Map cycleMap = new HashMap();
        for (TeachCycle teachCycle : cycleList) {
            cycleMap.put(teachCycle.getCycleYear() + teachCycle.getCycleSemester(), teachCycle.getId());
        }
        //查询所有的班级教室信息
        for (IOCRefClassRoomView iocRefClassRoomView : list) {
            try {
                //gradeclassId、courseId
                RefClassRoom refClassRoom = new RefClassRoom();
                errRefClassRoom = iocRefClassRoomView;
                ///////////////////////id
                String gradeClassId = "";
                String cycleId = "";
                //根据表格拿到学年和学期
                String cycleYear = iocRefClassRoomView.getCycleYear();
                Integer cycleSemester = iocRefClassRoomView.getCycleSemester();
                if (null != cycleMap.get(cycleYear + cycleSemester)) {
                    cycleId = cycleMap.get(cycleYear + cycleSemester).toString();
                }
                ////////////////////////////cycleId
                refClassRoom.setCycleId(cycleId);
                ////////////////gradeClassId
                GradeClass gradeClass = (GradeClass) classMap.get(iocRefClassRoomView.getBj() + iocRefClassRoomView.getNj() + classSectionMap.get(iocRefClassRoomView.getXdName()));
                //拿到对应的classId
                if (gradeClass.getXd().equals(classSectionMap.get(iocRefClassRoomView.getXdName()))) {
                    if (gradeClass.getNj().equals(iocRefClassRoomView.getNj())) {
                        if (gradeClass.getName().equals(iocRefClassRoomView.getBj())) {
                            gradeClassId = gradeClass.getId();
                        }
                    }
                }
                refClassRoom.setGradeClass(gradeClassId);

                //根据校区的名字和schoolId可以拿到
                /////////////////////////schoolTypeId
                String schoolTypeName = iocRefClassRoomView.getXiaoQu();
                String schoolTypeId = null;
                if (null != schoolTypeMap.get(schoolTypeName)) {
                    schoolTypeId = schoolTypeMap.get(schoolTypeName).toString();
                }
                refClassRoom.setSchoolTypeId(schoolTypeId);
                //根据schoolId、校区名称、所在教学楼楼号、教室号可以唯一确定roomId
                String classRoomNum = iocRefClassRoomView.getClassRoomNum();
                Integer teachBuilding = iocRefClassRoomView.getTeachBuilding();

                /////////////////////roomId
                String roomId = "";
                if (null != classRoomMap.get(schoolTypeId + teachBuilding + classRoomNum)) {
                    ClassRoom classRoom = (ClassRoom) classRoomMap.get(schoolTypeId + teachBuilding + classRoomNum);
                    roomId = classRoom.getId();
                }

                refClassRoom.setRoomId(roomId);
                //查看是否已经导入
                if (null != refClassRoomMap.get(cycleId + gradeClassId + roomId)) {
                    throw new ErrcodeException("已经导入过，请勿重复导入");
                } else {
                    refClassRoom.setId(PrimaryKey.get());
                    correctRefClassRoomList.add(refClassRoom);
                }
            } catch (Exception e) {
                errorRefClassRoomList.add(errRefClassRoom);
                e.printStackTrace();
                continue;
            }
        }
//
        if (correctRefClassRoomList.size() > 0)
            teachTaskService.batchInsertRefClassRoom(correctRefClassRoomList);
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correctRefClassRoomList.size() + "条成功，" + errorRefClassRoomList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorRefClassRoomList);
        return new ResponseEntity(res, HttpStatus.OK);
    }


    //班级教室编辑的弹窗
    @RequestMapping(value = "/ref/class/room/edit/pop", method = RequestMethod.GET)
    public String refClassRoomEditPop(HttpServletRequest request, Model model) {
        String refId = getParamVal(request, "refId");
        String schoolTypeId = getParamVal(request, "schoolTypeId");
        String building = getParamVal(request, "teachBuilding");
        String roomId = getParamVal(request, "roomId");
        //根据refId查询
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        RefClassRoomView refClassRoomView = teachTaskService.findRefClassRoomViewByRefId(refId);
        if (schoolTypeId == "") {
            schoolTypeId = refClassRoomView.getSchoolTypeId();
        }
        if (building == "") {
            building = refClassRoomView.getTeachBuildingName();
        }
        //查询所有的校区
        List<SchoolType> schoolTypeList = teachTaskService.findAllSchoolTypeBySchoolId(schoolId);
        //根据schoolTypeId查询所有的教学楼 并根据教学楼分组
        List<ClassRoom> buildingList = teachTaskService.findBuildingByschoolTypeId(schoolTypeId);
        //根据schoolTypeId building查询教室号
        List<ClassRoom> rooms = teachTaskService.findRooomsBySchoolTypeIdAndBuilding(schoolTypeId, building);
//        model.addAttribute("teachBuildingList", buildingMap.keySet());

        model.addAttribute("building", building);
        model.addAttribute("roomNumList", rooms);
        model.addAttribute("schoolTypeList", schoolTypeList);
        model.addAttribute("refClassRoomView", refClassRoomView);
        model.addAttribute("buildingList", buildingList);
        model.addAttribute("refId", refId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("schoolTypeId", schoolTypeId);
        return "teachTask/pop/refClassRoomEditPop";
    }

    //班级教室编辑
    @RequestMapping(value = "/ref/class/room/edit", method = RequestMethod.POST)
    public String refClassRoomEdit(HttpServletRequest request, Model model) {
        String refId = getParamVal(request, "refId");
        String schoolTypeId = getParamVal(request, "schoolTypeId");
        String roomId = getParamVal(request, "roomId");
        //根据refId查询
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据refId查询该班级教室信息
        RefClassRoom refClassRoom = new RefClassRoom();
        refClassRoom.setRoomId(roomId);
        refClassRoom.setId(refId);
        refClassRoom.setSchoolTypeId(schoolTypeId);
        int succ = teachTaskService.updateRefClassRoomByKey(refClassRoom);
        return "teachTask/refClassRoom";

    }

    //下载导入失败的classRoom列表
    @RequestMapping(value = "/ref/class/room/error/export", method = RequestMethod.POST)
    public void errorRefClassRoom(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "班级教室错误信息列表.xlsx";
            String anno = "注释：所有字段均为必填项\n" +
                    "          1.校区一栏填写:主校区、西校区，按此规则填写;教室类型一栏请填写存在的类型\n" +
                    "          2.楼层、容纳人数、有效座位数、考试座位数、房间号请填写整数\n" +
                    "          3.是否用于选课请填写是或者否 \n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCRefClassRoomView> exportFile = new ArrayList<IOCRefClassRoomView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCRefClassRoomView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCRefClassRoomView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel(false, "班级教室错误信息列表", IOCRefClassRoomView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 教务管理-科目课时管理
     *
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = "/course/hour", method = RequestMethod.GET)
    public String courseHour(HttpServletRequest request, Model model) {
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String courseId = getParamVal(request, "courseId");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //获得周期列表，补全两个下拉框
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId()))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        String cycleId = teachCycle.getId();
        //根据cycleId查询所有课程
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, cycleId);
        if (courseId == "") {
            if (courseList.size() > 0)
                courseId = courseList.get(0).getId();
        }

        //cycleId、courseId都有了 查询
        List<CourseClassView> courseClassViewList = teachTaskService.findRefCourseClassByCycleIdCourseId(cycleId, courseId);
        List<Integer> hours = new ArrayList<>();
        if (courseClassViewList.size() > 0) {
            for (CourseClassView courseClassView : courseClassViewList) {
                if (courseClassView.getCourseHour() != null) {
                    hours.add(courseClassView.getCourseHour());
                }
            }

        }
        if (hours.size() > 0) {
            model.addAttribute("save", 1);
        } else {
            model.addAttribute("save", 0);
        }
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseClassViewList", courseClassViewList);

        return "teachTask/courseHour";

    }


    @RequestMapping(value = "/course/hour/edit", method = RequestMethod.POST)
    public String courseHourEdit(HttpServletRequest request, Model model) {
        String sectionIdAndCourseHour = getParamVal(request, "sectionIdAndCourseHour");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String courseId = getParamVal(request, "courseId");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());
        String cycleId = "";
        if (teachCycle != null) {
            cycleId = teachCycle.getId();
        }
        String[] sectionIdArray = sectionIdAndCourseHour.split(",");
        List<String> sectionIdList = new ArrayList<>();
        List<A_CourseClassHour> a_courseClassHourArrayList = new ArrayList<>();
        Map map = new HashedMap();
        if (sectionIdArray.length > 0) {
            for (int i = 1; i < sectionIdArray.length; i++) {

                //该数组中的第一个元素代表sectionId,第二个元素代表nj,第三个元素代表课时数
                String[] arr = sectionIdArray[i].split(":");
                for (int j = 0; j < arr.length; j++) {
                    A_CourseClassHour a_courseClassHour = new A_CourseClassHour();
                    a_courseClassHour.setSectionId(arr[0]);
                    a_courseClassHour.setNj(NumberConvertUtil.convertS2I(arr[1]));
                    a_courseClassHour.setCourseHour(NumberConvertUtil.convertS2I(arr[2]));
                    a_courseClassHourArrayList.add(a_courseClassHour);
                }
            }
        }

        //根据courseId获取所有的classId
        List<CourseClass> courseClasses = teachTaskService.findAllCourseClassByCourseId(courseId);

        List<CourseClass> updateForCourseClass = new ArrayList<>();
        //根据a_courseClassHourArrayList里面的sectionId和nj去查所有的班级
        List<GradeClass> gradeClassList = teachTaskService.findGradeClassBySectionIdAndNj(a_courseClassHourArrayList);
        for (GradeClass gradeClass : gradeClassList) {
            for (A_CourseClassHour a_courseClassHour : a_courseClassHourArrayList) {
                if (a_courseClassHour.getSectionId().equals(gradeClass.getXd()) && a_courseClassHour.getNj() == gradeClass.getNj()) {
                    for (CourseClass courseClass : courseClasses) {
                        if (courseClass.getClassId().equals(gradeClass.getId())) {
                            //这会就可以根据classId courseId唯一确定CourseClass对象了
                            CourseClass courseClassDB = teachTaskService.findCourseClassByClassIdAndCourseId(courseId, gradeClass.getId());
                            courseClassDB.setCourseHour(a_courseClassHour.getCourseHour());
                            updateForCourseClass.add(courseClassDB);
                        }
                    }
                }
            }
        }
        if (updateForCourseClass.size() > 0) {
            for (CourseClass courseClass : updateForCourseClass) {
                teachTaskService.updateCourseClassByPrimareyKey(courseClass);
            }
        }
//        teachTaskService.updateCourseClassByList(updateForCourseClass);
        return "redirect:/teach/task/course/hour";
    }


    /**
     * 班级日常课时管理
     */

//首页
    @RequestMapping(value = "/daily/hour", method = RequestMethod.GET)
    public String dailyHour(HttpServletRequest request, Model model) {
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        String xdId = getParamVal(request, "sectionId");
        String nj = getParamVal(request, "nj");
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            teachCycle = cycleList.get(0);
        }
        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }

        //根据学校id查询学段的名字
        List<ClassSection> classSectionList = teachTaskService.findAllXd(schoolId);
        if (xdId == "") {
            if (classSectionList.size() > 0) {
                xdId = classSectionList.get(0).getId();
            }
        }
        String cycleId = teachCycle.getId();
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }
        if (nj == "" || nj == null) {
            nj = "1";
        }

        //根据xd、cycleId、nj查询所有的班级日常课时信息
        PageInfo<DailyHourView> pageInfo = teachTaskService.findDailyHourByXdAndCycleIdAndNj(schoolId, xdId, cycleId, nj, pageNum, pageSize);
        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("nj", nj);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/dailyHour";

    }

    //班级日常课时的弹窗
    @RequestMapping(value = "/daily/hour/add/pop", method = RequestMethod.GET)
    public String dailyHourAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        String bj = getParamVal(request, "bj");//班级
        String nj = getParamVal(request, "nj");
        String xdId = getParamVal(request, "xdId");
        //根据上述条件查询cycleId的值
        List<TeachCycle> cycleList = teachTaskService.getCycleList(schoolId);
        Map map = new HashMap<>();
        for (TeachCycle teachCycle : cycleList) {
            map.put(teachCycle.getCycleYear(), teachCycle.getId());
        }
        //查询所有的班级
        List<GradeClass> gradeClassList = teachTaskService.getAllClassBySchoolIdAndNj(schoolId, nj, xdId);
        DailyHour dailyHour = null;
        if (dailyId != "") {
            dailyHour = teachTaskService.findDailyHourById(dailyId);
        }
        try {
            bj = new String(bj.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        model.addAttribute("bj", bj);
        model.addAttribute("gradeClassList", gradeClassList);
        model.addAttribute("yearList", map.keySet());
        model.addAttribute("dailyHour", dailyHour);
        return "teachTask//pop/dailyHourAdd";
    }


    //班级日常课时的增加
    @ResponseBody
    @RequestMapping(value = "/daily/hour/add", method = RequestMethod.POST)
    public ResultEntity dailyHourAdd(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();


        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        String cycleId = null;

        if (teachCycle.getId() != null) {
            cycleId = teachCycle.getId();
        } else {
            teachCycle = getLatestTeachCycle(schoolId);
            cycleId = teachCycle.getId();
        }

        String skts = getParamVal(request, "skts");
        String swks = getParamVal(request, "swks");
        String xwks = getParamVal(request, "xwks");
        String kjc = getParamVal(request, "kjc");
        String classIds = getParamVal(request, "classIds");
        if (skts == "" || kjc == "" || swks == "" || xwks == "" || classIds == "" || cycleSemester == "") {
            return ResultEntity.newErrEntity("所有项均为必填项");
        }
        String reg = "^[0-9]*[1-9][0-9]*$";
        if (!skts.matches(reg) || !kjc.matches(reg) || !swks.matches(reg) || !xwks.matches(reg)) {
            return ResultEntity.newErrEntity("数据格式不正确");
        }
        List<DailyHour> dailyHourList = new ArrayList<>();
        String[] classIdArr = classIds.split(",");
        if (classIdArr.length > 0) {
            for (int i = 1; i < classIdArr.length; i++) {
                DailyHour dailyHour = new DailyHour();
                dailyHour.setId(PrimaryKey.get());
                dailyHour.setSkts(NumberConvertUtil.convertS2I(skts));
                dailyHour.setSwks(NumberConvertUtil.convertS2I(swks));
                dailyHour.setXwks(NumberConvertUtil.convertS2I(xwks));
                dailyHour.setKjc(NumberConvertUtil.convertS2I(kjc));
                dailyHour.setCycleId(cycleId);
                dailyHour.setCreateTime(System.currentTimeMillis());
                dailyHour.setUpdateTime(System.currentTimeMillis());
                dailyHour.setUpdateBy(user.getId());
                dailyHour.setGradeClassId(classIdArr[i]);
                dailyHour.setDelFlag(0);
                dailyHour.setXn(cycleYear);
                dailyHour.setXq(cycleSemester);
                dailyHour.setSchoolId(schoolId);
                dailyHourList.add(dailyHour);
            }
        }
        //查询所有的dailyHour,看是否已经创建过一次了
        teachTaskService.batchInsertDailyHour(dailyHourList);
        return ResultEntity.newResultEntity("");
    }


    //班级日常课时编辑
    @ResponseBody
    @RequestMapping(value = "/daily/hour/edit", method = RequestMethod.POST)
    public ResultEntity dailyHourEdit(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
        String cycleId = null;
        if (teachCycle != null) {
            cycleId = teachCycle.getId();
        }
        DailyHour dailyHour = new DailyHour();
        if (dailyId != "") {
            dailyHour.setId(dailyId);
        }
        String skts = getParamVal(request, "skts");
        String swks = getParamVal(request, "swks");
        String xwks = getParamVal(request, "xwks");
        String kjc = getParamVal(request, "kjc");
        dailyHour.setXq(cycleSemester);
        if (skts == "" || kjc == "" || swks == "" || xwks == "" || cycleSemester == "") {
            return ResultEntity.newErrEntity("所有项均为必填项");
        }
        dailyHour.setSkts(NumberConvertUtil.convertS2I(skts));
        dailyHour.setSwks(NumberConvertUtil.convertS2I(swks));
        dailyHour.setXwks(NumberConvertUtil.convertS2I(xwks));
        dailyHour.setKjc(NumberConvertUtil.convertS2I(kjc));
        dailyHour.setCycleId(cycleId);
        dailyHour.setUpdateTime(System.currentTimeMillis());
        dailyHour.setUpdateBy(user.getId());
        dailyHour.setDelFlag(0);
        dailyHour.setXn(cycleYear);
        dailyHour.setXq(cycleSemester);
        teachTaskService.saveDailyHour(dailyHour);
        return ResultEntity.newResultEntity("");
    }

    @ResponseBody
    @RequestMapping(value = "/daily/hour/del", method = RequestMethod.POST)
    public ResultEntity dailyHourDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String dailyId = getParamVal(request, "dailyId");
        teachTaskService.delDailyHourById(dailyId);
        return ResultEntity.newResultEntity("");
    }

    /**
     * 课节设置
     */
//首页
    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String nodeIndex(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (teachCycle.getId() == null) {
            teachCycle = cycleList.get(0);
        }
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }


        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        String cycleId = teachCycle.getId();
        //根据schoolId查询时间表
        PageInfo<CourseNodeInit> pageInfo = teachTaskService.findCourseNodeInitBySchoolId(schoolId, pageNum, pageSize, cycleId);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/node";
    }


    @RequestMapping(value = "/node/detail/pop")
    public String nodeDetail(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String nodeInitId = getParamVal(request, "nodeId");
        //根据nodeId查询所有的courseNode

        List<CourseNode> courseNodeList = teachTaskService.findCourseNodeByNodeId(nodeInitId);
        List<CourseNodeView> courseNodeViewList = new ArrayList<>();
        if (courseNodeList.size() > 0) {
            for (CourseNode courseNode : courseNodeList) {
                CourseNodeView courseNodeView = new CourseNodeView();
                courseNodeView.setCourseNode(courseNode);
                Long start = courseNode.getStartTime();
                Long end = courseNode.getEndTime();
                String _start = null;
                String _end = null;
                try {
                    _start = longToString(start, "HH:mm");
                    _end = longToString(end, "HH:mm");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                courseNodeView.setStartTime(_start);
                courseNodeView.setEndTime(_end);
                courseNodeViewList.add(courseNodeView);
            }
        }
        model.addAttribute("courseNodeViewList", courseNodeViewList);
        return "teachTask/pop/nodeDetail";
    }


    @RequestMapping(value = "/node/del")
    public String nodeDel(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String nodeId = getParamVal(request, "nodeId");
        //根据nodeId查询所有的courseNode

        //根据id删除 不做逻辑删除，直接干掉了
        teachTaskService.delCourseNodeInit(nodeId);

        //再根据nodeId，删除courseNode表中的所有数据，也是直接干掉，不做逻辑删除
//        teachTaskService.delCourseNodeByNodeInitId(nodeId);

        return "teachTask/node";
    }


    public CourseNode courseNode(Integer node, Long start, Long end, String schoolId, User user, String courseInitId, String nodeName, String morningAfternoon) {
        CourseNode courseNode = new CourseNode();
        courseNode.setNode(node);//node
        courseNode.setStartTime(start);
        courseNode.setEndTime(end);
        courseNode.setSchoolId(schoolId);
        courseNode.setCreateTime(System.currentTimeMillis());
        courseNode.setUpdateTime(System.currentTimeMillis());
        courseNode.setUpdateBy(user.getId());
        courseNode.setId(PrimaryKey.get());
        courseNode.setDelFlag(0);
        courseNode.setCourseNodeInitId(courseInitId);
        courseNode.setMorningAfternoon(morningAfternoon);
        courseNode.setNodeName(nodeName);
        return courseNode;
    }

    /**
     * 教室类型管理
     */
//   教室类型管理的弹窗
    @RequestMapping(value = "/room/type/pop", method = RequestMethod.GET)
    public String roomTypePop(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        List<RoomType> roomTypeList = teachTaskService.roomTypeList(user.getSchoolId());

        model.addAttribute("roomTypeList", roomTypeList);
        return "teachTask/pop/roomType";
    }

    //教室类型  添加 删除  更新
    @RequestMapping(value = "/room/type/add", method = RequestMethod.POST)
    public String roomTypeAdd(HttpServletRequest request, Model model, RoomType roomType) {
        User user = getLoginUser();
        String roomTypeId = getParamVal(request, "id");
        String doType = getParamVal(request, "type");
        if (doType != "" && doType.equals("delete")) {
            roomType.setDelFlag(1);//隐藏
        }
        int succ = teachTaskService.saveRoomType(roomType, user);
//        return "teachTask/pop/roomType";
        return "redirect:/teach/task/room/type/pop";
    }

    //新增课节 管理
    @RequestMapping(value = "/node/new", method = RequestMethod.GET)
    public String nodeIndexNew(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");

        //根据学年学期获得周期
        TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), user.getSchoolId());

        //查询所有的教学周期
        List<TeachCycle> cycleList = teachTaskService.getCycleList(user.getSchoolId());

        List<TeachCycle> semesterList = new ArrayList<>();
        if (GukeerStringUtil.isNullOrEmpty(teachCycle.getId())) {
            teachCycle = cycleList.get(0);
        }
        if (StringUtil.isEmpty(cycleYear)) {
            cycleYear = teachCycle.getCycleYear();
        }


        Map yearMap = new TreeMap();
        for (TeachCycle cycle : cycleList) {
            if (teachCycle.getCycleYear().equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        String cycleId = teachCycle.getId();
        //根据schoolId查询时间表
        PageInfo<CourseNodeInit> pageInfo = teachTaskService.findCourseNodeInitBySchoolId(schoolId, pageNum, pageSize, cycleId);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("pageInfo", pageInfo);
        return "teachTask/nodeNew";
    }

    //新增课节的增加的弹窗
    @RequestMapping(value = "/node/add/new/pop", method = RequestMethod.GET)
    public String newCourseNodeAddPop(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        TeachCycle teachCycle = getLatestTeachCycle(user.getSchoolId());

        String nodeId = getParamVal(request, "nodeId");
        CourseNodeInit courseNodeInit = new CourseNodeInit();
        if (nodeId != "") {
            courseNodeInit = teachTaskService.findCourseNodeInitById(nodeId);
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("courseNodeInit", courseNodeInit);

        //根据courseNodeInitId查询所有的courseNode
        List<CourseNode> courseNodeList = new ArrayList<>();
        if (courseNodeInit.getId() != null)
            courseNodeList = teachTaskService.findCourseNodeByNodeId(courseNodeInit.getId());

        List<CourseNodeExtention> courseNodeExtentions = new ArrayList<>();
        if (courseNodeList.size() > 0) {
            for (CourseNode courseNode : courseNodeList) {
                CourseNodeExtention courseNodeExtention = new CourseNodeExtention();
                courseNodeExtention.setNodeName(courseNode.getNodeName());
                courseNodeExtention.setCycleId(courseNode.getCycleId());
                try {
                    courseNodeExtention.setStartTime(longToString(courseNode.getStartTime(), "hh:mm"));
                    courseNodeExtention.setEndTime(longToString(courseNode.getEndTime(), "hh:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                courseNodeExtentions.add(courseNodeExtention);
            }
        }

        String cycleId = "";
        Integer weekCount = 0;
        if (teachCycle.getId() != null) {
            weekCount = teachCycle.getWeekCount();
            cycleId = teachCycle.getId();
            //根据cycleId查询node_init表 做时间比较
            List<CourseNodeInit> courseNodeInitList = teachTaskService.findCourseNodeInitByCycleId(cycleId);
            if (courseNodeInitList.size() > 0) {
                model.addAttribute("courseNodeInitEndWeek", courseNodeInitList.get(0).getEndWeek());
            } else {
                model.addAttribute("courseNodeInitEndWeek", 0);
            }
        }

        model.addAttribute("courseNodeExtentions", courseNodeExtentions);
        model.addAttribute("courseNodeListSize", courseNodeList.size());
        model.addAttribute("cycleId", cycleId);
        model.addAttribute("weekCount", weekCount);
        return "teachTask/pop/newNodeAddPop";
    }

    //新增课节 增加的弹窗
    @RequestMapping(value = "/node/add/new", method = RequestMethod.POST)
    public String newCourseNodeAdd(HttpServletRequest request, Model model) {
        User user = getLoginUser();
        String arr = getParamVal(request, "arr");
        String nodeInitId = getParamVal(request, "nodeInitId");

        Gson gson = new Gson();
        List<CourseNodeExtention> courseNodeExtentions = gson.fromJson(arr, new TypeToken<List<CourseNodeExtention>>() {
        }.getType());

        String cycleId = getParamVal(request, "cycleId");
        String startweek = getParamVal(request, "startweek");
        String endweek = getParamVal(request, "endweek");
        String initName = getParamVal(request, "initName");

        CourseNodeInit courseNodeInit = new CourseNodeInit();
        if (nodeInitId != "") {
            courseNodeInit.setId(nodeInitId);
        } else {
            courseNodeInit.setId(PrimaryKey.get());
        }
        courseNodeInit.setCycleId(cycleId);
        courseNodeInit.setName(initName);
        courseNodeInit.setStartWeek(NumberConvertUtil.convertS2I(startweek));
        courseNodeInit.setEndWeek(NumberConvertUtil.convertS2I(endweek));
        courseNodeInit.setSchoolId(user.getSchoolId());
        courseNodeInit.setUpdateBy(user.getId());

        List<CourseNode> courseNodeList = new ArrayList<>();
        if (courseNodeExtentions != null && courseNodeExtentions.size() > 0) {
            for (CourseNodeExtention courseNodeExtention : courseNodeExtentions) {
                try {
                    Long start = stringToLong(courseNodeExtention.getStartTime(), "hh:mm");
                    Long end = stringToLong(courseNodeExtention.getEndTime(), "hh:mm");

                    CourseNode courseNode = new CourseNode();
                    courseNode.setId(PrimaryKey.get());
                    courseNode.setCourseNodeInitId(courseNodeInit.getId());
                    courseNode.setCycleId(cycleId);
                    courseNode.setUpdateBy(user.getId());
                    courseNode.setSchoolId(user.getSchoolId());
                    courseNode.setCreateTime(System.currentTimeMillis());
                    courseNode.setDelFlag(0);
                    courseNode.setEndTime(end);
                    courseNode.setStartTime(start);
                    courseNode.setNodeName(courseNodeExtention.getNodeName());

                    courseNodeList.add(courseNode);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        teachTaskService.saveCourseNodeInit(courseNodeInit, courseNodeList);
        return "teachTask/nodeNew";
    }


    public static void main(String[] args) {
        List<String> lis = new ArrayList<>();
        lis.add("a");
        lis.add("b");
        lis.add("c");
        lis.add("d");
        String s = "风一样(的)存在";
        s.replace("(", "（");
        System.out.println(s);
        System.out.println(s);
        String[] d = s.split("\\(");
        System.out.println(s.length());
        System.out.println(s);
        System.out.println(d.toString());


//        for (int i=0;i<lis.size()-1;i++){
//            System.out.println(lis.size());
//            for (int j = lis.size()-1;j>i;j--){
//            System.out.println(lis.size());
////                lis.remove(j);
//                System.out.println("i==========="+i);
//                System.out.println("j======"+j);
////                lis.remove(0);
////                lis.remove(1);
//            }
//        }
    }

}

