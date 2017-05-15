package cc.gukeer.smartBoard.persistence.dao;

import cc.gukeer.smartBoard.persistence.entity.UserRole;
import cc.gukeer.smartBoard.persistence.entity.UserRoleExample;
import cc.gukeer.smartBoard.persistence.entity.UserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(UserRoleKey key);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}