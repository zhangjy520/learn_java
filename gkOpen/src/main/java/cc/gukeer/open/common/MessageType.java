package cc.gukeer.open.common;

/**
 * Created by LL on 2017/1/23.
 */
public enum MessageType {
    //用户
    USER(0),

    //app
    APP(1);
    private  final int statenum;
    public int getStatenum() {
        return statenum;
    }
    MessageType(int statenum){
        this.statenum=statenum;
    };
}
