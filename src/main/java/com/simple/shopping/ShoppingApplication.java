package com.simple.shopping;

import com.simple.shopping.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class ShoppingApplication implements WebMvcConfigurer{

	@Autowired
	LoginCheckInterceptor loginCheckInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("========================================================");
		if(!registry.hasMappingForPattern("/webjars/**")){
			System.out.println("has not mapping for pattern : /webjars/**");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/");
//			registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/");
		}
		if(!registry.hasMappingForPattern("/static/**")){
			System.out.println("has not mapping for pattern : /static/**");
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		System.out.println("========================================================");
	}

	@Bean
	public FilterRegistrationBean httpMethodFilterRegistration(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new HiddenHttpMethodFilter());
		registration.addUrlPatterns("/*");
		registration.setName("httpMethodFilter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE+1);
		return registration;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/users/login");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor);
	}
  
}
