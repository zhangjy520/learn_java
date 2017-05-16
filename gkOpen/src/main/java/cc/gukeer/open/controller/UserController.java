package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.service.DynamicService;
import cc.gukeer.open.service.OpenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lx on 2016/12/29.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BasicController{

    @Autowired
    OpenUserService openUserService;
    @Autowired
    DynamicService dynamicService;
    @RequestMapping(value = "/index")

    public String userindex(HttpServletRequest request){
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
        request.setAttribute("list",list);
        return "/index";
    }

    @RequestMapping(value = "/edit/{mark}")
    public String edit(@PathVariable String mark, HttpServletRequest request){
        String userId = request.getParameter("id");
        if (mark.equals("delete")) {
            openUserService.deleteById(userId);
        }else if(mark.equals("able")){
           int succ = openUserService.updateCheckState(CheckStateType.AUDIT_SUCCESS.getStatenum(),userId);
        }else if (mark.equals("disable")){
            openUserService.updateCheckState(CheckStateType.FORBIDDEN.getStatenum(),userId);
        }
        return "redirect:/admin/index";
    }

}



