package cc.gukeer.open.modelView;

import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.Personal;

import java.io.Serializable;

public class PersonalAllInfoView implements Serializable {
    private Personal personal;

    private Accessories accessories;

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