package cc.gukeer.smartBoard.modelView;


import cc.gukeer.smartBoard.persistence.entity.Role;

import java.io.Serializable;

/**
 * Created by conn on 2016/8/19.
 */
public class RoleView extends Role implements Serializable {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
