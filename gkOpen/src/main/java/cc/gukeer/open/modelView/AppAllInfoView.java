package cc.gukeer.open.modelView;

import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.App;
import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.Personal;

/**
 * Created by lx on 2017/1/9.
 */
public class AppAllInfoView {
    private App app;
    private Company company;
    private Personal personal;
    private Accessories accessories;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
}
