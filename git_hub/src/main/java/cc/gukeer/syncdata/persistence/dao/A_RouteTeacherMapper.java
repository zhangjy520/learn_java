package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteTeacher;
import cc.gukeer.syncdata.persistence.entity.RouteTeacherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface A_RouteTeacherMapper {
    int insertBatch(List<RouteTeacher> routeTeacher);
    List<String> selectId(String mark);

}