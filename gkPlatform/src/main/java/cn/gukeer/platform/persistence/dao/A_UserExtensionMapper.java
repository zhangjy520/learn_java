package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.persistence.entity.User;
import cn.gukeer.platform.persistence.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_UserExtensionMapper {

    String insertUserBatch(List<User> userList);

    Integer insertUserRoleBatch(List<UserRole> userRole);

    List<User> selectUserByRoleId(Map map);

    List<Map<Object, Object>> selectUserByAppId(String appId);
}
