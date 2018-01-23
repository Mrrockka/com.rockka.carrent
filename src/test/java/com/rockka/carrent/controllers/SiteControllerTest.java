package com.rockka.carrent.controllers;

import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.OrmConfig;
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
public class SiteControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webContext;

	@Before
	public void startUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	@Category(BasicTest.class)
	public void infoTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/info"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/info"));
	}
	@Test
	@Category(BasicTest.class)
	public void accessDenied() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/access_denied"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/access_denied"));
	}

	@Test
	@Category(BasicTest.class)
	public void logout() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/login"));
	}

	@Test
	@Category(BasicTest.class)
	public void success() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/success"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("public/success"));
	}

	@Test
	@Category(SecurityTest.class)
	public void username() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/username"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("unknown"));
	}
}
