package com.placideh.ticketingapi;

import com.placideh.ticketingapi.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean=new FilterRegistrationBean<>();
		AuthFilter authFilter=new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/v1/*");
		return registrationBean;

	}

}
