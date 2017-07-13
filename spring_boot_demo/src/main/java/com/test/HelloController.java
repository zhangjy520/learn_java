package com.test;
 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
/**
 * Created by miaorf on 2016/6/19.
 */
@Controller
@EnableAutoConfiguration
public class HelloController {
 
    @RequestMapping("/index")
    public String index(Model model){
 
        model.addAttribute("name","Ryan");
        model.addAttribute("time",System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat();
        System.out.println(format.format(new Date()));
        System.out.println(format.format(System.currentTimeMillis()));
        model.addAttribute("date",format.format(new Date()));
        model.addAttribute("date1",format.format(System.currentTimeMillis()));
        return "index";
    }
 
 
    @RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Ryan");
        map.put("age","18");
        map.put("sex","man");
        return map;
    }
}