package com.example.demo;

import com.example.demo.Filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UserManagementApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
	 FilterRegistrationBean fb = new FilterRegistrationBean();
	 fb.setFilter(new JWTFilter());
	 fb.addUrlPatterns("/api/v1/**");
		return fb;
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
