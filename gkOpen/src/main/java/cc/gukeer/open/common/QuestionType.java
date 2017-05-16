package cc.gukeer.open.common;

/**
 * Created by LL on 2017/1/23.
 */
public enum QuestionType {
    //常见问题
    COMMON(0),
    //登陆注册问题
    LOGINANDREGISTER(1),
    //应用审核问题
    APP_CHECK(2),
    //用户审核问题
    USER_CHECK(3),
    //用户审核问题
    OTHER(4);
    private  final int statenum;
    public int getStatenum() {
        return statenum;
    }
    QuestionType(int statenum){
        this.statenum=statenum;
    };
}
