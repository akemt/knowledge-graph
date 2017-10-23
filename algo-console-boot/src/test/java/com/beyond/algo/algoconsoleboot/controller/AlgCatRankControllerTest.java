package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.common.UUIDUtil;
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
public class AlgCatRankControllerTest {
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
    public void listAlg() throws Exception {
        String result = this.mockMvc.perform(get("/algcatrank/{catname}/{usage}","deeplearning","cat").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"acdSn":"3249d819260548a6b93981dad55a452f","usrSn":"8303430b322d43b1bc6bdd63d2103fa3","acdName":"0000000000000000","acdId":"BeyondrJelJ3rWrDj5rfG1","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null},{"acdSn":"9ea7ae09d32b4a2e85c5945e4f51dde4","usrSn":"8303430b322d43b1bc6bdd63d2103fa3","acdName":"thisnewid","acdId":"ce8205470b5b4c6583e0c2115f808355","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null}]
    }
}
