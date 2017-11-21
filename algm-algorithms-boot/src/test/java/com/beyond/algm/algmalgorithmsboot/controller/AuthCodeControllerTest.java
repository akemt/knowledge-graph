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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCodeControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;


    //private String acdSn; //本表主键
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }
    @Test
    public void contextLoads() {
    }
    @Test
    public void listAuthCode() throws Exception {
        String result = this.mockMvc.perform(get("/authCode/listAuthCode/{usrSn}","8303430b322d43b1bc6bdd63d2103fa3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"acdSn":"3249d819260548a6b93981dad55a452f","usrSn":"8303430b322d43b1bc6bdd63d2103fa3","acdName":"0000000000000000","acdId":"BeyondrJelJ3rWrDj5rfG1","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null},{"acdSn":"9ea7ae09d32b4a2e85c5945e4f51dde4","usrSn":"8303430b322d43b1bc6bdd63d2103fa3","acdName":"thisnewid","acdId":"ce8205470b5b4c6583e0c2115f808355","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null}]
    }
    @Test
    public void listAuthCodeDomain() throws Exception {
        String result = this.mockMvc.perform(get("/authCode/listAuthCodeDomain/{acdSn}","7516d699937e447ba557b99472cbb798").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"addSn":"023427a40baa483c82fd25bfbb56ec96","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge2.com"},{"addSn":"7b72969e280f49c0b2600e138735d1fe","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge3.com"},{"addSn":"e6c657cf2e2a48809367182106d8df55","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge1.com"}]
    }
    @Test
    public void generateKey() throws Exception {
        String result = this.mockMvc.perform(post("/authCode/generateKey").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","8303430b322d43b1bc6bdd63d2103fa3")
                .param("acdName","0000000000000000")
                .param("callFromClient","1")
                .param("addUrl","www.baidulaingge1.com")
                .param("addUrl","www.baidulaingge2.com")
                .param("addUrl","www.baidulaingge3.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void deleteAuthCode() throws Exception {
        //通过acdSn主键删除
        //同时删除内外两个表内容
        String result = this.mockMvc.perform(get("/authCode/deleteAuthCode/{acdSn}","ba1cf831978740f19b019504a2e3a0fb").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void update() throws Exception {
        String result = this.mockMvc.perform(post("/authCode/update").contentType(MediaType.APPLICATION_JSON)
                .param("acdSn","7516d699937e447ba557b99472cbb798")
                .param("usrSn","8303430b322d43b1bc6bdd63d2103fa3")
                .param("acdName","thisnewid")
                .param("acdId","ce8205470b5b4c6583e0c2115f808355")
                .param("callFromClient","1")
                .param("addUrl","www.baidulaingge01.com")
                .param("addUrl","www.baidulaingge02.com")
                .param("addUrl","www.baidulaingg03.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
