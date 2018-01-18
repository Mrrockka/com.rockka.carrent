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
	public UserService userServiceTest(){
		return Mockito.mock(UserService.class);
	}

	@Bean
	public CarService carServiceTest(){
		return Mockito.mock(CarService.class);
	}

	@Bean
	public OrderService orderServiceTest(){
		return Mockito.mock(OrderService.class);
	}
}
