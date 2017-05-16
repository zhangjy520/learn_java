package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteStudent;
import cc.gukeer.syncdata.persistence.entity.RouteStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouteStudentMapper {
    int countByExample(RouteStudentExample example);

    int deleteByExample(RouteStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(RouteStudent record);

    int insertSelective(RouteStudent record);

    List<RouteStudent> selectByExample(RouteStudentExample example);

    RouteStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RouteStudent record, @Param("example") RouteStudentExample example);

    int updateByExample(@Param("record") RouteStudent record, @Param("example") RouteStudentExample example);

    int updateByPrimaryKeySelective(RouteStudent record);

    int updateByPrimaryKey(RouteStudent record);
}