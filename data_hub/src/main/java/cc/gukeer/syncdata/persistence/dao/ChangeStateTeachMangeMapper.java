package cc.gukeer.syncdata.persistence.dao;

import cc.gukeer.syncdata.persistence.entity.ChangeStateTeachMange;
import cc.gukeer.syncdata.persistence.entity.ChangeStateTeachMangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeStateTeachMangeMapper {
    int countByExample(ChangeStateTeachMangeExample example);

    int deleteByExample(ChangeStateTeachMangeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChangeStateTeachMange record);

    int insertSelective(ChangeStateTeachMange record);

    List<ChangeStateTeachMange> selectByExample(ChangeStateTeachMangeExample example);

    ChangeStateTeachMange selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChangeStateTeachMange record, @Param("example") ChangeStateTeachMangeExample example);

    int updateByExample(@Param("record") ChangeStateTeachMange record, @Param("example") ChangeStateTeachMangeExample example);

    int updateByPrimaryKeySelective(ChangeStateTeachMange record);

    int updateByPrimaryKey(ChangeStateTeachMange record);
}