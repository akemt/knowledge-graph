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
    @Test
    public void showDataSet() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/showDataSet").contentType(MediaType.APPLICATION_JSON)
                .param("usrSn", "data11"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void addDataSet() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/addDataSet").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetName", "数据集")
                .param("usrSn", "data11"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void deleteDataSet() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/deleteDataSet").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetSn", "6ad675dfe66040de8c6f6886fb73792c"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void showData() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/showData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetSn", "6ad675dfe66040de8c6f6886fb73792c"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void addData() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/addData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSetSn", "6ad675dfe66040de8c6f6886fb73792c")
                .param("usrSn", "data11")
                .param("dataName", "数学2")
                .param("dataEnName", "math3")
                .param("dataSize", "1231ee")
                .param("dataAddr", "1231")
                .param("dataLoc", "1")
                .param("isOpenSrc", "1")
                .param("dataAbs", "1")
                .param("dataContent", "1")
                .param("dataCredit", "1231"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void deleteData() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/deleteData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSn", "50778c1a6848419f93569cc15f937f85"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void modifyData() throws Exception {
        String result = this.mockMvc.perform(post("/algo_dataset/modifyData").contentType(MediaType.APPLICATION_JSON)
                .param("dataSn", "91c74defe4184ba495f668766537880f")
                .param("dataSetSn", "6ad675dfe66040de8c6f6886fb73792c")
                .param("usrSn", "data11")
                .param("dataName", "数学9")
                .param("dataEnName", "math4")
                .param("dataSize", "1231ee")
                .param("dataAddr", "1231")
                .param("dataLoc", "1")
                .param("isOpenSrc", "1")
                .param("dataAbs", "1")
                .param("dataContent", "1")
                .param("dataCredit", "1231"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
