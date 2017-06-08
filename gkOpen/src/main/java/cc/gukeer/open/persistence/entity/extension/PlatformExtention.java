package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.Platform;

/**
 * Created by LL on 2017/2/24.
 */
public class PlatformExtention extends Platform {
    private  int appOptStatus;//ref_platform_app中间表中的推送成功与否的状态
    private  Platform platform;
    private int appStatus;//ref_platform_app中间表中的应用推送状态 当设置为5时表示在中间表中没有的状态，仅仅作为页面的显示状态存在
    private int isExist;//0表示在中间表中不存在  1表示存在
    private String appId;
    private String platformId;

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getIsExist() {
        return isExist;
    }

    public void setIsExist(int isExist) {
        this.isExist = isExist;
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
