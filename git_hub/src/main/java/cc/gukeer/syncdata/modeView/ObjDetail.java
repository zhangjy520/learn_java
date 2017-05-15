package cc.gukeer.syncdata.modeView;

import java.util.List;

/**
 * Created by lx on 2017/4/20.
 */
public class ObjDetail {
    private String objId;
    private String objNmae;
    private List<String> columnNames;

    public String getObjNmae() {
        return objNmae;
    }

    public void setObjNmae(String objNmae) {
        this.objNmae = objNmae;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
