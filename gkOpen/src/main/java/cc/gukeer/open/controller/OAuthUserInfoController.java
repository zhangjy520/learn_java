package cc.gukeer.open.controller;

import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.Constants;
import cc.gukeer.open.modelView.Data;
import cc.gukeer.open.modelView.ResponseData;
import cc.gukeer.open.modelView.TokenView;
import cc.gukeer.open.service.OAuthService;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class OAuthUserInfoController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "/userInfo")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
        try {

            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            //获取Access Token
            String accessToken = oauthRequest.getAccessToken();

            //验证Access Token
            if (!oAuthService.checkToken(accessToken)) {
                // 如果不存在或者过期了，返回token错误
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(Constants.RESOURCE_SERVER_NAME)
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .setErrorDescription("无效的access_token")
                        .buildJSONMessage();

                return new ResponseEntity(oauthResponse.getBody(), HttpStatus.valueOf(oauthResponse.getResponseStatus()));
            }
            //返回对象
            TokenView data = (TokenView) oAuthService.getCacheByKey(accessToken);
            ResponseData responseData = new ResponseData();
            responseData.setCode("0");//设置响应码
            responseData.setMsg("ok");

            Data dataUser = new Data();
            dataUser.setUser(data.getData());//设置获取的对象
            dataUser.setOther("预留信息");//返回的其他信息，预留

            responseData.setData(dataUser);
            return new ResponseEntity(responseData, HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //�?查是否设置了错误�?
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(Constants.RESOURCE_SERVER_NAME)
                        .buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }

            OAuthResponse oauthResponse = OAuthRSResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setRealm(Constants.RESOURCE_SERVER_NAME)
                    .setError(e.getError())
                    .setErrorDescription(e.getDescription())
                    .setErrorUri(e.getUri())
                    .buildHeaderMessage();

            HttpHeaders headers = new HttpHeaders();
            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/login/check")
    public Object checkLoginStatus(HttpServletRequest request) throws OAuthSystemException {
        String accessToken = request.getParameter("accessToken");
//        Object obj = oAuthService.getCacheByKey(accessToken);
        boolean flag = oAuthService.checkToken(accessToken);
        Map res = new HashMap<>();
        if (flag){
            res.put("login",true);
        }else {
            res.put("login",false);
        }
        ResultEntity result = ResultEntity.newResultEntity(res);
        return result;
    }
}
