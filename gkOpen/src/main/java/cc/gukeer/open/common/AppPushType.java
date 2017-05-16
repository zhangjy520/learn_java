package cc.gukeer.open.common;

/**
 * Created by LL on 2017/2/16.
 */
public enum AppPushType {
    //未推送到其他平台
    UNPUSH(0),
    //已经推送
    PUSHED(1);
    private final int statenum;

    AppPushType(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }

}
