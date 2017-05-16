package cc.gukeer.syncdata.modeView;

import java.util.List;

/**
 * Created by lx on 2017/4/20.
 */
public class ObjInfo {
    private String pushObjId;
    private List<ObjDetail> objDetail;

    public String getPushObjId() {
        return pushObjId;
    }

    public void setPushObjId(String pushObjId) {
        this.pushObjId = pushObjId;
    }

    public List<ObjDetail> getObjDetail() {
        return objDetail;
    }

    public void setObjDetail(List<ObjDetail> objDetail) {
        this.objDetail = objDetail;
    }
}
