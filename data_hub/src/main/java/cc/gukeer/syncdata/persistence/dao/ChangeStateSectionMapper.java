package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateSection;
import cc.gukeer.syncdata.persistence.entity.ChangeStateSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateSectionMapper {
    int deleteByExample(ChangeStateSectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateSection record);

    int insertSelective(ChangeStateSection record);

    List<ChangeStateSection> selectByExample(ChangeStateSectionExample example);

    ChangeStateSection selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateSection record, @Param("example") ChangeStateSectionExample example);

    int updateByExample(@Param("record") ChangeStateSection record, @Param("example") ChangeStateSectionExample example);

    int updateByPrimaryKeySelective(ChangeStateSection record);

    int updateByPrimaryKey(ChangeStateSection record);
}