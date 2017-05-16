package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.App;
import cc.gukeer.open.persistence.entity.RefPlatformApp;
import com.github.pagehelper.PageInfo;

/**
 * Created by LL on 2017/2/24.
 */
public class RefPlatformAppExtention extends RefPlatformApp {
    private int checkStatus;
    private App app;
    private AppExtention appExtention;
    private PageInfo<RefPlatformApp> pageInfo;
    private int category;
    private String appName;
    private String appId;
    private RefPlatformApp refPlatformApp;
    public RefPlatformApp getRefPlatformApp() {
        return refPlatformApp;
    }

    public void setRefPlatformApp(RefPlatformApp refPlatformApp) {
        this.refPlatformApp = refPlatformApp;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public PageInfo<RefPlatformApp> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<RefPlatformApp> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public AppExtention getAppExtention() {
        return appExtention;
    }

    public void setAppExtention(AppExtention appExtention) {
        this.appExtention = appExtention;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
