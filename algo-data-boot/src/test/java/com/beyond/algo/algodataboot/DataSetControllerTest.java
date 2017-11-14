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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSetControllerTest {


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

    //初始化测试
    @Test
    public void initData() throws Exception {
        String result = this.mockMvc.perform(get("/initdata").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //添加数据集
    @Test
    public void addDataSet() throws Exception {
        String result = this.mockMvc.perform(post("/adddataset").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetName", "a6666")
                .param("usrSn", "a6666"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //删除数据
    @Test
    public void deleteData() throws Exception {
        String result = this.mockMvc.perform(post("/deleteData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSn", "888"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //删除当前数据集
    @Test
    public void deleteDataSet() throws Exception {
        String result = this.mockMvc.perform(post("/deleteDataSet").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetSn", "fba4aa1b36864c71bbf44c069e5d6e0d"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //点击数据集关联查询数据
    @Test
    public void showData() throws Exception {
        String result = this.mockMvc.perform(get("/showData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetSn", "777"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }



}
