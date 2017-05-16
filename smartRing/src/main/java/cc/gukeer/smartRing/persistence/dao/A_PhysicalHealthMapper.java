package cc.gukeer.smartRing.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface A_PhysicalHealthMapper {

    //获取时间列表
    List<String> getTimeList(@Param("schoolId") String schoolId);

    //体质健康，体制分析流线图数据
    List<Map> scoreChangeLine(Map param);

    //通过学号查询基本信息
    Map getStuInfo(@Param("stuNum") String stuNum, @Param("schoolId") String schoolId);

    //通过学号查询各项目最新测试成绩
    List<Map> getStuAllLatestScore(@Param("stuNum") String stuNum, @Param("scoreType") Integer scoreType,
                                   @Param("schoolId") String schoolId);

    //通过学号+项目id,查询个人此项目的各次成绩
    List<Map> getStuItemScore(@Param("stuNum") String stuNum, @Param("itemId") String itemId,
                              @Param("scoreType") Integer scoreType, @Param("schoolId") String schoolId);

    List<Map> getDailyHealthy(Map param);

    //根据学号，查询此学生日期范围内日常健康数据（dataType:0(每天的数据)；1（这些天的平均成绩））
    List<Map> getStuDailyHealthy(@Param("stuNum") String stuNum, @Param("schoolId") String schoolId,
                           @Param("beginDate") String beginDate, @Param("endDate") String endDate,
                           @Param("dataType") Integer dataType);



}
