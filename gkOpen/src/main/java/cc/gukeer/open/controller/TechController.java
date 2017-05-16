package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.open.persistence.entity.Question;
import cc.gukeer.open.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LL on 2017/1/18.
 */
@Controller
@RequestMapping(value = "/tech")
public class TechController extends BasicController {
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String tech(HttpServletRequest request, Model model) {
        model.addAttribute("tech", "tech");
        return "tech/question/index";
    }

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public String commonDetail(HttpServletRequest request, Model model) {
        String status = request.getParameter("status");
        model.addAttribute("question", "question");
        if (status == null) {
            return "tech/question/common/detail";
        }
        if (status.equals("person")) {
            return "tech/question/common/register/personal";
        } else if (status.equals("company")) {
            return "tech/question/common/register/company";
        } else if (status.equals("login")) {
            return "tech/question/login/login";
        } else if (status.equals("login1")) {
            return "tech/question/login/updateCheck";
        } else if (status.equals("login2")) {
            return "tech/question/login/duration";
        } else if (status.equals("login3")) {
            return "tech/question/login/findAccount";
        } else if (status.equals("login4")) {
            return "tech/question/login/findPWD";
        } else if (status.equals("login5")) {
            return "tech/question/login/login";
        } else if (status.equals("process")) {
            return "tech/question/app/process";
        } else if (status.equals("submit")) {
            return "tech/question/app/submit";
        } else if (status.equals("time1")) {
            return "tech/question/app/time";
        } else if (status.equals("time2")) {
            return "tech/question/app/update";
        } else if (status.equals("other")) {
            return "tech/question/other/other";
        }else if (status.equals("user1")) {
            return "tech/question/user/process";
        }else if (status.equals("user2")) {
            return "tech/question/user/submit";
        }else if (status.equals("user3")) {
            return "tech/question/user/time";
        }else if (status.equals("user4")) {
            return "tech/question/user/update";
        }

        return null;
    }


    public static void main(String agrs){

    }

}

