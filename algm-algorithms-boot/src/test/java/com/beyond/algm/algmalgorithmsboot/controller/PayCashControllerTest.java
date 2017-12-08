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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author ：zhangchuanzhi
 * @Description:测试
 * @date ：9:47 2017/10/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayCashControllerTest {

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
    public void payRecord() throws Exception{


        String result = this.mockMvc.perform(post("/pay/payRecord").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","aac44b648b10429cbaf85a6ae0f63a65")
                .param("page","0")
                .param("rows","2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * @author ：lindw
     * @Description:用户注册，赠送积分
     */
    @Test
    public void payCash() throws Exception{
        String result = this.mockMvc.perform(post("/presentcash").contentType(MediaType.APPLICATION_JSON)
                .param("freeBal","5.2f"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * @author ：lindw
     * @Description:积分充值
     */
    @Test
    public void recharge() throws Exception{
        String result = this.mockMvc.perform(post("/recharge").contentType(MediaType.APPLICATION_JSON)
                .param("freeBal","5.2f"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
