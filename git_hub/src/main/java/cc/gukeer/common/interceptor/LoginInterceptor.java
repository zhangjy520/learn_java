package cc.gukeer.common.interceptor;

import cc.gukeer.common.utils.GukeerStringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String fullUrl = request.getRequestURL().toString();
        String context = request.getContextPath();
        String _context = context + "/";

        if (fullUrl.endsWith(context) || fullUrl.endsWith(_context)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("loginUser");
        if (GukeerStringUtil.isNullOrEmpty(obj)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("bbbbbbbbbbb");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("aaaaa");
    }
}