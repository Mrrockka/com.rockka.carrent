package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.test_categories.BasicTest;
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
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {
				TestConfig.class
				, OrmConfig.class
				, MvcConfig.class
		})
@WebAppConfiguration
public class IndexControllerTest {
	@Autowired
	private CarService carService;

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@Before
	public void startUp() {
		Mockito.reset(carService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void pathTest(Model model) throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/index"));

		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/index"));

		mockMvc.perform(MockMvcRequestBuilders.get("/home"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/index"));

		Mockito.verify(carService, Mockito.times(3)).getAll();
		Mockito.verifyNoMoreInteractions(carService);
	}

}
