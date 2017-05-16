package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.Personal;

/**
 * Created by LL on 2016/12/28.
 */
public interface AccessoriesService {

    void save(Accessories accessories);
    Accessories findAccessoriesByCompany(Company company);
    Accessories findAccessoriesByPersonal(Personal personal);
    int updateAccessoriesByPrimaryKeySelective(Accessories accessories);

    Accessories findAccessoriesById(String id);
}
