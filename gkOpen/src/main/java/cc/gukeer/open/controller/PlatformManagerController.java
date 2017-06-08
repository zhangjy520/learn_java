package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.MD5Utils;
import cc.gukeer.common.utils.*;
import cc.gukeer.open.persistence.entity.Platform;
import cc.gukeer.open.service.PushPlatformService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by LL on 2017/2/20.
 */
@Controller
@RequestMapping(value = "/platform", method = RequestMethod.GET)
public class PlatformManagerController extends BasicController {
    @Autowired
    PushPlatformService pushPlatformService;//不直接用PlatformService命名原因是:打出来试试就知道了


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String platform(HttpServletRequest request, Model model) {
        Integer pageNum = getPageNum(request);
        Integer pageSize = getPageSize(request);
        PageInfo<Platform> pageInfo = pushPlatformService.findAllPushPlatform(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("platformIndex", "platformIndex");
        model.addAttribute("platformManager", "platformManager");
        return "admin/platform/index";
    }



    @RequestMapping(value = "/add/page", method = RequestMethod.GET)
    public String addPage(HttpServletRequest request) {
        String id = getParamVal(request,"id");
        if (id != "") {
            Platform platform = pushPlatformService.findPlatformById(id);
            request.setAttribute("platformManager", "platformManager");
            request.setAttribute("platform", platform);
            request.setAttribute("status",1);//这个status仅仅是作为前台判断前台有没有平台的密钥元素用的
            request.setAttribute("platformIndex", "platformIndex");
        }else {
            request.setAttribute("addPlatformPage", "addPlatformPage");
        }
        return "admin/platform/add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultEntity pop(HttpServletRequest request, @ModelAttribute Platform platform) {
//        Platform platform = new Platform();
        try {
            request.setCharacterEncoding("UTF-8");
            String platformName = request.getParameter("platformName");
            if (null == platformName || "" == platformName) {
                return ResultEntity.newErrEntity("名称不能为空");
            }
            String urlVisit = request.getParameter("urlVisit");
            if (null == urlVisit || "" == urlVisit) {
                return ResultEntity.newErrEntity("平台访问地址不能为空");
            }
            String urlApp = request.getParameter("urlApp");
            if (null == urlApp || "" == urlApp) {
                return ResultEntity.newErrEntity("应用推送地址不能为空");
            }
            String platformIntroduce = request.getParameter("platformIntroduce");
            if (null == platformIntroduce || "" == platformIntroduce) {
                return ResultEntity.newErrEntity("介绍不能为空");
            }
            platform.setName(platformName);
            platform.setUrlApp(urlApp);
            platform.setUrlVisit(urlVisit);
            platform.setIntroduce(platformIntroduce);
            platform.setCreateTime(new Date().getTime());
            platform.setDelFlag(0);
            platform.setInitStatus(0);
            platform.setId(PrimaryKey.get());
            platform.setInitStatus(0);
            String password = RandomStringUtils.random(6, true, true);
            platform.setPassword(password);
            String random = RandomStringUtils.random(6, true, true);
            int count = pushPlatformService.selectCount(random);
            while (count>0){
                String random_only = RandomStringUtils.random(6, true, true);
                platform.setIdentity(random_only);
            }
            platform.setIdentity(random);
            int succ = pushPlatformService.insertPlatform(platform);
            if (succ > 0) {
                return ResultEntity.newResultEntity("保存成功", "/platform/add/page");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity("保存失败");
        }
        return ResultEntity.newErrEntity("保存失败");
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String appPlatformDel(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (null != id || "" != id) {
            int succ = pushPlatformService.delPlatformById(id);
        }
        return "redirect:/platform/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String appPlatformDetail(HttpServletRequest request) {
        String id = getParamVal(request,"id");
        if (id != "") {
            Platform platform = pushPlatformService.findPlatformById(id);
            request.setAttribute("platformManager", "platformManager");
            request.setAttribute("platform", platform);
            return "admin/platform/detail";
        } else {
            request.setAttribute("error", "没有该平台信息");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultEntity appPlatformUpdate(HttpServletRequest request) {
        String id = request.getParameter("id");
        Platform platform = new Platform();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String platformName = request.getParameter("platformName");
        if (null == platformName || "" == platformName) {
            return ResultEntity.newErrEntity("名称不能为空");
        }
        String urlVisit = request.getParameter("urlVisit");
        if (null == urlVisit || "" == urlVisit) {
            return ResultEntity.newErrEntity("平台访问地址不能为空");
        }
        String urlApp = request.getParameter("urlApp");
        if (null == urlApp || "" == urlApp) {
            return ResultEntity.newErrEntity("应用推送地址不能为空");
        }
        String platformIntroduce = request.getParameter("platformIntroduce");
        if (null == platformIntroduce || "" == platformIntroduce) {
            return ResultEntity.newErrEntity("介绍不能为空");
        }
        platform.setId(id);
        platform.setUpdateTime(new Date().getTime());
        platform.setName(platformName);
        platform.setUrlApp(urlApp);
        platform.setUrlVisit(urlVisit);
        platform.setInitStatus(0);
        platform.setIntroduce(platformIntroduce);
        int succ = pushPlatformService.updatePlatform(platform);
        if (succ > 0) {
            request.setAttribute("platform", platform);
            return ResultEntity.newResultEntity("修改成功", "platform/index");
        } else {
            return ResultEntity.newErrEntity("修改失败");
        }
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request) {
        String id = request.getParameter("id");
//        String actionUrl = "initPlatform";
        String basePath = getBasepath(request);
        String openUrl = basePath;
        if (null != id) {
            Platform platform = pushPlatformService.findPlatformById(id);
            StringBuilder paramString = new StringBuilder();
            //加密字段
            String clientId = platform.getIdentity();
            String password = platform.getPassword();//不作为传输过程中的加密字段
            String random = RandomStringUtils.random(6, true, true);
            long currentTime = new Date().getTime();
            paramString.append(clientId).append(currentTime).append(random).append(password);
            String security = MD5Utils.md5(paramString.toString());

            Map<String, Object> map = new HashMap<>();
            String urlVisit = platform.getUrlVisit();
            map.put("clientId", clientId);
            map.put("openurl", openUrl);
            map.put("random", random);
            map.put("time", currentTime);
            map.put("security", security);
            map.put("id", id);
            String _reback = HttpClientUtil.postContent(urlVisit, security, map);
            JSONObject json = JSON.parseObject(_reback);
            Integer code = (Integer) json.get("code");
            if (code == 0) {
                platform.setInitStatus(1);
                pushPlatformService.updatePlatform(platform);
                logger.info("平台成功初始化" + urlVisit);
            }else {
                platform.setInitStatus(2);
                pushPlatformService.updatePlatform(platform);
                logger.info("初始化失败" + urlVisit);
            }
            return "redirect:/platform/index";
        }
        return null;
    }
}
