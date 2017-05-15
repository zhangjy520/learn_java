package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteTeacherClass;
import cc.gukeer.syncdata.persistence.entity.RouteTeacherClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouteTeacherClassMapper {
    int countByExample(RouteTeacherClassExample example);

    int deleteByExample(RouteTeacherClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(RouteTeacherClass record);

    int insertSelective(RouteTeacherClass record);

    List<RouteTeacherClass> selectByExample(RouteTeacherClassExample example);

    RouteTeacherClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RouteTeacherClass record, @Param("example") RouteTeacherClassExample example);

    int updateByExample(@Param("record") RouteTeacherClass record, @Param("example") RouteTeacherClassExample example);

    int updateByPrimaryKeySelective(RouteTeacherClass record);

    int updateByPrimaryKey(RouteTeacherClass record);
}