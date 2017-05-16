package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SportsTestService {

    ScoreMapRule getRuleByPrimary(String ruleId);

    //根据年级，查询xd,nj
    Map getXdAndNj(String gradeName, User user);

    //根据登录用户的身份，查询班级id列表
    List<String> getClassByTeacher(String teacherId);

    //根据成绩的主键查询对应的成绩
    Map getScoreByPrimary(String testSeq);

    //根据动态条件，查询成绩
    PageInfo<Map> findScoreList(Map paramMap);

    //根据动态条件，查询成绩映射规则
    PageInfo<ScoreMapRule> findScoreMapRuleByCriteria(Map<String, Object> paramMap);

    //分页查询项目名称
    PageInfo<SportItem> findSportItem(Map<String,Object> map);
    // 修改/新增体育项目
    int saveSportItem(SportItem item, User user);

    //修改/新增 成绩映射规则
    int saveScoreMapRule(ScoreMapRule mapRule, User user);

    //保存学生成绩
    int saveStuScore(StuScoreRef stuScoreRef);

    int addStuScore(StuScoreRef stuScoreRef);

    //根据项目名查找项目
    SportItem getItemByName(String itemName, User user);
    //根据项目Id查找项目
    SportItem getItemById(String itemId,User user);
    //查询所有项目
    List<SportItem> getAllItem(User user);

    List<SportItem> getAllTzItem();

    //查询所有的测试次序
    List<Integer> getAllTestCount(User user);

    //查询成绩映射中所有的年级
    List<String> getAllNj(User user);

    //学段
    List<ClassSection> getSections(String schoolId);

    //查询所有的年级，班级信息
    List<Map> getSchoolClass(String schoolId, List<String> param);

    //更新学生成绩表，通过对象查询同一人同一项目的成绩，将其数据置过期，过期解释：同一个项目，同一个人测试多次，最新一次成绩视为未过期，之前成绩视为过期
    int updateRefScore(StuScoreRef stuScoreRef, User user);

    //更新学生成绩表，根据规则计算出分数和等级
    int setScoreAndLevel(StuScoreRef stuScoreRef);

    //根据年级，班级获得班级 id List
    List<Map> getClassDetail(String shortName, String schoolId);

    //根据学段获取xd
    ClassSection getSection(String xdName, User user);

    //教学建议：查询最新平均成绩，平均分数
    List<Map> getAvg(Map param);

    List<Map> getFailStuList(Map param);

    List<Map> getScoreLine(Map param);

    List<Map> getItems(Integer scoreType, String xdId, Integer njId, String schoolId);

    /*
    * 根据个人信息，选择对应的映射规则,作差取绝对值，第一条规则
    * param:Map(keys:
    *       itemId:项目编号
    *       schoolId:机构id
    *       xd:学段 （数字   小学，初中，高中）
    *       nj:年纪  （1-6: 一年级--六年级）
    *       )
    * */
    Map<Object, Object> chooseRuleByInfo(Map<Object, Object> paramMap);

    /*首页，获取当前身份的所有学生的平均日常健康数据；参数：
    *   classId（List<Integer>）,当前身份拥有的classId集合
    *   schoolId(Integer)，机构id
    *  whichDay（Integer）。whichDay:0(最新的统计成绩) 1（最新成绩的上一次成绩） 。不支持其他参数
    * */
    Map getGlobalDaily(Map param);

    /*首页，获取当前身份的所有学生的平均各项目的测试数据；参数：
    *   classId（List<Integer>）,当前身份拥有的classId集合
    *   schoolId(Integer)，机构id
    * */
    PageInfo<Map> getGlobalSport(Map param);

    //获取教师关联学段信息
    List<Map> getTeacherIdentify(String schoolId, String teacherId);

    //根据输入，判断此学生此项目此次成绩是否已经存在
    boolean scoreExist(StuScoreRef stuScoreRef);

    boolean stuNumExist(String stuNum);

    boolean itemExist(String itemName);

    boolean xdExist(String xd);

    boolean njExist(String xd, Integer nj);

    List<GradeClass> getAllClass(String schoolId);
    //查询所有项目
    List<String> itemNameList(Integer itemType);
    //查询所有学号
    List<String> stuNoList(String schoolId);
    //批量插入学生成绩并更新学生过期成绩
    int batchSave(List<StuScoreRef> scoreList,User user);
    //获取分数集合
    List<Map> getBatchInfo(List<String> prims);
    //批量更新学生成绩
    int batchUpdateScore(List<StuScoreRef> stuScoreRefs);

    List<Map> batchStuInfo(List<StuScoreRef> stuScoreRefs);

    int batchMapRuleSave(List<ScoreMapRule> ruleList);

    int delItemByPrimaryId(String id);


    //查询体育班级
    PageInfo<Map> checkSportClassByname(Map map);


//    List<Map> addSportCheck(String schoolId);
}
