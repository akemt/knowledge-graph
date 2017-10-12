package com.beyond.algo.algoconsoleboot;

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
 * @author ：zhangchuanzhi
 * @Description:测试用户调用情况
 * @date ：15:26 2017/10/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentControllerTest {
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
    public void algorithmRecord() throws Exception{

        String result = this.mockMvc.perform(post("/content/algorithmRecord").contentType(MediaType.APPLICATION_JSON)
                .param("callUsrSn","aac44b648b10429cbaf85a6ae0113a65")
                .param("page","0")
                .param("rows","4"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
