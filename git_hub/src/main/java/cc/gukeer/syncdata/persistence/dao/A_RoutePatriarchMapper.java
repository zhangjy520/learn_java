package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStatePatriarch;
import cc.gukeer.syncdata.persistence.entity.RoutePatriarch;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pc-daisike on 2017/2/15.
 */
public interface A_RoutePatriarchMapper {
    int  insertBatch(List<RoutePatriarch> routePatriarch );
    List<String> selectId(String mark);
}
