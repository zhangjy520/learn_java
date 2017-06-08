package cc.gukeer.open.persistence.dao;

import cc.gukeer.open.persistence.entity.App;
import cc.gukeer.open.persistence.entity.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    int countByExample(AppExample example);

    int deleteByExample(AppExample example);

    int deleteByPrimaryKey(String id);

    int insert(App record);

    int insertSelective(App record);

    List<App> selectByExample(AppExample example);

    App selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}