package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.Platform;

/**
 * Created by LL on 2017/2/24.
 */
public class PlatformExtention extends Platform {
    private  int appOptStatus;
    private  Platform platform;
    private int appStatus;
    private int exist;

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }

    public int getAppOptStatus() {
        return appOptStatus;
    }

    public void setAppOptStatus(int appOptStatus) {
        this.appOptStatus = appOptStatus;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
