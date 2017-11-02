package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.vo.AddAlgorithmVo;
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
                .param("usrSn","45454545")
                .param("lanName","Java")
                .param("catName","dee")
                .param("licName","apache协议")
                .param("modId","1122")
                .param("modDesc","asasasadasdsadas21212"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
