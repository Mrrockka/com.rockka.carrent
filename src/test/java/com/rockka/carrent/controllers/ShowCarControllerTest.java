package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.enums.CarStatus;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.test_categories.BasicTest;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ShowCarControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;
	@Autowired
	private CarService carService;

	@Before
	public void startUp() {
		Mockito.reset(carService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void getById() throws Exception {
		Car car = new Car()
				.setPrice(123)
				.setCountry("England")
				.setColor("Double gray white")
				.setName("Nested exception")
				.setId(1)
				.setReleaseDate(new LocalDate())
				.setStatus(CarStatus.DELETED);
		Mockito.when(carService.getById(1)).thenReturn(car);
		mockMvc.perform(MockMvcRequestBuilders.get("/car/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/show_car"))
				.andExpect(MockMvcResultMatchers.model().attribute(
						"car", Matchers.hasProperty("id", Matchers.is(1L))
				));
		Mockito.verify(carService, Mockito.times(1)).getById(1);
		Mockito.verifyNoMoreInteractions(carService);
	}

}
