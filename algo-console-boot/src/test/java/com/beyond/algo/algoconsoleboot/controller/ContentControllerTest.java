package com.beyond.algo.algoconsoleboot.controller;

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
 * @author ：zhangchuanzhi
 * @Description:测试用户调用情况
 * @date ：15:26 2017/10/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentControllerTest {
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
    public void algorithmRecord() throws Exception{
        // 用户使用情况
        String result = this.mockMvc.perform(post("/content/algorithmRecord").contentType(MediaType.APPLICATION_JSON)
                .param("callUsrSn","aac44b648b10429cbaf85a6ae0113a65")
                .param("page","0")
                .param("rows","2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void algorithmEarnRecord() throws Exception{
       // 用户收益情况
        String result = this.mockMvc.perform(post("/content/algorithmEarnRecord").contentType(MediaType.APPLICATION_JSON)
                .param("ownerUsrSn","aac44b648b10429cbaf85asse0113aa5")
                .param("needNotify","1")
                .param("rows","5"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void collectArticles() throws Exception{
        String result = this.mockMvc.perform(post("/content/collectArticles").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","aac44b648b10429cbaf85a6ae0113a65")
                .param("page","0")
                .param("rows","1"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void algorithmRank() throws Exception{
        String result = this.mockMvc.perform(post("/content/algorithmRank").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void searchArticles() throws Exception{
        String result = this.mockMvc.perform(post("/content/searchArticles").contentType(MediaType.APPLICATION_JSON)
                .param("id","1"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
