package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.persistence.entity.SportItem;
import cc.gukeer.smartRing.persistence.entity.StuScoreRef;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PhysicalHealthService {

    //获取体质体质健康项目,全国通用,不判断schoolId
    List<SportItem> getAllItems();

    //获取体质健康时间列表，不判断schoolId
    List<String> getTimeList(String schoolId);

    //保存体育测试成绩
    int insertStuScore(StuScoreRef stuScoreRef);

    //修改/删除体育测试成绩(ps：导入成绩需要主键list，修改保存分为2方法不合并)
    int updateStuScore(StuScoreRef stuScoreRef);

    //体质健康，体质分析曲线图
    List<Map> scoreChangeLine(Map param);

    //通过学号查询基本信息
    Map getStuInfo(String stuNum, String schoolId);

    //通过学号查询各项目最新测试成绩
    List<Map> getStuAllLatestScore(String stuNum, Integer scoreType, String schoolId);

    //通过学号+项目id,查询个人此项目的各次成绩
    List<Map> getStuItemScore(String stuNum, String itemId, Integer scoreType, String schoolId);

    PageInfo<Map> getDailyHealthy(Map param);

    List<Map> getStuDailyHealthy(String stuNum, String schoolId, String beginDate, String endDate, Integer dataType);

}
