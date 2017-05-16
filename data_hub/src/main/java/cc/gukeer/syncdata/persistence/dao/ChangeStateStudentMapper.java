package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateStudent;
import cc.gukeer.syncdata.persistence.entity.ChangeStateStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateStudentMapper {
    int countByExample(ChangeStateStudentExample example);

    int deleteByExample(ChangeStateStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateStudent record);

    int insertSelective(ChangeStateStudent record);

    List<ChangeStateStudent> selectByExample(ChangeStateStudentExample example);

    ChangeStateStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateStudent record, @Param("example") ChangeStateStudentExample example);

    int updateByExample(@Param("record") ChangeStateStudent record, @Param("example") ChangeStateStudentExample example);

    int updateByPrimaryKeySelective(ChangeStateStudent record);

    int updateByPrimaryKey(ChangeStateStudent record);
}