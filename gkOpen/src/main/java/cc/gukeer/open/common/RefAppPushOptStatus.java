package cc.gukeer.open.common;

/**
 * Created by lx on 2016/11/24.
 */
public enum RefAppPushOptStatus {
    //推送失败
    FAIL(0),

    //成功
    SUCC(1);

    private final int statenum;

    RefAppPushOptStatus(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }
}
