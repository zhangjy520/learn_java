package cc.gukeer.open.service;

import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.OpenUser;


/**
 * Created by lx on 2016/12/29.
 */
public interface CompanyService {
    Company findCompanylbyloginUser(OpenUser OpenUser);
    int updateCompanyByPrimaryKeySelective(Company company);
    int save(Company company);
    int updateCompanyByPrimaryKey(Company company);
}
