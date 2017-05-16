package cc.gukeer.smartRing.modelView.importExport;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2017/1/6.
 */
public class ImportUser implements Serializable {
    private String no;
    private String roleName;
    private String xd;
    private String nj;
    private String classId;
    private String msg;

    @ExcelField(title = "教工号", align = 0, sort = 1, groups = {1, 2})
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @ExcelField(title = "角色", align = 0, sort = 2, groups = {1, 2})
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ExcelField(title = "学段", align = 0, sort = 3, groups = {1, 2})
    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    @ExcelField(title = "年级", align = 0, sort = 4, groups = {1, 2})
    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    @ExcelField(title = "班级", align = 0, sort = 5, groups = {1, 2})
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
