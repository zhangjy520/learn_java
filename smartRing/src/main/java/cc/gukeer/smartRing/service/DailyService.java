package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.HealthyStandard;
import cc.gukeer.smartRing.persistence.entity.extension.LogDetailView;
import cc.gukeer.smartRing.persistence.entity.extension.MessageView;
import cc.gukeer.smartRing.persistence.entity.extension.StandardView;
import cc.gukeer.smartRing.persistence.entity.extension.Statistics;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/12/19.
 */
public interface DailyService {
//    List<MessageView> findMessageByTeacherId(String teacherId, String xd, Integer nj,
//                                             String classId, Integer fromDate, Integer toDate);

    PageInfo<MessageView> findMessageByTeacherId(String teacherId, String xd, Integer nj,
                                                 String classId, Integer pageSize, Integer pageNum, Integer fromDate, Integer toDate);

//    List<MessageView> findMessageSleepByTeacherId(String teacherId, String xd, Integer nj,
//                                                  String classId, Integer fromDate, Integer toDate);

    PageInfo<MessageView> findMessageSleepByTeacherId(String teacherId, String xd, Integer nj,
                                                 String classId, Integer pageSize, Integer pageNum, Integer fromDate, Integer toDate);

    Statistics findStatisticsByTeacherId(String teacherId, String xd, Integer nj,
                                         String classId, Integer fromDate, Integer toDate);



    List<Statistics> findDayStatisticsByTeacherId(String teacherId, String xd, Integer nj,
                                                  String classId, Integer fromDate, Integer toDate);

    List<StandardView> findStandardByTeahcerId(String teacherId);

    HealthyStandard findStandardByClass(String schoolId, String xd, Integer nj);

    int saveStandard(HealthyStandard healthyStandard);

    List<HashMap<String, String>> CountStuNumByStation(Long lastUpdate);

    List<HashMap<String, String>> CountStuNumByStu(Long lastUpdate);

    List<HashMap<String, String>> StuNowPosition(Long lastUpdate,String name);

    List<HashMap<String, Object>> avgTimeInArea(String xd, Integer nj , String classId ,Long fromDate,Long toDate);

    List<LogDetailView> personalDetail(String studentId,Long fromDate,Long toDate);
    //增
    List<Map<Object,Object>> messagePie(String teacherId, String xd, Integer nj,
                         String classId, Integer fromDate, Integer toDate);

}
