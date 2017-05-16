package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateRefTeacher;
import cc.gukeer.syncdata.persistence.entity.ChangeStateRefTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateRefTeacherMapper {
    int deleteByExample(ChangeStateRefTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateRefTeacher record);

    int insertSelective(ChangeStateRefTeacher record);

    List<ChangeStateRefTeacher> selectByExample(ChangeStateRefTeacherExample example);

    ChangeStateRefTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateRefTeacher record, @Param("example") ChangeStateRefTeacherExample example);

    int updateByExample(@Param("record") ChangeStateRefTeacher record, @Param("example") ChangeStateRefTeacherExample example);

    int updateByPrimaryKeySelective(ChangeStateRefTeacher record);

    int updateByPrimaryKey(ChangeStateRefTeacher record);
}