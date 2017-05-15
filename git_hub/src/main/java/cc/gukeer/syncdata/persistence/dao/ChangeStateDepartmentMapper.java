package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateDepartment;
import cc.gukeer.syncdata.persistence.entity.ChangeStateDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateDepartmentMapper {
    int deleteByExample(ChangeStateDepartmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateDepartment record);

    int insertSelective(ChangeStateDepartment record);

    List<ChangeStateDepartment> selectByExample(ChangeStateDepartmentExample example);

    ChangeStateDepartment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateDepartment record, @Param("example") ChangeStateDepartmentExample example);

    int updateByExample(@Param("record") ChangeStateDepartment record, @Param("example") ChangeStateDepartmentExample example);

    int updateByPrimaryKeySelective(ChangeStateDepartment record);

    int updateByPrimaryKey(ChangeStateDepartment record);
}