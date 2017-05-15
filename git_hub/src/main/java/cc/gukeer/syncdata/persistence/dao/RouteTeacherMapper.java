package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.RouteTeacher;
import cc.gukeer.syncdata.persistence.entity.RouteTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouteTeacherMapper {
    int countByExample(RouteTeacherExample example);

    int deleteByExample(RouteTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(RouteTeacher record);

    int insertSelective(RouteTeacher record);

    List<RouteTeacher> selectByExample(RouteTeacherExample example);

    RouteTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RouteTeacher record, @Param("example") RouteTeacherExample example);

    int updateByExample(@Param("record") RouteTeacher record, @Param("example") RouteTeacherExample example);

    int updateByPrimaryKeySelective(RouteTeacher record);

    int updateByPrimaryKey(RouteTeacher record);
}