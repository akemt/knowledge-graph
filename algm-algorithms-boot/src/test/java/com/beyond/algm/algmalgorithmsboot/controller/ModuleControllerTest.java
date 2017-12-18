package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.model.AlgModule;
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

/**
 * @author ：Lindewei
 * @Description:目录Tree的展示、返回、文本展示
 * @date ：10:26 2017/10/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private ModuleController moduleController;

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

    //tree目录返回
    @Test
    public void buildAndUpLoadProject() throws Exception{

        String result = this.mockMvc.perform(post("/module/TestJavaZhang/buildProject").contentType(MediaType.APPLICATION_JSON)
                .param("password","12345678"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 发布接口-发布版本号、计算版权费用
     * @throws Exception
     */
   @Test
    public void getAlgModuleVersion() throws Exception {
       String result = this.mockMvc.perform(get("/erniu4/11111/publish").contentType(MediaType.APPLICATION_JSON)
//               .param("modId","TestJavaO1")
//               .param("usrCode","erniu4")
       )
               .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
       System.out.println(result);
    }

    /**
     * 新增组织算法
     * @throws Exception
     */
    @Test
    public void AddOrgAlgorithm() throws Exception {
        String result = this.mockMvc.perform(post("/module/addOrgPro").contentType(MediaType.APPLICATION_JSON)
               .param("orgUsrCode","testGroup2")
                //一些是组织实体类
                .param("modId","project09")
                .param("modName","项目09")
                .param("lanSn","1")
                .param("catSn","86e89ac235ee4ba7ba7cec1907815ba8")
                .param("licSn","1")
                .param("atlSn","1")
                .param("needWeb","1")
                .param("envType","1")
                .param("isTrain","1")
                .param("isColony","1")
                .param("modDesc","1")
        )
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
