/*
package cc.gukeer.smartBoard.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.dwr.SendMsg;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.exception.ErrcodeException;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.utils.helper.ConfigHelper;
import cc.gukeer.common.utils.util.WriteStreamAppendUtil;
import cc.gukeer.smartBoard.common.UserRoleType;
import cc.gukeer.smartBoard.persistence.entity.User;
import cc.gukeer.smartBoard.service.UserService;
import nettySDK.API.MessageHandler;
import nettySDK.API.NettyBootStrap;
import nettySDK.factory.ClientNettyBootStrapFactory;
import nettySDK.factory.ServerNettyBootStrapFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class LoginController extends BasicController {

    @Autowired
    UserService userService;

    NettyBootStrap nettyBootStrap;
    NettyBootStrap nettyBootStrapc;


    @RequestMapping(value = "/server/init")
    public void test1() throws Exception {
        nettyBootStrap = ServerNettyBootStrapFactory.getNettyBootStrap(10001,true);
        nettyBootStrap.connect();

        nettyBootStrap.listenerMessage(new MessageHandler() {
            @Override
            public void onMessageReceive(Object o) {
                System.out.println("有人来了" + o);
            }
        });
    }
    @RequestMapping(value = "/server/send")
    public void test2() throws Exception {
        nettyBootStrap.sendMessage("test\r\n");
    }
    @RequestMapping(value = "/client/init")
    public void test3() throws Exception {
        nettyBootStrapc  = ClientNettyBootStrapFactory.getNettyBootStrap("127.0.0.1",10001,true);
        nettyBootStrapc.connect("test");
<<<<<<< HEAD
      */
/*  nettyBootStrapc.listenerMessage(new MessageHandler() {
            public void onMessageReceive(Object message) {
                System.out.println("服务器发来" + message);
            }
        });*//*

=======
        nettyBootStrapc.listenerMessage(new MessageHandler() {
            public void onMessageReceive(Object message) {
                System.out.println("服务器发来" + message);
            }
        });
>>>>>>> adb54afbceee83ca4e16c8d3112e2fb4a5e4d3a6
    }
    @RequestMapping(value = "/client/send")
    public void test4() throws Exception {
        nettyBootStrapc.sendMessage("1234");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String reg(HttpServletRequest request,Model model) {
        model.addAttribute("time",System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat();
        System.out.println(format.format(new Date()));
        System.out.println(format.format(System.currentTimeMillis()));
        model.addAttribute("date",format.format(new Date()));
        model.addAttribute("date1",format.format(System.currentTimeMillis()));
        return "login/register";
    }

    @ResponseBody
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public ResultEntity register(HttpServletRequest request) {

        try {

            String username = getParamVal(request, "username");
            String password = getParamVal(request, "password");

            logger.info("username: {}, password: {}", username, password);
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return ResultEntity.newErrEntity("请提交完整信息");
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(AESencryptor.encryptCBCPKCS5Padding(password));

            User rst = userService.saveUser(user);
            if (rst == null) {
                return ResultEntity.newErrEntity("添加帐号失败");
            } else {
                return ResultEntity.newResultEntity(rst);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.newErrEntity();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<String> users = new ArrayList<String>();
        users.add("6");
        users.add("7");

        new SendMsg().send("测试成功",users);

        String username = getParamVal(request, "username");
        String password = getParamVal(request, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ErrcodeException("用户名或密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), AESencryptor.encryptCBCPKCS5Padding(password));
        Subject loginUser = SecurityUtils.getSubject();
        String errmsg = null;

        try {
            loginUser.login(token);
        } catch (UnknownAccountException uae) {
            errmsg = "账户不存在";
        } catch (IncorrectCredentialsException ice) {
            errmsg = "用户名或者密码错误";
        } catch (LockedAccountException lae) {
            errmsg = "账户已锁定,暂时不能登录";
        } catch (ExcessiveAttemptsException eae) {
            errmsg = "错误次数过多,暂时不能登录";
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            ae.printStackTrace();
        }

        if (!loginUser.isAuthenticated()) {
            token.clear();
            if (StringUtils.isEmpty(errmsg)) {
                errmsg = "验证出错,请联系管理员";
            }
            throw new ErrcodeException(errmsg);
        }

        WebUtils.getSavedRequest(request);
        String url = "/";

        if (loginUser.isAuthenticated()) {
            if (loginUser.hasRole(UserRoleType.ROLE_ROOT)) {
//                url = "root/index";
                // todo ...
                url = "device/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_ADMIN)) {
                url = "admin/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_TEACHER)) {
                url = "teacher/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_STUDENT)) {
                url = "student/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_PATRIARCH)) {
                url = "parents/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_BOARD_USER)) {
                url = "device/index";
            }
        }

        System.out.print(loginUser.isPermitted("student:add"));
        String ip = getClientIp(request);
        logger.info("=============client ip is: {}", ip);
        // 登录成功标识
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", getLoginUser());
        return ResultEntity.newResultEntity(url);
    }

    @RequestMapping(value = "/tencent/login/index",method = RequestMethod.GET)
    public String qqlogin(){
        return "login/tencent";
    }

    @RequestMapping(value = "/tencent/login",method = RequestMethod.GET)
    public void save(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html,charset=utf-8");
        String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
        String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

        String content = "username : " + username + "  password : " + password;
        WriteStreamAppendUtil.method3(ConfigHelper.getValueByKey("file.path"), content);
    }
}
*/
