package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.entity.Notify;
import cn.gukeer.platform.persistence.entity.Role;

import java.util.List;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_RoleExtensionMapper {
    List<RoleView> selectRoleViewByUserId(String userId);
    
    int insertRoleBackId(Role role);
    
    List<Role> findRoleByApp(String appId);
}
