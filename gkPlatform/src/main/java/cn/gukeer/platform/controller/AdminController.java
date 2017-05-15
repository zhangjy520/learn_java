package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by conn on 2016/8/1.
 * admin
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BasicController {

    @RequestMapping(value = "/index")
    public String index() {
        return "admin/index";
    }

}
