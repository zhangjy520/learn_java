package cc.gukeer.open.common;

/**
 * Created by lx on 2016/11/24.
 */
public enum CheckStateType {
    //未提交审核状态 就是在注册的时候就默认为0
    UNAUDIT(0),

    //已提交待审核状态  就是提交了详细信息之后
    AUDITING(1),

    //审核成功
    AUDIT_SUCCESS(2),

    //审核失败
    AUDIT_FAIL(3),

    //下线
    FORBIDDEN(4),

    //修改待审核
    UPDATE_AUDITING(5);

    private final int statenum;

    CheckStateType(int statenum){
        this.statenum = statenum;
    }

    public int getStatenum() {
        return statenum;
    }
}
