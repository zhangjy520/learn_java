package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.OpenMessage;
import cc.gukeer.open.persistence.entity.OpenMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpenMessageMapper {
    int deleteByExample(OpenMessageExample example);

    int insert(OpenMessage record);

    int insertSelective(OpenMessage record);

    List<OpenMessage> selectByExample(OpenMessageExample example);

    int updateByExampleSelective(@Param("record") OpenMessage record, @Param("example") OpenMessageExample example);

    int updateByExample(@Param("record") OpenMessage record, @Param("example") OpenMessageExample example);
}