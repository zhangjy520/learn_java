package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.CompanyMapper;
import cc.gukeer.open.persistence.entity.Company;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lx on 2016/12/29.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    @Override
    public Company findCompanylbyloginUser(OpenUser OpenUser) {
        String id = OpenUser.getCompanyId();
        if (StringUtils.isNotEmpty(id)) {
            Company company = companyMapper.selectByPrimaryKey(id);
            return company;
        }
       return null;
    }

    @Override
    public int updateCompanyByPrimaryKeySelective(Company company) {
        int result = companyMapper.updateByPrimaryKeySelective(company);
        return result;
    }

    public int save(Company company) {
        return companyMapper.insert(company);
    }

    public int updateCompanyByPrimaryKey(Company company) {
        int result = companyMapper.updateByPrimaryKey(company);
        if (result > 0) {
            return  result;
        }else {
            return 0;
        }
    }
}
