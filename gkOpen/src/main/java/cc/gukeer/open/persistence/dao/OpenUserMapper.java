package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.persistence.entity.OpenUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenUserMapper {
    int deleteByExample(OpenUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(OpenUser record);

    int insertSelective(OpenUser record);

    List<OpenUser> selectByExample(OpenUserExample example);

    OpenUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OpenUser record, @Param("example") OpenUserExample example);

    int updateByExample(@Param("record") OpenUser record, @Param("example") OpenUserExample example);

    int updateByPrimaryKeySelective(OpenUser record);

    int updateByPrimaryKey(OpenUser record);
}