package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.Accessories;
import cc.gukeer.open.persistence.entity.AccessoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessoriesMapper {
    int deleteByExample(AccessoriesExample example);

    int deleteByPrimaryKey(String id);

    int insert(Accessories record);

    int insertSelective(Accessories record);

    List<Accessories> selectByExample(AccessoriesExample example);

    Accessories selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Accessories record, @Param("example") AccessoriesExample example);

    int updateByExample(@Param("record") Accessories record, @Param("example") AccessoriesExample example);

    int updateByPrimaryKeySelective(Accessories record);

    int updateByPrimaryKey(Accessories record);
}