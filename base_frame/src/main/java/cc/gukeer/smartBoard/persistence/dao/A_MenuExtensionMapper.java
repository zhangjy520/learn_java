package cc.gukeer.smartBoard.persistence.dao;


import cc.gukeer.smartBoard.modelView.MenuView;

import java.util.List;

/**
 * Created by conn on 2016/8/21.
 */
public interface A_MenuExtensionMapper {
    List<MenuView> selectMenusByRoleId(Integer roleId);
}
