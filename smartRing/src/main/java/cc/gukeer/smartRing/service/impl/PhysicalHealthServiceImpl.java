package cc.gukeer.smartRing.service.impl;

import cc.gukeer.common.service.BasicService;
import cc.gukeer.common.utils.NumberConvertUtil;
import cc.gukeer.smartRing.persistence.dao.A_PhysicalHealthMapper;
import cc.gukeer.smartRing.persistence.dao.SportItemMapper;
import cc.gukeer.smartRing.persistence.dao.StuScoreRefMapper;
import cc.gukeer.smartRing.persistence.entity.SportItem;
import cc.gukeer.smartRing.persistence.entity.SportItemExample;
import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import cc.gukeer.smartRing.service.PhysicalHealthService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjy on 2016/12/9.
 */
@Service
public class PhysicalHealthServiceImpl extends BasicService implements PhysicalHealthService {

    @Autowired
    SportItemMapper sportItemMapper;

    @Autowired
    StuScoreRefMapper stuScoreRefMapper;

    @Autowired
    A_PhysicalHealthMapper a_physicalHealthMapper;


    @Override
    public List<SportItem> getAllItems() {
        SportItemExample example = new SportItemExample();
        example.createCriteria().andDelFlagEqualTo(0).andItemTypeEqualTo(1);
        List<SportItem> sportItemList = sportItemMapper.selectByExample(example);
        return sportItemList;
    }

    @Override
    public List<String> getTimeList(String schoolId) {
        return a_physicalHealthMapper.getTimeList(schoolId);
    }

    @Override
    public int insertStuScore(StuScoreRef stuScoreRef) {
        int flag = stuScoreRefMapper.insertSelective(stuScoreRef);
        return flag;
    }

    @Override
    public int updateStuScore(StuScoreRef stuScoreRef) {
        int flag = stuScoreRefMapper.updateByPrimaryKeySelective(stuScoreRef);
        return flag;
    }

    @Override
    public List<Map> scoreChangeLine(Map param) {
        return a_physicalHealthMapper.scoreChangeLine(param);
    }

    @Override
    public Map getStuInfo(String stuNum, String schoolId) {
        Map stuView = new HashMap();
        stuView = a_physicalHealthMapper.getStuInfo(stuNum, schoolId);
        return stuView;
    }

    @Override
    public List<Map> getStuAllLatestScore(String stuNum, Integer scoreType, String schoolId) {

        return a_physicalHealthMapper.getStuAllLatestScore(stuNum, scoreType, schoolId);
    }

    @Override
    public List<Map> getStuItemScore(String stuNum, String itemId, Integer scoreType, String schoolId) {

        return a_physicalHealthMapper.getStuItemScore(stuNum, itemId, scoreType, schoolId);
    }

    @Override
    public PageInfo<Map> getDailyHealthy(Map param) {
        int pageNum = NumberConvertUtil.convertS2I(param.get("pageNum").toString());
        int pageSize = NumberConvertUtil.convertS2I(param.get("pageSize").toString());
        pageSize = (pageSize == 0 ? 10 : pageSize);

        PageHelper.startPage(pageNum, pageSize);

        List<Map> mapList = a_physicalHealthMapper.getDailyHealthy(param);
        PageInfo<Map> pageInfo = new PageInfo<Map>(mapList);
        return pageInfo;
    }

    @Override
    public List<Map> getStuDailyHealthy(String stuNum, String schoolId, String beginDate, String endDate, Integer dataType) {

        return a_physicalHealthMapper.getStuDailyHealthy(stuNum, schoolId, beginDate, endDate, dataType);
    }


}
