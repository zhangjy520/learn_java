package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateClassRoom;
import cc.gukeer.syncdata.persistence.entity.ChangeStateClassRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateClassRoomMapper {
    int countByExample(ChangeStateClassRoomExample example);

    int deleteByExample(ChangeStateClassRoomExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateClassRoom record);

    int insertSelective(ChangeStateClassRoom record);

    List<ChangeStateClassRoom> selectByExample(ChangeStateClassRoomExample example);

    ChangeStateClassRoom selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateClassRoom record, @Param("example") ChangeStateClassRoomExample example);

    int updateByExample(@Param("record") ChangeStateClassRoom record, @Param("example") ChangeStateClassRoomExample example);

    int updateByPrimaryKeySelective(ChangeStateClassRoom record);

    int updateByPrimaryKey(ChangeStateClassRoom record);
}