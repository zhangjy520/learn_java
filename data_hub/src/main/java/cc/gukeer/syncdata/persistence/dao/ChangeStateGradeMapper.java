package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateGrade;
import cc.gukeer.syncdata.persistence.entity.ChangeStateGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateGradeMapper {
    int deleteByExample(ChangeStateGradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateGrade record);

    int insertSelective(ChangeStateGrade record);

    List<ChangeStateGrade> selectByExample(ChangeStateGradeExample example);

    ChangeStateGrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateGrade record, @Param("example") ChangeStateGradeExample example);

    int updateByExample(@Param("record") ChangeStateGrade record, @Param("example") ChangeStateGradeExample example);

    int updateByPrimaryKeySelective(ChangeStateGrade record);

    int updateByPrimaryKey(ChangeStateGrade record);
}