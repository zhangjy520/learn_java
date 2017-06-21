package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.open.common.AppPushType;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.modelView.AppBaseInfoView;
import cc.gukeer.open.modelView.SyncView;
import cc.gukeer.open.modelView.UserBaseInfoView;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.extension.ExtentionDynamic;
import cc.gukeer.open.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by conn on 2016/8/1.
 * admin
 */
@Controller
//进入审核管理页面
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController extends BasicController {
    @Autowired
    OpenUserService openUserService;

    @Autowired
    AppService appService;

    @Autowired
    CompanyService companyService;

    @Autowired
    PersonalService personalService;

    @Autowired
    DynamicService dynamicService;

    @Autowired
    PushPlatformService pushPlatformService;

    @Autowired
    SyncService syncService;

    @Autowired
    RefPlatformService refPlatformService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        //前端显示用户时字段为user
        String show = request.getParameter("show");
        int userPageNum = getPageNum(request);
        int appPageNum = getAppPageNum(request);
        int pageSize = getPageSize(request);
        Integer appStatus = getStatus(request, "appStatus");
        Integer userStatus = getStatus(request, "userStatus");
        String delFlag = request.getParameter("del");
        if (delFlag == null) {
            delFlag = "0";
        }

        //所有用户
        PageInfo<UserBaseInfoView> userBasePageInfo =
                openUserService.getUserByCheckState(userStatus, userPageNum, pageSize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //时间类型的转换
        List<UserBaseInfoView> userBaseInfoList = userBasePageInfo.getList();
        for (UserBaseInfoView userBaseInfo : userBaseInfoList) {
            long long_date = userBaseInfo.getUpdateTime();
            Date date = new Date(long_date);
            String res = simpleDateFormat.format(date);
            userBaseInfo.setTime(res);
        }

        //所有应用
        PageInfo<AppBaseInfoView> appBasePageInfo =
                appService.getAppBaseInfoByStatus(appStatus, appPageNum, pageSize,NumberConvertUtil.convertS2I(delFlag));
        ////时间类型的转换
        List<AppBaseInfoView> appBaseInfoList = appBasePageInfo.getList();
        for (AppBaseInfoView appBaseInfo : appBaseInfoList) {
            long long_date = appBaseInfo.getUpdateDate();
            Date date = new Date(long_date);
            String res = simpleDateFormat.format(date);
            appBaseInfo.setTime(res);
        }

        model.addAttribute("userBasePageInfo", userBasePageInfo);
        model.addAttribute("appBasePageInfo", appBasePageInfo);
        model.addAttribute("show", show);
        model.addAttribute("appStatus", appStatus);
        model.addAttribute("userStatus", userStatus);
        model.addAttribute("userBaseInfoList", userBaseInfoList);
        model.addAttribute("appBaseInfoList", appBaseInfoList);
        model.addAttribute("adminIndex", "adminIndex");
        return "admin/index";
    }

    public Integer getStatus(HttpServletRequest request, String parm) {
        String _status = request.getParameter(parm);
        if (!StringUtils.isEmpty(_status)) {
            if (_status.equals("AUDIT_FAIL")) {
                return CheckStateType.AUDIT_FAIL.getStatenum();
            }
            if (_status.equals("AUDIT_SUCCESS")) {
                return CheckStateType.AUDIT_SUCCESS.getStatenum();
            }
            if (_status.equals("AUDITING")) {
                return CheckStateType.AUDITING.getStatenum();
            }
            if (_status.equals("FORBIDDEN")) {
                return CheckStateType.FORBIDDEN.getStatenum();
            }

            if (_status.equals("PUSHED")) {
                return AppPushType.PUSHED.getStatenum();
            }

            if (_status.equals("UNPUSH")) {
                return AppPushType.UNPUSH.getStatenum();
            }
        }
        return null;
    }


    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public String sync(HttpServletRequest request, Model model) {
        PageInfo<SyncView> syncViews = syncService.getSyncView();
        model.addAttribute("syncViews", syncViews);
        model.addAttribute("sync", "sync");
        return "admin/sync";
    }

    @ResponseBody
    @RequestMapping(value = "/sync/setName", method = RequestMethod.POST)
    public ResultEntity setName(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String queueName = request.getParameter("content");
        refPlatformService.setName(id, queueName);
        return ResultEntity.newResultEntity();
    }

    @RequestMapping(value = "/sync/open", method = RequestMethod.GET)
    public String syncOpen(HttpServletRequest request) {
        String id = request.getParameter("id");
        refPlatformService.updateSyncStatus(id, 1);

        return "redirect:/admin/sync";
    }

    @RequestMapping(value = "/sync/close", method = RequestMethod.GET)
    public String syncClose(HttpServletRequest request) {
        String id = request.getParameter("id");
        refPlatformService.updateSyncStatus(id, 0);
        return "redirect:/admin/sync";
    }

    /**
     * 应用推送的首页  查所有应用
     */
    @RequestMapping(value = "/push/index", method = RequestMethod.GET)
    public String push(HttpServletRequest request, Model model) {
        int appPageNum = getPageNum(request);
        int pageSize = getPageSize(request);

        PageInfo<AppBaseInfoView> pageInfo = appService.findAppBaseInfoContainDel(appPageNum, pageSize);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("appPush", "appPush");

        return "admin/push";
    }
}