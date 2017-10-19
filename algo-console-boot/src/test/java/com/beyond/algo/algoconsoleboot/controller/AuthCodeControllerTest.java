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
    public void listauthcode() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/listauthcode").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","0aeabf55b32e4c0484670656f274e951"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void listauthcodedomain() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/listauthcodedomain").contentType(MediaType.APPLICATION_JSON)
                .param("acdSn","b95de94a97464e7e91258fee2c65b1f2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"addSn":"023427a40baa483c82fd25bfbb56ec96","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge2.com"},{"addSn":"7b72969e280f49c0b2600e138735d1fe","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge3.com"},{"addSn":"e6c657cf2e2a48809367182106d8df55","acdSn":"b95de94a97464e7e91258fee2c65b1f2","addUrl":"www.baidulaingge1.com"}]
    }
    @Test
    public void generatekey() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/generatekey").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","8303430b322d43b1bc6bdd63d2103fa3")
                .param("acdName","thisid")
                .param("callFromClient","1")
                .param("addUrl","www.baidulaingge1.com")
                .param("addUrl","www.baidulaingge2.com")
                .param("addUrl","www.baidulaingge3.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void deleteauthcode() throws Exception {
        //通过acdSn主键删除
        //同时删除内外两个表内容
        String result = this.mockMvc.perform(get("/authcode/deleteauthcode/{acdSn}","8cdf6ad058844541a50ba8e23fd6d181").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void update() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/update").contentType(MediaType.APPLICATION_JSON)
                .param("acdSn","7c957748fe3d48a7ba904124bfa8b36c")
                .param("usrSn","8303430b322d43b1bc6bdd63d2103fa3")
                .param("acdName","thisnewid")
                .param("acdId","ce8205470b5b4c6583e0c2115f808355")
                .param("callFromClient","1")
                .param("addUrl","www.baidulaingge10.com")
                .param("addUrl","www.baidulaingge11.com")
                .param("addUrl","www.baidulaingg12.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
//    @Test
//    public void create() throws Exception {
//        String usrSn = UUIDUtil.createUUID();
//        String result = this.mockMvc.perform(post("/authcode/create").contentType(MediaType.APPLICATION_JSON)
//                //AuthCode数据
//                .param("usrSn","0aeabf55b32e4c0484670656f274e951")
//                .param("acdName","thisisacdname")
//                .param("acdId","thisisacdid001")
//                .param("callFromClient","1"))
//
//                /*//AuthCodeDomain的Url
//                .param("addUrl","www.baidulaingge1.com")
//                .param("addUrl","www.baidulaingge2.com")
//                .param("addUrl","www.baidulaingge3.com"))*/
//                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//    }



//    @Test
//    public void deleteauthcodedomain() throws Exception {
//        //通过acdSn主键删除
//        //同时删除两个表内容
//        String result = this.mockMvc.perform(get("/authcode/deleteauthcodedomain/{addSn}","2b62bcb40c844c41999a45d53e2171a6").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//    }


//    @Test
//    public void select() throws Exception {
//        String result = this.mockMvc.perform(get("/authcode/select/{acdSn_id}","8303420b322d43b1bc6bdd63d2103fa3").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//    }
}
