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
        String result = this.mockMvc.perform(get("/authcodedomain/delete/{addSn_id}","2ce2f1ad87d942f0b20b10d3b7db6be1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void update() throws Exception {
        String result = this.mockMvc.perform(post("/authcodedomain/update").contentType(MediaType.APPLICATION_JSON)
                .param("addSn","d0a311840c2542bbac9bb625368f9463")
                .param("acdSn","123345567677")
                .param("addUrl","www.sina.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void select() throws Exception {
        String result = this.mockMvc.perform(get("/authcodedomain/select/{addSn_id}","d0a311840c2542bbac9bb625368f9463").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void list() throws Exception {
        String result = this.mockMvc.perform(post("/authcodedomain/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
