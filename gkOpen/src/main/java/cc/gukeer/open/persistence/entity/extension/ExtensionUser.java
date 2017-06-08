package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.OpenUser;

import java.io.Serializable;

/**
 * Created by conn on 16-10-11.
 */
public class ExtensionUser extends OpenUser implements Serializable {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
