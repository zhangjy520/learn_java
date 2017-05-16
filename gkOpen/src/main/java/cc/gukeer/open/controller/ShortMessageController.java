package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.PropertiesUtil;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.OpenUserService;
import cc.gukeer.open.service.ShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import java.util.Random;

/**
 * Created by LL on 2016/12/13.
 */
@Controller
public class ShortMessageController extends BasicController {
    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private OpenUserService openUserService;

    @ResponseBody
    @RequestMapping(value = "/shortMessage", method = RequestMethod.POST)
    public ResultEntity sendMessage(HttpServletRequest request) {
        try {
            Properties properties = PropertiesUtil.getProperties("message.properties");
            String account = properties.getProperty("account");
            String pswd = properties.getProperty("pswd");
            String url = properties.getProperty("url");
            int _random = new Random().nextInt(1000000);
            int random = makerandom(_random);
            String msg = "【教育云开放平台】欢迎注教育云开放平台，您的验证码为" + random;
            String mobile = request.getParameter("mobile");
            String username = request.getParameter("username");
            OpenUser OpenUser = openUserService.queryUserByUserNameEmail(username);
            OpenUser.setRandom(String.valueOf(random));
            openUserService.updateRandom(OpenUser);
            String status = shortMessageService.batchSend(url, account, pswd, mobile, msg, true, null);
            return ResultEntity.newResultEntity("success",random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEntity.newErrEntity();
    }

    public int makerandom(int random) {
        if (random < 100000) {
            return makerandom(new Random().nextInt(1000000));
        }
            return random;
    }
}

