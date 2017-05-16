package cc.gukeer.smartRing.persistence.dao;

import cc.gukeer.smartRing.persistence.entity.SportItem;
import cc.gukeer.smartRing.persistence.entity.SportItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SportItemMapper {
    int deleteByExample(SportItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(SportItem record);

    int insertSelective(SportItem record);

    List<SportItem> selectByExample(SportItemExample example);

    SportItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") SportItem record, @Param("example") SportItemExample example);

    int updateByExample(@Param("record") SportItem record, @Param("example") SportItemExample example);

    int updateByPrimaryKeySelective(SportItem record);

    int updateByPrimaryKey(SportItem record);
}