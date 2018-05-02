package com.simple.shopping.interceptor;

import com.simple.shopping.domain.User;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("loginCheckInterceptor")
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            String email = authentication.getName();
            User loginUser = userService.getUserByEmail(email);
            if(loginUser != null && "Y".equals(loginUser.getUse())){
                request.setAttribute("loginUser", loginUser);
            }else {
                response.sendRedirect("/users/login");
            }
        }
        return true;
        }
}
