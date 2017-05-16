package cc.gukeer.open.modelView;

import java.io.Serializable;

public class Data implements Serializable {
    private Object user;
    private Object other;

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
