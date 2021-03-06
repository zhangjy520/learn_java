package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.GradeClass;
import cc.gukeer.smartRing.persistence.entity.GradeClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GradeClassMapper {
    int deleteByExample(GradeClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(GradeClass record);

    int insertSelective(GradeClass record);

    List<GradeClass> selectByExample(GradeClassExample example);

    GradeClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GradeClass record, @Param("example") GradeClassExample example);

    int updateByExample(@Param("record") GradeClass record, @Param("example") GradeClassExample example);

    int updateByPrimaryKeySelective(GradeClass record);

    int updateByPrimaryKey(GradeClass record);
}