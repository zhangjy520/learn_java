package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSchoolType;
import cc.gukeer.syncdata.persistence.entity.ChangeStateSchoolTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateSchoolTypeMapper {
    int deleteByExample(ChangeStateSchoolTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateSchoolType record);

    int insertSelective(ChangeStateSchoolType record);

    List<ChangeStateSchoolType> selectByExample(ChangeStateSchoolTypeExample example);

    ChangeStateSchoolType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateSchoolType record, @Param("example") ChangeStateSchoolTypeExample example);

    int updateByExample(@Param("record") ChangeStateSchoolType record, @Param("example") ChangeStateSchoolTypeExample example);

    int updateByPrimaryKeySelective(ChangeStateSchoolType record);

    int updateByPrimaryKey(ChangeStateSchoolType record);
}