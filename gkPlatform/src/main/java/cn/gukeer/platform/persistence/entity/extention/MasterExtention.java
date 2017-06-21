package cn.gukeer.platform.persistence.entity.extention;

import java.util.Iterator;

/**
 * Created by LL on 2017/4/24.
 */
public class MasterExtention {
    private String xdId;
    private Integer nj;
    private String bj;
    private String classId;
    private String masterNo;
    private String deputyNo;
    private String masterId;
    private String deputyId;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getDeputyId() {
        return deputyId;
    }

    public void setDeputyId(String deputyId) {
        this.deputyId = deputyId;
    }

    public String getMasterNo() {
        return masterNo;
    }

    public void setMasterNo(String masterNo) {
        this.masterNo = masterNo;
    }

    public String getDeputyNo() {
        return deputyNo;
    }

    public void setDeputyNo(String deputyNo) {
        this.deputyNo = deputyNo;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getXdId() {
        return xdId;
    }

    public void setXdId(String xdId) {
        this.xdId = xdId;
    }
}
