package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.RoleMenu;
import cc.gukeer.smartBoard.persistence.entity.RoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    int deleteByExample(RoleMenuExample example);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<RoleMenu> selectByExample(RoleMenuExample example);

    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);
}