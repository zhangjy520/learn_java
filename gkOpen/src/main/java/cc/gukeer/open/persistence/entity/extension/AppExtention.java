package cc.gukeer.open.persistence.entity.extension;

import cc.gukeer.open.persistence.entity.App;

import java.security.PrivateKey;

/**
 * Created by LL on 2017/2/22.
 */
public class AppExtention extends App {
    private  App app;
    private String companyName;
    private  String developer;
    private String contactPhone;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
