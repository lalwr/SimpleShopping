package com.simple.shopping.interceptor;

import com.simple.shopping.domain.User;
import com.simple.shopping.security.LoginUserInfo;
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

        if(authentication != null && authentication.getPrincipal() instanceof LoginUserInfo){
            LoginUserInfo loginUserInfo = (LoginUserInfo)authentication.getPrincipal();
            User user = new User();
            user.setNo(loginUserInfo.getNo());
            user.setName(loginUserInfo.getName());
            user.setEmail(loginUserInfo.getEmail());

            User loginUser = userService.getUserByEmail(loginUserInfo.getEmail());
            request.setAttribute("loginUser", loginUser);
        }

        //refer 설정
        String refer = request.getHeader("refer");
        String uri = request.getRequestURI();
        if("/users/login".equals(uri)){
            uri = refer;
        }
        request.setAttribute("loginRedirect", uri);
        return true;
    }
}
