package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.TeacherClassExample;
import cc.gukeer.smartBoard.persistence.entity.TeacherClassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherClassMapper {
    int deleteByExample(TeacherClassExample example);

    int deleteByPrimaryKey(TeacherClassKey key);

    int insert(TeacherClassKey record);

    int insertSelective(TeacherClassKey record);

    List<TeacherClassKey> selectByExample(TeacherClassExample example);

    int updateByExampleSelective(@Param("record") TeacherClassKey record, @Param("example") TeacherClassExample example);

    int updateByExample(@Param("record") TeacherClassKey record, @Param("example") TeacherClassExample example);
}