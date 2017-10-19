package com.beyond.algo.algoconsoleboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgControllerTests {

	@Autowired
	protected WebApplicationContext wac;
	private MockMvc mockMvc;
	private MockHttpSession session;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
		this.session = new MockHttpSession();
	}
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	public void createOrgTest() throws Exception {
		String result = this.mockMvc.perform(post("/org/create").contentType(MediaType.APPLICATION_JSON)
				.param("usrCode", "testOrg0")
				.param("usrName", "测试组织0")
				.param("email", "test@qq.com")
				.param("ownerId", "62a6a211ecfc480bbc9c67d65a44b535")
				.session(session))
				.andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
		log.info(result);
	}

}
