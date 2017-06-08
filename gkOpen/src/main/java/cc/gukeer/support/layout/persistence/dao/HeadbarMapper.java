package cc.gukeer.support.layout.persistence.dao;

import cc.gukeer.support.layout.persistence.entity.Headbar;
import cc.gukeer.support.layout.persistence.entity.HeadbarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HeadbarMapper {
    int deleteByExample(HeadbarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Headbar record);

    int insertSelective(Headbar record);

    List<Headbar> selectByExample(HeadbarExample example);

    Headbar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Headbar record, @Param("example") HeadbarExample example);

    int updateByExample(@Param("record") Headbar record, @Param("example") HeadbarExample example);

    int updateByPrimaryKeySelective(Headbar record);

    int updateByPrimaryKey(Headbar record);
}