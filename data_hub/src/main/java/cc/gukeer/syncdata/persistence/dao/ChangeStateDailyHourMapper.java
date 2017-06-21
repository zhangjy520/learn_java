package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateDailyHour;
import cc.gukeer.syncdata.persistence.entity.ChangeStateDailyHourExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateDailyHourMapper {
    int countByExample(ChangeStateDailyHourExample example);

    int deleteByExample(ChangeStateDailyHourExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateDailyHour record);

    int insertSelective(ChangeStateDailyHour record);

    List<ChangeStateDailyHour> selectByExample(ChangeStateDailyHourExample example);

    ChangeStateDailyHour selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateDailyHour record, @Param("example") ChangeStateDailyHourExample example);

    int updateByExample(@Param("record") ChangeStateDailyHour record, @Param("example") ChangeStateDailyHourExample example);

    int updateByPrimaryKeySelective(ChangeStateDailyHour record);

    int updateByPrimaryKey(ChangeStateDailyHour record);
}