package cn.gukeer.platform.persistence.dao;

import java.util.List;

import cn.gukeer.platform.modelView.MenuView;
import cn.gukeer.platform.persistence.entity.Menu;

/**
 * Created by conn on 2016/8/21.
 */
public interface A_MenuExtensionMapper {
    List<MenuView> selectMenusByRoleId(String roleId);
    List<Menu> selevtMenuByBelong(Integer belong);
}
