package cc.gukeer.smartRing.modelView.importExport;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

/**
 * Created by pc-daisike on 2016/12/15.
 */
public class ImportBundling {
    private String mac;
    private String xh;
    private String msg;

    @ExcelField(title = "手环编号", align = 0, sort = 1, groups = {1, 2})
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @ExcelField(title = "学号", align = 0, sort = 2, groups = {1, 2})
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
