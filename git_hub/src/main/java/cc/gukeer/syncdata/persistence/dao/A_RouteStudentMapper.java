package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteStudent;
import cc.gukeer.syncdata.persistence.entity.RouteStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface A_RouteStudentMapper {
   int insertBatch(List<RouteStudent> routeStudent );
   List<String> selectId(String mark);
}