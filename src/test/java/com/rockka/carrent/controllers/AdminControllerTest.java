package com.rockka.carrent.controllers;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				TestConfig.class
				, AppConfig.class
				, MvcConfig.class
		})
@WebAppConfiguration
public class AdminControllerTest {

	@Autowired
	@Qualifier("carServiceTest")
	CarService carService;
	@Autowired
	@Qualifier("userServiceTest")
	UserService userService;
	@Autowired
	@Qualifier("orderServiceTest")
	OrderService orderService;
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@Before
	public void startUp() {
		Mockito.reset(carService, userService, orderService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void show_all_orders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		verify(orderService, times(1)).getAll();
		verifyNoMoreInteractions(orderService);
	}

	@Test
	public void show_all_users() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		verify(userService, times(1)).getAll();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void show_all_cars() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/cars/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		verify(carService, times(1)).getAll();
		verifyNoMoreInteractions(carService);
	}
}
