package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.HttpClientUtil;
import cc.gukeer.open.common.AppPushType;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
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
        String appId = getParamVal(request,"id");
        OpenUser openUser = getLoginUser();
        int userType = openUser.getUserType();

        if (edit.equals("delete")) {
            appService.deleteById(appId);
            //执行了这个操作之后还要下线所有平台的应用信息，查询中间表，设置推送状态为3
            List<RefPlatformApp> refPlatformAppList = refPlatformService.findRefplatformByAppId(appId);
            if (refPlatformAppList.size() > 0) {
                for (RefPlatformApp refPlatformApp : refPlatformAppList) {
                    //禁用下线状态
                    refPlatformApp.setAppStatus(4);
                    refPlatformApp.setOptStatus(0);
                    refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
                }
            }
        } else if (edit.equals("able")) {
            appService.updateStatus(appId, 2);
        } else if (edit.equals("disable")) {
            appService.updateStatus(appId, 4);
            //在此时查询ref_app_class表，若有数据，则将ref_app_class的app_status修改为3（禁用下线）
            List<RefPlatformApp> refPlatformApps = refPlatformService.findRefplatformByAppId(appId);
            if (refPlatformApps.size() > 0) {
                for (RefPlatformApp refPlatformApp : refPlatformApps) {
                    refPlatformApp.setAppStatus(3);
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
     * 创建应用
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity save(HttpServletRequest request, App app) {
        try {
            String _status = getParamVal(request,"status");
            int status = Integer.valueOf(_status);
            String appName =getParamVal( request,"appName");
            if ( "" == appName || appName.length() > 6) {
                return ResultEntity.newErrEntity("名称不能为空且不能大于6个字");
            }
            String appAbstruct = getParamVal(request,"appAbstruct");
            if (null == appAbstruct || "" == appAbstruct || appAbstruct.length() > 300) {
                return ResultEntity.newErrEntity("介绍不能为空且不能超过500字");
            }
            String logo = getParamVal(request,"logo");
            if ( "" == logo) {
                return ResultEntity.newErrEntity("logo图片不能为空");
            }
            String myselect = getParamVal(request,"myselect");

            int category = Integer.valueOf(myselect);
            if ("" == myselect) {
                return ResultEntity.newErrEntity("类别不能为空");
            }
            String _targetUser = getParamVal(request,"targetUser");
            if ("" == _targetUser) {
                return ResultEntity.newErrEntity("目标用户不能为空");
            }
            String targetUser = _targetUser.substring(5, _targetUser.length());
            String _isFree = getParamVal(request,"isFree");
            if ("" == _isFree) {
                return ResultEntity.newErrEntity("是否免费不能为空");
            }
            int isFree = Integer.valueOf(_isFree);
            String _rank = getParamVal(request,"rank");
            if ("" == _rank) {
                return ResultEntity.newErrEntity("级别不能为空");
            }
            int rank = Integer.valueOf(_rank);
            String demoUrl = getParamVal(request,"demoUrl");
            if ("" == demoUrl) {
                return ResultEntity.newErrEntity("演示地址不能为空");
            }
            String demoAccount = getParamVal(request,"demoAccount");
            if ("" == demoAccount) {
                return ResultEntity.newErrEntity("演示账号不能为空");
            }
            String version = getParamVal(request,"version");
            if ("" == version) {
                return ResultEntity.newErrEntity("版本号不能为空");
            }
            String arrsrc = getParamVal(request,"arrsrc");
            if ("" == arrsrc) {
                return ResultEntity.newErrEntity("请选择应用截图");
            }

            String arrindex = getParamVal(request,"arrindex");
            String[] src = arrsrc.split(",");
            String[] index = arrindex.split(",");
            Integer[] intIndex = new Integer[index.length];
            for (int i = 0; i < index.length; i++) {
                System.out.println(Integer.parseInt(index[i]));
                intIndex[i] = Integer.parseInt(index[i]);
            }
            Map<String, Integer> map = new TreeMap<>();
            for (int j = 0; j < src.length; j++) {
                map.put(src[j], intIndex[j]);
            }
            printMap(map);
            sortMap(map);
            printMap(map);
            OpenUser openUser = getLoginUser();
            app.setId(PrimaryKey.get());
            app.setUserId(openUser.getId());
            app.setCreateDate(new Date().getTime());
            app.setUpdateDate(System.currentTimeMillis());
            app.setCheckStatus(status);
            app.setName(appName);
            app.setAppAbstruct(appAbstruct);
            app.setLogo(logo);
            app.setCategory(category);
            app.setIsFree(isFree);
            app.setAppRank(rank);
            app.setAppScreenshot(map.toString());
            app.setAppUrl(demoUrl);
            app.setDemoAccount(demoAccount);
            app.setDelFlag(0);
            app.setVersion(version);
            app.setAppSecret(RandomStringUtils.random(6, true, true));
            String client_id = RandomStringUtils.random(8, true, true);
            String client_secret = RandomStringUtils.random(16, true, true);
            int client_id_count = appService.selectCount(client_id);
            while (client_id_count > 0) {
                String client_id_only = RandomStringUtils.random(8, true, true);
                app.setClientId(client_id_only);
            }
            int client_secret_count = appService.selectCount(client_secret);
            while (client_secret_count > 0) {
                String client_secret_only = RandomStringUtils.random(16, true, true);
                app.setClientSecret(client_secret_only);
            }
            app.setClientId(client_id);
            app.setClientSecret(client_secret);
            int succ = appService.save(app);
            if (status == 0) {
                return ResultEntity.newResultEntity("应用已经保存", "manager/index");
            } else {
                return ResultEntity.newResultEntity("应用已经提交审核", "manager/index");
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return ResultEntity.newErrEntity("填写信息错误可能导致系统异常，请确保所有信息填写完整");
        }

    }


    private static void printMap(Map map) {
        System.out.println("===================mapStart==================");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("===================mapEnd==================");
    }

    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        Map newMap = new TreeMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    /**
     * 修改应用
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity appUpdate(HttpServletRequest request, Model model) {
        String id = getParamVal(request,"id");
        App app = appService.getAppById(id);
        app.setCheckStatus(5);
        String appName = getParamVal(request,"appName");
        String appAbstruct = getParamVal(request,"appAbstruct");
        String myselect = getParamVal(request,"myselect");
        int category = Integer.valueOf(myselect);
        String _isFree = getParamVal(request,"isFree");
        int isFree = Integer.valueOf(_isFree);
        String _rank = getParamVal(request,"rank");
        int rank = Integer.valueOf(_rank);
        String demoUrl = getParamVal(request,"demoUrl");
        String demoAccount = getParamVal(request,"demoAccount");
        app.setCreateDate(new Date().getTime());
        app.setUpdateDate(new Date().getTime());
        app.setName(appName);
        app.setAppAbstruct(appAbstruct);
        app.setCategory(category);
        app.setIsFree(isFree);
        app.setAppRank(rank);
        app.setAppUrl(demoUrl);
        app.setDemoAccount(demoAccount);
        app.setDelFlag(0);
        int succ = appService.updateAppById(app);
        App app1 = appService.getAppById(id);
        String _appScreenShot = app1.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app1);
        model.addAttribute("appListSize", appScreenShotList.size());
        return ResultEntity.newResultEntity("应用修改成功", "manager/index");
    }

    /**
     * 应用
     */
    @RequestMapping(value = "/sreen", method = RequestMethod.POST)
    public String appUpdateSreenshot(HttpServletRequest request, Model model) {
        String id = getParamVal(request,"id");
        String imgs = getParamVal(request,"multifyInput2");
        App app = appService.getAppById(id);
        app.setAppScreenshot(imgs);
        app.setCheckStatus(5);
        app.setCreateDate(new Date().getTime());
        int succ = appService.updateAppById(app);
        App app1 = appService.getAppById(id);
        String _appScreenShot = app1.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app1);
        model.addAttribute("appListSize", appScreenShotList.size());
        return "manager/app/update";
    }

    public Integer getStatus(HttpServletRequest request, String parm) {
        String _status = getParamVal(request,parm);
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


    /*@RequestMapping(value = "/push", method = RequestMethod.POST)
    public String dopush(HttpServletRequest request) {
        String appId = getParamVal("appId");
        String platformId = getParamVal("platformId");
        App app = null;
        ArrayList arrayList = new ArrayList<>();
        app = appService.findAppByPrimarykeyAndCheckestatus(appId);
        String userId = app.getUserId();
        String companName = null;
        String developer = null;
        OpenUser openUser = openUserService.getOpenUserById(userId);
        Integer userType = openUser.getUserType();
        if (userType == LoginUserType.COMPANY.getStatenum()) {
            Company company = companyService.findCompanylbyloginUser(openUser);
            companName = company.getBusinessName();
            developer = company.getDeveloperName();
        } else {
            Personal personal = personalService.findPersonalbyloginUser(openUser);
            companName = personal.getCompanyName();
            developer = personal.getName();
        }
        AppExtention appExtention = new AppExtention();
        appExtention.setApp(app);
        appExtention.setDeveloper(developer);
        appExtention.setCompanyName(companName);
        arrayList.add(appExtention);
        Platform platform = pushPlatformService.findPlatformById(platformId);
        String urlApp = platform.getUrlApp();
        //加密串的处理
        String clientId = platform.getIdentity();
        String random = RandomStringUtils.random(6, true, true);
        long currentTime = new Date().getTime();
        //创建拼接对象
        StringBuilder paramString = new StringBuilder();
        paramString.append(clientId).append(currentTime).append(random).append(platform.getPassword());
        //加密处理
        String security = MD5Utils.md5(paramString.toString());
        //封装请求参数
        Map<String, Object> map = new HashMap<>();
        JSONObject appJson = (JSONObject) JSON.toJSON(app);
        map.put("app", appJson);
        map.put("developer", developer);
        map.put("companName", companName);
        map.put("clientId", clientId);
        map.put("random", random);
        map.put("time", currentTime);
        map.put("security", security);
        String _reback = HttpClientUtil.postContent(urlApp, security, map);
        JSONObject json = JSON.parseObject(_reback);
        Integer code = (Integer) json.get("code");
        if (code == 0) {
            RefPlatformApp refPlatformApp = new RefPlatformApp();
//            refPlatformService.findRefPlatformApp();
            refPlatformApp.setId(PrimaryKey.get());
            refPlatformApp.setAppId(appId);
            refPlatformApp.setOptStatus(1);
            refPlatformApp.setPlatformId(platformId);
            refPlatformApp.setCreateTime(new Date().getTime());
            RefPlatformApp refPlatformAppFromDb = refPlatformService.selectById(platformId, appId);
            if (refPlatformAppFromDb != null) {
                refPlatformAppFromDb.setOptStatus(1);
                refPlatformAppFromDb.setUpdateTime(new Date().getTime());
                int update = refPlatformService.updateRefPlatformByPrimarykey(refPlatformAppFromDb);
            } else {
                int succ = refPlatformService.insertRefPlatformApp(refPlatformApp);
                logger.info("应用推送成功");
            }

        } else {
            RefPlatformApp refPlatformAppFromDb = refPlatformService.selectById(platformId, appId);
            if (refPlatformAppFromDb != null) {
                refPlatformAppFromDb.setOptStatus(0);
                refPlatformAppFromDb.setUpdateTime(new Date().getTime());
                int update = refPlatformService.updateRefPlatformByPrimarykey(refPlatformAppFromDb);
            } else {
                RefPlatformApp refPlatformApp = new RefPlatformApp();
                refPlatformApp.setOptStatus(0);
                refPlatformApp.setId(PrimaryKey.get());
                refPlatformApp.setAppId(appId);
                refPlatformApp.setPlatformId(platformId);
                refPlatformApp.setCreateTime(new Date().getTime());

                int succ = refPlatformService.insertRefPlatformApp(refPlatformApp);
                logger.info("应用推送失败");
            }

        }
        return "admin/push";
    }*/


    /**
     * 推送应用的pop弹窗的方法
     */
    @RequestMapping(value = "/pop", method = RequestMethod.GET)
    public String platformPop(HttpServletRequest request) {
        //1.获取appId  2.查询所有平台 3.查询app<-->platform推送状态

        String appId = getParamVal(request,"appId");//获取appId
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
                    platformExtention.setName(platform.getName());
                    platformExtention.setPlatform(platform);
                    //在这设置这两个状态仅仅作为页面显示的作用
                    platformExtention.setAppStatus(0);//云应用状态[0未推送,1推送,2修改,4禁用下线(应用已经退出市场，和产品迭代没关系)]
                    platformExtention.setAppOptStatus(2);//操作状态[0操作失败，1操作成功]

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
        String appId = getParamVal(request,"appId");
        String platformId = getParamVal(request,"platformId");
        String appStatus = getParamVal(request,"appStatus");
        RefPlatformApp refPlatformApp = refPlatformService.selectById(appId, platformId);
        if (null != refPlatformApp) {
            if (appStatus.equals("online")){
                refPlatformApp.setOptStatus(0);
                refPlatformApp.setAppStatus(1);
                App app = new App();
                app.setId(appId);
                app.setCheckStatus(2);
                appService.updateAppById(app);
                refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
            }
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        } else {
            RefPlatformApp refPlatformAppNew = new RefPlatformApp();
            refPlatformAppNew.setId(PrimaryKey.get());
            refPlatformAppNew.setAppId(appId);
            refPlatformAppNew.setPlatformId(platformId);
            refPlatformAppNew.setAppStatus(1);
            refPlatformAppNew.setOptStatus(0);
            refPlatformAppNew.setDataStatus(0);
            refPlatformAppNew.setSyncStatus(0);
            refPlatformService.insertRefPlatformApp(refPlatformAppNew);
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        }
    }

    @RequestMapping(value = "/off/line", method = RequestMethod.POST)
    public String unpush(HttpServletRequest request) {
        String appId = getParamVal(request,"appId");
        String platformId = getParamVal(request,"platformId");
        //根据appId和platformId修改中间表ref_app_platform，同时经app的check_status状态改为4,禁用下线状态
        App app = appService.getAppById(appId);
        app.setCheckStatus(4);
        app.setDelFlag(1);
        appService.updateAppById(app);
        RefPlatformApp refPlatformApp = refPlatformService.findRefPlatformByAppIdAndPlatformId(appId, platformId);
        refPlatformApp.setAppStatus(0);
        refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
        Platform platform = pushPlatformService.findPlatformById(platformId);
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
            refPlatformApp.setAppStatus(3);
            refPlatformApp.setOptStatus(0);
            int succ = refPlatformService.updateRefPlatformByPrimarykey(refPlatformApp);
            logger.info("应用推送成功");
        } else {
            logger.info("应用推送失败");
        }
        return "admin/push";
    }

    @RequestMapping(value = "/manager/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) {
        String id = getParamVal(request,"id");
        String _status = getParamVal(request,"status");
        int status = 0;
        if (_status != "" && _status != null) {
            status = Integer.parseInt(_status);
        }
        App app = appService.getAppById(id);
        String userId = app.getUserId();
        String appName = app.getName();
        OpenMessage openMessage = messageService.findByMessageByUserIdAndAppName(userId, appName);
        String _appScreenShot = app.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app);
        model.addAttribute("appListSize", appScreenShotList.size());
        if (openMessage != null) {
            model.addAttribute("clauseNotPass", openMessage.getText());
        }
        if (status == 5) {
            return "manager/app/update";
        }
        return "manager/app/detail";
    }
}
