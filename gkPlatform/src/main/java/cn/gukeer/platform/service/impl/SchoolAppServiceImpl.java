package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.StringUtils;
import cn.gukeer.platform.persistence.dao.SchoolAppMapper;
import cn.gukeer.platform.persistence.entity.SchoolApp;
import cn.gukeer.platform.persistence.entity.SchoolAppExample;
import cn.gukeer.platform.service.SchoolAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GW on 2016/9/9.
 */
@Service
public class SchoolAppServiceImpl extends BasicService implements SchoolAppService {
    @Autowired
    SchoolAppMapper schoolAppMapper;

    @Override
    public int insertSchoolApp(SchoolApp schoolApp) {
        return schoolAppMapper.insert(schoolApp);
    }

    @Override
    public int deleteSchoolApp(SchoolApp schoolApp) {
        SchoolAppExample schoolAppExample = new SchoolAppExample();
        SchoolAppExample.Criteria criteria = schoolAppExample.createCriteria();
        if (!StringUtils.isEmpty(schoolApp.getAppId())){
            criteria.andAppIdEqualTo(schoolApp.getAppId());
        }
        criteria.andSchoolIdEqualTo(schoolApp.getSchoolId());

        return schoolAppMapper.deleteByExample(schoolAppExample);
    }

    @Override
    public List<SchoolApp> findAllListBySchoolId(String schoolId) {
        SchoolAppExample schoolAppExample=new SchoolAppExample();
        schoolAppExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolApp> schoolAppList=schoolAppMapper.selectByExample(schoolAppExample);
        return schoolAppList;
    }

}
