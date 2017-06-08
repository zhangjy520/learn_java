package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LL on 2016/12/1.
 */
@Controller
@RequestMapping(value = "/document")
public class DocumentController extends BasicController {
    /**
     * 文档规范
     * */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String document(HttpServletRequest request,Model model) {
        model.addAttribute("document","document");
        return "document/index";
    }

    /**
     *      接入指南部分
     * */
    @RequestMapping(value = "/join/register", method = RequestMethod.GET)
    public String developerRegister(HttpServletRequest request,Model model) {
        model.addAttribute("register","register");
        return "document/joinGuide/register";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joininStandard(HttpServletRequest request,Model model) {
        model.addAttribute("join","join");
        return "document/joinGuide/join";
    }

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String operatioManage(HttpServletRequest request,Model model) {
        model.addAttribute("run","run");
        return "document/joinGuide/run";
    }


    /**
     *开发文档---标准规范
     *
     * */

    //开发者协议
    @RequestMapping(value = "/treaty", method = RequestMethod.GET)
    public String documentTreaty(HttpServletRequest request, Model model) {
        model.addAttribute("treaty","treaty");
        return "document/standard/developer-treaty";
    }

    @RequestMapping(value = "/ilegal", method = RequestMethod.GET)
    public String standard01(HttpServletRequest request,Model model) {
        model.addAttribute("ilegal","ilegal");
        return "document/standard/ilegal";
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public String standard02(HttpServletRequest request,Model model) {
        model.addAttribute("service","service");
        return "document/standard/servicetreaty";
    }



    @RequestMapping(value = "/plate", method = RequestMethod.GET)
    public String standard04(HttpServletRequest request,Model model) {
        model.addAttribute("plate","plate");
        return "document/standard/plate";
    }


    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String api(HttpServletRequest request,Model model) {
        model.addAttribute("api","api");
        return "document/api/api";
    }


    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String standard03(HttpServletRequest request,Model model) {
        model.addAttribute("codeback","codeback");
        return "document/api/fetchcode";
    }
}
