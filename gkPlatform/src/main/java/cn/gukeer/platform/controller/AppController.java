package cn.gukeer.platform.controller;

import cn.gukeer.common.config.Global;
import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.VFSUtil;
import cn.gukeer.common.utils.syncdata.LdapUtils;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by conn on 2016/8/8.
 */
@Controller
@RequestMapping(value = "/app")
public class AppController extends BasicController {
    private final String VFS_ROOT_PATH = VFSUtil.getVFSRootPath();
    private final String UPLOAD_PATH = VFSUtil.getVFSRootPath() + "/app/detail/";

    public static Properties prop = LdapUtils.getProperties("/syncData.properties");
    public static final String PASSWORD = prop.getProperty("password");

    @Autowired
    AppService appService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    SchoolAppService schoolAppService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    MyAppService myAppService;
    
    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MonitorService monitorService;
    
    @Autowired
    MenuService menuService;
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("appEntity")
    public void initBinderAppEntity(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("appEntity.");
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
        try {
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);

            PageInfo<App> pageInfo = appService.findAllList(pageNum, pageSize);
            List<App> appList = pageInfo.getList();

            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("AppController.index()---error", e);
        }
        return "app/admin/index";
    }
    
    @RequestMapping(value = "/selectbyname")
    public String selectByName(HttpServletRequest request, Model model) {
        try {
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);
            PageHelper.startPage(pageNum,pageSize);
            String name=getParamVal(request,"name");
            List<App> appList=appService.findByName(name);
            model.addAttribute("appList", appList);
        } catch (Exception e) {
            logger.error("AppController.index()---error", e);
        }
        return "app/admin/index_select";
    }
    
   /* @RequestMapping(value = "/selectByNameApp")
    public String selectByNameapp(HttpServletRequest request, Model model) {
        try {
            int pageNum = getPageNum(request);
            int pageSize = getPageSize(request);
            PageHelper.startPage(pageNum,pageSize);
            String name=getParamVal(request,"name");
            List<App> appList=a_appextensionmapper.findByName(name);
            model.addAttribute("appList", appList);
        } catch (Exception e) {
            logger.error("AppController.index()---error", e);
        }
        return "app/index";
    }*/

    /**
     * 应用商店推介页面
     *
     * @param request
     */
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request) {
        return "app/admin/addApp";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/appstore/index")
    public String appStoreIndex(HttpServletRequest request, Model model) {
//        int pageNum = getPageNum(request);
//        int pageSize = getPageSize(request);
        //获取当前登录对象的学校id
        String schoolId=getLoginUser().getSchoolId();

        String name = getParamVal(request,"name");
        String category = getParamVal(request,"category");
        String targetUser = getParamVal(request,"targetUser");
        String area = getParamVal(request, "area");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //PageHelper.startPage(pageNum, pageSize);
        List<App> appList = appService.findAppByNameAndCategoryAndTargetUser(name, category, targetUser, area, schoolId);
        //PageInfo<App> pageInfo = new PageInfo<App>(appList);

        int size = appList.size();
    /*    for(int i=0;i<size;i++){
            if(!StringUtil.isEmpty(appList.get(i).getTargetUser())){
                String[] targetUsers = appList.get(i).getTargetUser().split(",");
                appList.get(i).setTargetUsers(targetUsers);
            }
        }*/
        PageHelper.startPage(0, 8);
        List<App> appAllList = appService.findAppByCriteria();//系统全部应用

        model.addAttribute("name",name);
        model.addAttribute("category",category);
        model.addAttribute("targetUser",targetUser);
        model.addAttribute("area",area);
        model.addAttribute("appList", appList);
        //model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("size", size);
        model.addAttribute("appAllList", appAllList);
        return "app/index";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage")
    public String manage(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageInfo<School> pageInfo = schoolService.selectAllList(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "app/admin/manage";
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage/add")
    public String manageEdit(HttpServletRequest request, Model model) {
        String schoolId = request.getParameter("id");
        if (!StringUtil.isEmpty(schoolId)) {
            List<App> appList = appService.findAppBySchool(schoolId);
            model.addAttribute("appList", appList);
            List<App> allList = appService.findNotHaveList(schoolId);
            model.addAttribute("allList", allList);
            model.addAttribute("sid", schoolId);
        }
        return "app/admin/manage/add";
    }

    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add/save")
    public SchoolApp addAppToSchool(HttpServletRequest request) {
        try {
            String appId = request.getParameter("id");
            String schoolId = request.getParameter("sid");
            if (!StringUtil.isEmpty(appId) && !StringUtil.isEmpty(schoolId) && null != getLoginUser() && (getLoginUser().getSchoolId().equals(schoolId) || getLoginUser().getUserType() == 0)) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppId(appId);
                schoolApp.setSchoolId(schoolId);
                schoolAppService.insertSchoolApp(schoolApp);
                return schoolApp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 应用商店推介页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage/delete")
    public String manageDel(HttpServletRequest request, Model model) {
        String schoolId = request.getParameter("id");
        if (!StringUtil.isEmpty(schoolId)) {
            List<App> appList = appService.findAppBySchool(schoolId);
            model.addAttribute("appList", appList);
            List<App> allList = appService.findNotHaveList(schoolId);
            model.addAttribute("allList", allList);
            model.addAttribute("sid", schoolId);
        }
        return "app/admin/manage/delete";
    }

    /**
     * 删除应用
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/save")
    public SchoolApp delAppFromSchool(HttpServletRequest request) {
        try {
            String appId = request.getParameter("id");
            String schoolId = request.getParameter("sid");
            if (!StringUtil.isEmpty(appId) && !StringUtil.isEmpty(schoolId) && null != getLoginUser() && (getLoginUser().getSchoolId().equals(schoolId) || getLoginUser().getUserType() == 0)) {
                SchoolApp schoolApp = new SchoolApp();
                schoolApp.setAppId(appId);
                schoolApp.setSchoolId(schoolId);
                schoolAppService.deleteSchoolApp(schoolApp);
                return schoolApp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approle/add")
    public String addAppRole(HttpServletRequest request, Model model) {
        String appId = request.getParameter("id");
        if (!StringUtil.isEmpty(appId)) {
            List<Role> roleList = roleService.findRoleByAppId(appId);
            List<Role> allRoleList = roleService.findNotHaveList(appId);
            model.addAttribute("allRoleList", allRoleList);
            model.addAttribute("appId", appId);
            model.addAttribute("roleList", roleList);
        }
        return "app/admin/addRole";
    }

    /**
     * 添加角色至应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/approle/add/save")
    public AppRole addRoleToApp(HttpServletRequest request) {
        try {
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != getLoginUser() && getLoginUser().getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRole.setSchoolId("0");
                appRoleService.insertAppRole(appRole);
                return appRole;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 添加角色并将角色加到应用
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addroleaddtoapp")
    public ResultEntity addRoleaddToApp(HttpServletRequest request) {
            String appId = getParamVal(request, "appid");
            String name = getParamVal(request, "name");
            String remarks = getParamVal(request, "remarks");
            String identify = request.getParameter("identify");
            Role role = new Role();
            role.setRoleIdentify(identify);
            role.setName(name);
            role.setRemarks(remarks);
            role.setCreateBy(getLoginUser().getId());
            role.setCreateDate(System.currentTimeMillis());
            role.setDelFlag(0);
            String pri = PrimaryKey.get();
            role.setId(pri);
            /*back*/
            roleService.insertRoleBackId(role);
            AppRole appRole = new AppRole();
            appRole.setAppId(appId);
            appRole.setRoleId(pri);
            appRole.setSchoolId("0");
            appRoleService.insertAppRole(appRole);
            return ResultEntity.newResultEntity();
    }
    
    /**
     * 添加应用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approle/delete")
    public String delAppRole(HttpServletRequest request, Model model) {
        String appId = request.getParameter("id");
        if (!StringUtil.isEmpty(appId)) {
            List<Role> roleList = roleService.findRoleByAppId(appId);
            model.addAttribute("roleList", roleList);
            List<Role> allRoleList = roleService.findNotHaveList(appId);
            model.addAttribute("allRoleList", allRoleList);
            model.addAttribute("appId", appId);
        }
        return "app/admin/delRole";
    }

    /**
     * 讲角色从应用中删除
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delrolefromapp")
    public AppRole delRoleFromApp(HttpServletRequest request) {
        try {
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != getLoginUser() && getLoginUser().getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRole.setSchoolId("0");
                appRoleService.deleteAppRole(appRole);
                return appRole;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //=================================应用管理==============================================//


    
    
   /* *//**
     * 新增
     *//*
    @RequestMapping(value = "/addApp")
    public String addApp(HttpServletRequest request, Model model) {
        try {
            String id = getParamVal(request, "id");
            String icon=getParamVal(request, "icon");
            String pic=getParamVal(request, "pic");
            String name=getParamVal(request, "name");
            String appStatus=getParamVal(request, "appStatus");
            String webUrl=getParamVal(request, "webUrl");
            String remarks=getParamVal(request, "remarks");
            App app = new App();
            app.setName(name);
            app.setIconUrl(icon);
            app.setPicUrl(pic);
            app.setAppStatus( Integer.parseInt(appStatus));
            app.setWebUrl(webUrl);
            app.setRemarks(remarks);
            if (!StringUtil.isEmpty(app.getPicUrl())) {
                String[] picUrls = app.getPicUrl().split(",");
                int num = picUrls.length;
                String[] result = new String[num];
                for (int i = num - 1; i >= 0; i--) {
                    result[i] = picUrls[num - 1 - i];
                }
                app.setPicUrls(result);
            }
            appService.updateApp(app);
            model.addAttribute("app", app);

        } catch (Exception e) {
            logger.error("AppController.edit()---error", e);
        }
        return "app/admin/add";
    }*/
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit")
    public String edit_new(HttpServletRequest request, Model model) {
        try {
            String id = getParamVal(request, "id");
            String _id = id;

            App app = appService.findAppById(_id);
            if (!GukeerStringUtil.isNullOrEmpty(app)) {
                String[] picUrls = app.getPicUrl().split(",");
                int num = picUrls.length;
                String[] result = new String[num];
                for (int i = num - 1; i >= 0; i--) {
                    result[i] = picUrls[num - 1 - i];
                }
                model.addAttribute("picUrls", result);
                //app.setPicUrls(result);
            }
            if(!GukeerStringUtil.isNullOrEmpty(app)){
                String[] targetUsers = app.getTargetUser().split(",");
                model.addAttribute("targetUsers", targetUsers);
               // app.setTargetUsers(targetUsers);
            }
            model.addAttribute("app", app);

        } catch (Exception e) {
            logger.error("AppController.edit()---error", e);
        }
        return "app/admin/edit";
    }
    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(HttpServletRequest request) {
            String id = getParamVal(request, "id");
            String _id = id;
            App app = appService.findAppById(_id);
            app.setDelFlag(Global.YES);
            appService.updateApp(app);
            return "ok";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public void save(@ModelAttribute("appEntity") App appEntity,HttpServletRequest request) {
        try {
        	String icon = getParamVal(request, "iconNew");
            String pic = getParamVal(request, "picNew");
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getPrincipal();
            if(!icon.equals("")){
                appEntity.setIconUrl(icon);
            }
            //appEntity.setPicUrl(pic);
            appEntity.setCreateBy(user.getId());
            appEntity.setCreateDate(System.currentTimeMillis());


            if (StringUtil.isEmpty(appEntity.getId())) {
                appEntity.setId(PrimaryKey.get());
                appService.insertApp(appEntity);
            }
            else appService.updateApp(appEntity);
        } catch (Exception e) {
            logger.error("AppController.update()---error", e);
        }

    }

    /**
     * 应用管理弹出编辑角色框
     */
  @RequestMapping(value = "/role/edit")
  public String roleEdit(HttpServletRequest request, Model model) {

      String appId = getParamVal(request, "appId");
      model.addAttribute("appid", appId);
      return "app/admin/editRole";
  }
  /**
   * 应用管理弹出新增角色框
   */
  @RequestMapping(value = "/role/add")
  public String roleadd(HttpServletRequest request, Model model) {
       String appid = request.getParameter("appid");
       model.addAttribute("appid", appid);
      return "app/admin/editRole";
  }
    /**
     * 文件上传
     *
     * @param file
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploads")
    public void uploads(@Param("file") MultipartFile file, HttpServletResponse response, @Param("imgName") String imgName) throws Exception {
        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            String suffix = imgName.substring(imgName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String absPath = UPLOAD_PATH + fileName;
            fis = file.getInputStream();
            File f = new File(absPath);
            fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = fis.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("url", "/app/showpicture?picPath=" + absPath);
            response.getWriter().print(jsonObject);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            logger.error("上传文件失败", e);
        } finally {
            fos.close();
            fis.close();
        }
    }

    /**
     * 读取图片文件
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/showpicture")
    public void showPicture(HttpServletResponse response, String picPath) throws Exception {
           File file = new File(VFS_ROOT_PATH + picPath);
          // File file = new File("C:\\platform\\vfsroot\\201609\\1474608271852.jpg");
        if (!file.exists()) {
            logger.error("找不到文件[" + VFS_ROOT_PATH + picPath + "]");
            return;
        }
        response.setContentType("multipart/form-data");
        InputStream reader = null;
        /*try {*/
            reader = VFSUtil.getInputStream(file, true);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = reader.read(buf)) != -1) {
            }
           // response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(buf);
            stream.flush();
            stream.close();
      /*  } catch (Exception ex) {
            logger.error("显示图片时发生错误:" + ex.getMessage(), ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    logger.error("关闭文件流出错", ex);
                }
            }
        }*/
    }
    /**
     * 我的应用页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/myapp")
    public String myApp(HttpServletRequest request, Model model){
        String userId=getLoginUser().getId();
        int userType=getLoginUser().getUserType();
        String schoolId=getLoginUser().getSchoolId();

        User user=new User();
        user.setId(userId);
        user.setUserType(userType);
        user.setSchoolId(schoolId);
        List<App> myAppList=appService.selectAppByUserIdAndUserType(user);
        List<App> defaultAppList=appService.selectAppByIsDefault();

        List<App> appList = new ArrayList<App>();

        if(defaultAppList.size()>0){
            for(App app:defaultAppList){
                appList.add(app);
            }
        }
        if(myAppList.size()>0){
            for(App app:myAppList){
                appList.add(app);
            }
        }
        int size=appList.size();
        model.addAttribute("appList",appList);
        model.addAttribute("size",size);

        return  "app/myApp";
    }

    /**
     * 我的应用页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showalertapps")
    public String showAlertApps(HttpServletRequest request, Model model){
        String userId=getLoginUser().getId();
        int userType=getLoginUser().getUserType();
        String schoolId=getLoginUser().getSchoolId();

        User user=new User();
        user.setId(userId);
        user.setUserType(userType);
        user.setSchoolId(schoolId);
        List<App> otherAppList=appService.findOtherSchoolAppList(user);
        int size=otherAppList.size();

        model.addAttribute("otherAppList",otherAppList);
        model.addAttribute("size",size);
        return  "app/alertApps";
    }
    /**
     * 添加我的应用
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/savemyapp")
    public int saveMyApp(HttpServletRequest request){
        try {
            String appIds=request.getParameter("ids");
            String[] split = appIds.split(",");
            for (int i = 0; i < split.length; i++) {
                String appId=split[i];
                String userId=getLoginUser().getId();
                int userType=getLoginUser().getUserType();
                if(null!=getLoginUser()) {
                    MyApp myApp=new MyApp();
                    myApp.setUserId(userId);
                    myApp.setAppId(appId);
                    myApp.setUserType(userType);
                    myAppService.insertMyApp(myApp);

            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 删除我的应用
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delmyapp")
    public MyApp delMyApp(HttpServletRequest request) {
        try {
            String appId = request.getParameter("appId");
            String userId=getLoginUser().getId();
            int userType=getLoginUser().getUserType();
            if (!StringUtil.isEmpty(appId) && null != getLoginUser() ) {
                MyApp myApp=new MyApp();
                myApp.setUserId(userId);
                myApp.setUserType(userType);
                myApp.setAppId(appId);
                myAppService.deleteMyApp(myApp);
                return myApp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 我的应用详情
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/showappdetails")
    public String showAppDetails(HttpServletRequest request, Model model) {
        String userId = getLoginUser().getId();
        int userType = getLoginUser().getUserType();
        String schoolId = getLoginUser().getSchoolId();

        String app_id = getParamVal(request,"id");
        String _id = app_id;
        App app = appService.findAppById(_id);
        if (!StringUtil.isEmpty(app.getPicUrl())) {
            String[] picUrls = app.getPicUrl().split(",");
            int num = picUrls.length;
            String[] result = new String[num];
            for (int i = num - 1; i >= 0; i--) {
                result[i] = picUrls[num - 1 - i];
            }
            //app.setPicUrls(result);
//            int size=app.getPicUrls().length;
            model.addAttribute("picUrls",result);
            model.addAttribute("size", result.length);
        }
        if(!StringUtil.isEmpty(app.getTargetUser())){
            String[] targetUsers=app.getTargetUser().split(",");
//            app.setTargetUsers(targetUsers);
           model.addAttribute("targetUsers",targetUsers);
        }
        List<MyApp> myAppList = myAppService.findMyApp(userId, userType, _id);
        List<SchoolApp> schoolAppList = schoolService.findSchoolAppById(schoolId, _id);
        model.addAttribute("app", app);
        model.addAttribute("myAppList", myAppList);
        model.addAttribute("schoolAppList", schoolAppList);
        return "app/appDetails";
    }
    /**
     * 删除权限以及权限和应用的关系
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del_role_and_app")
    public ResultEntity del_role_and_app(HttpServletRequest request) {
        try {
            String roleId = request.getParameter("roleId");
            String appId = request.getParameter("appId");
            if (!StringUtil.isEmpty(roleId) && !StringUtil.isEmpty(appId) && null != getLoginUser() && getLoginUser().getUserType() == 0) {
                AppRole appRole = new AppRole();
                appRole.setAppId(appId);
                appRole.setRoleId(roleId);
                appRole.setSchoolId("0");
                appRoleService.deleteAppRole(appRole);
            }
            Role role = roleService.findRoleById(roleId);
            role.setDelFlag(Global.YES);
            roleService.updateRole(role);
            return null;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newResultEntity();
    }
    
    /**
     * 弹出授权应用树
     * @param request
     * @return
     */
    @RequestMapping(value = "/getmenutree")
    public  String getAppList(HttpServletRequest request,Model model){
        int belong=NumberConvertUtil.convertS2I(getParamVal(request,"appId"));
        String roleId=getParamVal(request,"roleId");
        List<Menu> menu_list=menuService.selectMenuByBelong(belong);
        model.addAttribute("menuList",menu_list);
        model.addAttribute("appId",belong);
        model.addAttribute("roleId",roleId);
        return  "app/admin/appRoleTree";
    }
    
    /**
     * 给角色分配菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ref_menu_role")
    public  void testPermissions(HttpServletRequest request){
        String menuList=getParamVal(request,"menuList");
        String roleId=getParamVal(request,"roleId");
        String[] menu=menuList.split(",");
        roleMenuService.deleteByRoleId(roleId);
        for (int i = 0; i <menu.length ; i++) {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenuId(menu[i]);
            roleMenu.setRoleId(roleId);
            roleMenu.setSchoolId("0");
            roleMenuService.insert(roleMenu);
        }
    }
    @ResponseBody
    @RequestMapping( value = "/save/fromopen", method = RequestMethod.POST)
    public ResultEntity appSaveFromOpen( HttpServletRequest request){
        try {
            Map params = transToMAP(request.getParameterMap());
            String paramString = params.get("security").toString();
            String clientId = params.get("clientId").toString();
            String time = params.get("time").toString();
            String random = params.get("random").toString();

            List<String> list = new ArrayList<>();
            List<Monitor> monitorList = monitorService.selectMonitor();
            for ( Monitor monitor:monitorList){
                StringBuffer buffer = new StringBuffer();
                buffer.append( monitor.getClientId()).append(time).append(random).append(PASSWORD);
                String token = MD5Utils.md5( buffer.toString());
                list.add( token);
            }
            if ( !list.contains(paramString)){
                return ResultEntity.newErrEntity("假数据");
            }
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(params.get("app").toString());
            App app = new App();
            app.setId( jsonObject.getString("id"));
            app.setName( jsonObject.getString("name"));
            app.setIconUrl( jsonObject.getString("logo"));
            app.setWebUrl( jsonObject.getString("appUrl"));
            app.setPicUrl( jsonObject.getString("appScreenshot"));
//            app.setAppStatus( 1);//已上线
            app.setIsDefault( Integer.valueOf(jsonObject.getString("isFree")));
            app.setCreateDate( Long.valueOf(jsonObject.getString("createDate")));
            app.setRemarks( jsonObject.getString("appAbstruct"));
            app.setDelFlag( 0);//显示
            app.setSfczxmz( 2);//开放平台推送
            String[] targetUsers = jsonObject.getString("targetUser").split(",");
            String targetUser = null;
            if ( targetUsers.length>0){
                for ( int i = 0; i < targetUsers.length; i++) {
                    if (targetUsers[i].equals("教师")){
                        if ( i==0){
                            targetUser = "1";
                        } else {
                            targetUser = targetUser + "," + "1";
                        }
                    } else if ( targetUsers[i].equals("学生")) {
                        if ( i==0){
                            targetUser = "2";
                        } else {
                            targetUser = targetUser + "," + "2";
                        }
                    } else if ( targetUsers[i].equals("家长")) {
                        if ( i==0){
                            targetUser = "3";
                        } else {
                            targetUser = targetUser + "," + "3";
                        }
                    }
                }
            }
            app.setTargetUser( targetUser);
            if (jsonObject.getString("category").equals("0")) {
                app.setCategory( "1");
            } else if ( jsonObject.getString("category").equals("1")) {
                app.setCategory( "2");
            }
            app.setDevelopers( params.get("companName").toString());
            app.setCreateBy( params.get("developer").toString());
            int count = appService.saveApp(app);
            if (count == 1){
                return ResultEntity.newResultEntity( "操作成功");
            } else {
                return ResultEntity.newErrEntity();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity( "");
        }
    }

    /**
     * 取消退送，应用下线
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/app/cancle/fromopen", method = RequestMethod.POST)
    public ResultEntity appCancleFromOpen( HttpServletRequest request){
        try {
            Map params = transToMAP(request.getParameterMap());
            String paramString = params.get("security").toString();
            String clientId = params.get("clientId").toString();
            String time = params.get("time").toString();
            String random = params.get("random").toString();

            List<String> list = new ArrayList<>();
            List<Monitor> monitorList = monitorService.selectMonitor();
            for ( Monitor monitor:monitorList){
                StringBuffer buffer = new StringBuffer();
                buffer.append( monitor.getClientId()).append(time).append(random).append(PASSWORD);
                String token = MD5Utils.md5( buffer.toString());
                list.add( token);
            }
            if ( !list.contains(paramString)){
                return ResultEntity.newErrEntity("假数据");
            }

            String id = params.get("appId").toString();
            App app = new App();
            app.setId( id);//clientId
            app.setDelFlag( 1);//隐藏相当于下线
            int count = appService.saveApp( app);
            if (count == 1){
                return ResultEntity.newResultEntity( "操作成功");
            } else {
                return ResultEntity.newErrEntity();
            }
        } catch ( Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity( );
        }
    }

}
