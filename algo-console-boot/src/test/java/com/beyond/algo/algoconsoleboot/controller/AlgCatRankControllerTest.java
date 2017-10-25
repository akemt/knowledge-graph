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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgCatRankControllerTest {
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
    public void generateKey() throws Exception {
        String result = this.mockMvc.perform(get("/algCatRank/listAlg").contentType(MediaType.APPLICATION_JSON)
                .param("catName","deep")
                .param("usage","creditSn")
                .param("searchName","人脸")
                .param("numPage","1")
                .param("numRows","2"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
        //结果：[{"modSn":null,"usrSn":"aac44b648b10429cbaf85a6ae0113a65","lanSn":"3","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别01","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null},{"modSn":null,"usrSn":"37bf2269ee4845da8e86861bbde2438a","lanSn":"1","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别02","modId":"TestJava","isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null},{"modSn":null,"usrSn":"aac44b648b10429cbaf85a6ae0113a65","lanSn":"2","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别03","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null},{"modSn":null,"usrSn":"aac44b648b10429cbaf85a6ae0113a65","lanSn":"3","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别04","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null}]
        //加入分页后结果：[{"modSn":null,"usrSn":"aac44b648b10429cbaf85a6ae0113a65","lanSn":"3","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别01","modId":null,"isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null},{"modSn":null,"usrSn":"37bf2269ee4845da8e86861bbde2438a","lanSn":"1","catSn":"suanfafenlei447ba557b99472cbb701","licSn":"111111111111","atlSn":null,"modName":"人脸识别02","modId":"TestJava","isOpenSrc":null,"needWeb":null,"needCallOther":null,"envType":null}]
    }
}
