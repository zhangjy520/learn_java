package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.ChangeStateUser;
import cc.gukeer.open.persistence.entity.ChangeStateUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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