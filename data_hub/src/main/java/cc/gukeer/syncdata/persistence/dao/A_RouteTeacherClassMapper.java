package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteTeacherClass;
import cc.gukeer.syncdata.persistence.entity.RouteTeacherClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface A_RouteTeacherClassMapper {
   int insertBatch(List<RouteTeacherClass> routeTeacherClass );
}