package cc.gukeer.smartRing.service;

import cc.gukeer.smartRing.modelView.MenuView;
import cc.gukeer.smartRing.modelView.RoleView;
import cc.gukeer.smartRing.persistence.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	User saveUser(User user);

	User getByAccountAndPwd(String account, String pwd);

	User getUserById(String userId);

	List<RoleView> selectRoleViewByUserId(String userId);

	List<MenuView> selectMenusByRoleId(String roleId);

	List<User> getUsersByIds(List<String> ids);

	User getUserByTypeAndRefId(int type, String refId);

	Map getUserInfo(String userId);
}
