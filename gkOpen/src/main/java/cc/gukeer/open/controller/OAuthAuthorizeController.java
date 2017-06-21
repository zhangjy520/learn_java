package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.security.AESencryptor;
import cc.gukeer.common.utils.CommonUtils;
import cc.gukeer.common.utils.Constants;
import cc.gukeer.common.utils.GlobalUtils;
import cc.gukeer.open.modelView.CodeView;
import cc.gukeer.open.persistence.entity.ChangeStateUser;
import cc.gukeer.open.service.ClientService;
import cc.gukeer.open.service.OAuthService;
import cc.gukeer.open.service.OAuthUserService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class OAuthAuthorizeController extends BasicController {

    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OAuthUserService oauthUserService;

    @RequestMapping("/authLogin/authLogin")
    public String loginPage(HttpServletRequest request,Model model){
        model.addAttribute("client",request.getParameter("client"));
        return "authLogin/authLogin";
    }

    @ResponseBody
    @RequestMapping("/authorize")
    public Object authorize(
            Model model,
            HttpServletRequest request,
            HttpServletResponse resp)
            throws URISyntaxException, OAuthSystemException, IOException {

        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            String clientId = oauthRequest.getClientId();
            //检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(clientId)) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                //return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
                ResultEntity res = ResultEntity.newResultEntity(null);
                res.setMsg(response.getBody());
                return res;
            }

            //如果用户没有登录，跳转到登陆页面
//            Object obj = oAuthService.getCacheByKey(AESencryptor.encryptCBCPKCS5Padding(Constants.OAUTH_USER_CACHE_KEY+clientId));
            Object obj = getSubject(request.getSession(),clientId);
            if (GlobalUtils.isNullOrEmptyObj(obj)){
                if (!login(request,clientId)) {
                    //String source = request.getParameter("source");
                    /* if (StringUtils.isEmpty(source) || "PC".equalsIgnoreCase(source)){
                         Map map = new HashMap();
                         map.put("login",false);
                         return ResultEntity.newResultEntity(map);//直接返回json字符串
                     }else if("APP".equalsIgnoreCase(source)){
                         Map map = new HashMap();
                         map.put("login",false);
                         return ResultEntity.newResultEntity(map);//直接返回json字符串
                     }*/
                    Map map = new HashMap();
                    map.put("login",false);
                    return ResultEntity.newResultEntity(map);//直接返回json字符串
                }
            }

           // SysUser user = null;// 授权登录应该为sys_user [第三方用户，从云平台过来]，所以不能和直接用 getLoginUser() 获取登录用户;

            //生成授权码
            String authorizationCode = null;
            Long codeExpireIn = oAuthService.getExpireIn(OAuth.OAUTH_CODE);
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();

                //这里判断scope来决定存储什么信息
                obj = getSubject(request.getSession(),clientId);//再次获取session里存储的用户信息
                oAuthService.addCache(authorizationCode, new CodeView(authorizationCode, codeExpireIn, System.currentTimeMillis(), obj));
                //oAuthService.addCache(authorizationCode, new CodeView(authorizationCode, codeExpireIn, System.currentTimeMillis(), "存储的用户信息"));
            }

            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码,授权码过期时间。
            builder.setCode(authorizationCode);
            builder.setExpiresIn(codeExpireIn);

            //得到到客户端重定向地
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

            //根据OAuthResponse返回ResponseEntity响应
           // HttpHeaders headers = new HttpHeaders();
           // headers.setLocation(new URI(response.getLocationUri()));

            Map map = new HashMap();
            map.put("access_code", authorizationCode);
            map.put("expire", codeExpireIn);
            map.put("status", HttpStatus.OK);
            map.put("login",true);
            return ResultEntity.newResultEntity(map);//直接返回json字符串

           // return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));//访问redirect_uri并且传回code等参数
        } catch (OAuthProblemException e) {

            //出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                //告诉客户端没有传入redirectUri直接报错
                //return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
                ResultEntity res = ResultEntity.newResultEntity(null);
                res.setMsg("OAuth callback url needs to be provided by client!!!");
                return res;
            }
            //返回错误消息
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                            .error(e).location(redirectUri).buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
//            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
            ResultEntity res = ResultEntity.newResultEntity(null);
            return res;
        }
    }

    private boolean login(HttpServletRequest request,String clientId) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }

        ChangeStateUser user = oauthUserService.getByAccountAndPwd(username, AESencryptor.encryptCBCPKCS5Padding(password));
        if (GlobalUtils.isNullOrEmptyObj(user)){
            return false;
        }else {
            //将登录的用户存在cache中,键为 特定key+clientId
            request.getSession().setAttribute(AESencryptor.encryptCBCPKCS5Padding(username+password+clientId),user);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("password",password);
//            oAuthService.addCache(AESencryptor.encryptCBCPKCS5Padding(Constants.OAUTH_USER_CACHE_KEY+clientId),user);
            return true;
        }
    }
    public Object getSubject(HttpSession session,String clientId){
        Object username = session.getAttribute("username");
        Object password = session.getAttribute("password");
        Object obj = session.getAttribute(AESencryptor.encryptCBCPKCS5Padding(CommonUtils.getString(username)+CommonUtils.getString(password)+clientId));
        return obj;
    }
/*
private boolean login(Subject subject, HttpServletRequest request) {

if("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username.toLowerCase(), AESencryptor.encryptCBCPKCS5Padding(password));
        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("error", "登录失败:" + e.getClass().getName());
            return false;
        }
    }*/


}
