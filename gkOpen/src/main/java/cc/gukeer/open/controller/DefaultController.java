package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.utils.LoginUtil;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DefaultController extends BasicController {

    @Autowired
    DynamicService dynamicService;

    @RequestMapping(value = "/err")
    public String err() {
        return "error";
    }

	@RequestMapping(value = "/", method = {RequestMethod.GET})
    public String root(HttpServletRequest request) {
        boolean isLogin = LoginUtil.isLoginUser(request);
        List<Dynamic> dynamicList = dynamicService.findAllDynamic();
        List<Dynamic> list = new ArrayList<>();
        if (dynamicList != null && dynamicList.size()>2){
            list.add(dynamicList.get(0));
            list.add(dynamicList.get(1));
            list.add(dynamicList.get(2));

        }else{
            for (Dynamic dynsmic:dynamicList) {
                list.add(dynsmic);
            }
        }
        logger.info("login status=========:{}", isLogin);
        if (isLogin) {
            request.setAttribute("home","home");
            request.setAttribute("list",list);
            return "/index";
        } else {
            request.setAttribute("home","home");
            request.setAttribute("list",list);
            return "/index";
        }
    }



}
