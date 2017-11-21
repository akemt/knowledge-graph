package com.beyond.algm.algmalgorithmsboot.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author ：Lindewei
 * @Description:算法新增
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddAlgorithmControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private ModuleController moduleController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }
    @Test
    public void contextLoads() {

    }

    //算法新增
    @Test
    public void showFile() throws Exception{
        String result = this.mockMvc.perform(post("/addAlgorithm").contentType(MediaType.APPLICATION_JSON)
                .param("catName","计算机视觉")
                .param("envType","2")
                .param("isColony","1")
                .param("isOpenSrc","1")
                .param("isTrain","1")
                .param("lanName","Java")
                .param("licName","Algmarket platform Licese")
                .param("modDesc","123")
                .param("modId","java123")
                .param("modName","测试")
                .param("needWeb","1"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
