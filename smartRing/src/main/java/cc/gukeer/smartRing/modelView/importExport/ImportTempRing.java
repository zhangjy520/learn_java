package cc.gukeer.smartRing.modelView.importExport;

import cc.gukeer.common.utils.excel.annotation.ExcelField;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/13.
 */
public class ImportTempRing implements Serializable {
    private String mac;

    private String msg;

    @ExcelField(title = "手环编号", align = 0, sort = 1, groups = {1, 2})
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
