package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.utils.PrimaryKey;
import cc.gukeer.common.utils.RedisUtil;
import cc.gukeer.open.common.CheckStateType;
import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.common.RegisterStatusType;
import cc.gukeer.open.persistence.entity.Dynamic;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.DynamicService;
import cc.gukeer.open.service.OpenUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BasicController {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    OpenUserService openUserService;
    @Autowired
    DynamicService dynamicService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String userName = cookie.getName();
                String pass = cookie.getName();
            }
        }

            List<Dynamic> dynamicList = dynamicService.findAllDynamic();
            List<Dynamic> list = new ArrayList<>();
            String aa = request.getParameter("exception");
            if (dynamicList != null && dynamicList.size() > 2) {
                list.add(dynamicList.get(0));
                list.add(dynamicList.get(1));
                list.add(dynamicList.get(2));

            } else {
                for (Dynamic dynsmic : dynamicList) {
                    list.add(dynsmic);
                }
            }
            request.setAttribute("list", list);
            return "/index";
        }

        @ResponseBody
        @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
        public ResultEntity login (HttpServletRequest request, HttpServletResponse response){
            String username = getParamVal(request, "username");
            String password = getParamVal(request, "password");
            String remember = getParamVal(request, "remember");
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return ResultEntity.newErrEntity("用户名或密码不能为空");
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), AESencryptor.encryptCBCPKCS5Padding(password), true);

            Subject subject = SecurityUtils.getSubject();
            String errmsg = null;
            try {
                subject.login(token);
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


            if (!subject.isAuthenticated()) {
                token.clear();
                if (StringUtils.isEmpty(errmsg)) {
                    errmsg = "验证出错,请联系管理员";
                }
                return ResultEntity.newErrEntity(errmsg);
        /*    throw new ErrcodeException(errmsg);*/
            }
            WebUtils.getSavedRequest(request);
            String ip = getClientIp(request);
            logger.info("=============client ip is: {}", ip);

            OpenUser openUser = (OpenUser) subject.getPrincipal();

            int register_status = openUser.getRegisterStatus();

            String tokenForCookie = PrimaryKey.get();
            if ("1".equals(remember)) {
                token.setRememberMe(true);

                Cookie cookie1 = new Cookie("SESSION_LOGIN_NAME",tokenForCookie );
                cookie1.setPath("/gkOpen");
                cookie1.setMaxAge(999999);
                response.addCookie(cookie1);

                openUser.setLoginMark(tokenForCookie);
                openUserService.updateOpenUserById(openUser,openUser.getId());
            }
            subject.getSession().setAttribute("openUser", openUser);
            request.getSession().setAttribute("status", openUser.getStatus());


            String url = "/user/index";
            if (openUser.getStatus() == CheckStateType.FORBIDDEN.getStatenum()) {
                return ResultEntity.newErrEntity("出于安全原因，您的账号已被锁定，若需解锁，请联系管理员：400-100-1111");
            }
            if (subject.hasRole(LoginUserType.ROLE_ADMIN)) {
                url = "/admin/index";
            }

            if (register_status == RegisterStatusType.UNACTIVATE.getStatenum()) {
                return ResultEntity.newResultEntity("前往邮箱激活账号后登陆", request.getContextPath() + "/register/emailcheck?username=" + username + "&id=" + openUser.getId());
            }
            return ResultEntity.newResultEntity(request.getContextPath() + url);
        }

        @RequestMapping(value = "/registerLogin")
        public String registerLoginlogin (HttpServletRequest request){

            String username = getParamVal(request, "username");
            String password = getParamVal(request, "password");
            UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            WebUtils.getSavedRequest(request);
            String ip = getClientIp(request);
            logger.info("=============client ip is: {}", ip);
            OpenUser openUser = (OpenUser) subject.getPrincipal();
            int register_status = openUser.getRegisterStatus();
            subject.getSession().setAttribute("openUser", openUser);
            request.getSession().setAttribute("status", openUser.getStatus());
            return "redirect:/manager/index";
        }

    }
