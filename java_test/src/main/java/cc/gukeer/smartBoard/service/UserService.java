package cc.gukeer.smartBoard.service;

import cc.gukeer.smartBoard.modelView.MenuView;
import cc.gukeer.smartBoard.modelView.RoleView;
import cc.gukeer.smartBoard.persistence.entity.User;

import java.util.List;

public interface UserService {

	User saveUser(User user);

	User getByAccountAndPwd(String account, String pwd);

	User getUserById(Integer userId);

	List<RoleView> selectRoleViewByUserId(Integer userId);

	List<MenuView> selectMenusByRoleId(Integer roleId);
}
