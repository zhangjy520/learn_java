package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.platform.persistence.dao.AppRoleMapper;
import cn.gukeer.platform.persistence.entity.AppRole;
import cn.gukeer.platform.persistence.entity.AppRoleExample;
import cn.gukeer.platform.service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by GW on 2016/9/10.
 */
@Service
public class AppRoleServiceImpl extends BasicService implements AppRoleService {
    @Autowired
    AppRoleMapper appRoleMapper;

    @Override
    public int deleteAppRole(AppRole appRole) {
        AppRoleExample appRoleExample = new AppRoleExample();
        appRoleExample.createCriteria().andAppIdEqualTo(appRole.getAppId()).andSchoolIdEqualTo(appRole.getSchoolId())
                .andRoleIdEqualTo(appRole.getRoleId());
        return appRoleMapper.deleteByExample(appRoleExample);

    }

    @Override
    public int insertAppRole(AppRole appRole) {
        return appRoleMapper.insertSelective(appRole);
    }

}
