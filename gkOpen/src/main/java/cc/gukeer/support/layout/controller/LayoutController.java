package cc.gukeer.support.layout.controller;

import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.support.layout.persistence.entity.Headbar;
import cc.gukeer.support.layout.persistence.entity.Link;
import cc.gukeer.support.layout.service.LayoutService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2016/12/13.
 */
@Controller
@RequestMapping(value = "/layout")
public class LayoutController {
    @Autowired(required=true)
    LayoutService layoutService;

    @RequestMapping(value = "edit/{flag}")
    public String editview(Model model,@PathVariable String flag){
        Headbar headbar = layoutService.getHeadbarByAppNameAndIsLogin(flag,0);
        String html = headbar.getHtml();
        Link link = layoutService.getLink(html);
        model.addAttribute("link",link);
        model.addAttribute("appName",flag);
        return "public/editHeader";
    }
    @ResponseBody
    @RequestMapping(value = "/doedit")
    public ResultEntity doEditview(HttpServletRequest request,Link link){
        String appName = request.getParameter("appName");
        Headbar headbarLogin = layoutService.getHeadbarByAppNameAndIsLogin(appName,0);
        Headbar headbarNoLogin = layoutService.getHeadbarByAppNameAndIsLogin(appName,1);
        String newHtmlLogin = layoutService.updateLink(link,headbarLogin);
        String newHtmlNoLogin = layoutService.updateLink(link,headbarNoLogin);
        Headbar updateheadbarLogin = new Headbar();
        updateheadbarLogin.setHtml(newHtmlLogin);
        layoutService.updateLayout(appName,updateheadbarLogin,0);
        Headbar updateheadbarNoLogin = new Headbar();
        updateheadbarNoLogin.setHtml(newHtmlNoLogin);
        layoutService.updateLayout(appName,updateheadbarNoLogin,1);
        System.out.print(newHtmlNoLogin);
        return ResultEntity.newResultEntity("修改成功");
    }

    @ResponseBody
    //isLogin0:代表登录
    @RequestMapping(value = "/getheadbar/{appName}/{isLogin}")
    public ResultEntity getgkPlatformHeadbar(@PathVariable int isLogin,@PathVariable String appName,HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
        String contextPath = request.getContextPath();
        String path = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+contextPath+"/";
        Headbar headbar = layoutService.getHeadbarByAppNameAndIsLogin(appName,isLogin);
        Map map = new HashMap();
        String css_temp = headbar.getCss();
        String html = headbar.getHtml();
        String css =layoutService.addImgPath(css_temp,path,"url(");
        map.put("css",css);
        map.put("html",html);
        Gson gson =new Gson();
        String layoutList = gson.toJson(map);

        return ResultEntity.newResultEntity(layoutList);
    }

}
