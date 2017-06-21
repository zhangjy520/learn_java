package cc.gukeer.open.common;

/**
 * Created by LL on 2017/1/23.
 */
public enum MessageIsRead {
    //未读
    UNREAD(0),

    //已读
    READED(1);
    private  final int statenum;
    public int getStatenum() {
        return statenum;
    }
    MessageIsRead(int statenum){
        this.statenum=statenum;
    };
}
