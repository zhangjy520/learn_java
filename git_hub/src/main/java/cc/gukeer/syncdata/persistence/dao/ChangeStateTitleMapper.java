package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateTitle;
import cc.gukeer.syncdata.persistence.entity.ChangeStateTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateTitleMapper {
    int deleteByExample(ChangeStateTitleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateTitle record);

    int insertSelective(ChangeStateTitle record);

    List<ChangeStateTitle> selectByExample(ChangeStateTitleExample example);

    ChangeStateTitle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateTitle record, @Param("example") ChangeStateTitleExample example);

    int updateByExample(@Param("record") ChangeStateTitle record, @Param("example") ChangeStateTitleExample example);

    int updateByPrimaryKeySelective(ChangeStateTitle record);

    int updateByPrimaryKey(ChangeStateTitle record);
}