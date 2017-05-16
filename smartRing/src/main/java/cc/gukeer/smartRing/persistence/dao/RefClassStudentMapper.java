package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.RefClassStudent;
import cc.gukeer.smartRing.persistence.entity.RefClassStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefClassStudentMapper {
    int deleteByExample(RefClassStudentExample example);

    int insert(RefClassStudent record);

    int insertSelective(RefClassStudent record);

    List<RefClassStudent> selectByExample(RefClassStudentExample example);

    int updateByExampleSelective(@Param("record") RefClassStudent record, @Param("example") RefClassStudentExample example);

    int updateByExample(@Param("record") RefClassStudent record, @Param("example") RefClassStudentExample example);
}