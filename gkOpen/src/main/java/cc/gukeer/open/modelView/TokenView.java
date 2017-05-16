package cc.gukeer.open.modelView;

import java.io.Serializable;

public class TokenView implements Serializable {
    private String token;
    private Long tokenTimeStamp;
    private Long expireIn;
    private Object data;

    public TokenView() {
    }

    public TokenView(String token, Long expireIn, Long tokenTimeStamp, Object data) {
        this.token = token;
        this.expireIn = expireIn;
        this.tokenTimeStamp = tokenTimeStamp;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTokenTimeStamp() {
        return tokenTimeStamp;
    }

    public void setTokenTimeStamp(Long tokenTimeStamp) {
        this.tokenTimeStamp = tokenTimeStamp;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
