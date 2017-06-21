package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.HttpClientUtil;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.open.common.*;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.persistence.entity.extension.AppExtention;
import cc.gukeer.open.persistence.entity.extension.PlatformExtention;
import cc.gukeer.open.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import cc.gukeer.common.utils.PrimaryKey;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/app")
public class AppController extends BasicController {

    @InitBinder("accessories")
    public void initBinderAccessories(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("accessories.");
    }

    @InitBinder("app")
    public void initBinderApp(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("app.");
    }

    @Autowired
    AccessoriesService accessoriesService;

    @Autowired
    CompanyService companyService;

    @Autowired
    RefPlatformService refPlatformService;


    @Autowired
    PersonalService personalService;

    @Autowired
    MultigraphService multigraphService;

    @Autowired
    AppService appService;

    @Autowired
    OpenUserService openUserService;
    @Autowired
    MessageService messageService;


    @Autowired
    PushPlatformService pushPlatformService;

    /**
     * 管理员对创建成功的应用的操作:审核、删除（禁用）、查看详情的方法
     */
    @RequestMapping(value = "/do/{edit}")
    public String edit(HttpServletRequest request, @PathVariable String edit) {
        String appId = getParamVal(request, "id");
        OpenUser openUser = getLoginUser();
        int userType = openUser.getUserType();

        if (edit.equals("delete")) {
            appService.deleteById(appId);
            //执行了这个操作之后还要下线所有平台的应用信息，查询中间表，设置推送状态为3
            List<RefPlatformApp> refPlatformAppList = refPlatformService.findRefplatformByAppId(appId);
            if (refPlatformAppList.size() > 0) {
                for (RefPlatformApp refPlatformApp : refPlatformAppList) {
                    //禁用下线状态
                    refPlatformApp.setAppStatus(RefAppPushStatus.FORBIDDEN.getStatenum());
                    refPlatformApp.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
                    refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
                }
            }
        } else if (edit.equals("able")) {
            appService.updateStatus(appId, 2);
        } else if (edit.equals("disable")) {
            appService.updateStatus(appId, 4);
            //在此时查询ref_app_class表，若有数据，则将ref_app_class的app_status修改为4（禁用下线）
            List<RefPlatformApp> refPlatformApps = refPlatformService.findRefplatformByAppId(appId);
            if (refPlatformApps.size() > 0) {
                for (RefPlatformApp refPlatformApp : refPlatformApps) {
                    refPlatformApp.setAppStatus(RefAppPushStatus.FORBIDDEN.getStatenum());
                    refPlatformApp.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
                    int succ = refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
                }
            }
        }
        if (userType == LoginUserType.PERSONAL.getStatenum() || userType == LoginUserType.COMPANY.getStatenum()) {
            return "redirect:/manager/index";
        }
        return "forward:/admin/index";
    }

    /**
     * 用户审核通过后对应用的所有操作  创建应用  修改应用
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity save(HttpServletRequest request, App app) {
        try {
            //获取图片的index和src
            String arrsrc = getParamVal(request, "arrsrc");
            String arrindex = getParamVal(request, "arrindex");
            String id = getParamVal(request, "id");
            if (id != "") {
                app.setId(id);
            }

            //保存应用
            app.setUserId(getLoginUser().getId());
            int succ = appService.save(app, arrsrc, arrindex);
            if (succ > 0) {
                return ResultEntity.newResultEntity("应用已经提交审核", "manager/index");
            } else {
                return ResultEntity.newErrEntity("填写信息错误可能导致系统异常，请确保所有信息填写完整");
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return ResultEntity.newErrEntity("填写信息错误可能导致系统异常，请确保所有信息填写完整");
        }
    }



    public Integer getStatus(HttpServletRequest request, String parm) {
        String _status = getParamVal(request, parm);
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

    /**
     * 推送应用的pop弹窗的方法
     */
    @RequestMapping(value = "/pop", method = RequestMethod.GET)
    public String platformPop(HttpServletRequest request) {
        //1.获取appId  2.查询所有平台 3.查询app<-->platform推送状态

        String appId = getParamVal(request, "appId");//获取appId
        List<Platform> platformList = pushPlatformService.findPlatformByInitStatus(); //查询所有平台

        RefPlatformApp refPlatformApp = null;
        ArrayList<PlatformExtention> platformExtentionList = new ArrayList<>();//创建这个list的目的是通过appId和platformId通过设置一个exist状态看是否已经推送了
        //遍历platform
        if (platformList.size() > 0) {
            for (Platform platform : platformList) {
                String platformId = platform.getId();
                refPlatformApp = refPlatformService.selectById(appId, platformId);//查询app<-->platform推送状态
                PlatformExtention platformExtention = new PlatformExtention();
                if (refPlatformApp == null) {
                    platformExtention.setIsExist(0);  //在中间表中不存在
                    platformExtention.setPlatform(platform);
                    //在这设置这两个状态仅仅作为页面显示的作用
                    platformExtention.setAppStatus(RefAppPushStatus.UNPUSH.getStatenum());//云应用状态[0未推送,1推送,2修改,4禁用下线(应用已经退出市场，和产品迭代没关系)]
//                    platformExtention.setAppOptStatus(2);//操作状态[0操作失败，1操作成功]

                    platformExtentionList.add(platformExtention);
                } else {
                    int appStatus = refPlatformApp.getAppStatus();
                    int optStatus = refPlatformApp.getOptStatus();

                    platformExtention.setIsExist(1);//存在于2中间表中
                    platformExtention.setPlatform(platform);

                    platformExtention.setAppOptStatus(optStatus);
                    platformExtention.setAppStatus(appStatus);

                    platformExtentionList.add(platformExtention);
                }
            }
        }
        request.setAttribute("platformExtentionList", platformExtentionList);
        request.setAttribute("platformList", platformList);
        request.setAttribute("appId", appId);
        return "admin/pop";
    }

