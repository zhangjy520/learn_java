package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.open.service.RefPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lx on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/rpc")
public class RpcController extends BasicController{
    @Autowired
    RefPlatformService refPlatformService;

    @ResponseBody
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String RpcsyncInit(HttpServletRequest request) {
        String id = request.getParameter("id");
        String tocken = request.getParameter("tocken");
        if (tocken.equals("2012335444456871128213845")){

            try {
//                syncMian.init(id);
                refPlatformService.updateInitData(id,1);
            }catch (Exception e){
                return "error";
            }
            return "success";
        }else {
            return "error";
        }
    }
}
