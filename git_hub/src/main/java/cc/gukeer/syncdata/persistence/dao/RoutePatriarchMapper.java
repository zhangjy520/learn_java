package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RoutePatriarch;
import cc.gukeer.syncdata.persistence.entity.RoutePatriarchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoutePatriarchMapper {
    int countByExample(RoutePatriarchExample example);

    int deleteByExample(RoutePatriarchExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoutePatriarch record);

    int insertSelective(RoutePatriarch record);

    List<RoutePatriarch> selectByExample(RoutePatriarchExample example);

    RoutePatriarch selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoutePatriarch record, @Param("example") RoutePatriarchExample example);

    int updateByExample(@Param("record") RoutePatriarch record, @Param("example") RoutePatriarchExample example);

    int updateByPrimaryKeySelective(RoutePatriarch record);

    int updateByPrimaryKey(RoutePatriarch record);
}