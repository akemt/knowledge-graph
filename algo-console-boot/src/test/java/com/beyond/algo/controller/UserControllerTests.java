package com.beyond.algo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

	@Autowired
	protected WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}
	@Test
	public void contextLoads() {

	}
	@Test
	public void registerUser() throws Exception{


		String result = this.mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.param("usrName","qihe")
				.param("passwd","12345678")
				.param("email","test@qq.com"))
				.andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	public void testOAuth2() throws Exception{

		String result = this.mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.param("usrName","qihe")
				.param("passwd","12345678"))
				.andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

}
