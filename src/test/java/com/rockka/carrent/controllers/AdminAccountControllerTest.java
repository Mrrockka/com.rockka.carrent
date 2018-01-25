package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
public class AdminAccountControllerTest {

	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

//	Basic general test objects
	private User user = new User()
			.setUsername("Abdula")
			.setPassword("123")
			.setFirstName("Abdula")
			.setSecondName("Abdurahman")
			.setBirthday(new Date(1999, 1, 2))
			.setRoles("ROLE_USER")
			.setAddress("Sim salabim")
			.setCreatedAt(new Date())
			.setModifiedAt(new Date())
			.setUserStatus(0);

	private Car car = new Car()
			.setName("Batmobile v1000")
			.setCountry("Mexico")
			.setColor("Blackest")
			.setPrice(9999)
			.setReleaseDate(new Date(1939, 12, 02));

	private Order order = new Order()
			.setUser(user)
			.setCar(car)
			.setStartsAt(new Date(1990, 12, 15))
			.setExpiresAt(new Date(2000, 12, 15))
			.setPrice(1000000000)
			.setDescription("Veeeeryyyy loooong order")
			.setOrderStatus(1);

	@Before
	public void startUp() {
		Mockito.reset(carService, userService, orderService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void show_all_orders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(orderService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(orderService);
	}

	@Test
	@Category(BasicTest.class)
	public void show_all_users() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(userService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(userService);
	}

	@Test
	@Category(BasicTest.class)
	public void show_all_cars() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/car/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(carService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	@Category(DetailTest.class)
	public void show_car() throws Exception {
		Mockito.when(carService.getById(1)).thenReturn(car);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/car/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(carService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	@Category(BasicTest.class)
	public void show_user() throws Exception {
		Mockito.when(userService.getByUsername("abd")).thenReturn(user);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/abd"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(userService, Mockito.times(1)).getByUsername("abd");
		Mockito.verifyNoMoreInteractions(carService);
	}

	@Test
	@Category(BasicTest.class)
	public void show_order() throws Exception {
		Mockito.when(orderService.getById(1)).thenReturn(order);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/order/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;

		Mockito.verify(orderService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(orderService);
	}

	@Test
    @Category(BasicTest.class)
    public void account() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/admin/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/account"));

    }
}
