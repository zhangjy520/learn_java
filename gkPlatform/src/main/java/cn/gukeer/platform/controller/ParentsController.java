package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by conn on 2016/8/1.
 * graduate
 */
@Controller
@RequestMapping(value = "/parents")
public class ParentsController extends BasicController{

    @RequestMapping(value = "/index")
    public String index(){
        return "parents/index";
    }

}
