package cn.gukeer.common.interceptor;

import cn.gukeer.common.config.RoleUtil;
import cn.gukeer.common.exception.PermissionException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        Subject subject = SecurityUtils.getSubject();
        System.out.println("=============uri:" + uri + " isAuthenticated:" + subject.isAuthenticated());

        return true;
    }
}
