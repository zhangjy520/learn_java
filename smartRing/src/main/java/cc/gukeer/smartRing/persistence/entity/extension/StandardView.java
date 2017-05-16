package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.HealthyStandard;

/**
 * Created by pc-daisike on 2016/12/22.
 */
public class StandardView extends HealthyStandard {
    private String xdName;

    public String getXdName() {
        return xdName;
    }

    public void setXdName(String xdName) {
        this.xdName = xdName;
    }
}
