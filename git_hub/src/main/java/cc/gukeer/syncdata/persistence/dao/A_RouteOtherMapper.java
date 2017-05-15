package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteOther;
import cc.gukeer.syncdata.persistence.entity.RouteOtherExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface A_RouteOtherMapper {
    int insertBatch(List<RouteOther> routeOther);
    List<String> selectId(String mark);
}