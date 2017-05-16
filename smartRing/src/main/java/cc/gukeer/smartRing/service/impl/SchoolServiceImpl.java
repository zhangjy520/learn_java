package cc.gukeer.smartRing.service.impl;

import cc.gukeer.smartRing.persistence.dao.SchoolMapper;
import cc.gukeer.smartRing.persistence.entity.SchoolExample;
import cc.gukeer.smartRing.service.SchoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by connli on 2017/3/10.
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int countSchoolsById(String schoolId) {

        if (StringUtils.isBlank(schoolId)) {
            return 0;
        }
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(schoolId);
        int count = schoolMapper.countByExample(example);

        return count;
    }
}
