package cc.gukeer.common.shiro;

import cc.gukeer.smartBoard.modelView.MenuView;
import cc.gukeer.smartBoard.modelView.RoleView;
import cc.gukeer.smartBoard.persistence.entity.User;
import cc.gukeer.smartBoard.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conn on 2016/7/29.
 */
public class ShiroAuthRealm extends AuthorizingRealm {

    UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) super.getAvailablePrincipal(principalCollection);

        SimpleAuthorizationInfo simpleAuthorInfo = null;
        if (null != user) {

            simpleAuthorInfo = new SimpleAuthorizationInfo();

            List<String> permissions = new ArrayList<String>();

            List<RoleView> roleViews = userService.selectRoleViewByUserId(user.getId());
            for (RoleView roleView : roleViews) {
                String role = roleView.getRoleIdentify();
                if (!StringUtils.isEmpty(role)) {
                    simpleAuthorInfo.addRole(role);

                    List<MenuView> menuViews = userService.selectMenusByRoleId(roleView.getId());

                    for (MenuView menuView : menuViews) {
                        String permission = menuView.getPermission();
                        if (!StringUtils.isEmpty(permission)) {
                            permissions.add(permission);
                        }
                    }
                }
            }

            simpleAuthorInfo.addStringPermissions(permissions);
        } else {
            throw new AuthorizationException();
        }

        return simpleAuthorInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String pwd = String.valueOf(usernamePasswordToken.getPassword());
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        User user = userService.getByAccountAndPwd(username, pwd);

        if (user != null) {
            return new SimpleAuthenticationInfo(user,
                    user.getPassword(), getName());
        }

        return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
