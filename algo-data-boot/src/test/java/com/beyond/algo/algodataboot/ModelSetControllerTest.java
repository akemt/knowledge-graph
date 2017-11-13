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
                .param("modelSetName", "插入排序")
                .param("usrSn", "1321"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void deleteModelSet() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/deleteModelSet").contentType(MediaType.APPLICATION_JSON)
                .param("modelSetSn", "329406a7108b447eb889eb319be657da"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void addAlgModel() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/addModel").contentType(MediaType.APPLICATION_JSON)
                .param("datSn", "asf11")
                .param("usrSn", "qqq")
                .param("modelSetSn", "5a0e41ea4c904012b71b4f2ea2043c6f")
                .param("verSn", "0")
                .param("modelName", "1231vq")
                .param("modelEnName", "1231ee12")
                .param("modelSize", "1231")
                .param("isOpenSrc", "1")
                .param("comment", "1231"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void deleteModel() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/deleteModel").contentType(MediaType.APPLICATION_JSON)
                .param("modelSn", "24488612a256467a923d8de84d56e310"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void modifyAlgModel() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/modifyModel").contentType(MediaType.APPLICATION_JSON)
                .param("datSn", "1116661")
                .param("usrSn", "2266622")
                .param("modelSn", "785a55e7e7344328adc72e48fe4e3eec")
                .param("verSn", "6")
                .param("modelName", "1231666vq")
                .param("modelEnName", "1231ee12")
                .param("modelSize", "241")
                .param("isOpenSrc", "6")
                .param("comment", "1v弄via欧喷爱积分帕宋"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void fileUpload() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/modelFileUpload").contentType(MediaType.APPLICATION_JSON)
                .param("modelSetSn", "33ba30fce4e14dd690f61ec44701d974")
                .param("usrSn", "1231")
                .param("file",""))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void queryModelSet() throws Exception {
        String result = this.mockMvc.perform(post("/queryModelSet").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void queryModel() throws Exception {
        String result = this.mockMvc.perform(post("/queryModel").contentType(MediaType.APPLICATION_JSON)
                .param("modelSetSn", "5a0e41ea4c904012b71b4f2ea2043c6f"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void queryModelList() throws Exception {
        String result = this.mockMvc.perform(post("/algo_modelset/qwer1/test/modeldata").contentType(MediaType.APPLICATION_JSON)
                .param("page","0")
                .param("rows","2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
