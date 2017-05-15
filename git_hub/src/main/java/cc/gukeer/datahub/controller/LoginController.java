package cc.gukeer.datahub.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.datahub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lx on 2017/4/12.
 */
@Controller
public class LoginController extends BasicController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String dologin(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.dologin(username, password, request)) {
            return "index";
        } else {
            model.addAttribute("status", "fail");
            return "login";
        }
    }

    @RequestMapping(value = "/doLogout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUser");
        return "login";
    }
}
