package com.simple.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@SpringBootApplication
public class ShoppingApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("========================================================");
		if(!registry.hasMappingForPattern("/webjars/**")){
			System.out.println("webjars has not mapping for pattern!!");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/");
//			registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/");
		}

		System.out.println("========================================================");
	}

}
