package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.DeviceStation;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/19.
 */
public class StationView extends DeviceStation implements Serializable {
    private String areaName;

    private String xd;

    private Integer nj;

    private String bj;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }
}
