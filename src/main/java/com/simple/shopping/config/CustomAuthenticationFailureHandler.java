package com.simple.shopping.config;

import com.simple.shopping.security.oauth2.NotSocialLoginContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String loginId = NotSocialLoginContext.getEmail();
        if(loginId != null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/users/already-social-join");
        }else{
            SessionFlashMapManager manager = new SessionFlashMapManager();
            FlashMap flashMap = new FlashMap();
            if(e.getClass() == BadCredentialsException.class) {
                flashMap.put("eMessage", "존재하지 않는 이메일 입니다");
            }
            flashMap.put("loginId", httpServletRequest.getParameter("id"));
            manager.saveOutputFlashMap(flashMap, httpServletRequest, httpServletResponse);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/users/login)");
        }
    }
}
