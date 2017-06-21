package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateClassRoomType;
import cc.gukeer.syncdata.persistence.entity.ChangeStateClassRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateClassRoomTypeMapper {
    int countByExample(ChangeStateClassRoomTypeExample example);

    int deleteByExample(ChangeStateClassRoomTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateClassRoomType record);

    int insertSelective(ChangeStateClassRoomType record);

    List<ChangeStateClassRoomType> selectByExample(ChangeStateClassRoomTypeExample example);

    ChangeStateClassRoomType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateClassRoomType record, @Param("example") ChangeStateClassRoomTypeExample example);

    int updateByExample(@Param("record") ChangeStateClassRoomType record, @Param("example") ChangeStateClassRoomTypeExample example);

    int updateByPrimaryKeySelective(ChangeStateClassRoomType record);

    int updateByPrimaryKey(ChangeStateClassRoomType record);
}