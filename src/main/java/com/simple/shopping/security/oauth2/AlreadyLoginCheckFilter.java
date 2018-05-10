package com.simple.shopping.security.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

public class AlreadyLoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String currentPrincipalName = authentication.getName();
            if(!"annonymousUser".equals(currentPrincipalName)) { // 인증된 사용자일 경우

                servletRequest.setAttribute("alreadyLoginId", currentPrincipalName);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse); //다음 필터로 요청과 응답 전달
    }

    @Override
    public void destroy() {

    }
}
