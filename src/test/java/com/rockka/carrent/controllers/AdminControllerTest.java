package com.rockka.carrent.controllers;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				TestConfig.class
				, OrmConfig.class
				, MvcConfig.class
		})
@WebAppConfiguration
public class AdminControllerTest {

	@Autowired
	private CarService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

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
		Mockito.verify(orderService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(orderService);
	}

	@Test
	public void show_all_users() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(userService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(userService);
	}

	@Test
	public void show_all_cars() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/car/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(carService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	public void show_car() throws Exception {
		Car car = new Car()
				.setName("Batmobile v1000")
				.setCountry("Mexico")
				.setColor("Blackest")
				.setPrice(9999)
				.setReleaseDate(new Date(1939, 12, 02))
				.setCreatedAt(new Date())
				.setDeleted();
		Mockito.when(carService.getById(1)).thenReturn(car);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/car/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/show_car"))
				.andExpect(MockMvcResultMatchers.model().attribute("car" , Matchers.hasProperty("name", Matchers.is("Batmobile v1000"))))
		;
		Mockito.verify(carService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	public void show_user() throws Exception {
		User user = new User()
				.setNickname("Abdula")
				.setPassword("123")
				.setFirstName("Abdula")
				.setSecondName("Abdurahman")
				.setBirthday(new Date(1999, 1, 2))
				.setRoles("ROLE_USER")
				.setAddress("Sim salabim")
				.setId(10)
				.setCreatedAt(new Date())
				.setModifiedAt(new Date())
				.setIsDeleted(0);
		Mockito.when(userService.getUserByNickname("abd")).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/abd"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("admin/show_user"))
				.andExpect(MockMvcResultMatchers.model().attribute("user", Matchers.hasProperty("roles", Matchers.is("ROLE_USER"))))
		;
		Mockito.verify(userService, Mockito.times(1)).getUserByNickname("abd");
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	@Ignore
	public void show_order() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
		;
		Mockito.verify(orderService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(orderService);
	}
}
