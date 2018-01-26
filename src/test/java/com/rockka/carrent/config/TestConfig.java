package com.rockka.carrent.config;

import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	public UserService userServiceTest(){ return Mockito.spy(UserService.class); }

	@Bean
	public CarService carService(){
		return Mockito.spy(CarService.class);
	}

	@Bean
	public InvoiceService invoiceService(){
		return Mockito.spy(InvoiceService.class);
	}
}
