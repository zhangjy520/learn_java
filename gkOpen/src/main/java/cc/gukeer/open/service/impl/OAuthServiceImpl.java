package cc.gukeer.open.service.impl;

import cc.gukeer.open.modelView.CodeView;
import cc.gukeer.open.modelView.TokenView;
import cc.gukeer.open.service.ClientService;
import cc.gukeer.open.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Service
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;

    @Autowired
    private ClientService clientService;

    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    public OAuthServiceImpl(){}

    @Override
    public void addCache(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void removeCache(String key) {
        cache.evict(key);
    }

    @Override
    public Object getCacheByKey(String key) {
        Cache.ValueWrapper res = cache.get(key);
        if (res != null) {
            return res.get();
        } else {
            return null;
        }
    }


    @Override
    public boolean checkAuthCode(String authCode) {
        CodeView code = (CodeView) getCacheByKey(authCode);
        if (code != null) {
            if (System.currentTimeMillis() - code.getCodeTimeStamp() > code.getExpireIn() && code.getExpireIn() != 0) {
                cache.evict(authCode);//超时，清除这个code
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean checkToken(String token) {
        TokenView tokenView = (TokenView) getCacheByKey(token);
        if (tokenView != null) {
            if (System.currentTimeMillis() - tokenView.getTokenTimeStamp() > tokenView.getExpireIn() && tokenView.getExpireIn() != 0) {
                cache.evict(token);//超时，清除这个token
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

  /*  @Override
    public boolean checkRefreshToken(String refreshToken) {
        TokenView token = (TokenView) getCacheByKey(refreshToken);
        if (token != null) {
            if (System.currentTimeMillis() - token.getTokenTimeStamp() > token.getExpireIn() && token.getExpireIn() != 0) {
                if (token.getExpireIn() == 0) {
                    return true;
                } else {
                    cache.evict(refreshToken);//超时，清除这个token
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }*/

    @Override
    public boolean checkClientId(String clientId) {
        return clientService.findByClientId(clientId) != null;
    }

    @Override
    public boolean checkClientSecret(String clientId, String clientSecret) {
        return clientService.findByClientSecret(clientId, clientSecret) != null;
    }

    @Override
        public long getExpireIn(String expireInType) {
        Properties p = new Properties();
        InputStream inStream = null;
        try {
            inStream = new ClassPathResource("/oauth2.properties").getInputStream();
            p.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.valueOf(p.getProperty(expireInType))*1000;
    }
}
