package com.beyond.algm.algmcallboot.controller;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author ：lindewei
 * @Description: API调用计费
 * @date ：9:30 2017/11/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgChargingCallControllerTest {
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

    //API调用计费
    @Test
    public void algChargingCall() throws Exception{

        String result = this.mockMvc.perform(get("/lindw/TestJava/1.3.8").contentType(MediaType.APPLICATION_JSON)
                .param("keyValue","9a98142cbff747b38461259d5638ebf2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
