package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateUser;
import cc.gukeer.syncdata.persistence.entity.ChangeStateUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateUserMapper {
    int deleteByExample(ChangeStateUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateUser record);

    int insertSelective(ChangeStateUser record);

    List<ChangeStateUser> selectByExample(ChangeStateUserExample example);

    ChangeStateUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateUser record, @Param("example") ChangeStateUserExample example);

    int updateByExample(@Param("record") ChangeStateUser record, @Param("example") ChangeStateUserExample example);

    int updateByPrimaryKeySelective(ChangeStateUser record);

    int updateByPrimaryKey(ChangeStateUser record);
}