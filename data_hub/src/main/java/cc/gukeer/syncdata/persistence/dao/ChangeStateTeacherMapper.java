package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateTeacher;
import cc.gukeer.syncdata.persistence.entity.ChangeStateTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateTeacherMapper {
    int countByExample(ChangeStateTeacherExample example);

    int deleteByExample(ChangeStateTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateTeacher record);

    int insertSelective(ChangeStateTeacher record);

    List<ChangeStateTeacher> selectByExample(ChangeStateTeacherExample example);

    ChangeStateTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateTeacher record, @Param("example") ChangeStateTeacherExample example);

    int updateByExample(@Param("record") ChangeStateTeacher record, @Param("example") ChangeStateTeacherExample example);

    int updateByPrimaryKeySelective(ChangeStateTeacher record);

    int updateByPrimaryKey(ChangeStateTeacher record);
}