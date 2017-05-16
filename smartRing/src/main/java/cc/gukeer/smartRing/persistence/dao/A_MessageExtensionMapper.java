package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.extension.LogDetailView;
import cc.gukeer.smartRing.persistence.entity.extension.MessageView;
import cc.gukeer.smartRing.persistence.entity.extension.StandardView;
import cc.gukeer.smartRing.persistence.entity.extension.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/12/20.
 */
public interface A_MessageExtensionMapper {
    List<Map<Object,Object>> messagePieChart(@Param("teacherId") String teacherId,
                              @Param("xd") String xd,
                              @Param("nj")Integer nj,
                              @Param("classId")String classId,
                              @Param("fromDate")Integer fromDate,
                              @Param("toDate")Integer toDate);

    List<MessageView> findMessageByTeacherId(@Param("teacherId") String teacherId,
                                             @Param("xd") String xd,
                                             @Param("nj") Integer nj,
                                             @Param("classId") String classId,
                                             @Param("fromDate") Integer fromDate,
                                             @Param("toDate") Integer toDate);
    List<MessageView> findMessageSleepByTeacherId(@Param("teacherId") String teacherId,
                                             @Param("xd") String xd,
                                             @Param("nj") Integer nj,
                                             @Param("classId") String classId,
                                             @Param("fromDate") Integer fromDate,
                                             @Param("toDate") Integer toDate);

    Statistics findStatisticsByTeacherId(@Param("teacherId") String teacherId,
                                         @Param("xd") String xd,
                                         @Param("nj") Integer nj,
                                         @Param("classId") String classId,
                                         @Param("fromDate") Integer fromDate,
                                         @Param("toDate") Integer toDate);

    List<Statistics> findDayStatisticsByTeacherId(@Param("teacherId") String teacherId,
                                         @Param("xd") String xd,
                                         @Param("nj") Integer nj,
                                         @Param("classId") String classId,
                                         @Param("fromDate") Integer fromDate,
                                         @Param("toDate") Integer toDate);
    List<StandardView> findStandardByTeacherId(@Param("teacherId") String teacherId);
    List<HashMap<String, String>> CountStuNumByStation(@Param("lastUpdate") Long lastUpdate);
    List<HashMap<String, String>> CountStuNumByStu(@Param("lastUpdate") Long lastUpdate);
    List<HashMap<String, String>> StuNowPosition(@Param("lastUpdate") Long lastUpdate,@Param("name") String name);
    List<HashMap<String, Object>> avgTimeInArea(@Param("xd") String xd , @Param("nj") Integer nj , @Param("classId") String classId ,
                                               @Param("fromDate") Long fromDate , @Param("toDate") Long toDate);
    List<LogDetailView> personalDetail(@Param("studentId") String studentId,@Param("fromDate") Long fromDate , @Param("toDate") Long toDate);
}
