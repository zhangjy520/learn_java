package cc.gukeer.datahub.modeView;


import cc.gukeer.datahub.persistence.entity.RefPlatformApp;

/**
 * Created by lx on 2017/2/24.
 */
public class SyncView {

    private String appName;

    private String platformName;

    private String password;

    private RefPlatformApp refPlatformApp;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public RefPlatformApp getRefPlatformApp() {
        return refPlatformApp;
    }

    public void setRefPlatformApp(RefPlatformApp refPlatformApp) {
        this.refPlatformApp = refPlatformApp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
