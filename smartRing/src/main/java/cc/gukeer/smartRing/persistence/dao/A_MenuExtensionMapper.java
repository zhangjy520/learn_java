package cc.gukeer.smartRing.persistence.dao;


import cc.gukeer.smartRing.modelView.MenuView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/21.
 */
public interface A_MenuExtensionMapper {
    List<MenuView> selectMenusByRoleId(String roleId);

    Map getUserInfo(@Param("userId") String userId);
}
