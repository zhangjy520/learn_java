package cc.gukeer.open.common;

/**
 * Created by lx on 2016/11/25.
 */
public enum LoginUserType {

    //个人用户
    PERSONAL(0),
    //公司用户
    COMPANY(1),
    //管理员
    ADMIN(2);

    public static final String ROLE_ADMIN = "admin";
    private final int statenum;
    LoginUserType(int statenum){
        this.statenum = statenum;
    }
    public int getStatenum() {
        return statenum;
    }

}
