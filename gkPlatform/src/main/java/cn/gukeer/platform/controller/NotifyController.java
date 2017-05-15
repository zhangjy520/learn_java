package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.modelView.NotifyView;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by conn on 2016/8/1.
 * notify
 */
@Controller
@RequestMapping(value = "/notify")
public class NotifyController extends BasicController {

    @Autowired
    UserService userService;
    @Autowired
    NotifyService notifyService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    AppService appService;
    @Autowired
    RoleService roleService;

    /*    @RequiresPermissions("notify:notify:view")*/
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) throws ParseException {
        String columnId = getParamVal(request, "lanmu");
        String title = getParamVal(request, "title");
        String beginDate = getParamVal(request, "beginDate").replace("-", "");
        String endDate = getParamVal(request, "endDate").replace("-", "");
        String refId = getLoginUser().getRefId();
        Teacher teacher = teacherService.findTeacherById(refId);
        try {
            title = java.net.URLDecoder.decode(title, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("userId", refId);
        paramMap.put("beginDate", DateUtils.yyyyMMddToMillis(beginDate));
        paramMap.put("endDate", DateUtils.yyyyMMddToMillis(endDate));
        paramMap.put("title", "%" + title + "%");
        paramMap.put("columId", columnId);
        paramMap.put("schoolId", getLoginUser().getSchoolId());
        paramMap.put("isPublish", System.currentTimeMillis());//判断公告是否已经发布的字段。比对发布时间和当前日期

        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        PageHelper.startPage(pageNum, pageSize);

        List<Map<Object, Object>> resultList = notifyService.findNotifyView(paramMap);
        PageInfo<Map<Object, Object>> pageInfo = new PageInfo<Map<Object, Object>>(resultList);

        List<NotifyColumn> notifyColumnList = notifyService.findAllColumn(getLoginUser().getSchoolId());
        //保留前台查询传入的参数
        model.addAttribute("columId", columnId);
        model.addAttribute("title", title);
        model.addAttribute("beginDate", getParamVal(request, "beginDate"));
        model.addAttribute("endDate", getParamVal(request, "endDate"));
        model.addAttribute("notifyColumnList", notifyColumnList);
        model.addAttribute("teacher", teacher);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("resultList", resultList);

        if (StringUtil.isNotEmpty(getParamVal(request,"appId")))
        request.getSession().setAttribute("notifyId",getParamVal(request,"appId"));

        return "notify/index";
    }

    /**
     * 公告详情页面
     *
     * @return
     */
    @RequestMapping(value = "/details/{id}")
    public String details(HttpServletRequest request, Model model, @PathVariable String id) {
        User user = getLoginUser();
        Integer publicCount = notifyService.selectRemindNotifyCount(user.getRefId());
        HttpSession session = request.getSession();
        session.setAttribute("publicCount", publicCount);//未读通知公告

        NotifyView notifyView = new NotifyView();
        String _id = id;

        Notify notify = notifyService.findNotifyById(_id);
        User u = userService.getUserById(notify.getCreateBy());

        String cid = notify.getColumnId();
        NotifyColumn notifyColumn = notifyService.findNotifyColumnById(cid);
        if (null == notifyColumn) {
            notifyView.setColumnName("其他");
        } else {
            notifyView.setColumnId(notifyColumn.getId());
            notifyView.setColumnName(notifyColumn.getName());
        }
        notifyView.setId(notify.getId());
        notifyView.setTitle(notify.getTitle());
        notifyView.setContent(notify.getContent());
        notifyView.setCreateBy(notify.getCreateBy());
        notifyView.setPublishTime(notify.getPublishTime());

        if (!StringUtils.isEmpty(notify.getFiles())) {
            List fileList = new ArrayList(Arrays.asList(notify.getFiles().split(",")));//转换附件列表为list
            notifyView.setFiles(fileList);
            notifyView.setFiles(fileList);
        }

        if (null != u) {
            notifyView.setCreateName(u.getName());
        }
        notifyView.setCreateDate(notify.getCreateDate());
        //更新这条公告为已读状态
        NotifyRecord record = new NotifyRecord();
        record.setReadFlag(1);
        record.setReadDate(System.currentTimeMillis());
        record.setUserId(user.getRefId());
        record.setNotifyId(_id);
        notifyService.saveNotifyRecord(record);

        model.addAttribute("notify", notifyView);
        return "notify/publicDetails";
    }

    /**
     * 新增公告页面,发布
     *
     * @return
     */
    @RequiresPermissions("notify:notify:add")
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "id");
        String columId = getParamVal(request, "columId");
        String _id = id;

        Notify notify = notifyService.findNotifyById(_id);
        model.addAttribute("notify", notify);
        if (notify != null) {
            columId = notify.getColumnId().toString();
            String filesString = notify.getFiles();
            String[] files = filesString.split(",");
            if (files.length > 1) {
                List<String> fileList = new ArrayList<>();
                for (int i = 0; i < files.length; i++) {
                    if (!StringUtils.isEmpty(files[i])) {
                        fileList.add(files[i]);
                    }
                }

                model.addAttribute("filesString", filesString);
                model.addAttribute("fileList", fileList);
            }
        }
        model.addAttribute("columId", columId);
        List<Map<Object, Object>> recordList = notifyService.getRecordList(_id);
        //StringBuilder builderIds = new StringBuilder();
        //StringBuilder builderNames = new StringBuilder();
        String builderIds = "";
        String builderNames = "";
        if (recordList.size() > 0) {
            for (Map<Object, Object> record : recordList) {
                builderIds += record.get("departId") + ",";
                builderNames += record.get("departName") + " , ";
            }
            model.addAttribute("departIds", builderIds);
            model.addAttribute("departNames", builderNames);
        }
        List<NotifyColumn> notifyColumnList = notifyService.findAllColumn(getLoginUser().getSchoolId());
        model.addAttribute("notifyColumnList", notifyColumnList);
        return "notify/publicAdd";
    }

    /**
     * 新增栏目弹出页面
     *
     * @return
     */
    @RequestMapping(value = "/lanmu")
    public String addLanMu() {

        return "notify/lanMuAdd";
    }

    /**
     * 添加用户弹出页面
     *
     * @return
     */
    @RequestMapping(value = "/user/add")
    public String addUser() {

        return "notify/userAdd";
    }

    /**
     * 选择分组弹出页面
     *
     * @return
     */
    @RequestMapping(value = "/choosefz")
    public String chooseFz(HttpServletRequest request, Model model) {
        String ids = getParamVal(request, "bumens");
        List<Department> departmentList = departmentService.findAllDepartment(getLoginUser().getSchoolId());

        model.addAttribute("departmentList", departmentList);
        return "notify/chooseFz";
    }

    @RequestMapping(value = "/chooseperson/show")
    public String choosePerson(HttpServletRequest request, Model model) {
        String choosesId = getParamVal(request, "chooseIds");
        model.addAttribute("choosesId", choosesId);
        return "notify/choosePerson";
    }

    /**
     * 栏目管理页面
     *
     * @return
     */
    @RequiresPermissions("notify:lanmu:view")
    @RequestMapping(value = "/lanmu/index")
    public String lanMuManage(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);
        String beginTime = getParamVal(request, "beginDate").replace("-", "");
        String endTime = getParamVal(request, "endDate").replace("-", "");
        String columId = getParamVal(request, "columId");
        columId = "".equals(columId) ? "0" : columId;

        User user = getLoginUser();
        Notify notifyParam = new Notify();
        notifyParam.setColumnId(columId);
        notifyParam.setSchoolId(user.getSchoolId());

        PageHelper.startPage(pageNum, pageSize);
        List<Notify> list = notifyService.getNotifyByCriteria(notifyParam, beginTime, endTime);

        PageInfo<Notify> pageInfo = new PageInfo<Notify>(list);

        List<NotifyColumn> notifyColumnList = notifyService.findAllColumn(user.getSchoolId());
        for (NotifyColumn column : notifyColumnList) {
            if (column.getId().equals(columId)) {
                model.addAttribute("currentColumName", column.getName());
                model.addAttribute("columId", columId);
                break;
            }
        }
        List<NotifyView> notifyViewList = new ArrayList<NotifyView>();
        for (Notify notify : list) {
            NotifyView notifyView = new NotifyView();
            notifyView.setId(notify.getId());
            notifyView.setTitle(notify.getTitle());
            notifyView.setPublishTime(notify.getPublishTime());
            for (NotifyColumn column : notifyColumnList) {
                if (column.getId().equals(notify.getColumnId())) {
                    notifyView.setColumnId(column.getId());
                    notifyView.setColumnName(column.getName());
                    break;
                }
            }
            notifyView.setCreateBy(notify.getCreateBy());
            User u = userService.getUserById(notify.getCreateBy());
            if (null != u) {
                notifyView.setCreateName(u.getName());
            }
            notifyView.setCreateDate(notify.getCreateDate());
            notifyViewList.add(notifyView);
        }
        model.addAttribute("currentColumId", columId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("notifyViewList", notifyViewList);
        model.addAttribute("notifyColumnList", notifyColumnList);

        return "notify/lanMuManage";
    }

    /**
     * 角色分配页面
     *
     * @return
     */
    @RequiresPermissions("notify:role:view")
    @RequestMapping(value = "/role/index")
    public String roleFenPei(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);
        PageHelper.startPage(pageNum, pageSize);
        String roleId = getParamVal(request, "roleId");
        User loginUser = getLoginUser();
        /*App app = new App();
        app.setName("通知公告");
        List<App> appList = appService.findAppByCriteria(app);*/
        HttpSession session = request.getSession();
        Object appId = session.getAttribute("notifyId");

        List<Role> roleList = new ArrayList();
        if (!GukeerStringUtil.isNullOrEmpty(appId))
            roleList = roleService.findRoleByAppId(appId.toString());//。。。。人事管理

       // List<Role> roleList = roleService.findRoleByAppId(appList.get(0).getId());//。。。。通知公告管理
        model.addAttribute("roleList", roleList);
        List<String> idList = new ArrayList<String>();
        for (Role role : roleList) {
            idList.add(role.getId());
        }
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>();
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
            List<String> primList = new ArrayList<>();
            for (User user : userList) {
                primList.add(user.getRefId());
//                Teacher teacher = teacherService.findTeacherById(user.getRefId());
//                if (teacher != null) {
//                    teacherList.add(teacher);
//                }
            }
            teacherList = teacherService.selectBatchTeachers(primList,loginUser.getSchoolId());
            pageInfo = new PageInfo<Teacher>(teacherList);
            model.addAttribute("teacherList", teacherList);
        }
        if (roleId.equals("") && roleList.size() > 0) {
            roleId = roleList.get(0).getId().toString();
        }

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("currentRole", roleId);

        return "notify/roleFenPei";
    }

    /**
     * 角色分配-添加用户-弹出页面
     *
     * @return
     */
    @RequestMapping(value = "/roleuser/add")
    public String addRole(HttpServletRequest request, Model model) {
        String roleId = getParamVal(request, "roleId");
        List<Teacher> teacherList = teacherService.findHaveAccountTeacher(getLoginUser().getSchoolId());

        model.addAttribute("teacherList", new Gson().toJson(teacherList));
        model.addAttribute("roleId", roleId);

        return "notify/notifyRoleAdd";
    }

    /*
    * 角色分配-添加用户-保存
    * */
    @ResponseBody
    @RequestMapping(value = "/roleuser/save")
    public void addUserSave(HttpServletRequest request) {
        String teacherId = getParamVal(request, "teacherId");
        String roleId = getParamVal(request, "roleId");
        List<User> userList = userService.getTeacherByRefId(teacherId);
        User loginUser = getLoginUser();
        for (User user : userList) {
            UserRole userRole = new UserRole();
            userRole.setSchoolId(loginUser.getSchoolId());
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userService.saveUserRole(userRole);
        }

    }

    @RequestMapping(value = "/roleuser/delete")
    public void deleteRoleUser(HttpServletRequest request) {
        String userRefId = getParamVal(request, "userId");
        String roled = getParamVal(request, "roleId");
        String userId = userService.getTeacherByRefId(userRefId).get(0).getId();

        UserRole userRole = new UserRole();
        userRole.setSchoolId(getLoginUser().getSchoolId());
        userRole.setRoleId(roled);
        userRole.setUserId(userId);

        userService.deleteUserRole(userRole);
    }

    /**
     * 删除：逻辑删除
     */
    @RequestMapping(value = "/delete")
    public String edit(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        User user = getLoginUser();

        String[] idArr = id.split(",");
        for (int a = 0; a < idArr.length; a++) {
            Notify notify = new Notify();
            notify.setId(idArr[a]);
            notify.setDelFlag(1);
            notify.setSchoolId(user.getSchoolId());
            notifyService.saveNotify(notify);
        }
        return "";
    }

    /**
     * g公告发布 保存
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity update(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String title = getParamVal(request, "title");
        String content = getParamVal(request, "content");
        String file = getParamVal(request, "fileUrl");

        String _id = id;
        String columId = getParamVal(request, "lanmu");
        String publishTime = getParamVal(request, "publishTime").replace("-", "");

        User user = getLoginUser();
        Notify notify = new Notify();
        notify.setId(_id);
        notify.setTitle(title);
        notify.setContent(content);
        notify.setFiles(file);
        // notify.setPublishTime(DateUtils.yyyyMMddToMillis(publishTime));//定时发布未结合推送暂时不做
        notify.setPublishTime(System.currentTimeMillis());
        notify.setColumnId(columId);
        notify.setDelFlag(0);
        notify.setSchoolId(user.getSchoolId());

        if (_id.length() == 0) {
            String primary = PrimaryKey.get();
            notify.setId(primary);
            notify.setCreateBy(user.getId());
            notify.setCreateDate(System.currentTimeMillis());
            notifyService.addNotifyBackId(notify);
            /*back*/
            _id = primary;//获取插入公告的id
        } else {
            //若是对公告进行修改，删除公告通知表notifyRecord表中的相关通知记录！
            NotifyRecord record = new NotifyRecord();
            record.setNotifyId(_id);
            notifyService.deleteNotifyRecord(record);

            notify.setUpdateBy(user.getId());
            notify.setUpdateDate(System.currentTimeMillis());
            notify.setCreateDate(System.currentTimeMillis());//更新的公告当作新公告来排序
            notifyService.saveNotify(notify);
        }

        //type1:发布公告到人员------------------------begin---------------通过人员id  1，2，3，4，5发布给1，2，3，4，5这些人-----------
        String teachers = getParamVal(request, "whichDepartMent");
        String[] teacherIds = teachers.split(",");
        for (int i = 0; i < teacherIds.length; i++) {
            NotifyRecord record = new NotifyRecord();
            record.setId(PrimaryKey.get());
            record.setNotifyId(_id);
            record.setUserId(teacherIds[i]);
            record.setReadFlag(0);//默认未读
            notifyService.addNotifyRecord(record);
        }

        //type1:发布公告到人员------------------------end-----------------通过人员id  1，2，3，4，5发布给1，2，3，4，5这些人-----------

        //type2:发布公告到部门------------------------begin----------------通过部门id "1，2，3，4，5" 发布给1，2，3，4，5这些部门下的所有人员---
        //添加通知公告到指定部门的功能,使用的是refId和公告id进行的绑定，也就是teache表的id
            /*    String depratMent = getParamVal(request, "whichDepartMent");
                String[] departmentIds = depratMent.split(",");
                for (int i = 0; i < departmentIds.length; i++) {
                    List<Teacher> teacherList = teacherService.findTeacherBySingleDepartment(Integer.parseInt(departmentIds[i]), getLoginUser().getSchoolId());
                    for (Teacher teacher : teacherList) {
                        NotifyRecord record = new NotifyRecord();
                        record.setId(null);
                        record.setNotifyId(_id);
                        record.setUserId(teacher.getId());
                        record.setReadFlag(0);//默认未读
                        notifyService.addNotifyRecord(record);
                    }
                }*/
        //type2:发布公告到部门------------------------end----------------通过部门id "1，2，3，4，5" 发布给1，2，3，4，5这些部门下的所有人员---

        return ResultEntity.newResultEntity();
    }


    @RequestMapping(value = "/col/edit", method = RequestMethod.GET)
    public String colEdit(HttpServletRequest request, Model model) {
        String id = getParamVal(request, "colId");
        String _id = id;

        NotifyColumn notifyColumn = notifyService.findNotifyColumnById(_id);

        model.addAttribute("notifyColumn", notifyColumn);
        return "notify/notifyColumEdit";
    }

    /*栏目编辑/保存*/
    @ResponseBody
    @RequestMapping(value = "/col/save", method = RequestMethod.POST)
    public void saveCol(HttpServletRequest request) {
        String id = getParamVal(request, "id");
        String name = getParamVal(request, "columName");
        String _id = id;
        User user =getLoginUser();
        NotifyColumn notifyColumn = new NotifyColumn();
        notifyColumn.setId(_id);
        notifyColumn.setName(name);
        notifyColumn.setSchoolId(user.getSchoolId());

        if (_id.equals("") || _id == null) {
            notifyColumn.setCreateBy(user.getId());
            notifyColumn.setCreateDate(System.currentTimeMillis());
        } else {
            notifyColumn.setUpdateBy(user.getId());
            notifyColumn.setUpdateDate(System.currentTimeMillis());
        }
        notifyService.saveNotifyColumn(notifyColumn);
    }

    /*栏目删除*/
    @ResponseBody
    @RequestMapping(value = "/col/delete", method = RequestMethod.POST)
    public ResultEntity deleteCol(HttpServletRequest request) {
        String id = getParamVal(request, "colId");
        String _id = id;
        NotifyColumn notifyColumn = new NotifyColumn();
        notifyColumn.setId(_id);
        notifyColumn.setDelFlag(1);/*删除栏目*/
        notifyService.saveNotifyColumn(notifyColumn);
        //删除栏目下的相关公告
        Notify notify = new Notify();
        notify.setDelFlag(1);
        notify.setColumnId("");
        notifyService.deleteNotifyByColumnId(notify, _id);

        return ResultEntity.newErrEntity();
    }
}

