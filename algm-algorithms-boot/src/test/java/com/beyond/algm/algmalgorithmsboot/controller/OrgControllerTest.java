package com.beyond.algm.algmalgorithmsboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgControllerTest {

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
    public void createOrgTest() throws Exception {
        String result = this.mockMvc.perform(post("/org/").contentType(MediaType.APPLICATION_JSON)
                .param("usrCode", "testOrg0")
                .param("usrName", "测试组织0")
                .param("email", "test@qq.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void deleteOrgTest() throws Exception {
        String result = this.mockMvc.perform(delete("/org/").contentType(MediaType.APPLICATION_JSON)
                .param("orgSn", "f0a18cf334f34671b0468c6b7ba72beb"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void updateOrgTest() throws Exception {
        String result = this.mockMvc.perform(put("/org/").contentType(MediaType.APPLICATION_JSON)
                .param("usrCode", "testOrg0")
                .param("usrName", "测试组织00")
                .param("email", "test@qq.com")
                .param("usrUrl", "www.baidu.com"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void getOrgListTest() throws Exception {
        String result = this.mockMvc.perform(get("/org/list").contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "1")
                .param("pageSize", "10"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void getOrgDetailTest() throws Exception {
        String result = this.mockMvc.perform(get("/org/").contentType(MediaType.APPLICATION_JSON)
                .param("orgCode", "testOrg2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void addMemberTest() throws Exception {
        String result = this.mockMvc.perform(post("/org/addMember").contentType(MediaType.APPLICATION_JSON)
                .param("orgSn", "1ab380d8078d414f8edc4dcc33a65348")
                .param("memberSn", "64bd0e5ee1a6409f97d12c271bb8fa68"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void removeMemberTest() throws Exception {
        String result = this.mockMvc.perform(post("/org/removeMember").contentType(MediaType.APPLICATION_JSON)
                .param("orgSn", "1ab380d8078d414f8edc4dcc33a65348")
                .param("memberSn", "64bd0e5ee1a6409f97d12c271bb8fa68"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        log.info(result);
    }
}
