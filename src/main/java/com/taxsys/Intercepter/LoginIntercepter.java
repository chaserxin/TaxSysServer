package com.taxsys.Intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 刘华鑫 on 2017/5/22.
 */
public class LoginIntercepter implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //此处cook获取的是一个数组
        Cookie[] cookies = httpServletRequest.getCookies();
        //获取COOKIE时得先判断是否为空，然后再去获取相关值
        if(cookies.length!=0) {
            for(int i=0; i< cookies.length;i++) {
                if(cookies[i].getName().equalsIgnoreCase("sessionId")){
                    String sessionId = cookies[i].getValue().toString();
                    if(httpServletRequest.getSession().getId().equals(sessionId)) {
                        // 用户登录过
                        return true;
                    }
                }
            }

        }
        //没有登陆，转向登陆界面
        httpServletResponse.sendRedirect("/login.html");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
