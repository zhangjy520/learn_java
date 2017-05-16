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

    @RequestMapping(value = "/do/{edit}")
    public String edit(HttpServletRequest request, @PathVariable String edit) {
        String appId = request.getParameter("id");
        OpenUser openUser = getLoginUser();
        int userType = openUser.getUserType();

        if (edit.equals("delete")) {
            appService.deleteById(appId);
        } else if (edit.equals("able")) {
            appService.updateStatus(appId, 2);
        } else if (edit.equals("disable")) {
            appService.updateStatus(appId, 4);
        }
        if (userType == LoginUserType.PERSONAL.getStatenum() || userType == LoginUserType.COMPANY.getStatenum()) {
            return "redirect:/manager/index";
        }
        return "forward:/admin/index";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity save(HttpServletRequest request, App app) {
        try {
            String _status = request.getParameter("status");
            int status = Integer.valueOf(_status);
            String appName = request.getParameter("appName");
            if (null == appName ||"" == appName || appName.length() > 6) {
                return ResultEntity.newErrEntity("名称不能为空且不能大于6个字");
            }
            String appAbstruct = request.getParameter("appAbstruct");
            if (null == appAbstruct||"" == appAbstruct ||appAbstruct.length() > 300) {
                return ResultEntity.newErrEntity("介绍不能为空且不能超过500字");
            }
            String logo = request.getParameter("logo");
            if (null == logo || "" == logo) {
                return ResultEntity.newErrEntity("logo图片不能为空");
            }
            String myselect = request.getParameter("myselect");

            int category = Integer.valueOf(myselect);
            if (null == myselect || "" == myselect) {
                return ResultEntity.newErrEntity("类别不能为空");
            }
            String _targetUser = request.getParameter("targetUser");
            if (null == _targetUser || "" == _targetUser) {
                return ResultEntity.newErrEntity("目标用户不能为空");
            }
            String targetUser = _targetUser.substring(5, _targetUser.length());
            String _isFree = request.getParameter("isFree");
            if (null == _isFree || "" == _isFree) {
                return ResultEntity.newErrEntity("是否免费不能为空");
            }
            int isFree = Integer.valueOf(_isFree);
            String _rank = request.getParameter("rank");
            if (null == _rank || "" == _rank) {
                return ResultEntity.newErrEntity("级别不能为空");
            }

            int rank = Integer.valueOf(_rank);
//            String multifyInput2 = request.getParameter("multifyInput2");
//            if (null == multifyInput2 || "" == multifyInput2) {
//                return ResultEntity.newErrEntity("应用截图为必传项");
//            }
            String demoUrl = request.getParameter("demoUrl");
            if (null == demoUrl || "" == demoUrl) {
                return ResultEntity.newErrEntity("演示地址不能为空");
            }
            String demoAccount = request.getParameter("demoAccount");
            if (null == demoAccount || "" == demoAccount) {
                return ResultEntity.newErrEntity("演示账号不能为空");
            }
            String version = request.getParameter("version");
            if (null == version || "" == version) {
                return ResultEntity.newErrEntity("版本号不能为空");
            }
            String arrsrc = request.getParameter("arrsrc");
            if (arrsrc == logo || "" == arrsrc) {
                return ResultEntity.newErrEntity("应用截图不能为空");
            }
            String arrindex = request.getParameter("arrindex");
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
//            List<Map.Entry<String, Integer>> list_Data = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//            //通过Collections.sort(List I,Comparator c)方法进行排序
//            Collections.sort(list_Data, new Comparator<Map.Entry<String, Integer>>() {
//                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                    return (o1.getValue() - o2.getValue());
//                }
//            });
//            System.out.println(list_Data);
            sortMap(map);
            printMap(map);
//            Map<String, Integer> map1 = new TreeMap<>();
//            for (int i = 0; i < list_Data.size(); i++) {
//                Integer entry = list_Data.get(i).getValue();
//                map1.put(list_Data.get(i).getKey(), list_Data.get(i).getValue());
//            }
//            printMap(map1);
//            System.out.println(map1.toString());
            OpenUser openUser = getLoginUser();
            app.setId(PrimaryKey.get());
            app.setUserId(openUser.getId());
            app.setCreateDate(new Date().getTime());
            app.setUpdateDate(System.currentTimeMillis());
            app.setCheckStatus(status);
            app.setName(appName);
//            app.setAppAbbreviation(abbreviation);
            app.setAppAbstruct(appAbstruct);
            app.setLogo(logo);
            app.setCategory(category);
            app.setTargetUser(targetUser);
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

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity appUpdate(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        App app = appService.getAppById(id);
        app.setCheckStatus(5);
        String appName = request.getParameter("appName");
        String appAbstruct = request.getParameter("appAbstruct");
        String logo = request.getParameter("logo");
        if (null == logo || "" == logo) {
            return ResultEntity.newErrEntity("图标不能为空");
        }
        String myselect = request.getParameter("myselect");
        int category = Integer.valueOf(myselect);
        String _isFree = request.getParameter("isFree");
        int isFree = Integer.valueOf(_isFree);
        String _rank = request.getParameter("rank");
        int rank = Integer.valueOf(_rank);
        String demoUrl = request.getParameter("demoUrl");
        String demoAccount = request.getParameter("demoAccount");
        String arrsrc = request.getParameter("arrsrc");
        String arrindex = request.getParameter("arrindex");
        if (arrsrc == logo || "" == arrsrc) {
            return ResultEntity.newErrEntity("应用截图不能为空");
        }
        String[] src = arrsrc.split(",");
        String[] index = arrindex.split(",");
        Integer[] intIndex = new Integer[index.length];
        for (int i = 0; i < index.length; i++) {
            intIndex[i] = Integer.parseInt(index[i]);
        }
        Map<String, Integer> map = new TreeMap<>();
        for (int j = 0; j < src.length; j++) {
            map.put(src[j], intIndex[j]);
        }
        printMap(map);
        sortMap(map);
        printMap(map);
        app.setCreateDate(new Date().getTime());
        app.setUpdateDate(new Date().getTime());
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

        int succ = appService.updateAppById(app);
        App app1 = appService.getAppById(id);
        String _appScreenShot = app1.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app1);
        model.addAttribute("appListSize", appScreenShotList.size());
        return ResultEntity.newResultEntity("应用修改成功","manager/index");
    }

    @RequestMapping(value = "/sreen", method = RequestMethod.POST)
    public String appUpdateSreenshot(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String imgs = request.getParameter("multifyInput2");
        App app = appService.getAppById(id);
        app.setAppScreenshot(imgs);
        app.setCheckStatus(5);
        app.setCreateDate(new Date().getTime());
//        app.setAppAbbreviation(null);
        int succ = appService.updateAppById(app);
        App app1 = appService.getAppById(id);
        String _appScreenShot = app1.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app1);
        model.addAttribute("appListSize", appScreenShotList.size());
        return "manager/app/update";
    }
//    @ResponseBody
//    @RequestMapping(value = "/push", method = RequestMethod.POST)
//    public ResultEntity send(HttpServletRequest request, Model model) {
//        String id = request.getParameter("id");
//        App app = appService.getAppAllInfoByPrimarykey(id);
//        if (null != app) {
//            return ResultEntity.newResultEntity("应用信息查询已返回", app);
//        }
//        return null;
//    }

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

    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public String dopush(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String platformId = request.getParameter("platformId");
        App app = null;
        ArrayList arrayList = new ArrayList<>();
//        if (null != appIds) {
//            for (int i = 1; i < appIdsArray.length; i++) {
//                String appId = appIdsArray[i];
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
//            }
//        }
//        if (null != platformIds) {
//            String[] platformIdsArray = platformIds.split(",");
//            for (int i = 1; i < platformIdsArray.length; i++) {
//                String platformId = platformIdsArray[i];
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
    }

    @RequestMapping(value = "/pop", method = RequestMethod.GET)
    public String platformPop(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        List<Platform> platformList = pushPlatformService.findPlatformByInitStatus();
        RefPlatformApp refPlatformApp = null;
        ArrayList<PlatformExtention> platformExtentionList = new ArrayList<>();
        if (platformList.size() > 0) {
            for (Platform platform : platformList) {
                String platformId = platform.getId();
                refPlatformApp = refPlatformService.selectById(appId, platformId);
                if (refPlatformApp == null) {
                    PlatformExtention platformExtention = new PlatformExtention();
                    platformExtention.setExist(0);
                    platformExtention.setPlatform(platform);
                    platformExtention.setAppStatus(5);
                    platformExtention.setAppOptStatus(5);
                    platformExtentionList.add(platformExtention);
                } else {
                    int appStatus = refPlatformApp.getAppStatus();
                    int optStatus = refPlatformApp.getOptStatus();
                    PlatformExtention platformExtention = new PlatformExtention();
                    platformExtention.setPlatform(platform);
                    platformExtention.setAppOptStatus(optStatus);
                    platformExtention.setAppStatus(appStatus);
                    platformExtentionList.add(platformExtention);
                }
            }
        }
        request.setAttribute("platformExtentionList", platformExtentionList);
        request.setAttribute("appId", appId);
        return "admin/pop";
    }

    @ResponseBody
    @RequestMapping(value = "/push/ref", method = RequestMethod.POST)
    public ResultEntity toref(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String platformId = request.getParameter("platformId");
        RefPlatformApp refPlatformApp = refPlatformService.selectById(appId, platformId);
        if (null != refPlatformApp) {
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        } else {
            RefPlatformApp refPlatformAppNew = new RefPlatformApp();
            refPlatformAppNew.setId(PrimaryKey.get());
            refPlatformAppNew.setAppId(appId);
            refPlatformAppNew.setPlatformId(platformId);
            refPlatformAppNew.setAppStatus(0);
            refPlatformAppNew.setOptStatus(0);
            refPlatformAppNew.setDataStatus(0);
            refPlatformAppNew.setSyncStatus(0);
            refPlatformService.insertRefPlatformApp(refPlatformAppNew);
            return ResultEntity.newResultEntity("已推送到任务管理", "app/pop");
        }
    }

    @RequestMapping(value = "/unpush", method = RequestMethod.POST)
    public String unpush(HttpServletRequest request) {
        String appId = request.getParameter("appId");
        String platformId = request.getParameter("platformId");
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
            platform.setInitStatus(1);
            pushPlatformService.updatePlatform(platform);
            RefPlatformApp refPlatformApp = new RefPlatformApp();
            refPlatformApp.setId(PrimaryKey.get());
            refPlatformApp.setAppId(appId);
            refPlatformApp.setPlatformId(platformId);
            int succ = refPlatformService.insertRefPlatformApp(refPlatformApp);
            logger.info("应用推送成功");
        } else {
            logger.info("应用推送失败");
        }
        return "admin/push";
    }

    @RequestMapping(value = "/manager/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String _status = request.getParameter("status");
        int status = 0;
        if (_status != "" && _status != null) {
            status = Integer.parseInt(_status);
        }
        App app = appService.getAppById(id);
        String userId = app.getUserId();
        String appName = app.getName();
        OpenMessage openMessage = messageService.findByMessageByUserIdAndAppName(userId,appName);
        String _appScreenShot = app.getAppScreenshot();
        List<String> appScreenShotList = multigraphService.makeImgPath(_appScreenShot);
        model.addAttribute("appScreenShotList", appScreenShotList);
        model.addAttribute("app", app);
        model.addAttribute("appListSize", appScreenShotList.size());
        if (openMessage != null){
            model.addAttribute("clauseNotPass", openMessage.getText());
        }
        if (status == 5) {
            return "manager/app/update";
        }
        return "manager/app/detail";
    }
}
