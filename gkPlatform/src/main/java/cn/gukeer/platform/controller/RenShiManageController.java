package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.*;
import cn.gukeer.common.utils.excel.ExportExcel;
import cn.gukeer.common.utils.excel.ImportExcel;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.TeacherView;
import cn.gukeer.platform.modelView.importExport.IOTeacherView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 * teacher
 */
@Controller
@RequestMapping(value = "/renshi")
public class RenShiManageController extends BasicController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    AppService appService;


    //    职务信息页面
    @RequestMapping(value = "/zhiwu/index")
    public String rsZhiWuIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String id = getParamVal(request, "titleId");
        String _id = id;

        List<Title> titleList = teacherService.selectTitleBySchoolId(getLoginUser().getSchoolId().toString());
        if (_id == null || _id.equals("") || _id.equals("0")) {
            if (titleList.size() > 0) {
                _id = titleList.get(0).getId(); //默认选择第一个
            }
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherService.findTeacherByTitleId(_id, getLoginUser().getSchoolId(),null,null);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);

        Title currentTitle = teacherService.selectTitleById(_id);
        model.addAttribute("currentTitle", currentTitle);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("titleList", titleList);
        model.addAttribute("teacherList", teacherList);
        return "renShiManage/rsZhiWuManage";
    }

    //职务详情页面
    @RequestMapping(value = "/title/detail")
    public String schoolDetails(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "titleId");

        Title title = teacherService.selectTitleById(id);
        List<Teacher> teacherList = teacherService.findTeacherByTitleId(id, getLoginUser().getSchoolId(),null,null);

        model.addAttribute("title", title);
        model.addAttribute("teacherList", teacherList);
        return "renShiManage/rsZhiWuDetails";
    }

    //    职务信息--删除教师职务
    @ResponseBody
    @RequestMapping(value = "/title/teacher/delete")
    public void titleIdDelete(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        Teacher teacher = teacherService.findTeacherById(id);
        teacher.setTitleId("");
        teacherService.save(teacher);
    }

    //    职务详情--新增教师职务
    @RequestMapping(value = "/title/teacher/add")
    public String teacherAdd(HttpServletRequest request, Model model) {
        String titleId = getParamVal(request, "titleId");

        List<Teacher> teacherList = teacherService.findHaveAccountTeacher(getLoginUser().getSchoolId());

        model.addAttribute("titleId", titleId);
        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        return "renShiManage/rsZhiWuTeacherAdd";
    }

    //职务详情--教师职务保存
    @ResponseBody
    @RequestMapping(value = "/title/teacher/save")
    public void teacherSave(HttpServletRequest request) {
        String titleId = getParamVal(request, "titleId");
        String teacherId = getParamVal(request, "teacherId");
        String[] idArr = teacherId.split(",");
        for (int a = 0; a < idArr.length; a++) {
            Teacher teacher = teacherService.findTeacherById(idArr[a]);
            teacher.setTitleId(titleId);
            teacherService.save(teacher);
        }
    }

    //职务新增/编辑页面
    @RequestMapping(value = "/title/add")
    public String aa(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "titleId");

        String _id = id;
        if (!(_id == null || _id == "")) {
            Title title = teacherService.selectTitleById(_id);
            model.addAttribute("title", title);
        }
        return "renShiManage/rsZhiwuAdd";
    }

    //职务修改/新增保存
    @ResponseBody
    @RequestMapping(value = "/title/save", method = RequestMethod.POST)
    public void titleSave(HttpServletRequest request) {
        String titleId = getParamVal(request, "titleId");
        String titleName = getParamVal(request, "titleName");
        String titlePx = getParamVal(request, "titlePx");
        String titleRemark = getParamVal(request, "titleRemark");
        String title_id = titleId;

        Title title = new Title();
        title.setMc(titleName);
        title.setPx(titlePx);
        title.setRemarks(titleRemark);
        title.setSchoolId(getLoginUser().getSchoolId().toString());
        if (title_id == null || title_id.equals("")) {
            //新增操作
            title.setId(PrimaryKey.get());
            title.setCreateBy(getLoginUser().getId());
            title.setCreateDate(System.currentTimeMillis());
            teacherService.saveTitle(title);
        } else {
            //修改操作
            title.setId(title_id);
            teacherService.updateTitle(title);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/title/delete", method = RequestMethod.POST)
    public void titleDelete(HttpServletRequest request) {
        String id = getParamVal(request, "id");

        String[] idArr = id.split(",");
        for (int a = 0; a < idArr.length; a++) {
            Title title = new Title();
            title.setId(idArr[a]);
            title.setDelFlag(1);
            teacherService.updateTitle(title);
        }
    }

    //角色分配
    @RequiresPermissions("renShi:role:view")
    @RequestMapping(value = "/rolefp/index")
    public String rsRoleFpIndex(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();
        HttpSession session = request.getSession();
        Object appId = session.getAttribute("renshiId");

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
            userRoleList = userService.findUserRoleByCriteria(idList, role, loginUser.getSchoolId());//List<Integer> ids, Role role,String schoolId
        }
        if (userRoleList.size() > 0) {
            List<String> userIds = new ArrayList<String>();
            for (UserRole userRole : userRoleList) {
                userIds.add(userRole.getUserId());
            }
            List<User> userList = userService.selectUsersInIds(userIds);
            List<Teacher> teacherList = new ArrayList<Teacher>();
            List<String> teacherIdList = new ArrayList<>();
            for (User user : userList) {
                teacherIdList.add(user.getRefId());
//                Teacher teacher = teacherService.findTeacherById(user.getRefId());
//                if (teacher != null) {
//                    teacherList.add(teacher);
//                }
            }
            teacherList = teacherService.selectBatchTeachers(teacherIdList,loginUser.getSchoolId());
            PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("pageInfo", pageInfo);
        }

        if (roleId.equals("") && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }
        model.addAttribute("roleList", roleList);
        model.addAttribute("currentRole", roleId);
        return "renShiManage/rsRoleFpManage";
    }

    //角色分配-添加用户-弹出页面
    @RequestMapping(value = "/roleuser/add")
    public String addUser(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");

        List<Teacher> teacherList = teacherService.findHaveAccountTeacher(getLoginUser().getSchoolId());

        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        model.addAttribute("roleId", roleId);
        return "renShiManage/rsRoleUserAdd";
    }

    //角色分配-添加用户保存
    @ResponseBody
    @RequestMapping(value = "/roleuser/save")
    public ResultEntity addUserSave(HttpServletRequest request) {
        String teacherId = getParamVal(request, "teacherId");
        String roleId = getParamVal(request, "roleId");

        List<User> userList = userService.getTeacherByRefId(teacherId);
        List<UserRole> userRoleList = new ArrayList<>();
        String schoolId = getLoginUser().getSchoolId();
        for (User user : userList) {
            UserRole userRole = new UserRole();
            userRole.setSchoolId(schoolId);
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        ResultEntity entity = ResultEntity.newResultEntity();
        if (userRoleList.size() <= 0)
            entity.setMsg("分配失败");
        try {
            userService.saveUserRoleBatch(userRoleList);
            entity.setMsg("分配成功");
            entity.setCode(200);
        }catch (Exception e){
            e.printStackTrace();
            entity.setMsg("分配失败");
        }
        return entity;
    }

    //部门管理
    @RequiresPermissions("renShi:bumen:view")
    @RequestMapping(value = "/bumen/index")
    public String rsBuMenIndex(HttpServletRequest request, Model model) {
        String departmentId = getParamVal(request, "departmentId");
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);

        String teacherName = null;
        try {
            teacherName = java.net.URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Department currentDepart = departmentService.findDepartmentById(departmentId);//当前页面显示的部门
        List<Department> departmentList = departmentService.findAllDepartment(getLoginUser().getSchoolId());
        StringBuffer builder = new StringBuffer();
        for (Department depart : departmentList) {
            builder.append(depart.getParentId().toString() + ",");
        }

        Teacher _teacher = new Teacher();
        _teacher.setName(teacherName);
        _teacher.setSchoolId(getLoginUser().getSchoolId());

        PageInfo<Teacher> pageInfo = teacherService.findTeacherByDepartmentId(departmentId, _teacher, pageNum, pageSize); //teacherMapper.selectByExample(example);
        List<Teacher> teacherList = pageInfo.getList();
        List<TeacherView> teacherViewList = new ArrayList<TeacherView>();
        for (Teacher teacher : teacherList) {
            TeacherView teacherView = new TeacherView();
            teacherView.setId(teacher.getId());
            teacherView.setName(teacher.getName());
            teacherView.setGender(teacher.getGender());
            teacherView.setNo(teacher.getNo());
            for (int i = 0; i < departmentList.size(); i++) {
                if (teacher.getDepartmentId().equals(departmentList.get(i).getId())) {
                    teacher.setDepartmentId(teacher.getDepartmentId());
                    teacherView.setDepartName(departmentList.get(i).getName());
                    break;
                }
            }
            teacherViewList.add(teacherView);
        }
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("parentList", builder);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("currentDepart", currentDepart);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("teacherViewList", teacherViewList);
        return "renShiManage/rsBuMenManage";
    }

    //部门修改
    @RequestMapping(value = "/rsbumen/update")
    public String rsBuMenModify(HttpServletRequest request, Model model) {
        String teacherId = getParamVal(request, "teacherId");
        String _teacherId = teacherId;
        Teacher teacher = teacherService.findTeacherById(_teacherId);
        //查询所有部门
        List<Department> departmentList = departmentService.findAllDepartment(getLoginUser().getSchoolId());

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("teacher", teacher);
        return "renShiManage/buMenModify";
    }

    //修改部门保存
    @ResponseBody
    @RequestMapping(value = "/rsbumen/save")
    public void rsBumenModifySave(HttpServletRequest request) {
        String teacherId = getParamVal(request, "teacherId");
        String departMentId = getParamVal(request, "departName");

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setDepartmentId(departMentId);
        teacherService.updateTeacher(teacher);
    }

    @ResponseBody
    @RequestMapping(value = "/renyuan/delete")
    public void deleteTeacher(HttpServletRequest request) {
        String id = getParamVal(request, "id");

        String[] idArr = id.split(",");
        for (int i = 0; i < idArr.length; i++) {
            Teacher teacher = new Teacher();
            teacher.setDelFlag(1);
            teacher.setId(idArr[i]);
            teacher.setSchoolId(getLoginUser().getSchoolId());
            teacherService.updateTeacher(teacher);
            //删除sys——user
            User user = new User();
            user.setDelFlag(1);
            userService.updateUserByRefId(user, idArr[i], getLoginUser().getSchoolId(), 1);
        }
    }

    //删除部门人员
    @ResponseBody
    @RequestMapping(value = "/bumenUser/delete")
    public void rsBumenModifyDelete(HttpServletRequest request) {
        String teacherId = getParamVal(request, "teacherId");

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        //teacher.setDelFlag(1);
        teacher.setDepartmentId("0");//删除这个人员，将这个人员移动到 其他（序号为0） 部门 默认值
        teacherService.updateTeacher(teacher);
    }

    //删除部门
    @ResponseBody
    @RequestMapping(value = "/bumen/delete")
    public void rsBumenDelete(HttpServletRequest request) {
        String departmentId = getParamVal(request, "departmentId");
        String departId = departmentId;

        Department department = new Department();
        department.setDelFlag(1);
        department.setId(departId);
        departmentService.updateDepartment(department);

        List<String> allListId = new ArrayList<String>();
        allListId.add(departId);
        allListId.addAll(departmentService.getAllSonDepartment(allListId, getLoginUser().getSchoolId()));
        //删除部门下的人员；（查询该部门信息）
        Teacher teacherDelete = new Teacher();
        teacherDelete.setDepartmentId("0");  // 删除部门，该部门下的人员放到其他中
        teacherService.updateTeacherByCriteria(teacherDelete, allListId, getLoginUser().getSchoolId());
    }

    //账号管理
    @RequiresPermissions("renshi:account:view")
    @RequestMapping(value = "/account/index")
    public String rsAccountIndex(HttpServletRequest request, Model model) {
        String schoolId = getLoginUser().getSchoolId();

        Map<String, String> param = new HashMap<String, String>();
        param.put("teacherName", getParamVal(request, "teacherName"));
        param.put("pageSizeHave", getParamVal(request, "pageSizeHave"));
        param.put("pageNumHave", getParamVal(request, "pageNumHave"));
        param.put("pageSizeNo", getParamVal(request, "pageSizeNo"));
        param.put("pageNumNo", getParamVal(request, "pageNumNo"));


        String password = "000000";
        List<Config> configs = schoolService.selectConfigByType("defaultPassword");
        if (configs.size() > 0) {
            for (Config config : configs) {
                if (config.getParamKey().equals("teacher")) {
                    password = config.getParamValue();//默认密码
                    break;
                }
            }
        }
        model.addAttribute("password", password);

        Map haveAccountMap = teacherService.getTeacherList(param, true, schoolId);
        model.addAttribute("teacherListHave", haveAccountMap.get("teacherList"));
        model.addAttribute("pageInfoHave", haveAccountMap.get("pageInfo"));

        Map noAccountMap = teacherService.getTeacherList(param, false, schoolId);
        model.addAttribute("teacherListNo", noAccountMap.get("teacherList"));
        model.addAttribute("pageInfoNo", noAccountMap.get("pageInfo"));

        model.addAttribute("teacherName", haveAccountMap.get("teacherName"));
        model.addAttribute("whichPage", request.getParameter("whichPage"));//点击未生成翻页，停留在未生成的页面
        return "renShiManage/rsAccountManage";
    }

    //人事管理-人员管理
    @RequiresPermissions(value = {"renShi", "renShi:renYuan:view"}, logical = Logical.OR)
    @RequestMapping(value = "/renyuan/index")
    public String rsIndex(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String teacherName = getParamVal(request, "teacherName");
        try {
            teacherName = java.net.URLDecoder.decode(teacherName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        PageHelper.startPage(pageNum, pageSize);
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("schoolId", getLoginUser().getSchoolId());
        paramMap.put("teacherId", -1);//-1为查询所有人员
        paramMap.put("teacherName", "%" + teacherName + "%");
        List<Map<String, Object>> teacherList = teacherService.findTeacherViewByParams(paramMap);

        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(teacherList);

        model.addAttribute("teacherName", teacherName);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pageInfo", pageInfo);

        HttpSession session = request.getSession();
        if (StringUtil.isNotEmpty(getParamVal(request, "appId")))
            session.setAttribute("renshiId", getParamVal(request, "appId"));//将当前应用id存到session中

        return "renShiManage/rsRenYuanManage";
    }

    //导出数据页面
    @RequestMapping(value = "/moban/export")
    public String moBanExport(HttpServletRequest request, Model model) {
        String teachers = getParamVal(request, "teachers");
        String teacherName = getParamVal(request, "teacherName");
        String chooseSchoolId = getParamVal(request, "chooseSchoolId");
        model.addAttribute("teachers", teachers);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("chooseSchoolId", chooseSchoolId);
        return "renShiManage/moBanExport";
    }

    //导出功能
    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void exportFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            String teacherName = URLDecoder.decode(getParamVal(request, "teacherName"), "UTF-8");
            String teachers = getParamVal(request, "teachers");
            String _header = getParamVal(request, "header");
            String schoolId = getLoginUser().getSchoolId();
            String chooseSchoolId = getParamVal(request, "chooseSchoolId");
            if (StringUtil.isNotEmpty(chooseSchoolId)) {
                schoolId = chooseSchoolId;
                if (StringUtil.isNotEmpty(teacherName))
                    schoolId = null;
            }

            _header = URLDecoder.decode(_header, "UTF-8");//解决非post访问的中文乱码问题。

            //获取表头数据
            String[] head = _header.split(",");
            List<String> headerList = new ArrayList<String>();
            for (int i = 0; i < head.length; i++) {
                headerList.add(head[i]);
            }

            String fileName = "人事信息" + ".xlsx";
            List<IOTeacherView> teaList = new ArrayList<IOTeacherView>();
            List<Teacher> dataList = new ArrayList<Teacher>();

            if (StringUtil.isEmpty(teachers)) {
                //查询姓名导出
                dataList = teacherService.selectTeacherNameLike("%" + teacherName + "%", schoolId);
            } else {
                //导出check的数据
                dataList = teacherService.selectBatchTeachers(ConstantUtil.splitWithOutNull(teachers), schoolId);
            }
            if (dataList.size() > 0) {
                for (Teacher teacher : dataList) {
                    // Teacher teacher = teacherService.findTeacherById(teacherId[k]);
                    IOTeacherView ioTeacherView = new IOTeacherView();
                    ioTeacherView.setName(teacher.getName());
                    ioTeacherView.setAdrress(teacher.getAddress());
                    ioTeacherView.setBgsdh(teacher.getBgsdh());
                    ioTeacherView.setCym(teacher.getCym());
                    if (1 == teacher.getGender()) {
                        ioTeacherView.setGender("男");
                    } else if (2 == teacher.getGender()) {
                        ioTeacherView.setGender("女");
                    } else ioTeacherView.setGender("");

                    ioTeacherView.setGgjsjb(teacher.getGgjsjb());
                    ioTeacherView.setNo(teacher.getNo());
                    ioTeacherView.setMobile(teacher.getMobile());
                    ioTeacherView.setMail(teacher.getEmail());
                    ioTeacherView.setIdentity(teacher.getIdentity());
                    if (teacher.getStartWork() != null && teacher.getStartWork() != 0)
                        ioTeacherView.setStartWork(teacher.getStartWork().toString());
           /*     if (teacher.getTitleId() != null && teacher.getTitleId() != 0)
                    ioTeacherView.setTitle(teacher.getTitleId().toString());*/
                    if (teacher.getHighTime() != null && teacher.getHighTime() != 0)
                        ioTeacherView.setHignTime(teacher.getHighTime().toString());
                    ioTeacherView.setHignJob(teacher.getHighJob());
                    ioTeacherView.setPzxx(teacher.getPzxx());

                    if (teacher.getHtkssj() != null && teacher.getHtkssj() != 0)
                        ioTeacherView.setHtkssj(teacher.getHtkssj().toString());
                    ioTeacherView.setJtyb(teacher.getJtyb());
                    ioTeacherView.setSfzrjs(teacher.getSfzrjs());
                    ioTeacherView.setShenfen(teacher.getSf());
                    ioTeacherView.setWysp(teacher.getWysp());
                    ioTeacherView.setZgxz(teacher.getZgxz());
                    ioTeacherView.setXwsl(teacher.getXwsl());
                    ioTeacherView.setRjxkjb(teacher.getRjxkjb());
                    ioTeacherView.setXq(teacher.getXq());

                    ioTeacherView.setXj(teacher.getSalary());
                    ioTeacherView.setGwflf(teacher.getGwflf());
                    ioTeacherView.setJg(teacher.getJg());
                    ioTeacherView.setZzmm(teacher.getZzmm());
                    if (teacher.getYsbysj() != null && teacher.getYsbysj() != 0)
                        ioTeacherView.setYsbysj(teacher.getYsbysj().toString());
                    ioTeacherView.setZgxl(teacher.getZgxl());
                    ioTeacherView.setZgbyxx(teacher.getZgbyxx());
                    ioTeacherView.setYzy(teacher.getYzy());
                    ioTeacherView.setZgxw(teacher.getZgxw());
                    ioTeacherView.setZyjsgwfl(teacher.getZyjsgwfl());
                    ioTeacherView.setRjxk(teacher.getRjxk());
                    ioTeacherView.setSzjb(teacher.getSzjb());
                    ioTeacherView.setGzgwf(teacher.getGzgwf());

                    //dataList.add(teacher);
                    teaList.add(ioTeacherView);

                }
            }

            new ExportExcel("人事信息", headerList, "", 1).setDataListByHeader(teaList, headerList)
                    .write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载导入模板
    @ResponseBody
    @RequestMapping(value = "/moban/download")
    public void exportMoban(HttpServletResponse response) {
        try {
            String fileName = "人事导入模板.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n";
            new ExportExcel("人员数据", IOTeacherView.class, 2, anno, 1).setDataList(new ArrayList()).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入数据页面
    @RequestMapping(value = "/moban/import")
    public String moBanImport() {
        return "renShiManage/moBanImport";
    }

    //导入数据页面提交
    @ResponseBody
    @RequestMapping(value = "/moban/import/save", method = RequestMethod.POST)
    public ResponseEntity importExcel(@RequestParam(value = "file") MultipartFile file) throws Exception {
        Long begin = System.currentTimeMillis();
        List<IOTeacherView> errorTeacherList = new ArrayList<IOTeacherView>();
        List<Teacher> correctTeacherList = new ArrayList<Teacher>();
        IOTeacherView errorTeacher = new IOTeacherView();
        User user = getLoginUser();

        ImportExcel importExcel = new ImportExcel(file, 2, 0);
        List<IOTeacherView> list = importExcel.getDataList(IOTeacherView.class, 1);
        for (IOTeacherView teacherView : list) {
            try {
                errorTeacher = teacherView;
                Teacher teacher = new Teacher();
                teacher.setName(teacherView.getName());
                teacher.setGender(ConstantUtil.getKeyByValueAndFlag(teacherView.getGender(), "xb"));
                teacher.setQuanPin(CnToPyUtils.getPingYin(teacherView.getName()));
                teacher.setEmail(teacherView.getMail());
                teacher.setMobile(teacherView.getMobile());
                teacher.setNo(teacherView.getNo());
                teacher.setIdentity(teacherView.getIdentity());
                teacher.setStartWork(DateUtils.yyyyMMddToMillis(teacherView.getStartWork()));
                teacher.setCreateBy(user.getId());
                teacher.setCreateDate(System.currentTimeMillis());
                teacher.setSchoolId(user.getSchoolId());
                teacher.setDepartmentId("0");//新增人员放到0部门，属于未分配部门的范围（别名：其他）  默认值
                teacher.setHighTime(DateUtils.yyyyMMddToMillis(teacherView.getHignTime()));
                teacher.setZgbyxx(teacherView.getZgbyxx());
                teacher.setHighJob(teacherView.getHignJob());
                teacher.setPzxx(teacherView.getPzxx());
                teacher.setAddress(teacherView.getAdrress());
                teacher.setGgjsjb(teacherView.getGgjsjb());
                teacher.setHtkssj(DateUtils.yyyyMMddToMillis(teacherView.getHtkssj()));
                teacher.setHtjssj(DateUtils.yyyyMMddToMillis(teacherView.getHtjssj()));
                teacher.setCym(teacherView.getCym());
                teacher.setJtyb(teacherView.getJtyb());
                teacher.setSfzrjs(teacherView.getSfzrjs());
                teacher.setSalary(teacherView.getXj());
                teacher.setJg(teacherView.getJg());
                teacher.setZzmm(teacherView.getZzmm());
                teacher.setYsbysj(DateUtils.yyyyMMddToMillis(teacherView.getYsbysj()));
                teacher.setRjxk(teacherView.getRjxk());
                teacher.setSf(teacherView.getShenfen());
                teacher.setWysp(teacherView.getWysp());
                teacher.setZgxz(teacherView.getZgxz());
                teacher.setXwsl(teacherView.getXwsl());
                teacher.setRjxkjb(teacherView.getRjxkjb());
                teacher.setXq(teacherView.getXq());
                teacher.setGwflf(teacherView.getGwflf());
                teacher.setZgxl(teacherView.getZgxl());
                teacher.setYzy(teacherView.getYzy());
                teacher.setPzsj(DateUtils.yyyyMMddToMillis(teacherView.getPzsj()));
                teacher.setLwxsj(DateUtils.yyyyMMddToMillis(teacherView.getLwxsj()));
                teacher.setZzdh(teacherView.getZzdh());
                teacher.setGzgw(teacherView.getGzgw());
                teacher.setBgsdh(teacherView.getBgsdh());
                teacher.setSfhq(teacherView.getSfhq());
                teacher.setSfbzr(teacherView.getSfbzr());
                teacher.setWyyz(teacherView.getWyyz());
                teacher.setYxz(teacherView.getYxz());
                teacher.setZgxw(teacherView.getZgxw());
                teacher.setZyjsgwfl(teacherView.getZyjsgwfl());
                teacher.setSzjb(teacherView.getSzjb());
                teacher.setGzgwf(teacherView.getGzgwf());
                teacher.setId(PrimaryKey.get());
                teacher.setDelFlag(0);
                //                teacherService.insertTeacher(teacher);
                correctTeacherList.add(teacher);
            } catch (Exception e) {
                errorTeacherList.add(errorTeacher);
                e.printStackTrace();
                continue;
            }
        }
        teacherService.insertTeacherBatch(correctTeacherList);//批量插入正确验证的数据
        Long end = System.currentTimeMillis();
        Map res = new HashMap();
        res.put("msg", "导入完成，共" + correctTeacherList.size() + "条成功，" + errorTeacherList.size() + "条失败,耗时" + (end - begin) / 1000 + "秒");
        res.put("errorList", errorTeacherList);
        return new ResponseEntity(res, HttpStatus.OK);

    }

    //下载导入失败的teacher列表
    @RequestMapping(value = "/teacher/error/export", method = RequestMethod.POST)
    public void errorTeacher(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            String fileName = "错误信息列表.xlsx";
            String anno = "注释：红色字段为必填项\n" +
                    "          1.日期格式：yyyymmdd,例如：20160901\n";
            String msg = getParamVal(request, "msg");
            JsonArray jsonArray = new JsonParser().parse(msg).getAsJsonArray();
            List<IOTeacherView> exportFile = new ArrayList<IOTeacherView>();
            for (JsonElement jsonElement : jsonArray) {
                IOTeacherView importBundling = GsonUtil.fromJson(jsonElement.getAsJsonObject(), IOTeacherView.class);
                exportFile.add(importBundling);
            }
            new ExportExcel("人事信息", IOTeacherView.class, 2, anno, 1).setDataList(exportFile).write(response, fileName).dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //人事管理，人员编辑（新增）
    @RequestMapping(value = "/renyuan/edit")
    public String edit(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String _id = id;

        if (_id != null || _id != "") {
            Teacher teacher = teacherService.findTeacherById(_id);
            model.addAttribute("teacher", teacher);
        }
        List<Title> titleList = teacherService.selectTitleBySchoolId(getLoginUser().getSchoolId().toString());

        model.addAttribute("titleList", titleList);
        return "renShiManage/rsRenYuanEdit";
    }

    //人员 新增/修改 保存
    @ResponseBody
    @RequestMapping(value = "/renyuan/save", method = RequestMethod.POST)
    public void update(HttpServletRequest request) throws ParseException {
        String id = getParamVal(request, "id");//教职工id
        String name = getParamVal(request, "name");//姓名
        String gender = getParamVal(request, "gender");//性别
        String email = getParamVal(request, "email");//邮箱
        String mobile = getParamVal(request, "mobile");//手机号码
        String no = getParamVal(request, "no");//编号
        String identity = getParamVal(request, "identity");//身份证号码
        String beginWork = getParamVal(request, "beginWork").replace("-", "");//开始工作时间
        String title = getParamVal(request, "zhiwu");//职务
        String zgbysj = getParamVal(request, "zgbysj").replace("-", "");//最高毕业时间
        String zgzy = getParamVal(request, "zgzy");//最高专业
        String pzxx = getParamVal(request, "pzxx");//评职详细
        String jtzzxx = getParamVal(request, "jtzzxx");//家庭住址详细
        String ggjsjb = getParamVal(request, "ggjsjb");//骨干教师级别
        String htkssj = getParamVal(request, "htkssj").replace("-", "");//合同开始时间
        String cym = getParamVal(request, "cym");//曾用名
        String jtyb = getParamVal(request, "jtyb");//家庭邮编
        String sfzrjs = getParamVal(request, "sfzrjs");//是否专任教师
        String shenfen = getParamVal(request, "shenfen");//身份
        String wysp = getParamVal(request, "wysp");//外语水平
        String zgxz = getParamVal(request, "zgxz");//最高学制
        String xwsl = getParamVal(request, "xwsl");//学位数量
        String rjxkjb = getParamVal(request, "rjxkjb");//任教学科级别
        String xq = getParamVal(request, "xq");//校区
        String xz = getParamVal(request, "xinzhi");//薪资
        String gwflf = getParamVal(request, "gwflf");//岗位分类副
        String jg = getParamVal(request, "jg");//籍贯
        String zzmm = getParamVal(request, "zzmm");//政治面貌
        String ysbysj = getParamVal(request, "ysbysj").replace("-", "");//原始毕业时间
        String zgxl = getParamVal(request, "zgxl");//最高学历
        String zgbyxx = getParamVal(request, "zgbyxx");//最高毕业学校
        String yzy = getParamVal(request, "yzy");//原专业
        String pzsj = getParamVal(request, "pzsj");//评职时间
        String lwxsj = getParamVal(request, "lwxsj");//来我校时间
        String zzdh = getParamVal(request, "zzdh");//住宅电话
        String gzgw = getParamVal(request, "gzgw");//工资岗位
        String htjssj = getParamVal(request, "htjssj").replace("-", "");//合同结束时间
        String bgsdh = getParamVal(request, "bgsdh");//办公室电话
        String sfhq = getParamVal(request, "sfhq");//是否华侨
        String sfbzr = getParamVal(request, "sfbzr");//是否班主任
        String wyyz = getParamVal(request, "wyyz");//外语语种
        String yxz = getParamVal(request, "yxz");//原学制
        String zgxw = getParamVal(request, "zgxw");//最高学位
        String zyjsgwfl = getParamVal(request, "zyjsgwfl");//专业技术岗位分类
        String rjxk = getParamVal(request, "rjxk");//任教学科
        String szjb = getParamVal(request, "szjb");//实职级别
        String gzgwf = getParamVal(request, "gzgwf");//工资岗位副
        String headPic = getParamVal(request, "headUrl");
        String _id = id;
        User user = getLoginUser();
        Teacher teacher = new Teacher();
        teacher.setId(_id);
        teacher.setName(name);
        teacher.setGender(NumberConvertUtil.convertS2I(gender));
        teacher.setQuanPin(CnToPyUtils.getPingYin(name));
        teacher.setEmail(email);
        teacher.setMobile(mobile);
        teacher.setTitleId(title);
        teacher.setNo(no);
        teacher.setIdentity(identity);
        teacher.setStartWork(DateUtils.yyyyMMddToMillis(beginWork));
        teacher.setHighTime(DateUtils.yyyyMMddToMillis(zgbysj));
        teacher.setZgbyxx(zgbyxx);
        teacher.setHighJob(zgzy);
        teacher.setPzxx(pzxx);
        teacher.setAddress(jtzzxx);

        if (StringUtil.isNotEmpty(ggjsjb))
            teacher.setGgjsjb(ggjsjb.toString());

        teacher.setHtkssj(DateUtils.yyyyMMddToMillis(htkssj));
        teacher.setHtjssj(DateUtils.yyyyMMddToMillis(htjssj));
        teacher.setCym(cym);
        teacher.setJtyb(jtyb);
        teacher.setSfzrjs(sfzrjs);
        teacher.setSalary(xz);
        teacher.setJg(jg);
        teacher.setZzmm(zzmm);
        teacher.setYsbysj(DateUtils.yyyyMMddToMillis(ysbysj));
        teacher.setRjxk(rjxk);
        teacher.setSf(shenfen);
        teacher.setWysp(wysp);
        teacher.setZgxz(zgxz);
        teacher.setXwsl(xwsl);
        teacher.setRjxkjb(rjxkjb);
        teacher.setXq(xq);
        teacher.setGwflf(gwflf);
        teacher.setZgxl(zgxl);
        teacher.setYzy(yzy);
        teacher.setPzsj(DateUtils.yyyyMMddToMillis(pzsj));
        teacher.setLwxsj(DateUtils.yyyyMMddToMillis(lwxsj));
        teacher.setZzdh(zzdh);
        teacher.setGzgw(gzgw);
        teacher.setBgsdh(bgsdh);
        teacher.setSfhq(sfhq);
        teacher.setSfbzr(sfbzr);
        teacher.setWyyz(wyyz);
        teacher.setYxz(yxz);
        teacher.setZgxw(zgxw);
        teacher.setZyjsgwfl(zyjsgwfl);
        teacher.setSzjb(szjb);
        teacher.setGzgwf(gzgwf);
        teacher.setHeadUrl(headPic);

        if (_id == null || _id.equals("")) {
            teacher.setId(PrimaryKey.get());
            teacher.setCreateBy(user.getId());
            teacher.setCreateDate(System.currentTimeMillis());
            teacher.setSchoolId(user.getSchoolId());
            teacher.setDepartmentId("0");//新增人员放到0部门，属于未分配部门的范围（别名：其他）  默认值
            teacherService.insertTeacher(teacher);
        } else {
            teacher.setUpdateBy(user.getId());
            teacher.setUpdateDate(System.currentTimeMillis());
            teacherService.updateTeacher(teacher);
        }
    }

    //部门管理-部门新增/编辑 弹出窗
    @RequestMapping(value = "/rsbumen/add")
    public String rsBuMenAdd(HttpServletRequest request, Model model) {
        String departId = getParamVal(request, "departId");
        String _id = departId;
        User user = getLoginUser();
        List<Teacher> teacherList = teacherService.findAllTeacher(user.getSchoolId());
        List<Department> departList = departmentService.findAllDepartment(user.getSchoolId());

        if (_id == null || _id.equals("")) {
            //新增部门
        } else {
            //修改部门
            Department totalDepart = new Department();
            totalDepart = departmentService.findDepartmentById(_id);
            model.addAttribute("totalDepart", totalDepart);//当前部门
        }
        model.addAttribute("departList", departList);
        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        return "renShiManage/rsBuMenAddForm";
    }

    //部门管理-部门新增/编辑 保存
    @ResponseBody
    @RequestMapping(value = "/rsbumen/add/save")
    public void rsBuMenSave(HttpServletRequest request) {
        String departName = getParamVal(request, "departName");
        String departNo = getParamVal(request, "departNo");
        String departOrder = getParamVal(request, "departOrder");
        String departManager = getParamVal(request, "departManager");
        String departParent = getParamVal(request, "departParent");
        String departManagerId = getParamVal(request, "departManagerId");
        String delFlag = getParamVal(request, "delFlag");
        String currentDepartId = getParamVal(request, "currentDepartId");
        User user = getLoginUser();
        //创建部门
        Department depart = new Department();
        depart.setParentId(departParent);
        depart.setName(departName);
        depart.setMasterId(departManagerId);
        depart.setMasterName(departManager);
        depart.setSchoolId(user.getSchoolId());
        depart.setNo(departNo);
        depart.setDelFlag(0);

        String currentDepart = currentDepartId;
        if (currentDepart == null || currentDepart.equals("")) {//新增部门
            depart.setCreateDate(System.currentTimeMillis());
            depart.setCreateBy(user.getId());
        } else {
            //修改部门
            depart.setUpdateBy(user.getId());
            depart.setUpdateDate(System.currentTimeMillis());
            depart.setId(currentDepart);
        }
        departmentService.updateDepartment(depart);
    }

    //ajax请求，返回所有教师的列表
    @ResponseBody
    @RequestMapping(value = "/getAllTeacher")
    public String getAllTeacher(HttpServletRequest request) {
        String name = getParamVal(request, "name");//查询条件姓名
        String queryType = getParamVal(request, "type");

        User user = getLoginUser();
        List<Teacher> teacherList = teacherService.findTeacherLikeNameAndSchoolId(name, user.getSchoolId());//教师list
        List<Department> departmentList = teacherService.findDepartmentBySchool(user.getSchoolId());//部门list

        if (teacherList.size() == 0) {
            return null;
        }
        if (departmentList.size() == 0) {
            return null;
        }
        if (queryType.equals("1")) {
            //查询教师 格式：teacher1，teacher2，teacher3，teacher4
            return new Gson().toJson(teacherList);
        } else {
            //查询  格式：部门1[teacher1,teacher2],部门2[teacher3,teacher4]
            List<Map<String, List<Teacher>>> withDepart = new ArrayList<>();
            for (Department department : departmentList) {
                Map<String, List<Teacher>> resMap = new HashMap<>();
                List<Teacher> departMentTeacher = new ArrayList();
                for (Teacher teacher : teacherList) {
                    if (teacher.getDepartmentId().equals(department.getId())) {
                        departMentTeacher.add(teacher);
                        //teacherList.remove(teacher);//添加完remove，防止下次循环再次遍历
                    }
                }
                resMap.put(department.getName(), departMentTeacher);
                withDepart.add(resMap);
            }

            /*无部门的teacher*/
            Map<String, List<Teacher>> noDepartTeacherMap = new HashMap<>();
            List<Teacher> noDepartTeacher = new ArrayList();
            for (Teacher teacher : teacherList) {
                if (teacher.getDepartmentId() == null || teacher.getDepartmentId().equals("") || "0".equals(teacher.getDepartmentId())) {
                    noDepartTeacher.add(teacher);
                }
            }
            noDepartTeacherMap.put("其他", noDepartTeacher);

            withDepart.add(noDepartTeacherMap);
            return new Gson().toJson(withDepart);
        }
    }

    //部门管理-部门人员/批量添加 弹出页面
    @RequestMapping(value = "/teacher/many/add")
    public String rsBuMenTeacherAdd(HttpServletRequest request, Model model) {
        String departId = getParamVal(request, "departId");
        model.addAttribute("departId", departId);
        return "renShiManage/allTeacherPage";
    }

    //部门管理-部门人员(带部门)/批量添加 弹出页面
    @RequestMapping(value = "/teacher/manyDepart/add")
    public String rsBuMenTeacherDepartAdd(HttpServletRequest request, Model model) {
        String titleId = getParamVal(request, "titleId");
        model.addAttribute("titleId", titleId);
        return "renShiManage/allTeacherPageDepart";
    }

    //部门管理-部门人员/添加 弹出页面
    @RequestMapping(value = "/rsbumen/teacher/add")
    public String rsBuMenTeacherAlert(HttpServletRequest request, Model model) {
        String departId = getParamVal(request, "departId");
        String _id = departId;
        Department currentDepart = new Department();
        if (_id.equals("") || _id == null) {

        } else {
            currentDepart = departmentService.findDepartmentById(departId);
            model.addAttribute("currentDepart", currentDepart);
        }

        List<Teacher> teacherNoDepartList = teacherService.findAllTeacher(getLoginUser().getSchoolId());
        model.addAttribute("teacherNoDepartList", new Gson().toJson(teacherNoDepartList));
        return "renShiManage/rsBuMenUserAdd";
    }

    //部门管理-部门人员/添加 保存
    @ResponseBody
    @RequestMapping(value = "/bumen/teacher/save", method = RequestMethod.POST)
    public void rsBuMenTeacherSave(HttpServletRequest request) {
        String departId = getParamVal(request, "departmentId");
        String teacherId = getParamVal(request, "teacherId");

        String[] idArr = teacherId.split(",");
        for (int a = 0; a < idArr.length; a++) {
            Teacher teacher = new Teacher();
            teacher.setId(idArr[a]);
            teacher.setDepartmentId(departId);
            teacherService.updateTeacher(teacher);
        }

    }

    //人事管理-账号管理-生成账号
    @RequestMapping(value = "/account/add")
    @ResponseBody
    public void accountCreate(HttpServletRequest request) {
        String password = getParamVal(request, "password");
        User loginUser = getLoginUser();
        //查找未生成账号的职工
        List<Teacher> teacherList = teacherService.findNoAccountTeacher(loginUser.getSchoolId());
        List<String> refIds = new ArrayList<String>();
        //遍历未生成账号的人员并生成账号
        for (Teacher teacher : teacherList) {
            User user = new User();
            user.setUserType(1);//用户类型用户类型[0:root, 1:教师, 2:学生, 3:家长] 默认值
            user.setRefId(teacher.getId());//引用id
            user.setSchoolId(teacher.getSchoolId());//机构id
            user.setName(teacher.getName());//姓名
            user.setCreateBy(loginUser.getId());//创建人
            user.setCreateDate(System.currentTimeMillis());//创建时间
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));//默认密码6个0
            user.setPhotoUrl(FileUtils.DEFAULT_HEAD_PHOTO);
            String username = userService.findSameUserNameMax(teacher.getQuanPin(), loginUser); //return -1:没有同名用户; 0:仅有一个且没有结尾数字;  >0:结尾最大的数字
            user.setUsername(username);
            teacher.setAccount(username);
            userService.saveUser(user);
            teacherService.save(teacher);
            refIds.add(teacher.getId());
        }

        //1:将所有未生成账号的用户生成账号
        teacherService.createAccount(loginUser.getSchoolId());

        if (teacherList.size() > 0) {
            List<UserRole> userRoles = new ArrayList<UserRole>();
            List<User> users = userService.selectUserByCriteria(refIds, 1);
            for (User user : users) {
                UserRole userRole = new UserRole();
                userRole.setSchoolId(user.getSchoolId());
                userRole.setRoleId("7");
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            }
            userService.saveUserRoleBatch(userRoles);

        }
    }

    //人事管理-账号管理-重置密码
    @ResponseBody
    @RequestMapping(value = "/account/password/update")
    public void passwordReset(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String password = getParamVal(request, "password");
        String _id = id;
        User user = new User();
        user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));
        userService.updateUserByRefId(user, _id, getLoginUser().getSchoolId(), 1);
    }

}
