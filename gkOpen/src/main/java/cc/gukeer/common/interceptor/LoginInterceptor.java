package cc.gukeer.common.interceptor;

import cc.gukeer.common.exception.PermissionException;
import cc.gukeer.common.exception.PermissionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by conn on 2016/8/5.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获得请求路径的uri
        String uri = request.getRequestURI();
        if (StringUtils.isEmpty(uri)) {
            throw new PermissionException();
        }
        Subject OpenUser = SecurityUtils.getSubject();
        if (OpenUser.isAuthenticated()) return true;
        response.sendRedirect("admin/index");
        return false;
    }

}
