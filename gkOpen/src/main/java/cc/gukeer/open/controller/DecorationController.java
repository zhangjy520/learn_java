package cc.gukeer.open.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LL on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/self")
public class DecorationController {

    @RequestMapping(value = "/index")
    public String decoration(){
        return "self/index";
    }
}
