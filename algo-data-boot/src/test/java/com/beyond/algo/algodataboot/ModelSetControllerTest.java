package com.beyond.algo.algodataboot;

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
 * @author ：huangjinqing
 * @Description:算法商店模型模块Controller测试类
 * @date ：9:28 2017/10/18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelSetControllerTest {

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
    public void addModelSet() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/addModelSet").contentType(MediaType.APPLICATION_JSON)
                .param("modelSetName", "hjq")
                .param("usrSn", "插入排序"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
