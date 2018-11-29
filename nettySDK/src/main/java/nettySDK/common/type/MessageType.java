package nettySDK.common.type;

/**
 * Created by LL on 2017/2/16.
 */
public enum MessageType {
    //
    register(11101),
    //
    message(11102);

    private final int statenum;

    MessageType(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }

}
