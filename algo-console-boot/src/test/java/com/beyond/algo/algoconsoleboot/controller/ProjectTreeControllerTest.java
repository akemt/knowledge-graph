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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author ：Lindewei
 * @Description:目录Tree的展示、返回、文本展示
 * @date ：10:26 2017/10/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTreeControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private ProjectTreeController projectTreeController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }
    @Test
    public void contextLoads() {

    }

    //展示同级目录所有文件和文件夹，或者展示文本。
    @Test
    public void showFile() throws Exception{

        String result = this.mockMvc.perform(post("/tree/showFile").contentType(MediaType.APPLICATION_JSON)
                .param("path","F:/001/0010002/sadas.txt"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //tree目录返回
    @Test
    public void backFile() throws Exception{

        String result = this.mockMvc.perform(post("/tree/backFile").contentType(MediaType.APPLICATION_JSON)
                .param("path","F:/001/0010002"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
