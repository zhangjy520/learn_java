package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStatePatriarch;
import cc.gukeer.syncdata.persistence.entity.ChangeStatePatriarchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStatePatriarchMapper {
    int countByExample(ChangeStatePatriarchExample example);

    int deleteByExample(ChangeStatePatriarchExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStatePatriarch record);

    int insertSelective(ChangeStatePatriarch record);

    List<ChangeStatePatriarch> selectByExample(ChangeStatePatriarchExample example);

    ChangeStatePatriarch selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStatePatriarch record, @Param("example") ChangeStatePatriarchExample example);

    int updateByExample(@Param("record") ChangeStatePatriarch record, @Param("example") ChangeStatePatriarchExample example);

    int updateByPrimaryKeySelective(ChangeStatePatriarch record);

    int updateByPrimaryKey(ChangeStatePatriarch record);
}