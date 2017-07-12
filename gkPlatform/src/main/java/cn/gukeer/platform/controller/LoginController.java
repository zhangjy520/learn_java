package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.common.entity.ResultEntity;
import cn.gukeer.common.security.AESencryptor;
import cn.gukeer.common.security.MD5Utils;
import cn.gukeer.common.tld.GukeerStringUtil;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.common.utils.SnsUtil;
import cn.gukeer.platform.common.UserRoleType;
import cn.gukeer.platform.persistence.entity.App;
import cn.gukeer.platform.persistence.entity.Config;
import cn.gukeer.platform.persistence.entity.School;
import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.service.AppService;
import cn.gukeer.platform.service.SchoolService;
import cn.gukeer.platform.service.UserService;
import com.github.pagehelper.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


@Controller
public class LoginController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    AppService appService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String reg(HttpServletRequest request) {
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
            user.setId(PrimaryKey.get());
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
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        StringBuffer tempPathString = request.getRequestURL();
        String test = request.getRequestURI();
        Integer start = "http://".length();
        String _end = "";
        try {
            Properties p = new Properties();
            InputStream inStream = new ClassPathResource("/db.properties").getInputStream();
            p.load(inStream);
            _end = p.getProperty("develop_path");
        } catch (Exception e1) {
            _end = "";
        }
        if (StringUtil.isEmpty(_end)) {
            _end = ".gukeyun.cn";
        }

        Integer end = tempPathString.indexOf(_end);
        String schoolUrl = "";
        if (end > 0 && end > start) {
            schoolUrl = tempPathString.substring(start, end);
            School school = schoolService.selectSchoolByWholeUrl(schoolUrl);
            if (school != null) {
                model.addAttribute("logo", school.getLogo());
                model.addAttribute("bgPicture", school.getBgPicture());
                model.addAttribute("shortFlag", school.getShortFlag());
            }
        }

        List<Config> config = schoolService.selectConfigByType("bottomText");
        if (config.size() > 0) {
            model.addAttribute("bottomText", config.get(0).getParamValue());
        }
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String username = getParamVal(request, "username");
        String password = getParamVal(request, "password");
        String remember = getParamVal(request, "remember");
        String shortFlag = getParamVal(request, "shortFlag");

        if (!username.equalsIgnoreCase("root") && !shortFlag.equals("@"))
            username = username + shortFlag;


        UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), AESencryptor.encryptCBCPKCS5Padding(password));
        Subject loginUser = SecurityUtils.getSubject();
        String errmsg = null;
        if ("1".equals(remember)) {
            token.setRememberMe(true);
        }
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
            ae.printStackTrace();
        }
        if (!loginUser.isAuthenticated()) {
            token.clear();
            if (StringUtils.isEmpty(errmsg)) {
                errmsg = "验证出错,请联系管理员";
            }
            return ResultEntity.newErrEntity(errmsg);
        }

        WebUtils.getSavedRequest(request);
        String url = "/";
        if (loginUser.isAuthenticated()) {
            if (loginUser.hasRole(UserRoleType.ROLE_ROOT)) {
                url = "school/index";
            } else if (loginUser.hasRole(UserRoleType.ROLE_ADMIN)) {
                url = "admin/index";
            } else {
                url = "home/index";
            }
        }

        String ip = getClientIp(request);
        logger.info("=============client ip is: {}", ip);
        // 登录成功标识
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", getLoginUser());

        return ResultEntity.newResultEntity(url);
    }

    //此方法待修改为根据开发者来判断
    @RequestMapping(value = "/third/party/page")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        String appId = getParamVal(request, "appId");
        String backUrl = getParamVal(request, "backUrl");

        String appName = null;
        String url = null;
        User user = getLoginUser();

        App app = appService.findAppById(appId);
        if (!GukeerStringUtil.isNullOrEmpty(app)) {
            appName = app.getName();
            url = app.getWebUrl() + '?';
            if ("慕课直播".equals(appName)) {
                url += SnsUtil.getMoocLoginParams(user.getUsername());//慕课url
            } else if ("智能穿戴".equals(appName)) {
                url += SnsUtil.getThirdPartyParams(user.getSchoolId(), appId, user.getRefId(),user.getUserType(), String.valueOf(System.currentTimeMillis()));
            } else {
                //这里是第三方应用，参照api进行登录态验证.
                String snsMac = MD5Utils.md5(SnsUtil.KEY + user.getUsername());
                url += user.getUsername() + "=" + snsMac + "&picUrl=" + backUrl + "/file/pic/show?picPath=" + user.getPhotoUrl();//sns
            }
        }

        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:" + url);
        return view;
    }


}
