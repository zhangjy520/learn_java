package cc.gukeer.open.service;


public interface OAuthService {

    public void addCache(String key, Object value);

    public void removeCache(String key);

    Object getCacheByKey(String key);

    //验证auth code是否有效
    boolean checkAuthCode(String authCode);

    //验证 token是否有效
    boolean checkToken(String token);
/*
    //验证 refresh token是否有效
    boolean checkRefreshToken(String refreshToken);*/

    //auth code / access token 过期时间
    long getExpireIn(String expireInType);

    public boolean checkClientId(String clientId);

    public boolean checkClientSecret(String clientId,String clientSecret);


}
