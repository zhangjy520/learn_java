package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.DetailObjColumn;
import cc.gukeer.syncdata.persistence.entity.DetailObjColumnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DetailObjColumnMapper {
    int countByExample(DetailObjColumnExample example);

    int deleteByExample(DetailObjColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(DetailObjColumn record);

    int insertSelective(DetailObjColumn record);

    List<DetailObjColumn> selectByExample(DetailObjColumnExample example);

    DetailObjColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DetailObjColumn record, @Param("example") DetailObjColumnExample example);

    int updateByExample(@Param("record") DetailObjColumn record, @Param("example") DetailObjColumnExample example);

    int updateByPrimaryKeySelective(DetailObjColumn record);

    int updateByPrimaryKey(DetailObjColumn record);
}