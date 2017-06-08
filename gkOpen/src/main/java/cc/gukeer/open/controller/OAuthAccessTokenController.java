package cc.gukeer.open.controller;

import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.Constants;
import cc.gukeer.open.modelView.CodeView;
import cc.gukeer.open.modelView.TokenView;
import cc.gukeer.open.service.ClientService;
import cc.gukeer.open.service.OAuthService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuthAccessTokenController {
    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private ClientService clientService;

    @ResponseBody
    @RequestMapping(value = "/accessToken", method = RequestMethod.POST)
    public Object token(Model model, HttpServletRequest request) throws URISyntaxException, OAuthSystemException {

        String clientId = request.getParameter(OAuth.OAUTH_CLIENT_ID);
        String clientSecret = request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
        String authCode = request.getParameter(OAuth.OAUTH_CODE);
        String grantType = request.getParameter(OAuth.OAUTH_GRANT_TYPE);

        //OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
        if (!oAuthService.checkClientId(clientId)) {
            ResultEntity res = ResultEntity.newResultEntity(null);
            res.setMsg(Constants.INVALID_CLIENT_DESCRIPTION);
            res.setCode(1);
            return res;
        }
        if (!oAuthService.checkClientSecret(clientId, clientSecret)) {
            ResultEntity res = ResultEntity.newResultEntity(null);
            res.setMsg(Constants.INVALID_CLIENT_DESCRIPTION);
            res.setCode(1);
            return res;
        }
        if (grantType.equals(GrantType.AUTHORIZATION_CODE.toString())) {
            if (!oAuthService.checkAuthCode(authCode)) {
                ResultEntity res = ResultEntity.newResultEntity(null);
                res.setMsg("无效的access_code");
                res.setCode(1);
                return res;
            }
        }
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        final String accessToken = oauthIssuerImpl.accessToken();
        final String refreshToken = oauthIssuerImpl.refreshToken();
        final Long tokenExpiresIn = oAuthService.getExpireIn(OAuth.OAUTH_ACCESS_TOKEN);
        final Long refreshTokenExpiresIn = oAuthService.getExpireIn(OAuth.OAUTH_REFRESH_TOKEN);
        final CodeView code = (CodeView) oAuthService.getCacheByKey(authCode);
        oAuthService.addCache(accessToken,
                new TokenView(accessToken, tokenExpiresIn, System.currentTimeMillis(), code.getData()));
        oAuthService.addCache(refreshToken,
                new TokenView(refreshToken, refreshTokenExpiresIn, System.currentTimeMillis(), code.getData()));
        OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                .setAccessToken(accessToken).setRefreshToken(refreshToken)
                .setExpiresIn(String.valueOf(tokenExpiresIn))
                .buildJSONMessage();

        Map data = new HashMap();
        data.put("access_token",accessToken);
        data.put("refresh_token",refreshToken);
        data.put("expires_in",tokenExpiresIn);
        ResultEntity res = ResultEntity.newResultEntity(data);
        return res;
    }

    @ResponseBody
    @RequestMapping("/refreshToken")
    public Object refreshToken(HttpServletRequest request) throws OAuthSystemException {
        String refreshToken = request.getParameter(OAuth.OAUTH_REFRESH_TOKEN);
        if (!oAuthService.checkToken(refreshToken)) {
            ResultEntity res = ResultEntity.newResultEntity(null);
            res.setMsg("无效的refresh_token");
            res.setCode(1);
            return res;
        } else {
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();
            final String newRefreshToken = oauthIssuerImpl.refreshToken();
            final Long tokenExpiresIn = oAuthService.getExpireIn(OAuth.OAUTH_ACCESS_TOKEN);
            final Long refreshTokenExpiresIn = oAuthService.getExpireIn(OAuth.OAUTH_REFRESH_TOKEN);
            final TokenView token = (TokenView) oAuthService.getCacheByKey(refreshToken);
            oAuthService.addCache(accessToken,
                    new TokenView(accessToken, tokenExpiresIn, System.currentTimeMillis(), token.getData()));
            oAuthService.addCache(newRefreshToken,
                    new TokenView(newRefreshToken, refreshTokenExpiresIn, System.currentTimeMillis(), token.getData()));
            OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken).setRefreshToken(refreshToken)
                    .setExpiresIn(String.valueOf(tokenExpiresIn))
                    .buildJSONMessage();
//            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            Map data = new HashMap();
            data.put("access_token",accessToken);
            data.put("refresh_token",refreshToken);
            data.put("expires_in",tokenExpiresIn);
            ResultEntity res = ResultEntity.newResultEntity(data);
            return res;
        }
    }

}
