package cc.gukeer.smartRing.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.exception.ErrcodeException;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.tld.GukeerStringUtil;
import cc.gukeer.common.utils.*;
import cc.gukeer.smartRing.persistence.dao.SchoolMapper;
import cc.gukeer.smartRing.persistence.entity.User;
import cc.gukeer.smartRing.service.SportsTestService;
import cc.gukeer.smartRing.service.UserService;
import com.github.pagehelper.PageInfo;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    SportsTestService sportsTestService;

    @Autowired
    SchoolMapper schoolMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request, HttpServletResponse response) {

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
            return ResultEntity.newErrEntity(errmsg);
           /* if (StringUtils.isEmpty(errmsg)) {
                errmsg = "验证出错,请联系管理员";
            }
            throw new ErrcodeException(errmsg);*/
        }

        WebUtils.getSavedRequest(request);
        String url = "/";

        if (loginUser.isAuthenticated()) {
            url = "index";
        }

        String ip = getClientIp(request);
        logger.info("=============client ip is: {}", ip);
        // 登录成功标识
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", getLoginUser());
        return ResultEntity.newResultEntity(url);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        int pageSize = getPageSize(request);
        int pageNum = getPageNum(request);

        User user = getLoginUser();
        Map paramHealth = new HashMap();
        paramHealth.put("classId", sportsTestService.getClassByTeacher(user.getRefId()));
        paramHealth.put("schoolId", user.getSchoolId());

        paramHealth.put("whichDay", 0);
        Map healthLatest = sportsTestService.getGlobalDaily(paramHealth);//最新一次成绩
        paramHealth.put("whichDay", 1);
        Map healthBeforeLatest = sportsTestService.getGlobalDaily(paramHealth);//最新成绩上一次成绩

        Map poorThen = new HashMap();
        if (healthBeforeLatest != null && healthLatest != null) {
            Double sportPoor = NumberConvertUtil.sub(healthLatest.get("sportTime").toString(),
                    healthBeforeLatest.get("sportTime").toString());
            Double asleepPoor = NumberConvertUtil.sub(healthLatest.get("asleepTime").toString(),
                    healthBeforeLatest.get("asleepTime").toString());
            Double sleepLongPoor = NumberConvertUtil.sub(healthLatest.get("sleepLong").toString(),
                    healthBeforeLatest.get("sleepLong").toString());
            poorThen.put("sportPoor", sportPoor);
            poorThen.put("asleepPoor", asleepPoor);
            poorThen.put("sleepLongPoor", sleepLongPoor);
        }

        paramHealth.put("pageSize", pageSize);
        paramHealth.put("pageNum", pageNum);
        PageInfo<Map> pageInfo = sportsTestService.getGlobalSport(paramHealth);
        List<Map> sportList = pageInfo.getList();

        List<Map> standard = sportsTestService.getTeacherIdentify(user.getSchoolId().toString(),
                user.getRefId().toString());
        Map standardMap = new HashMap();
        if (standard.size() == 1) {
            standardMap.put("sportStandard", standard.get(0).get("sportStandard"));
            standardMap.put("asleepStandard", standard.get(0).get("asleepStandard"));
            standardMap.put("sleepLongStandard", standard.get(0).get("sleepStandard"));
        }
        model.addAttribute("standard", standard);
        model.addAttribute("standardMap", standardMap);
        model.addAttribute("poorThen", poorThen);
        model.addAttribute("healthLatest", healthLatest);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sportList", sportList);
        return "index";
    }

    @RequestMapping(value = "/login/status", method = RequestMethod.GET)
    public String loginStatus(HttpServletRequest request,Model model) throws IOException, ServletException {
        String platformId = getParamVal(request, "platform_id");
        String appId = getParamVal(request, "app_id");
        String userId = getParamVal(request, "user_id");
        String timeStamp = getParamVal(request, "timeStamp");
        String sign = getParamVal(request, "sign");
        String key = PropertiesUtil.getProperties("/db.properties").get("login.status.key").toString();

        String _sign = MD5Util.go(platformId + appId + userId + timeStamp + key);
        String msg = null;
        //单位毫秒
        if (System.currentTimeMillis() - NumberConvertUtil.convertS2L(timeStamp) > 60 * 1000 * 30){
            //throw new ErrcodeException("登录状态超时,请重新登录");//30分钟
            msg = "登录状态超时,请重新登录";
            return "login/login";
        }
        if (!sign.equals(_sign)){
            //throw new ErrcodeException("签名无效");
            msg = "签名无效";
            return "login/login";
        }

        User user = userService.getUserById(userId);
        if (GukeerStringUtil.isNullOrEmpty(user)){
//            throw new ErrcodeException("无效的用户");
            msg = "无效的用户";
            return "login/login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject loginUser = SecurityUtils.getSubject();
        loginUser.login(token);
        return index(request, model);

    }


    //测试授权登录接口
    @RequestMapping(value = "/oauth/redirect/url",method = RequestMethod.GET)
    public String redirectUrl(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //拿code的回调
        String code = getParamVal(request,"code");
        String expire = getParamVal(request,"expires_in");
        String client_id = "c1ebe466-1cdc-4bd3-ab69-77c3561b9dee";
        String client_secret ="d8346ea2-6017-43ed-ad68-19c0f971738b";
        String grant_type = "authorization_code";
        String redirect_uri = "http://localhost:8585/smartRing/oauth/redirect/url";


        //获取accesstoken
        String url = "http://localhost:8909/open/accessToken";

        Map entity = new HashMap();
        entity.put("client_id",client_id);
        entity.put("client_secret",client_secret);
        entity.put("grant_type",grant_type);
        entity.put("redirect_uri",redirect_uri);
        entity.put("code",code);

        String test = HttpRequestUtil.post(url,null,null,entity);
        Map map = GsonUtil.fromJson(test,Map.class);
        String accessToken = map.get("access_token").toString();
        String refreshToken = map.get("refresh_token").toString();
        String tokenExpire = map.get("expires_in").toString();

        //获取userInfo
        String urlForUserInfo = "http://localhost:8909/open/userInfo";
        Map mapForUserInfo = new HashMap();
        mapForUserInfo.put("access_token",accessToken);
        String userInfo = HttpRequestUtil.get(urlForUserInfo,null,mapForUserInfo);
        Map res = GsonUtil.fromJson(userInfo,Map.class);
        Map data = (Map) res.get("data");
        Map user = (Map) data.get("user");

        //关联 登入系统
        UsernamePasswordToken token = new UsernamePasswordToken(user.get("username").toString(), user.get("password").toString());
        Subject loginUser = SecurityUtils.getSubject();
        loginUser.login(token);

        return "index";
    }

}
