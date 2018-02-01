package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				TestConfig.class
				, OrmConfig.class
				, MvcConfig.class
		})
@WebAppConfiguration
public class AdminAccountControllerTest {
//	Bounding by hand because @Qualifier doesn't work
	@Autowired
	@Qualifier("userServiceTest")
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
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
			.setBirthday(new LocalDate(1999, 1, 2))
			.setRoles("ROLE_USER")
			.setAddress("Sim salabim")
			.setStatus(0);

	private Car car = new Car()
			.setName("Batmobile v1000")
			.setCountry("Mexico")
			.setColor("Blackest")
			.setPrice(9999)
			.setReleaseDate(new LocalDate(1939, 12, 02))
			.setStatus(0);

	private Invoice invoice = new Invoice()
			.setUser(user)
			.setCar(car)
			.setStartsAt(new LocalDateTime(1990, 12, 15, 15, 30))
			.setExpiresAt(new LocalDateTime(2000, 12, 15, 15, 30))
			.setPrice(1000000000)
			.setDescription("Veeeeryyyy loooong invoice")
			.setStatus(0);

	@Before
	public void startUp() {
		Mockito.reset(carService, userService, invoiceService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void show_all_orders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/invoice/show_all"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;
		Mockito.verify(invoiceService, Mockito.times(1)).getAll();
		Mockito.verifyNoMoreInteractions(invoiceService);
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
		Mockito.when(invoiceService.getById(1)).thenReturn(invoice);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/invoice/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		;

		Mockito.verify(invoiceService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(invoiceService);
	}

	@Test
    @Category(BasicTest.class)
    public void account() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/admin/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/account"));

    }
}
