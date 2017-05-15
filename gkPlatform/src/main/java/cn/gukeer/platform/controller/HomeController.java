package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.utils.IpAdressUtil;
import cn.gukeer.common.utils.SnsUtil;
import cn.gukeer.common.utils.WeatherUtil;
import cn.gukeer.platform.common.ConstantUtil;
import cn.gukeer.platform.modelView.*;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 * teacher
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BasicController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    NotifyService notifyService;

    @Autowired
    AppService appService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {

        int pageNum = getPageNum(request);

        User user = getLoginUser();

        //PageHelper.startPage(pageNum, 6);
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("userId", user.getRefId());
        paramMap.put("isPublish", System.currentTimeMillis());//判断公告是否已经发布的字段。比对发布时间和当前日期
        paramMap.put("schoolId", user.getSchoolId());
        List<Map<Object, Object>> resultList = notifyService.findNotifyView(paramMap);
        // PageInfo<Map<Object, Object>> pageInfo = new PageInfo<Map<Object, Object>>(resultList);
        //判断公告未读消息的条数：
        Integer publicCount = notifyService.selectRemindNotifyCount(user.getRefId());

        //默认应用
        /*List<App> defaultAppList = appService.selectAppByIsDefault();*/

        int permissionFlag = userService.isAreaAdmin(user.getSchoolId()) ? 1 : 2;//是 区级1，校级2, 3 公共

        List<App> defaultAppList = appService.findAppByIdDefaultByTargetUser(user.getUserType().toString(), permissionFlag);

        List<App> appList = new ArrayList<App>();
        if (defaultAppList.size() > 0) {
            for (App app : defaultAppList) {
                appList.add(app);
            }
        }
        //我的应用
        List<MyApp> myAppList = appService.selectAppByUser(user);
        List<String> myAppIds = new ArrayList<String>();
        if (myAppList.size() > 0) {
            for (MyApp myApp : myAppList) {
                myAppIds.add(myApp.getAppId());
            }

            List<App> myApps = appService.selectAppIdInList(myAppIds);
            if (myApps.size() > 0) {
                for (App app : myApps) {
                    appList.add(app);
                }
            }
        }

        HttpSession session = request.getSession();

        session.setAttribute("publicCount", publicCount);//未读通知公告
        if (getLoginUser().getUserType() != 3) {
            session.setAttribute("userName", getLoginUser().getName());
        }
        session.setAttribute("bottomName", getBottomName());
        session.setAttribute("userType", getLoginUser().getUserType());
        model.addAttribute("notifyList", resultList); 
        model.addAttribute("defaultAppList", appList);
        return "home/index";
    }


    //sns对接
    @ResponseBody
    @RequestMapping(value = "/sns/{id}", method = RequestMethod.POST)
    public ResultEntity sns(HttpServletRequest request, @PathVariable("id") String id) {

        String snsMac = MD5Utils.md5(SnsUtil.KEY + getLoginUser().getUsername());

        List res = new ArrayList();
        switch (id) {
            case "1":
                res = SnsUtil.getSns("/testsns/index.php/Api/Index/weibo_topic_hot", HotTopicView.class, 8, 0);//话题
                break;
            case "2":
                res = SnsUtil.getSns("/testsns/index.php/Api/Index/weibo_all", WeiBoView.class, 5, 0);//微博
                break;
            case "3":
                res = SnsUtil.getSns("/testsns/index.php/Api/Index/plate_hot", HotSpotView.class, 5, 0);//板块
                break;
            case "4":
                res = SnsUtil.getSns("/testsns/index.php/Api/Index/forum_all", AllForumView.class, 5, 0);//帖子
                break;
            case "5":
                List<Object> rep = SnsUtil.getSns("/testsns/index.php/Api/Index/fans?" + getLoginUser().getUsername() + "=" + snsMac, FansView.class, 1, 1);//关注，粉丝，动态
                if (rep.size() > 0) {
                    res.add(rep.get(0));
                } else {
                    FansView fansView = new FansView();
                    fansView.setFans(String.valueOf(0));
                    fansView.setFollow(String.valueOf(0));
                    fansView.setWb_num(String.valueOf(0));
                    res.add(fansView);
                }
                break;
            default:
                break;
        }

        Map resMap = new HashMap();
        resMap.put("list", res);
        resMap.put("snsUrl", SnsUtil.SNS_URL);
        resMap.put("snsMac", snsMac);
        resMap.put("loginUser", getLoginUser());
        return ResultEntity.newResultEntity(resMap);
    }


    //天气
    @ResponseBody
    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public ResultEntity getWeather(HttpServletRequest request) {
        String loginCity = IpAdressUtil.getCityByIp(getClientIp(request));
        Map<String, String> weather = WeatherUtil.getWeather(loginCity, 0);//当天天气
        return ResultEntity.newResultEntity(weather);
    }


    @RequestMapping(value = "/messagecenter")
    public String msgCenter(HttpServletRequest request, Model model) {
        int pageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        User user = getLoginUser();

        PageHelper.startPage(pageNum, pageSize);
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("userId", user.getRefId());
        paramMap.put("isPublish", System.currentTimeMillis());//判断公告是否已经发布的字段。比对发布时间和当前日期
        paramMap.put("schoolId", user.getSchoolId());
        List<Map<Object, Object>> notifyList = notifyService.findNotifyView(paramMap);
        PageInfo<Map<Object, Object>> pageInfo = new PageInfo<Map<Object, Object>>(notifyList);

        Integer publicCount = notifyService.selectRemindNotifyCount(user.getRefId());

        HttpSession session = request.getSession();
        session.setAttribute("publicCount", publicCount);//未读通知公告
        model.addAttribute("notifyList", notifyList);
        model.addAttribute("pageInfo", pageInfo);
        return "home/message";
    }


    public String getBottomName() {
        User user = getLoginUser();
        Integer userType = user.getUserType();
        if (userType == 1) {
            School school = schoolService.selectSchoolById(user.getSchoolId());
            if (school != null) return school.getName();
        }
        if (userType == 2) {
            String refId = user.getRefId();
            String classId = studentService.selectStudentById(refId).getClassId();
            if (classId != null) {
                GradeClass gradeClass = classService.selectClassById(classId);
                if (gradeClass != null) {
                    String njName = ConstantUtil.getValueByKeyAndFlag(gradeClass.getNj(), "nj");
                    String bjName = gradeClass.getName();
                    return njName + " " + bjName;
                }
            }
        }
        if (userType == 3) {
            String refId = user.getRefId();
            Patriarch par = (Patriarch) userService.getLoginUserDetail(refId, new Patriarch());
            Student student = studentService.selectStudentById(par.getStudentId());
            if (par != null) return student.getXsxm() + "的家长";
        }
        return "";
    }
}
