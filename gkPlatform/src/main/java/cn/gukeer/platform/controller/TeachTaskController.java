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
import cn.gukeer.platform.persistence.entity.extention.TeachCylcleExtention;
import cn.gukeer.platform.service.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        User user = getLoginUser();
        String schoolId = user.getSchoolId();

        Map param = new HashMap();
        param.put("pageNum", pageNum);
        param.put("pageSize", pageSize);
        param.put("schoolId", schoolId);
        param.put("cycleYear", cycleYear);
        //根据学校的查询所有的教学周期
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

        Map yearMap = new HashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        param.put("cycleId", teachCycle.getId());
        //查询所有的课程
        PageInfo<Course> coursePageInfo = teachTaskService.getAllCourseListByParam(param);
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
        //查询所有的课程类型
        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        Map param = new HashedMap();
        param.put("schoolId", schoolId);
        String courseId = request.getParameter("id");
        PageInfo<CourseType> courseTypePageInfo = teachTaskService.findAllCourseType(param);
        //查询所有的教室类型
        List<RoomType> roomTypeList = teachTaskService.getAllRoomTypeBySchoolId(schoolId);
        //根据学校的查询所有的教学周期
        PageInfo<TeachCycle> teachCyclePageInfo = teachTaskService.getTeachCycle(param);

        model.addAttribute("courseTypePageInfo", courseTypePageInfo);
        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("teachCyclePageInfo", teachCyclePageInfo);
        Course course = null;
        TeachCycle teachCycleDB = null;
        if (type.equals("edit")) {
            if (courseId != null && courseId != "")
                course = teachTaskService.getCourseByPrimaryKey(courseId);
            String cycleId = course.getCycleId();
            teachCycleDB = teachTaskService.selectByKey(cycleId);
            model.addAttribute("course", course);
            model.addAttribute("teachCycleDB", teachCycleDB);
            return "teachTask/pop/courseEditPop";
        }
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
        //根据courseId,查询course_classs表，作为页面checkbox默认选中的判断条件
        List<CourseClass> courseClassList = teachTaskService.findClassIdByCourseId(courseId);

        //学段容器
        List<ClassSectionView> sectionViewList = new ArrayList<ClassSectionView>();
        for (ClassSection classSection : sectionList) {
            //年级容器
            ClassSectionView sectionView = new ClassSectionView();
            sectionView.setId("school_" + schoolId + "section_" + classSection.getId());
            sectionView.setName(classSection.getName());
            sectionView.setPid("school_" + schoolId);

            ArrayList<GradeClassView> njlist = new ArrayList<GradeClassView>();
            List<GradeClass> njList = classService.getAllClassBySchoolId(schoolId);
            for (int k = 0; k < njList.size() - 1; k++) {
                for (int l = njList.size() - 1; l > k; l--) {
                    if (njList.get(l).getNj().equals(njList.get(k).getNj())) {
                        njList.remove(l);
                    }
                }
            }
            for (int i = 0; i < njList.size(); i++) {
                ArrayList<BanjiView> bjlist = new ArrayList<BanjiView>();
                GradeClass gradeClass = njList.get(i);
                GradeClassView classView = new GradeClassView();
                classView.setTid("school_" + schoolId + "section_" + classSection.getId() + "nianji" + gradeClass.getNj());
//                    classView.setNjname(GradeNameUtil.getStringName(gradeClass.getNj()));
                classView.setNjname(ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(), "nj"));
                classView.setPid("school_" + schoolId + "section_" + classSection.getId());
                List<GradeClass> bjList = teachTaskService.findBjList(gradeClass.getNj(), gradeClass.getXd(), gradeClass.getSchoolId());
                //得到年级下的班级集合
                for (int m = 0; m < bjList.size(); m++) {
                    BanjiView banjiView = new BanjiView();
                    GradeClass banji = bjList.get(m);
                    if (courseClassList != null) {
                        for (CourseClass courseClass : courseClassList) {
                            if (courseClass.getClassId().equals(banji.getId()))
                                banjiView.setSelectedId(courseClass.getClassId());
                        }
                    }
                    banjiView.setId(banji.getId());
                    banjiView.setName(banji.getName());
                    banjiView.setPid("school_" + schoolId + "section_" + classSection.getId() + "nianji" + gradeClass.getNj());
                    bjlist.add(banjiView);
                }
                classView.setBanjiview(bjlist);
                njlist.add(classView);
            }
            sectionView.setNjview(njlist);
            //年级排序
            if (njlist.size() != 0)
                for (int i = 0; i < njlist.size() - 1; i++) {
                    for (int j = i + 1; j < njlist.size(); j++) {
                        int left = NumberConvertUtil.convertS2I(njlist.get(i).getTid().substring(njlist.get(i).getTid().length() - 1));
                        int right = NumberConvertUtil.convertS2I(njlist.get(j).getTid().substring(njlist.get(j).getTid().length() - 1));
                        if (left > right) {
                            GradeClassView temp = njlist.get(i);
                            njlist.set(i, njlist.get(j));
                            njlist.set(j, temp);
                        }
                    }
                }
            sectionViewList.add(sectionView);
        }
        model.addAttribute("sectionViewList", sectionViewList);
        return "teachTask/courseClassPop";
    }

    //课程的增删改
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public String updateCourse(HttpServletRequest request, Model model) {
        String type = getParamVal(request, "type");
        User user = getLoginUser();
        String id = getParamVal(request, "id");
        String name = request.getParameter("name");
        String roomType = request.getParameter("roomType");
        String courseType = request.getParameter("courseType");
        String cycleYear = request.getParameter("cycleYear");
        String _semester = request.getParameter("semester");
        int semester = 0;
        if (_semester != "" && _semester != null) {
            semester = Integer.parseInt(_semester);
        }
        String schoolId = user.getSchoolId();
        TeachCycle teachCycle = null;
        //根据schoolId和学期学年数据查询cycleId
        if (cycleYear != null) {
            teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, semester, schoolId);
        }
        Course courseFromDB = null;
        if (id != "" && id != null) {
            courseFromDB = teachTaskService.getCourseByPrimaryKey(id);
        }
        if (ConstantUtil.UPDATE.equalsIgnoreCase(type.trim())) {
            courseFromDB.setRoomType(roomType);
            courseFromDB.setName(name);
            courseFromDB.setCycleId(teachCycle.getId());
            courseFromDB.setCourseType(courseType);
            int succ = teachTaskService.saveCourse(courseFromDB, user);
            return "teachTask/course";
        } else if (ConstantUtil.DELETE.equalsIgnoreCase(type.trim())) {
            courseFromDB.setDelFlag(1);
            teachTaskService.saveCourse(courseFromDB, user);
            return "teachTask/course";
        } else if (ConstantUtil.INSERT.equalsIgnoreCase(type.trim())) {
            //根据cycleId name schoolId查询该课程是否已存在
            Course courseIn = teachTaskService.findCourseBySchoolIdAndCycleIdAndName(teachCycle.getId(), name, schoolId);
            //若存在就更新
            if (courseIn != null) {
                teachTaskService.saveCourse(courseIn, user);
                return "teachTask/course";
            }

            Course course = new Course();
            course.setRoomType(roomType);
            course.setName(name);
            course.setCourseType(courseType);
            course.setCycleId(teachCycle.getId());
            teachTaskService.saveCourse(course, user);
        }
        return "teachTask/course";
    }

    //选择授课班级
    @RequestMapping(value = "/course/class/add", method = RequestMethod.POST)
    public String courseClass(HttpServletRequest request) {
        String courseId = request.getParameter("courseId");
        String classIds = request.getParameter("classIds");
        String[] idsArray = classIds.split(",");
        List<CourseClass> courseClasses = new ArrayList<>();
        if (idsArray.length > 0) {
            for (String classId : idsArray) {
                CourseClass courseClass = new CourseClass();
                if (classId != "" && classId != null) {
                    courseClass.setId(PrimaryKey.get());
                    courseClass.setClassId(classId);
                    courseClass.setCourseId(courseId);
                }
                courseClasses.add(courseClass);
            }
        }
        teachTaskService.batchInsertCourseClass(courseClasses);
        return "teachTask/course";
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
        CourseType courseTypeFromDB = null;
        if (typeId != null) {
            courseTypeFromDB = teachTaskService.selectCourseTypeByKey(typeId);
            courseTypeFromDB.setUpdateDate(new Date().getTime());
            courseTypeFromDB.setUpdateBy(user.getId());
            courseTypeFromDB.setName(oneCourse);
            courseTypeFromDB.setDelFlag(0);
            int succ = teachTaskService.saveCourseType(courseTypeFromDB);
            return ResultEntity.newResultEntity("修改成功", "teachTask/pop/courseCategoryPop");
        } else {
            if (oneCourse != null) {
                courseTypeFromDB = teachTaskService.findCourseTypeByName(oneCourse, schoolId);
                if (courseTypeFromDB != null) {
                    courseTypeFromDB.setDelFlag(0);
                    int succ = teachTaskService.saveCourseType(courseTypeFromDB);
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

    @ResponseBody
    @RequestMapping(value = "/course/type/batch", method = RequestMethod.POST)
    public ResultEntity courseCategoryAddBatch(HttpServletRequest request) {
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String tempAdd = request.getParameter("tempAdd");
        String tempUpdate = request.getParameter("tempUpdate");

        //查询所有课程类型
        List<CourseType> courseTypeList = teachTaskService.findAllCourseTypeBySchoolId(schoolId);

        if (tempAdd != null) {
            String[] batchAddArray = tempAdd.split(",");
            for (int i = 0; i < batchAddArray.length; i++) {
                if (courseTypeList.size() > 0) {
                    for (CourseType courseType : courseTypeList) {
                        if (batchAddArray[i].equals(courseType.getName())) {
                            return ResultEntity.newResultEntity("", "teachTask/pop/courseCategoryPop");
                        }
                    }
                }
            }
        }
        return ResultEntity.newResultEntity("创建成功", "teachTask/pop/courseCategoryPop");
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
        List<TeachCycle> teachCycleList = new ArrayList<>();
        //时间格式的转换
        for (TeachCycle teachCycle : list) {
            TeachCylcleExtention teachCylcleExtention = new TeachCylcleExtention();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = new Date(teachCycle.getBeginDate());
            String start = simpleDateFormat.format(date1);
            Date date2 = new Date(teachCycle.getEndDate());
            String end = simpleDateFormat.format(date2);
            teachCylcleExtention.setStart(start);
            teachCylcleExtention.setEnd(end);
            teachCylcleExtention.setTeachCycle(teachCycle);
            teachCycleList.add(teachCylcleExtention);
        }
        model.addAttribute("teachCycleList", teachCycleList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("list", list);
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
        TeachCycle teachCycleDB = null;
        if (id != null && id != "") {
            teachCycleDB = teachTaskService.selectByKey(id);
        }

        //使劲
        String strBeginDate = "";
        String strEndDate = "";
        TeachCycleView teachCycleView = new TeachCycleView();
        teachCycleView.setTeachCycle(teachCycleDB);
        try {
            strBeginDate = longToString(teachCycleDB.getBeginDate(), "yyyy-MM-dd");
            strEndDate = longToString(teachCycleDB.getEndDate(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        teachCycleView.setStrBeginDate(strBeginDate);
        teachCycleView.setStrEndDate(strEndDate);
        model.addAttribute("teachCycle", teachCycleView);
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
        User user = getLoginUser();
        TeachCycle teachCycleDB = null;
        String schoolId = user.getSchoolId();
        if (cycleYear == "" || cycleSemester == "" || _beginDate == "" || _endDate == "" || weekCount == "") {
            ResultEntity.newResultEntity("所填项均为必填", "teachTask/cycleIndex");
        } else {
            Long beginDate = 1L;
            Long endDate = 1L;
            //时间格式转换
            try {
                if (_beginDate != null && _endDate != null) {
                    beginDate = stringToLong(_beginDate, "yyyy-MM-dd");
                    endDate = stringToLong(_endDate, "yyyy-MM-dd");
                }
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
                    teachCycleDB.setBeginDate(beginDate);
                    teachCycleDB.setEndDate(endDate);
                    teachCycleDB.setSchoolId(schoolId);
                    teachCycleDB.setWeekCount(NumberConvertUtil.convertS2I(weekCount));
                    teachTaskService.saveTeachCycle(teachCycleDB);
                    return ResultEntity.newResultEntity("修改成功", "teachTask/cycleIndex");
                }
            } else {
                //根据学年 学期  学校Id判断该学期是否已经存在
                TeachCycle teachCycle = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
                if (teachCycle != null) {
                    return ResultEntity.newResultEntity("该学期已经存在，无需再创建", "teachTask/cycleIndex");
                } else {
                    TeachCycle teachCycleNew = new TeachCycle();
                    teachCycleNew.setCreateBy(user.getId());
                    teachCycleNew.setDelFlag(0);
                    teachCycleNew.setCreateDate(System.currentTimeMillis());
                    teachCycleNew.setCycleYear(cycleYear);
                    teachCycleNew.setCycleSemester(NumberConvertUtil.convertS2I(cycleSemester));
                    teachCycleNew.setBeginDate(beginDate);
                    teachCycleNew.setEndDate(endDate);
                    teachCycleNew.setSchoolId(schoolId);
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
                        preCycleId = teachCycle1.getId();
                    } else if (NumberConvertUtil.convertS2I(cycleSemester) == 1) {
                        String[] arr = cycleYear.split("-");
                        Integer before = Integer.parseInt(arr[0]) - 1;
                        Integer after = Integer.parseInt(arr[1]) - 1;
                        String preCycleYear = before.toString() + "-" + after.toString();
                        teachCycle1 = teachTaskService.getCycleByYearSemester(cycleYear, NumberConvertUtil.convertS2I(cycleSemester), schoolId);
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
                                classRoomList = teachTaskService.findAllClassRoomByCycleId(preCycleId);

                                List<RefRoomCycle> cycleList = new ArrayList<>();
                                for (ClassRoom classRoom : classRoomList) {
                                    String roomId = PrimaryKey.get();
                                    classRoom.setRoomId(roomId);

                                    //教室周期关联
                                    RefRoomCycle cycle = new RefRoomCycle();
                                    cycle.setCycleId(thisCycleId);
                                    cycle.setRoomId(roomId);
                                    cycle.setId(PrimaryKey.get());
                                    cycleList.add(cycle);
                                }
                                teachTaskService.batchInsertClassRoom(classRoomList);
                                teachTaskService.batchSaveRefRoomCycle(cycleList);
                            } else if (oneInfo.equals("班主任安排")) {

                                teacherClassList = teachTaskService.findLastMasterByPreCycleId(preCycleId);
                                for (TeacherClass teacherClass : teacherClassList) {
                                    teacherClass.setCycleId(thisCycleId);
                                    //aaaaaaaaaaaaaaaaaaa teacherClass.setId(PrimaryKey.get());
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
                                    course.setCycleId(thisCycleId);
                                }
                                teachTaskService.batchInsertCourse(courseList);
                            }
                        }
                    }

                    System.out.println(courseClassList1.size());

                    return ResultEntity.newResultEntity("成功创建教学周期", "teachTask/cycleIndex");
                }
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
        System.out.println(courseClassList1.size());
        //
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
                        classRoom.setRoomId(roomId);

                        //教室，周期关联
                        RefRoomCycle cycle = new RefRoomCycle();
                        cycle.setId(PrimaryKey.get());
                        cycle.setCycleId(cycleId);
                        cycle.setRoomId(roomId);

                        cycleList.add(cycle);
                    }
                    teachTaskService.batchInsertClassRoom(classRoomList);
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
        if (GukeerStringUtil.isNullOrEmpty(teachCycle))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(year))
            year = teachCycle.getCycleYear();

        Map yearMap = new HashMap();
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
    @ResponseBody
    @RequestMapping(value = "/room/update", method = RequestMethod.POST)
    public ResultEntity updateRoom(@ModelAttribute("inputForm") ClassRoom room, HttpServletRequest request) {

        User user = getLoginUser();

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());

        String pri = PrimaryKey.get();
        room.setSchoolId(user.getSchoolId());
        if (StringUtil.isNotEmpty(room.getRoomId())) {

            room.setUpdateBy(user.getId());
            room.setUpdateDate(System.currentTimeMillis());

        } else if (StringUtil.isEmpty(room.getRoomId())) {

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
        return ResultEntity.newResultEntity("删除成功");
    }

    //教室，删除，批量删除
    @RequestMapping(value = "/room/delete", method = RequestMethod.POST)
    public void deleteRoom(HttpServletRequest request) {
        String roomIds = getParamVal(request, "roomId");
        List<String> roomIdList = ConstantUtil.splitWithOutNull(roomIds);

        for (String roomId : roomIdList) {
            ClassRoom room = new ClassRoom();
            room.setDelFlag(1);
            room.setRoomId(roomId);
            teachTaskService.saveClassRoom(room, null);
        }
    }

    //教室导入模板
    @RequestMapping(value = "/room/download")
    public void exportRoomTemplate(HttpServletResponse response) {
        try {
            String fileName = "教室导入模板.xlsx";
            String anno = "请确保您的校区，教室类型等字段填写合格";
            new ExportExcel("教室导入模板", IOClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
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
                room.setRoomId(roomId);
                room.setRoomName(roomView.getRoomName());

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
                e.printStackTrace();
                continue;
            }
        }
        if (correctRoomList.size() > 0)
            teachTaskService.batchInsertClassRoom(correctRoomList);

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
            String anno = "请确保您的校区，教室类型等字段填写合格";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOClassRoomView> exportFile = new ArrayList<IOClassRoomView>();
            for (JsonElement jsonElement : jsonArray) {
                IOClassRoomView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOClassRoomView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("教室信息", IOClassRoomView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
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
        if (GukeerStringUtil.isNullOrEmpty(teachCycle)) {
            teachCycle = cycleList.get(0);
        }
        Map yearMap = new HashMap();
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
        if (teacherClasses.size() > 0) {
            teacherClassPageInfo = teachTaskService.findMasterByClassIdListAndType(classIdList, pageNum, pageSize, cycleId);
        }

        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("chooseYear", cycleYear);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("semesterList", semesterList);
        if (teacherClasses == null || teacherClasses.size() == 0) {
            return "teachTask/masterIndex";
        }
        //根据所有的classId查询中间表 得到所有的班主任
        PageInfo<BZRView> pageInfo = teachTaskService.getAllMasterByGradeClassIds(classIdList, pageNum, pageSize, xdId, NumberConvertUtil.convertS2I(nj));
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
            bzrView2.setDeputymasterName(deputyNames);
            bzrViewPageList.add(bzrView2);
        }


        model.addAttribute("xdId", xdId);
        model.addAttribute("cycleList", cycleList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("cycleYear", cycleYear);
        model.addAttribute("classSectionList", classSectionList);
        model.addAttribute("bzrViewPageList", bzrViewPageList);
        model.addAttribute("cycleSemester", cycleSemester);
        model.addAttribute("teacherClassPageInfo", teacherClassPageInfo);
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
        List<TeacherClass> TeacherClassList = new ArrayList<>();
        //全部根据classId进行批量更新
        if (classId != null) {
            //首先把原来的数据根据classId全部物理删除 再燃后直接批量插入
            int deleteSucc = teachTaskService.deleteTeacherClassByClassId(classId);
        }

        TeacherClass teacherClass = new TeacherClass();
        teacherClass.setType(1);
        teacherClass.setClassId(classId);
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
            //aaaaaaaaaaaaaaaaaaaaaaaaa   teacherClass1.setId(PrimaryKey.get());
//            teacherClass1.setCycleId();
            teacherClass1.setType(2);
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

        String classId = request.getParameter("classId");
        String deputyName = request.getParameter("deputyName");
        String master = request.getParameter("master");
        String teacherId = request.getParameter("teacherId");
        String deputyIds = request.getParameter("deputyIds");
        String cycleId = request.getParameter("cycleId");
        try {
            deputyName = new String(deputyName.getBytes("iso8859-1"), "utf-8");
            master = new String(master.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //根据classId查询得到班级的名字
        GradeClass gradeClass = classService.selectClassById(classId);


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
        model.addAttribute("cycleId", cycleId);
        return "teachTask/pop/masterEditPop";
    }


    //班主任搜索
    @RequestMapping(value = "/master/search")
    public String rsIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
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
                List<BZRView> bzrViews = teachTaskService.findMasterByCycleId(cycleId);
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
        List<TeacherClass> exitTeacherClassList = new ArrayList<TeacherClass>();
        IOCMasterView errorMaster = new IOCMasterView();

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

        TeachCycle cycleLatest = getLatestTeachCycle(user.getSchoolId());
        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOCMasterView> list = importExcel.getDataList(IOCMasterView.class);
        for (IOCMasterView iocMasterView : list) {
            TeacherClass ref1 = new TeacherClass();
            GradeClass gradeClass = (GradeClass) classMap.get(iocMasterView.getBj() + iocMasterView.getNj() + classSectionMap.get(iocMasterView.getXdName()));
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
            if (teachCycle != null) {
                cycleId = teachCycle.getId();
            }
            if (gradeClass.getNj().equals(iocMasterView.getNj()))
                if (gradeClass.getXd().equals(classSectionMap.get(iocMasterView.getXdName())))
                    ref1.setClassId(gradeClass.getId());
            ref1.setCycleId(cycleId);

            //班主任
            String[] masterNoArray = iocMasterView.getMasterName().split("\\(");
            String masterNo = masterNoArray[1].substring(0, masterNoArray[1].length() - 1);
            String teacherId = "";
            if (masterNo.equals(teacherMap.get(masterNo).getNo())) {
                Teacher teacher = teacherMap.get(masterNo);
                teacherId = teacher.getId();
                ref1.setTeacherId(teacherMap.get(masterNo).getId());
            }

            ref1.setType(1);
            //aaaaaaaaaaaaaaa ref1.setId(PrimaryKey.get());
            //根据classId cycleId masterteacherId去查数据  若存在则不导入
            TeacherClass teacherClass = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherId);
            if (teacherClass == null) {
                correctTeacherClassList.add(ref1);
            }
            //副班主任
            String deputyNoStr = iocMasterView.getDeputymasterName();
            if (deputyNoStr != null) {
                String[] deputyNoArray = deputyNoStr.split(",");
                for (int i = 0; i < deputyNoArray.length; i++) {
//                    System.out.println(deputyNoArray[i]);
                    String _deputyNo = deputyNoArray[i];
                    String[] _deputyArray = _deputyNo.split("\\(");
                    String deputyNo = _deputyArray[1].substring(0, _deputyArray[1].length() - 1);
                    TeacherClass ref2 = new TeacherClass();
                    ref2.setClassId(gradeClass.getId());
                    if (deputyNo.equals(teacherMap.get(deputyNo)))
                        System.out.println(teacherMap.get(deputyNo).getId());
                    ref2.setTeacherId(teacherMap.get(deputyNo).getId());
                    ref2.setType(2);
                    ref2.setCycleId(cycleId);
                    //aaaaaaaaaaaaaaaaa ref2.setId(PrimaryKey.get());
                    TeacherClass teacherClass1 = teachTaskService.findTeacherClassByClassIdCycleIdTeacherId(classId, cycleId, teacherMap.get(deputyNo).getId());
                    if (teacherClass1 == null) {
                        correctTeacherClassList.add(ref2);
                    }
                }
            }
        }

        teachTaskService.insertBatchTeacherClass(correctTeacherClassList);
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
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
        if (GukeerStringUtil.isNullOrEmpty(teachCycle))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new HashMap();
        for (TeachCycle cycle : cycleList) {
            if (cycleYear.equals(cycle.getCycleYear()))
                semesterList.add(cycle);
            yearMap.put(cycle.getCycleYear(), cycle);
        }
        //根据cycleId、schoolId查询存在的课程
        List<Course> courseList = teachTaskService.findAllCourseBySchoolIdAndCycleId(schoolId, teachCycle.getId());

        String courseName = null;
        if (StringUtil.isEmpty(courseId)) {
            if (courseList != null && courseList.size() > 0) {
                Course course = courseList.get(0);
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

        PageInfo<BZRView> pageInfo = teachTaskService.findAllCourseTeacherBycourseClassList(courseClassList, pageNum, pageSize);
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
                String cycleId = "";
                GradeClass gradeClass = (GradeClass) classMap.get(iocCourseTeacherView.getBj() + iocCourseTeacherView.getNj() + classSectionMap.get(iocCourseTeacherView.getXdName()));

                //拿到对应的classId
                if (gradeClass.getXd().equals(classSectionMap.get(iocCourseTeacherView.getXdName()))) {
                    if (gradeClass.getNj().equals(iocCourseTeacherView.getNj())) {
                        if (gradeClass.getName().equals(iocCourseTeacherView.getBj())) {
                            gradeClassId = gradeClass.getId();
                            cycleId = gradeClass.getCycleId();
                        }
                    }
                }

                String courseId = (String) courseNameMap.get(cycleId + iocCourseTeacherView.getCourse());

                //????????????????????????????????????老师姓名+（编号）
                String courseTeacher = iocCourseTeacherView.getCourseTeacher();
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
                courseClass.setCourseId(courseId);
                courseClass.setId(PrimaryKey.get());
                correcCourseClassList.add(courseClass);
            } catch (Exception e) {
                errorIOCCourseTeacherViewList.add(errCourse);
                e.printStackTrace();
                continue;
            }
        }
        if (correcCourseClassList.size() > 0)
            teachTaskService.batchInsertCourseClass(correcCourseClassList);
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
            String anno = "注释：红色字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n";
            new ExportExcel("人员数据", IOCCourseTeacherView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
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
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String cycleYear = getParamVal(request, "cycleYear");
        String cycleSemester = getParamVal(request, "cycleSemester");
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        String teacherName = request.getParameter("name");
        try {
            if (teacherName != null)
                teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
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

        Map yearMap = new HashMap();
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

    //班主任下载导入模板下载导入模板
    @ResponseBody
    @RequestMapping(value = "/master/moban/download")
    public void exportMaster(HttpServletResponse response) {
        try {
            String fileName = "班主任导入模板.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n";
            new ExportExcel("班主任", IOCMasterView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入失败的CourseClass表
    @RequestMapping(value = "/master/error/export", method = RequestMethod.POST)
    public void errorMaster(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "错误信息列表.xlsx";
            String anno = "请确保您的学段，年级班级等字段填写合格";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOCMasterView> exportFile = new ArrayList<IOCMasterView>();
            for (JsonElement jsonElement : jsonArray) {
                IOCMasterView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOCMasterView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("班主任", IOCMasterView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
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
            new ExportExcel("任课教师安排信息", IOCCourseTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
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
            new ExportExcel("任课教师", IOCCourseTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
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
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
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

        Map yearMap = new HashMap();
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
        PageInfo<RefClassRoomView> pageInfo = teachTaskService.getRefClassRoomList(pageNum, pageSize, schoolId, cycleId);

        model.addAttribute("yearList", yearMap.keySet());
        model.addAttribute("semesterList", semesterList);
        model.addAttribute("cycleYear", cycleYear);
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
            String anno = "注释：红色字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n" +
                    "          2.学期项:数字1表示第一学期,数字2表示第二学期\n";

            new ExportExcel("班级教室", IOCRefClassRoomView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
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
        if (allRefClassRoomList != null) {
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
                refClassRoom.setId(PrimaryKey.get());
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

                //根据schoolId、校区名称、所在教学楼楼号、教室号可以唯一确定roomId
                String classRoomNum = iocRefClassRoomView.getClassRoomNum();
                Integer teachBuilding = iocRefClassRoomView.getTeachBuilding();

                /////////////////////roomId
                String roomId = "";
                System.out.println(classRoomMap.get(schoolTypeId + teachBuilding + classRoomNum));
                if (null != classRoomMap.get(schoolTypeId + teachBuilding + classRoomNum)) {
                    ClassRoom classRoom = (ClassRoom) classRoomMap.get(schoolTypeId + teachBuilding + classRoomNum);
                    roomId = classRoom.getRoomId();
                }

                refClassRoom.setRoomId(roomId);
                //查看是否已经导入
                if (null != refClassRoomMap.get(cycleId + gradeClassId + roomId)) {
                    throw new ErrcodeException("已经导入过，请勿重复导入");
                } else {
                    correctRefClassRoomList.add(refClassRoom);
                }
            } catch (Exception e) {
                errorRefClassRoomList.add(errRefClassRoom);
                e.printStackTrace();
                continue;
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
        return null;
    }


    //班级教室编辑的弹窗
    @RequestMapping(value = "/ref/class/room/edit/pop", method = RequestMethod.GET)
    public String refClassRoomEditPop(HttpServletRequest request, Model model) {
        String refId = getParamVal(request, "refId");
        String schoolTypeId = getParamVal(request, "schoolTypeId");
        String building = getParamVal(request, "building");
        String roomId = getParamVal(request, "roomId");
        //根据refId查询
        User user = getLoginUser();
        String schoolId = user.getSchoolId();
        RefClassRoomView refClassRoomView = teachTaskService.findRefClassRoomViewByRefId(refId);
        if (schoolTypeId ==""){
            schoolTypeId=refClassRoomView.getSchoolTypeId();
        }
        if (building==""){
            building=refClassRoomView.getTeachBuildingName();
        }
        //查询所有的校区
        List<SchoolType> schoolTypeList = teachTaskService.findAllSchoolTypeBySchoolId(schoolId);
        //根据schoolTypeId查询所有的教学楼 并根据教学楼分组
        List<ClassRoom> buildingList = teachTaskService.findBuildingByschoolTypeId(schoolTypeId);
        //根据schoolTypeId building查询教室号
        List<ClassRoom> rooms = teachTaskService.findRooomsBySchoolTypeIdAndBuilding(schoolTypeId,building);
//        model.addAttribute("teachBuildingList", buildingMap.keySet());
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
        if (GukeerStringUtil.isNullOrEmpty(teachCycle))
            if (cycleList.size() > 0)
                teachCycle = cycleList.get(0);

        List<TeachCycle> semesterList = new ArrayList<>();
        if (StringUtil.isEmpty(cycleYear))
            cycleYear = teachCycle.getCycleYear();

        Map yearMap = new HashMap();
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

        if (courseClassViewList.size() > 0) {
            model.addAttribute("inputLength", ">0");
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
}

