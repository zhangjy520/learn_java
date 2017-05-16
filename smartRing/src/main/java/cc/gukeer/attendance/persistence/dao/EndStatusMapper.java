package cc.gukeer.attendance.persistence.dao;

import cc.gukeer.attendance.persistence.entity.EndStatus;
import cc.gukeer.attendance.persistence.entity.EndStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EndStatusMapper {
    int deleteByExample(EndStatusExample example);

    int insert(EndStatus record);

    int insertSelective(EndStatus record);

    List<EndStatus> selectByExample(EndStatusExample example);

    int updateByExampleSelective(@Param("record") EndStatus record, @Param("example") EndStatusExample example);

    int updateByExample(@Param("record") EndStatus record, @Param("example") EndStatusExample example);
}