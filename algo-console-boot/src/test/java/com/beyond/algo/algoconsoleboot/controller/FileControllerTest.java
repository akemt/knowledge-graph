package com.beyond.algo.algoconsoleboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import com.beyond.algo.controller.FileController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author ：Lindewei
 * @Description:文件读写保存
 * @date ：10:26 2017/10/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private FileController fileController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }
    @Test
    public void contextLoads() {

    }

    //文件读测试
    @Test
    public void testReadFile() throws Exception{

        String result = this.mockMvc.perform(post("/file/readFile").contentType(MediaType.APPLICATION_JSON)
                .param("path","D:/JGitServiceTest.java"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //文件写保存测试
    @Test
    public void testWriteFile() throws Exception{

        String result = this.mockMvc.perform(post("/file/writeFile").contentType(MediaType.APPLICATION_JSON)
                .param("con","D:/JGitServiceTest.javaadsdsadjkajdkajd打断对方考虑对方"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }



}
