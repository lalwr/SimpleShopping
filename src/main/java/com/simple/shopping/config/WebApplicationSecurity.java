package com.simple.shopping.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter{

    /*
    permitAll()의 경우는 인증 검사가 수행되긴 한다. static resource의 경우는 아예 인증을 무시하게 하는 것이 불필요한 sql이 실행안되게 할 수 있다.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
                .authorizeRequests()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/users/emailOverlap").permitAll()
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().ignoringAntMatchers("/**")
//                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().disable()
//                .and().formLogin() //시큐리티 로그인 사용
                .and().
                    formLogin()
                    .loginProcessingUrl("/users/login")
                    .loginPage("/users/login").usernameParameter("id").passwordParameter("password").defaultSuccessUrl("/users/user")
//                .and().rememberMe().tokenRepository(simpleBoardTokenRepositoryImpl).rememberMeParameter("remember-me").tokenValiditySeconds(1209600)
                .and().logout().permitAll();
    }

}
