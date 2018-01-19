package com.rockka.carrent.config;

import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	@Bean
	public UserService userService(){
		return Mockito.spy(UserService.class);
	}

	@Bean
	public CarService carService(){
		return Mockito.spy(CarService.class);
	}

	@Bean
	public OrderService orderService(){
		return Mockito.spy(OrderService.class);
	}
}
