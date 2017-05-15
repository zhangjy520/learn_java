package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSchool;
import cc.gukeer.syncdata.persistence.entity.ChangeStateSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateSchoolMapper {
    int countByExample(ChangeStateSchoolExample example);

    int deleteByExample(ChangeStateSchoolExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateSchool record);

    int insertSelective(ChangeStateSchool record);

    List<ChangeStateSchool> selectByExample(ChangeStateSchoolExample example);

    ChangeStateSchool selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateSchool record, @Param("example") ChangeStateSchoolExample example);

    int updateByExample(@Param("record") ChangeStateSchool record, @Param("example") ChangeStateSchoolExample example);

    int updateByPrimaryKeySelective(ChangeStateSchool record);

    int updateByPrimaryKey(ChangeStateSchool record);
}