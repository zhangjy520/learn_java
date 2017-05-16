package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.ScanLog;

public class LogDetailView extends ScanLog {
    private String areaName;
    private Long leavingTime;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Long leavingTime) {
        this.leavingTime = leavingTime;
    }
}
