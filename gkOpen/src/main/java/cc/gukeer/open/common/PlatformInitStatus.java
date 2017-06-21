package cc.gukeer.open.common;

/**
 * Created by LL on 2017/1/23.
 */
public enum PlatformInitStatus {
    //未初始化
    UNINIT(0),

    //初始化
    INITED(1),

    //失败
    FAIL_INIT(2);

    private  final int statenum;
    public int getStatenum() {
        return statenum;
    }
    PlatformInitStatus(int statenum){
        this.statenum=statenum;
    };
}
