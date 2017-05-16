package cc.gukeer.smartRing.modelView.importExport;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by admin on 2017/2/8.
 */
public class ImportStation implements Serializable {
    private String mac;
    private String xxwz;
    private String szqy;
    private String category;
    private String classId;
    private String msg;

    @ExcelField(title = "基站MAC", align = 0, sort = 1, groups = {1, 2}, isnull = 1)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @ExcelField(title = "详细位置", align = 0, sort = 2, groups = {1, 2}, isnull = 1)
    public String getXxwz() {
        return xxwz;
    }

    public void setXxwz(String xxwz) {
        this.xxwz = xxwz;
    }
    @ExcelField(title = "所在区域", align = 0, sort = 3, groups = {1, 2})
    public String getSzqy() {
        return szqy;
    }

    public void setSzqy(String szqy) {
        this.szqy = szqy;
    }

    @ExcelField(title = "基站种类", align = 0, sort = 4, groups = {1,2})
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ExcelField(title = "关联班级", align = 0, sort = 5, groups = {1,2})
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
