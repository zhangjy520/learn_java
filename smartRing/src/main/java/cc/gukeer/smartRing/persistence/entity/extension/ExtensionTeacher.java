package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.Teacher;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/15.
 */
public class ExtensionTeacher extends Teacher implements Serializable {
    private String roleName;
    private Integer rid;
    private Integer userId;
    private static final long serialVersionUID = 1L;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
