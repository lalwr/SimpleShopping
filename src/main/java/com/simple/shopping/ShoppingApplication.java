package com.simple.shopping;

import com.simple.shopping.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
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

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/users/login");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor);
	}
}
