package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.Menu;

/**
 * Created by conn on 2016/8/21.
 */
public class MenuView extends Menu {
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
