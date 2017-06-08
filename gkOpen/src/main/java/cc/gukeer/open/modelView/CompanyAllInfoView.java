package cc.gukeer.open.modelView;

import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.Company;

import java.io.Serializable;
import java.util.Date;

public class CompanyAllInfoView implements Serializable {
    private Company company;

    private Accessories accessories;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
}