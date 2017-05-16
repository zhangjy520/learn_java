package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.ScoreMapRule;
import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_SportTestMapper {

    //根据个人信息，选择对应的映射规则,做差取绝对值
    List<Map<Object, Object>> chooseRuleByInfo(Map<Object, Object> paramMap);

    //List<Map> getScoreAndStu(@Param("expire") Integer expire, @Param("schoolId") Integer schoolId);

    //根据动态条件查询分数列表
    List<Map> getScoreByCriteria(Map paramMap);

    //查询所有的测试次序
    List<Integer> getAllTestCount(@Param("schoolId") String schoolId);

    //查询所有的年级
    List<String> getAllNj(@Param("schoolId") String schoolId);

    List<Map> getSchoolClass(@Param("schoolId") String schoolId, @Param("classId") List<String> param);

    List<Map> getClassDetail(@Param("indexName") String indexName, @Param("schoolId") String schoolId);

    //    教学建议：最新分数柱状图和表格.. .
    List<Map> getAvg(Map<Object, Object> paramMap);

    List<Map> getFailStuList(Map paramMap);

    //根据年级查询项目列表
    List<Map> getItems(@Param("scoreType") Integer scoreType, @Param("xdId") String xdId,
                       @Param("njId") Integer njId, @Param("schoolId") String schoolId);

    //成绩统计列表
    List<Map> getScoreLine(Map paramMap);

    //根据成绩的主键查询对应的成绩
    Map getScoreByPrimary(@Param("testSeq") String testSeq);

    Map getGlobalDaily(Map param);

    List<Map> getGlobalSport(Map param);

    //获取教师教的学段信息
    List<Map> getTeacherIdentify(@Param("schoolId") String schoolId, @Param("teacherId") String teacherId);

    //批量插入学生成绩
    int batchInsertScore(@Param("list") List<StuScoreRef> list);

    List<Map> getBatchInfo(@Param("prims") List<String> prims);

    //批量更新学生成绩
    int batchUpdateScore(@Param("list") List<StuScoreRef> list);

    List<Map> batchStuInfo(@Param("stuScoreRefList") List<StuScoreRef> list);

    int batchMapRuleSave(@Param("list")List<ScoreMapRule> ruleList);

}
