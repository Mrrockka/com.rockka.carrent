package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.SecurityConfig;
import com.rockka.carrent.config.TestConfig;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.SecurityTest;
import org.junit.Before;
import org.junit.Ignore;
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
public class LoginControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@Before
	public void startUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void login() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/login"));
	}

	@Test
	@Category(SecurityTest.class)
	public void account() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/account"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/access_denied"));
	}

	@Test
	@Category(BasicTest.class)
	public void registration() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/registration"));
	}

}
