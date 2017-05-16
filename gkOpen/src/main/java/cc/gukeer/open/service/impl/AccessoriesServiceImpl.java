package cc.gukeer.open.service.impl;

import cc.gukeer.open.persistence.dao.AccessoriesMapper;
import cc.gukeer.open.persistence.entity.*;
import cc.gukeer.open.service.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by LL on 2016/12/28.
 */
@Service
public class AccessoriesServiceImpl implements AccessoriesService {
    @Autowired
    AccessoriesMapper accessoriesMapper;

    @Override
    public void save(Accessories accessories) {
        accessoriesMapper.insert(accessories);
    }

    public Accessories findAccessoriesByCompany(Company company) {
        String accessoriesId = company.getAccessoriesId();
        if (StringUtils.isEmpty(accessoriesId)){
            return null;
        }
        Accessories accessories = accessoriesMapper.selectByPrimaryKey(accessoriesId);
        return accessories;
    }

    @Override
    public Accessories findAccessoriesByPersonal(Personal personal) {
        String accessoriesId = personal.getAccessoriesId();
        if (StringUtils.isEmpty(accessoriesId)){
            return null;
        }
        Accessories accessories = accessoriesMapper.selectByPrimaryKey(accessoriesId);
        return accessories;
    }

    public int updateAccessoriesByPrimaryKeySelective(Accessories accessories) {
        int  result = accessoriesMapper.updateByPrimaryKeySelective(accessories);
        if (result > 0) {
            return result;
        } else {
            return  0;
        }
    }

    @Override
    public Accessories findAccessoriesById(String id) {
        Accessories accessories = accessoriesMapper.selectByPrimaryKey(id);
        if (accessories != null) {
            return accessories;
        }else {
            return null;
        }
    }
}
