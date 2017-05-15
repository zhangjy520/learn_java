package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteOther;
import cc.gukeer.syncdata.persistence.entity.RouteOtherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouteOtherMapper {
    int countByExample(RouteOtherExample example);

    int deleteByExample(RouteOtherExample example);

    int deleteByPrimaryKey(String id);

    int insert(RouteOther record);

    int insertSelective(RouteOther record);

    List<RouteOther> selectByExample(RouteOtherExample example);

    RouteOther selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RouteOther record, @Param("example") RouteOtherExample example);

    int updateByExample(@Param("record") RouteOther record, @Param("example") RouteOtherExample example);

    int updateByPrimaryKeySelective(RouteOther record);

    int updateByPrimaryKey(RouteOther record);
}