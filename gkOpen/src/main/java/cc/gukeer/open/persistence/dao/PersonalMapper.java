package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.Personal;
import cc.gukeer.open.persistence.entity.PersonalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonalMapper {
    int deleteByExample(PersonalExample example);

    int deleteByPrimaryKey(String id);

    int insert(Personal record);

    int insertSelective(Personal record);

    List<Personal> selectByExample(PersonalExample example);

    Personal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Personal record, @Param("example") PersonalExample example);

    int updateByExample(@Param("record") Personal record, @Param("example") PersonalExample example);

    int updateByPrimaryKeySelective(Personal record);

    int updateByPrimaryKey(Personal record);
}