
package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.utils.FileUtils;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/1.
 * school[机构管理]
 */
@Controller
@RequestMapping(value = "/school")
public class SchoolController extends BasicController {

    @Autowired
    SchoolService schoolService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    AppService appService;
    @Autowired
    SchoolAppService schoolAppService;
    @Autowired
    ClassService classService;

    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum, pageSize);
        String name = getParamVal(request, "name");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("schoolId", null);
        if (!StringUtils.isEmpty(name)) {
            paramMap.put("name", "%" + name + "%");
        }
        List<Map<String, Object>> schoolList = schoolService.selectTeacherByParam(paramMap);

        //翻译school中的parentId字段
        List<School> schools = schoolService.selectAllList();
        for (Map<String, Object> school : schoolList) {
            for (int i = 0; i < schools.size(); i++) {
                if (school.get("parentId").equals(schools.get(i).getId())) {
                    school.put("parentId", schools.get(i).getName());
                }
            }
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(schoolList);

        model.addAttribute("schoolList", schoolList);
        model.addAttribute("pageInfo", pageInfo);
        return "school/index";
    }

    @RequiresPermissions("school:role:view")
    @RequestMapping(value = "/permissionMan")
    public String permissionMan(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String roleName = getParamVal(request, "roleName");
        PageHelper.startPage(pageNum, pageSize);
        try {
            roleName = java.net.URLDecoder.decode(roleName, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Role> roleList = roleService.findRoleListByName(roleName);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("roleList", roleList);
        return "school/permissionMan";
    }


    //分配应用角色页面
    @RequestMapping(value = "/app/distribution")
    public String distribution(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum, pageSize);
        String id = getParamVal(request, "id");
        String name_gkb = request.getParameter("name");
        String name = new String(name_gkb.getBytes("ISO8859-1"), "UTF-8");
        List<Role> appRole = roleService.findRoleByAppId(id);
        PageInfo<Role> pageInfo = new PageInfo<Role>(appRole);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("appRole", appRole);
        model.addAttribute("appId", id);
        model.addAttribute("appName", name);
        return "app/admin/appDistribution";
    }

    /*重置机构管理员密码*/
    @ResponseBody
    @RequestMapping(value = "/admin/update")
    public ResultEntity resetPassWord(HttpServletRequest request) {
        String schoolId = getParamVal(request, "id");
        School school = schoolService.selectSchoolById(schoolId);
        List<User> userList = userService.getTeacherByRefId(school.getmId());
        for (User user : userList) {
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding("000000"));
            user.setUpdateBy(getLoginUser().getId());
            user.setUpdateDate(System.currentTimeMillis());
            userService.saveUser(user);
        }
        return ResultEntity.newErrEntity();
    }

    /*机构管理编辑/新增弹出页面*/
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Model model) {

        String id = getParamVal(request, "id");
        if (!id.equals("")) {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("schoolId", id);
            Map<String, Object> school = schoolService.selectTeacherByParam(paramMap).get(0);//根据id查询得到的是长度为1的list结果集
            model.addAttribute("school", school);
        }

        model.addAttribute("schoolList", schoolService.selectAllList());
        return "school/addSchool";
    }

    /*机构管理编辑(新增)-保存*/
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String shortFlag = getParamVal(request, "shortFlag");
        String type = getParamVal(request, "type");
        String address = getParamVal(request, "address");
        String deployUrl = getParamVal(request, "deployUrl");
        String parentId = getParamVal(request, "parentId");
        String email = getParamVal(request, "email");
        String chooseAddress = getParamVal(request, "s_province") +","+ getParamVal(request, "s_city")+","+ getParamVal(request, "s_county");
        String logoUrl = getParamVal(request, "headUrl");
        String grade = getParamVal(request,"grade");
        String _id = id;

        ResultEntity resultEntity = null;
        int rstNum = 0;
        School school = new School();
        school.setName(name);
        school.setLogo(logoUrl);
        school.setShortFlag(shortFlag);
        school.setType(NumberConvertUtil.convertS2I(type));
        school.setAddress(address);//地址输入框，联系地址
        school.setXz(chooseAddress);//级联选择，所属地区
        school.setDeployUrl(deployUrl);
        school.setParentId(parentId);
        school.setEmail(email);

        if ("1".equals(type))
            grade=null;

        school.setGrade(NumberConvertUtil.convertS2I(grade));

        if (_id == null || _id.equals("")) {
            String primary = PrimaryKey.get();
            school.setId(primary);

            school.setCreateBy(getLoginUser().getId());
            school.setCreateDate(System.currentTimeMillis());
            school.setDelFlag(0);//默认值
            schoolService.saveSchoolBackId(school);

//            //删除单例相应数据
//            SchoolMsg.deleteMsg(school.getDeployUrl());

            //分配管理员，创建机构的时候分配管理员账号admin+标识为登录名，密码初始为6个0.
            String manId = "";
            Teacher teacher = new Teacher();
            String teacherPri = PrimaryKey.get();
            teacher.setId(teacherPri);
            teacher.setSchoolId(primary);
            teacher.setIsManage(1);//默认是管理员身份  默认值
            teacher.setName("管理员");
            teacher.setGender(-1);
            teacher.setDelFlag(0);
            teacher.setAccount("admin@" + shortFlag);//生成账号管理员账号：admin+标识
            //teacher.setRoleId(3);
            teacher.setCreateDate(System.currentTimeMillis());
            teacher.setCreateBy(getLoginUser().getId());
            teacher.setDepartmentId("0");//默认值 0为其他部门
            /*back*/
            int flag = teacherService.saveTeacherBackId(teacher);//

            if (flag > 0) {
                manId = teacherPri;
            }

            //添加sys_user表
            User user = new User();
            String prim = PrimaryKey.get();
            user.setId(prim);
            user.setSchoolId(primary);
            user.setUsername(teacher.getAccount());
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding("000000"));//默认值
            user.setCreateBy(getLoginUser().getId());
            user.setCreateDate(System.currentTimeMillis());
            user.setRefId(manId);
            user.setName("管理员");
            user.setUserType(1);//用户类型[0:root, 1:教师, 2:学生, 3:家长]  默认值
            user.setPhotoUrl(FileUtils.DEFAULT_HEAD_PHOTO);

            String userId = "";
            /*back*/
            String userFlag = userService.insertUserBackId(user);
            if (!(userFlag == null || userFlag == "")) {
                userId = prim;
            }

            // 添加ref_user_role表
            UserRole userRole = new UserRole();
            userRole.setSchoolId(primary);
            userRole.setRoleId("3");
            userRole.setUserId(userId);
            userService.saveUserRole(userRole);

            //更新机构表管理员id字段
            School schoolMan = new School();
            school.setId(primary);
            school.setmId(manId);
            schoolService.saveSchoolSettingInfo(school);


            //学校生成后，创建主校区
            SchoolType schoolType = new SchoolType();
            schoolType.setSort(1);
            schoolType.setSchoolId(primary);
            schoolType.setName("主校区");
            schoolType.setCreateBy(getLoginUser().getId());
            schoolType.setCreateDate(System.currentTimeMillis());
            schoolType.setDelFlag(0);
            schoolService.saveSchoolType(schoolType);

            //学校生成后，创建默认学段
            ClassSection elementary = new ClassSection();
            elementary.setName("小学");
            elementary.setDelFlag(0);
            elementary.setCreateBy(getLoginUser().getId());
            elementary.setCreateDate(System.currentTimeMillis());
            elementary.setSchoolId(primary);
            elementary.setSectionYear(6);
            elementary.setLimitAge(7);
            elementary.setShortName("小");
            classService.saveClassSection(elementary);

            ClassSection junior = new ClassSection();
            junior.setName("初中");
            junior.setDelFlag(0);
            junior.setCreateBy(getLoginUser().getId());
            junior.setCreateDate(System.currentTimeMillis());
            junior.setSchoolId(primary);
            junior.setSectionYear(3);
            junior.setLimitAge(13);
            junior.setShortName("初");
            classService.saveClassSection(junior);


            ClassSection senior = new ClassSection();
            senior.setName("高中");
            senior.setDelFlag(0);
            senior.setCreateBy(getLoginUser().getId());
            senior.setCreateDate(System.currentTimeMillis());
            senior.setSchoolId(primary);
            senior.setSectionYear(3);
            senior.setLimitAge(16);
            senior.setShortName("高");
            classService.saveClassSection(senior);

        } else {
            try {
                school.setLogo("");
                school.setId(id);
                school.setUpdateBy(getLoginUser().getId());
                school.setUpdateDate(System.currentTimeMillis());
                if(school.getDeployUrl()!= null && school.getDeployUrl() != ""){
                   rstNum = schoolService.saveAndClearSchoolcache(school,school.getDeployUrl());
                }else{
                    rstNum = schoolService.saveSchoolSettingInfo(school);
                }
//                rstNum = schoolService.updateSchool(school);
            } catch (Exception e) {
                logger.error("params error:{}, cause:{}", e.getMessage(), e.getCause());
                resultEntity = ResultEntity.newErrEntity(e.getMessage());
            }
        }
        if (rstNum > 0) {
            resultEntity = ResultEntity.newResultEntity();
        }
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity apiBusinessUpdate(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        try {
            schoolService.delete(id);
        } catch (Exception e) {
            logger.error("delete error :{}, cause:{}", e.getMessage(), e.getCause());
            ResultEntity.newErrEntity(e.getMessage());
        }
        return ResultEntity.newResultEntity();
    }

    /*
//    分配管理员弹出框
    @RequestMapping(value = "/manager/add", method = RequestMethod.GET)
    public String managerAdd(HttpServletRequest request,Model model) {
        String id = getParamVal(request, "id");
        model.addAttribute("schoolId",id);
        return "school/addManager";
    }

//    分配管理员确认
    @RequestMapping(value = "/manager/save", method = RequestMethod.POST)
    public String managerSave(HttpServletRequest request) {
        String id = getParamVal(request, "schoolId");
        String account=getParamVal(request,"manAccount");

        String schoolId = id;
        int manId=0;

//        判断添加的管理员是否存在，若存在，进行更改，不存在则新建管理员
        UserExample userExample=new UserExample();
        userExample.createCriteria().andSchoolIdEqualTo(schoolId).andUsernameEqualTo(account);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.size()>0){
//            存在这个管理员,只需要将机构表的manId更新为这个管理员ref_id即可
            manId=userList.get(0).getRefId();
        }else{
            //不存在这个管理员,新增数据
//                添加user_teacher表
            Teacher teacher=new Teacher();
            teacher.setId(null);
            teacher.setSchoolId(schoolId);
            teacher.setName(account);
            teacher.setCreateDate(System.currentTimeMillis());
            teacher.setAccount(account);//生成账号为：1已生成 0未生成
            teacher.setCreateBy(getLoginUser().getId());
            int flag=extendTeacherMapper.insertUserBackId(teacher);//

            if(flag>0){
                manId=teacher.getId();
            }
            //添加sys_user表
            User user=new User();
            user.setId(null);
            user.setSchoolId(schoolId);
            user.setUsername(account);
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding("000000"));
            user.setCreateBy(getLoginUser().getId());
            user.setCreateDate(System.currentTimeMillis());
            user.setRefId(manId);
            user.setUserType(3);//用户类型[0:root, 1:区域管理员, 2:校方管理员, 3:教师, 4:学生, 5:家长, 6:校友]
            String userId=0;
            int userFlag=extendUserMapper.insertBackId(user);
            if(userFlag>0){
                userId=user.getId();
            }
       // 添加ref_user_role表
            UserRole userRole=new UserRole();
            userRole.setSchoolId(schoolId);
            userRole.setRoleId(3);//新增管理员默认是教师用户类型
            userRole.setUserId(userId);
            userRoleMapper.insert(userRole);
        }
        //更新机构表管理员id字段
        School school=new School();
        school.setId(schoolId);
        school.setmId(manId);
        schoolMapper.updateByPrimaryKeySelective(school);

        return "";
    }
*/

    /*机构详情*/
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public
    @ResponseBody
    String schoolDetails(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String _id = id;
        School school = schoolService.selectSchoolById(_id);

        model.addAttribute("school", school);
        return new Gson().toJson(school);
    }

    @RequestMapping(value = "/permission/view")
    public String getPermissions(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        List<Menu> menuList = menuService.findAllList();
        List<String> currentRolePermission = getRolePermission(roleId);

        model.addAttribute("menuList", menuList);
        model.addAttribute("roleId", roleId);
        model.addAttribute("currentRolePermission", currentRolePermission);
        return "school/menuList";
    }

    /*权限管理--给角色分配菜单权限*/
    @ResponseBody
    @RequestMapping(value = "/permission/save")
    public void testPermissions(HttpServletRequest request) {
        String menuList = getParamVal(request, "menuList");
        String roleId = getParamVal(request, "roleId");
        //清空角色菜单关联
        roleService.deleteRoleMenuByRoleId(roleId);

        String[] menu = menuList.split(",");
        for (int i = 0; i < menu.length; i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu[i]);
            roleMenu.setRoleId(roleId);
            roleMenu.setSchoolId(getLoginUser().getSchoolId());

            roleService.saveRoleMenu(roleMenu);
        }
    }

    //授权应用-弹出页面
    @RequestMapping(value = "/app/authorization")
    public String getAppList(HttpServletRequest request, Model model) {
        String schoolId = getParamVal(request, "schoolId");
        List<SchoolApp> schoolAppList = schoolAppService.findAllListBySchoolId(schoolId);
        List<String> appIds = new ArrayList<String>();
        for (SchoolApp app : schoolAppList) {
            appIds.add(app.getAppId().toString());
        }
        List<App> appList = appService.findAppByCriteria();//查询所有可用app

        model.addAttribute("appList", appList);
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("appIds", appIds);
        return "school/appList";
    }

    //授权应用-
    @ResponseBody
    @RequestMapping(value = "/app/authorization/save")
    public void appAuthorization(HttpServletRequest request) {
        String schoolId = getParamVal(request, "schoolId");
        String id = getParamVal(request, "apps");

        SchoolApp schoolAppDelete = new SchoolApp();
        schoolAppDelete.setSchoolId(schoolId);
        schoolAppService.deleteSchoolApp(schoolAppDelete);

        if(StringUtils.isEmpty(id)){
            return;
        }

        List<SchoolApp> schoolAppList = schoolAppService.findAllListBySchoolId(schoolId);
        List<String> appIds = new ArrayList<String>();
        for (SchoolApp app : schoolAppList) {
            appIds.add(app.getAppId().toString());
        }

        String[] idArr = id.split(",");
        for (int i = 0; i < idArr.length; i++) {
            if (!appIds.contains(idArr[i])) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppId(idArr[i]);
                schoolApp.setSchoolId(schoolId);
                schoolAppService.insertSchoolApp(schoolApp);
            }
        }
    }

    //   权限管理--删除角色
    @ResponseBody
    @RequestMapping(value = "/role/delete")
    public String roleDelete(HttpServletRequest request) {
        String roleId = getParamVal(request, "roleId");
        Role role = new Role();
        role.setId(roleId);
        role.setDelFlag(1);
        role.setUpdateBy(getLoginUser().getId());
        role.setUpdateDate(System.currentTimeMillis());

        roleService.updateRole(role);//更改角色为不可用
        roleService.deleteRoleMenuByRoleId(roleId);//删除角色菜单关联表
        return "";
    }

    //权限管理-查看角色信息
    @ResponseBody
    @RequestMapping(value = "/role/view")
    public String getRoleInfo(HttpServletRequest request) {
        String roleId = getParamVal(request, "roleId");
        Role role = roleService.findRoleById(roleId);
        List<String> currentRolePermission = getRolePermission(roleId);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("role", role);
        resultMap.put("permissions", currentRolePermission);
        return new Gson().toJson(resultMap);
    }

    private List<String> getRolePermission(String roleId) {
        List<MenuView> menuViews = userService.selectMenusByRoleId(roleId);
        List<String> currentRolePermission = new ArrayList<String>();
        for (MenuView menuView : menuViews) {
            String permission = menuView.getPermission();
            if (!StringUtils.isEmpty(permission)) {
                currentRolePermission.add(permission);
            }
        }
        return currentRolePermission;
    }

    //    权限管理-编辑角色弹出页面
    @RequestMapping(value = "/role/edit")
    public String roleEdit(HttpServletRequest request, Model model) {

        String id = getParamVal(request, "id");
        String _id = id;
        Role role = roleService.findRoleById(_id);

        model.addAttribute("role", role);
        return "school/editRole";
    }

    //权限管理-编辑(新增)角色保存
    @ResponseBody
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public ResultEntity roleSave(HttpServletRequest request) {

        String id = getParamVal(request, "id");
        String name = getParamVal(request, "name");
        String remarks = getParamVal(request, "remarks");
        String identify = getParamVal(request, "identify");

        String _id = id;

        User user = getLoginUser();
        Role role = new Role();
        role.setId(_id);
        role.setName(name);
        role.setRemarks(remarks);
        role.setRoleIdentify(identify);

        if (_id == "" || _id == null) {
            role.setId(null);
            role.setCreateBy(user.getId());
            role.setCreateDate(System.currentTimeMillis());
            roleService.insertRole(role);
        } else {
            role.setUpdateDate(System.currentTimeMillis());
            role.setUpdateBy(user.getId());
            roleService.updateRole(role);
        }
        return ResultEntity.newResultEntity();
    }

    /**
     * 管理日志
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/log")
    public String logShow(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);

        PageInfo<LogWithBLOBs> logList = schoolService.selectLog(pageNum, pageSize);
        model.addAttribute("pageInfo", logList);
        return "school/showLog";
    }

    /**
     * 日志详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/log/detail")
    public String logDetail(HttpServletRequest request, Model model) {
        String logId = getParamVal(request, "id");
        String type = getParamVal(request, "type");

        LogWithBLOBs logWithBLOBs = schoolService.selectLogById(logId);

        String detail = "";
        if (type.equals("params")) {
            detail = logWithBLOBs.getParams();
        }
        if (type.equals("exception")) {
            detail = logWithBLOBs.getException();
        }
        model.addAttribute("log", detail);
        return "school/logDetail";
    }

    @RequiresPermissions("school:index:view")
    @RequestMapping(value = "/config")
    public String sysconfig(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);

        PageInfo<Config> configList = schoolService.selectConfig(pageNum, pageSize);
        model.addAttribute("pageInfo", configList);
        return "school/config";
    }

    @RequestMapping(value = "/config/delete")
    public ResultEntity configDelete(HttpServletRequest request, Model model) {
        String _id = getParamVal(request, "id");
        String id = _id;

        Config config = schoolService.selectConfigById(id);
        config.setDelFlag(1);
        schoolService.saveConfig(config);
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/config/add", method = RequestMethod.GET)
    public String configAdd(HttpServletRequest request, Model model) {
        String _id = getParamVal(request, "id");
        String id = _id;

        Config config = schoolService.selectConfigById(id);
        model.addAttribute("config", config);

        return "school/addConfig";
    }

    @RequestMapping(value = "/config/save", method = RequestMethod.POST)
    public String configAdd(Config config) {
        schoolService.saveConfig(config);
        return "school/config";
    }
}
