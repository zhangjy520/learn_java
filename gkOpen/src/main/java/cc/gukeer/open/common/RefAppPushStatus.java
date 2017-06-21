package cc.gukeer.open.common;

/**
 * Created by lx on 2016/11/24.
 */
public enum RefAppPushStatus {
    //未推送
    UNPUSH(0),

    //已推送
    PUSHED(1),

    //修改推送
    UPDATE_PUSH(2),

    //下线
    FORBIDDEN(4);


    private final int statenum;

    RefAppPushStatus(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }
}
