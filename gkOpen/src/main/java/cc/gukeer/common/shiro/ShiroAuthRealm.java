package cc.gukeer.common.shiro;

import cc.gukeer.open.common.LoginUserType;
import cc.gukeer.open.persistence.entity.OpenUser;
import cc.gukeer.open.service.OpenUserService;
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

    OpenUserService openUserService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        OpenUser user = (OpenUser) super.getAvailablePrincipal(principalCollection);

        SimpleAuthorizationInfo simpleAuthorInfo = null;
        if (null != user) {

            simpleAuthorInfo = new SimpleAuthorizationInfo();

            List<String> permissions = new ArrayList<String>();
            if (user.getUserType()== LoginUserType.ADMIN.getStatenum()){
                simpleAuthorInfo.addRole("admin");
            }

           /* List<RoleView> roleViews = openUserService.selectRoleViewById(user.getId());
            for (RoleView roleView : roleViews) {
                String role = roleView.getRoleIdentify();
                if (!StringUtils.isEmpty(role)) {
                    simpleAuthorInfo.addRole(role);

                    List<MenuView> menuViews = openUserService.selectMenusByRoleId(roleView.getId());

                    for (MenuView menuView : menuViews) {
                        String permission = menuView.getPermission();
                        if (!StringUtils.isEmpty(permission)) {
                            permissions.add(permission);
                        }
                    }
                }
            }

            simpleAuthorInfo.addStringPermissions(permissions);*/
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
            throw new UnknownAccountException();
        }

        OpenUser user = openUserService.getByAccountAndPwd(username, pwd);
        if (user != null) {
            return new SimpleAuthenticationInfo(user,
                    user.getPassword(), getName());
        }else {
            throw new IncorrectCredentialsException();
        }

    }

    public void setOpenUserService(OpenUserService openUserService) {
        this.openUserService = openUserService;
    }
}
