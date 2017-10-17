package com.beyond.algo.algoconsoleboot;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCodeDomainControllerTest {
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
    public void create() throws Exception {
        String addSn = UUIDUtil.createUUID();
        String acdSn = UUIDUtil.createUUID();
        String result = this.mockMvc.perform(post("/authcodedomain/create").contentType(MediaType.APPLICATION_JSON)
                .param("addSn",addSn)
                .param("acdSn",acdSn)
                .param("addUrl","www.baidu.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void delete() throws Exception {
        //通过主键删除
        String result = this.mockMvc.perform(get("/authcodedomain/delete/{acdSn_id}","2ce2f1ad87d942f0b20b10d3b7db6be1").contentType(MediaType.APPLICATION_JSON))
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
    public void list() throws Exception {
        String result = this.mockMvc.perform(post("/authcode/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
