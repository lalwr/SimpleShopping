package com.simple.shopping.config;

import com.simple.shopping.security.ShoppingTokenRepositoryImpl;
import com.simple.shopping.security.oauth2.AlreadyLoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    ShoppingTokenRepositoryImpl shoppingTokenRepositoryImpl;

    @Autowired
    ApplicationContext context;

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
                .requestMatchers(new AntPathRequestMatcher("/**.html")).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/users/emailOverlap").permitAll()
                .antMatchers(HttpMethod.GET,"/product/**").permitAll()
                .antMatchers("/product/**").hasRole("USER")
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().ignoringAntMatchers("/**")
//                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().disable()
//                .and().formLogin() //시큐리티 로그인 사용
                .and()
                    .formLogin()
                        .loginProcessingUrl("/users/login")
                        .loginPage("/users/login").usernameParameter("id").passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureHandler(customAuthenticationFailureHandler())
                .and().rememberMe().tokenRepository(shoppingTokenRepositoryImpl).rememberMeParameter("remember-me").tokenValiditySeconds(1209600)
                .and().logout().permitAll()
                .and()
                    .addFilterBefore(new AlreadyLoginCheckFilter(), BasicAuthenticationFilter.class)
                    .addFilterBefore((Filter)context.getBean("sso.filter"), BasicAuthenticationFilter.class);

    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
        customAuthenticationSuccessHandler.setDefaultUrl("/users/user");
        customAuthenticationSuccessHandler.setTargetUrlParameter("loginRedirect");
        customAuthenticationSuccessHandler.setUseReferer(true);
        return customAuthenticationSuccessHandler;
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler(){
        CustomAuthenticationFailureHandler customAuthenticationFailureHandler = new CustomAuthenticationFailureHandler();
        return customAuthenticationFailureHandler;
    }

}
