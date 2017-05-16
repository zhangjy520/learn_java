package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.DetailObj;
import cc.gukeer.syncdata.persistence.entity.DetailObjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetailObjMapper {
    int countByExample(DetailObjExample example);

    int deleteByExample(DetailObjExample example);

    int deleteByPrimaryKey(String id);

    int insert(DetailObj record);

    int insertSelective(DetailObj record);

    List<DetailObj> selectByExample(DetailObjExample example);

    DetailObj selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DetailObj record, @Param("example") DetailObjExample example);

    int updateByExample(@Param("record") DetailObj record, @Param("example") DetailObjExample example);

    int updateByPrimaryKeySelective(DetailObj record);

    int updateByPrimaryKey(DetailObj record);
}