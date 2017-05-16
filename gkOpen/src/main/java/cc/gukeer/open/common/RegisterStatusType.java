package cc.gukeer.open.common;

public enum RegisterStatusType {
    //注册未激活
    UNACTIVATE(0),

    //注册并且已经激活
    ACTIVATE(1),

    //已填写详细信息
    WRITE(2);
    private final int statenum;

    RegisterStatusType(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }
}
