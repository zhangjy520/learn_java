package cn.gukeer.platform.modelView;

import cn.gukeer.platform.persistence.entity.ClassCard;

/**
 * Created by alpha on 17-6-27.
 */
public class ClassCardView extends ClassCard {
    private String locationName;
    private String xd;
    private String nj;

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
