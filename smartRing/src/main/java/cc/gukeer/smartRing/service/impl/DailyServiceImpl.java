package cc.gukeer.smartRing.service.impl;

import cc.gukeer.smartRing.persistence.dao.A_MessageExtensionMapper;
import cc.gukeer.smartRing.persistence.dao.HealthyStandardMapper;
import cc.gukeer.smartRing.persistence.dao.RingMessageMapper;
import cc.gukeer.smartRing.persistence.entity.HealthyStandard;
import cc.gukeer.smartRing.persistence.entity.HealthyStandardExample;
import cc.gukeer.smartRing.persistence.entity.extension.LogDetailView;
import cc.gukeer.smartRing.persistence.entity.extension.MessageView;
import cc.gukeer.smartRing.persistence.entity.extension.StandardView;
import cc.gukeer.smartRing.persistence.entity.extension.Statistics;
import cc.gukeer.smartRing.service.DailyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyServiceImpl implements DailyService {
    @Autowired
    RingMessageMapper ringMessageMapper;
    @Autowired
    A_MessageExtensionMapper messageExtensionMapper;
    @Autowired
    HealthyStandardMapper healthyStandardMapper;

//    @Override
//    public List<MessageView> findMessageByTeacherId(String teacherId, String xd, Integer nj,
//                                                    String classId, Integer fromDate, Integer toDate) {
//        return messageExtensionMapper.findMessageByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
//    }
    @Override
    public PageInfo<MessageView> findMessageByTeacherId(String teacherId, String xd, Integer nj,
                                                        String classId, Integer pageSize, Integer pageNum, Integer fromDate, Integer toDate) {
        pageSize = (pageSize == 0 ? 10 : pageSize);

        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<MessageView> mapList = messageExtensionMapper.findMessageByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
        PageInfo<MessageView> pageInfo = new PageInfo<MessageView>(mapList);
        return pageInfo;
    }
//    @Override
//    public List<MessageView> findMessageSleepByTeacherId(String teacherId, String xd, Integer nj,
//                                                         String classId, Integer fromDate, Integer toDate) {
//        return messageExtensionMapper.findMessageSleepByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
//    }

    @Override
    public PageInfo<MessageView> findMessageSleepByTeacherId(String teacherId, String xd, Integer nj,
                                                      String classId, Integer pageSize, Integer pageNum, Integer fromDate, Integer toDate){
        pageSize = (pageSize == 0 ? 10 : pageSize);

        if (pageSize == -1) {
            //pageSize为-1时查询全部
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<MessageView> messageViews= messageExtensionMapper.findMessageSleepByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
        PageInfo<MessageView> pageInfo = new PageInfo<MessageView>(messageViews);
        return pageInfo;
    }

    @Override
    public Statistics findStatisticsByTeacherId(String teacherId, String xd, Integer nj,
                                                String classId, Integer fromDate, Integer toDate) {
        return messageExtensionMapper.findStatisticsByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
    }


    @Override
    public List<Statistics> findDayStatisticsByTeacherId(String teacherId, String xd, Integer nj, String classId,
                                                         Integer fromDate, Integer toDate) {
        return messageExtensionMapper.findDayStatisticsByTeacherId(teacherId, xd, nj, classId, fromDate, toDate);
    }

    @Override
    public List<StandardView> findStandardByTeahcerId(String teacherId) {
        return messageExtensionMapper.findStandardByTeacherId(teacherId);
    }

    @Override
    public HealthyStandard findStandardByClass(String schoolId, String xd, Integer nj) {
        HealthyStandardExample example = new HealthyStandardExample();
        HealthyStandardExample.Criteria criteria = example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        criteria.andXdEqualTo(xd);
        criteria.andNjEqualTo(nj);
        List<HealthyStandard> healthyStandards = healthyStandardMapper.selectByExample(example);
        if (healthyStandards.size() > 0) {
            return healthyStandards.get(0);
        }
        return null;
    }

    @Override
    public int saveStandard(HealthyStandard healthyStandard) {
        return healthyStandardMapper.updateByPrimaryKeySelective(healthyStandard);
    }

    @Override
    public List<HashMap<String, String>> CountStuNumByStation(Long lastUpdate) {
        return messageExtensionMapper.CountStuNumByStation(lastUpdate);
    }

    @Override
    public List<HashMap<String, String>> CountStuNumByStu(Long lastUpdate) {
        return messageExtensionMapper.CountStuNumByStu(lastUpdate);
    }

    @Override
    public List<HashMap<String, String>> StuNowPosition(Long lastUpdate, String name) {
        return messageExtensionMapper.StuNowPosition(lastUpdate, name);
    }

    @Override
    public List<HashMap<String, Object>> avgTimeInArea(String xd, Integer nj, String classId, Long fromDate, Long toDate) {
        return messageExtensionMapper.avgTimeInArea(xd, nj, classId, fromDate, toDate);
    }

    @Override
    public List<LogDetailView> personalDetail(String studentId, Long fromDate, Long toDate) {
        return messageExtensionMapper.personalDetail(studentId, fromDate, toDate);
    }

    //增
    @Override
    public List<Map<Object,Object>> messagePie (String teacherId, String xd, Integer nj,
                                 String classId, Integer fromDate, Integer toDate){
        return messageExtensionMapper.messagePieChart(teacherId,xd,nj,classId,fromDate,toDate);
    }
}
