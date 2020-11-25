package com.zgh.onlinevideo.interceptor;

import com.zgh.onlinevideo.util.Constants;
import com.zgh.onlinevideo.util.VideoUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        Cookie[] cookies = request.getCookies();
        ServletContext application = request.getServletContext();

        boolean valid = VideoUtil.checkLoginToken(cookies, application);

        if (valid) {
            // token有效 恢复登录状态
            session.setAttribute(Constants.LOGIN_USER, VideoUtil.getUserByApplication(cookies, application));
        }

        // 请求通过
        return true;
    }
}
