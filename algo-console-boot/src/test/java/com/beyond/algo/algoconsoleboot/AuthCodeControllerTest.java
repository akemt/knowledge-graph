package com.beyond.algo.algoconsoleboot;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCodeControllerTest {
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
    public void create() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/create").contentType(MediaType.APPLICATION_JSON)
                .param("acdSn","aac44b648b10429cbaf85a6ae0113a85")
                .param("usrSn","aac44b648b10430cbaf85a6ae0113a75")
                .param("acdName","thisisacdname")
                .param("acdId","thisisacdid001")
                .param("callFromClient","1"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
