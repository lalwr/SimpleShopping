package com.simple.shopping.security;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.security.oauth2.NotSocialLoginContext;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userService.getUserByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException(username + " not found");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            NotSocialLoginContext.setEmail(username);
            throw new UsernameNotFoundException("소셜계정으로 가입된 계정입니다.");
        }

        List<GrantedAuthority> list = new ArrayList<>();
        user.getRoles().forEach(role -> list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));

        LoginUserInfo userDetails = new LoginUserInfo(user.getEmail(), user.getPassword(), list);
        userDetails.setNo(user.getNo());
        userDetails.setName(user.getName());
        return userDetails;
    }


}
