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
    public void create() throws Exception {
        String usrSn = UUIDUtil.createUUID();
        String result = this.mockMvc.perform(post("/authcode/create").contentType(MediaType.APPLICATION_JSON)
                //AuthCode数据
                .param("usrSn","0aeabf55b32e4c0484670656f274e951")
                .param("acdName","thisisacdname")
                .param("acdId","thisisacdid001")
                .param("callFromClient","1")

                //AuthCodeDomain的Url
                .param("addUrl","www.baidulaingge1.com")
                .param("addUrl","www.baidulaingge2.com")
                .param("addUrl","www.baidulaingge3.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void delete() throws Exception {
        //通过主键删除
        String result = this.mockMvc.perform(get("/authcode/delete/{acdSn_id}","aac44b648b10429cbaf85a6ae0113a45").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void update() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/update").contentType(MediaType.APPLICATION_JSON)
                .param("acdSn","8303420b322d43b1bc6bdd63d2103fa3")  //从数据库里面取出id
                .param("usrSn","8303430b322d43b1bc6bdd63d2103fa3")
                .param("acdName","thisid")
                .param("acdId","thisid00")
                .param("callFromClient","1"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void select() throws Exception {
        String result = this.mockMvc.perform(get("/authcode/select/{acdSn_id}","8303420b322d43b1bc6bdd63d2103fa3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void listauthcode() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/listauthcode").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn","0aeabf55b32e4c0484670656f274e951"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"acdSn":"563f74d3271140d7b69ebdb806e55557","usrSn":"0aeabf55b32e4c0484670656f274e951","acdName":"thisisacdname","acdId":"db03554ee9f84d85a616126cad235edc","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null},{"acdSn":"b95de94a97464e7e91258fee2c65b1f2","usrSn":"0aeabf55b32e4c0484670656f274e951","acdName":"thisisacdname","acdId":"3edb57876bcd4558b8d1915e9d243544","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null},{"acdSn":"bcb0f9c316634405b66ff8a3eeb400c3","usrSn":"0aeabf55b32e4c0484670656f274e951","acdName":"thisisacdname","acdId":"5f63f826e0324f128d7af00697199c8c","callFromClient":"1","callFromBrowser":null,"restrictUrl":null,"dataUseType":null}]

    }

}
