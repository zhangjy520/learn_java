package cc.gukeer.open.modelView;

import java.io.Serializable;

public class CodeView implements Serializable {
    private String code;
    private Long codeTimeStamp;
    private Long expireIn;
    private Object data;

    public CodeView() {
    }

    public CodeView(String code, Long expireIn, Long codeTimeStamp, Object data) {
        this.code = code;
        this.expireIn = expireIn;
        this.codeTimeStamp = codeTimeStamp;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCodeTimeStamp() {
        return codeTimeStamp;
    }

    public void setCodeTimeStamp(Long codeTimeStamp) {
        this.codeTimeStamp = codeTimeStamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }
}
