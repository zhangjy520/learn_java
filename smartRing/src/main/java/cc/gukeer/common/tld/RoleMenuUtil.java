package cc.gukeer.common.tld;

import cc.gukeer.smartRing.persistence.entity.RoleMenu;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by conn on 2016/8/8.
 */
public class RoleMenuUtil {

    public static Boolean roleMenuContains(String roleId, String menuId, List<RoleMenu> list) {
        if (null == list || list.size() == 0) return false;

        for (RoleMenu rm : list) {
            if (StringUtils.equals(rm.getRoleId(), roleId) && StringUtils.equals(rm.getMenuId(), menuId)) {
                return true;
            }
        }
        return false;
    }
}