    @ResponseBody
    @RequestMapping(value = "/push/ref", method = RequestMethod.POST)
    public ResultEntity toref(HttpServletRequest request) {
        String appId = getParamVal(request, "appId");
        String platformId = getParamVal(request, "platformId");
        String appStatus = getParamVal(request, "appStatus");
        RefPlatformApp refPlatformApp = refPlatformService.selectById(appId, platformId);
        if (null != refPlatformApp) {
            if (appStatus.equals("online")) {
                refPlatformApp.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
                refPlatformApp.setAppStatus(RefAppPushStatus.PUSHED.getStatenum());

                App app = new App();
                app.setId(appId);
                app.setCheckStatus(CheckStateType.AUDIT_SUCCESS.getStatenum());

                appService.updateAppById(app);
                refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
            }
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        } else {
            RefPlatformApp refPlatformAppNew = new RefPlatformApp();

            refPlatformAppNew.setId(PrimaryKey.get());
            refPlatformAppNew.setAppId(appId);
            refPlatformAppNew.setPlatformId(platformId);
            refPlatformAppNew.setAppStatus(RefAppPushStatus.PUSHED.getStatenum());
            refPlatformAppNew.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
            refPlatformAppNew.setDataStatus(0);
            refPlatformAppNew.setSyncStatus(0);

            refPlatformService.insertRefPlatformApp(refPlatformAppNew);
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        }
    }

    @RequestMapping(value = "/off/line", method = RequestMethod.POST)
    public String unpush(HttpServletRequest request) {
        //获取参数
        String appId = getParamVal(request, "appId");
        String platformId = getParamVal(request, "platformId");

        //非空 则查询通过id查询
        App app = null;
        if (appId != "") {
            app = appService.getAppById(appId);
        }
        RefPlatformApp refPlatformApp = null;
        Platform platform = null;
        if (platformId != "") {
            platform = pushPlatformService.findPlatformById(platformId);
            refPlatformApp = refPlatformService.findRefPlatformByAppIdAndPlatformId(appId, platformId);
        }

        //根据appId和platformId修改中间表ref_app_platform，同时经app的check_status状态改为4,禁用下线状态
        app.setCheckStatus(CheckStateType.FORBIDDEN.getStatenum());
        app.setDelFlag(1);
        appService.updateAppById(app);

        //
        refPlatformApp.setAppStatus(RefAppPushStatus.PUSHED.getStatenum());
        refPlatformApp.setOptStatus(RefAppPushOptStatus.FAIL.getStatenum());
        refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);

        String urlApp = platform.getUrlApp();
        //加密串的处理
        String clientId = platform.getIdentity();
        String random = RandomStringUtils.random(6, true, true);
        long currentTime = new Date().getTime();
        //创建拼接对象
        StringBuilder paramString = new StringBuilder();
        paramString.append(clientId).append(currentTime).append(random);
        //加密处理
        String security = MD5Utils.md5(paramString.toString());
        //封装请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("appId", appId);
        map.put("clientId", clientId);
        map.put("random", random);
        map.put("time", currentTime);
        map.put("security", security);
        String _reback = HttpClientUtil.postContent(urlApp, security, map);
        JSONObject json = JSON.parseObject(_reback);
        Integer code = (Integer) json.get("code");
        if (code == 0) {
            refPlatformApp.setAppStatus(RefAppPushStatus.FORBIDDEN.getStatenum());
            refPlatformApp.setOptStatus(RefAppPushOptStatus.SUCC.getStatenum());
            int succ = refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
            logger.info("应用推送成功");
        } else {
            logger.info("应用推送失败");
        }
        return "admin/push";
    }

    /**
     * 应用详情
     */
    @RequestMapping(value = "/manager/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) {
        //获取参数
        String id = getParamVal(request, "id");
        String status = getParamVal(request, "status");
        App app = null;
        if (id != "") {
            app = appService.getAppById(id);
        }

        //显示审核不通过的原因 查询open_message表
        OpenMessage openMessage = messageService.findByMessageByUserIdAndAppName(app.getUserId(), app.getName());
        if (openMessage != null) {
            model.addAttribute("clauseNotPass", openMessage.getText());
        }

        //查询对应显示的图片
        List<String> appScreenShotList = multigraphService.makeImgPath(app.getAppScreenshot());

        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app);
        model.addAttribute("appListSize", appScreenShotList.size());
        model.addAttribute("status", status);

        if (NumberConvertUtil.convertS2I(status) == CheckStateType.UPDATE_AUDITING.getStatenum()) {
            return "manager/app/update";
        }
        return "manager/app/detail";
    }
}
