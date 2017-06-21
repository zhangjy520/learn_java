package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.utils.LoginUtil;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.DynamicService;
import cc.gukeer.open.service.OpenUserService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DefaultController extends BasicController {

    @Autowired
    DynamicService dynamicService;

    @Autowired
    OpenUserService openUserService;

    @RequestMapping(value = "/err")
    public String err() {
        return "error";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String root(HttpServletRequest request) {

        //获得cookie
        Cookie[] cookies = request.getCookies();
        OpenUser openUser = null;
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String token = cookie.getValue();
                openUser = openUserService.queryUserByToken(token);
                //自动登陆
                if (openUser != null){
                    String name = request.getServerName();
                    int port = request.getServerPort();
                    String comtext = request.getContextPath();
                    Subject subject =  getSubject();
                    subject.getSession().setAttribute("openUser",openUser);
                }
            }
        }

        boolean isLogin = LoginUtil.isLoginUser(request);
        List<Dynamic> dynamicList = dynamicService.findAllDynamic();
        List<Dynamic> list = new ArrayList<>();
        if (dynamicList != null && dynamicList.size() > 2) {
            list.add(dynamicList.get(0));
            list.add(dynamicList.get(1));
            list.add(dynamicList.get(2));

        } else {
            for (Dynamic dynsmic : dynamicList) {
                list.add(dynsmic);
            }
        }
        logger.info("login status=========:{}", isLogin);
        if (isLogin) {
            request.setAttribute("home", "home");
            request.setAttribute("list", list);
            return "/index";
        } else {
            request.setAttribute("home", "home");
            request.setAttribute("list", list);
            return "/index";
        }
    }


}
