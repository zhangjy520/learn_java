package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.DeviceRing;

import java.io.Serializable;

/**
 * Created by pc-daisike on 2016/12/9.
 */
public class RingView extends DeviceRing implements Serializable {
    protected String stName;
    protected String className;
    protected String xdName;
    protected Integer nj;
    protected String xh;
    private static final long serialVersionUID = 1L;

    public String getstName() {
        return stName;
    }


    public void setstName(String stName) {
        this.stName = stName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getXdName() {
        return xdName;
    }

    public void setXdName(String xdName) {
        this.xdName = xdName;
    }

    public Integer getNj() {
        return nj;
    }

    public void setNj(Integer nj) {
        this.nj = nj;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }
}
