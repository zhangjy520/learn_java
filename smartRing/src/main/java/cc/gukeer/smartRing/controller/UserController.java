package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BasicController {

    @Autowired
    UserService userService;

    /**
     * 账户信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/info/index")
    public String index(HttpServletRequest request, Model model) {
        User user = getLoginUser();

        Map userInfo = new HashMap();
        Map res = userService.getUserInfo(user.getId().toString());

        if (!GukeerStringUtil.isNullOrEmpty(res)) {
            userInfo = res;
            model.addAttribute("permissionList", userService.selectMenusByRoleId(userInfo.get("roleId").toString()));
        }
        else {
            userInfo.put("account",user.getUsername());
            userInfo.put("xm",user.getName());
        }
        model.addAttribute("userInfo", userInfo);
        return "person/accountInfo";
    }

}
