package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.RingMessage;
import cc.gukeer.smartRing.persistence.entity.RingMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RingMessageMapper {
    int deleteByExample(RingMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(RingMessage record);

    int insertSelective(RingMessage record);

    List<RingMessage> selectByExample(RingMessageExample example);

    RingMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RingMessage record, @Param("example") RingMessageExample example);

    int updateByExample(@Param("record") RingMessage record, @Param("example") RingMessageExample example);

    int updateByPrimaryKeySelective(RingMessage record);

    int updateByPrimaryKey(RingMessage record);
}