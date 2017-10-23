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
    /*@Test
    public void listAlg() throws Exception {
        String result = this.mockMvc.perform(get("/algcatrank/{catname}/{usage}","deeplearning","star_cnt").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"modSn":"1","usrSn":"aac44b648b10429cbaf85a6ae0113a65","lanSn":"2","catSn":"123","licSn":"333","atlSn":null,"modName":"张传智1","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null},{"modSn":"2","usrSn":"fasdfdsafsadfsadfsdfds","lanSn":"33","catSn":"121","licSn":"333","atlSn":null,"modName":"张现杰","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null}]
    }*/
    @Test
    public void listAlg() throws Exception {
        String result = this.mockMvc.perform(get("/algcatrank/{catname}/{usage}","deeplearning","follow_cnt").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果 ：
    }
}
