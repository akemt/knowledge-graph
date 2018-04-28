package com.hiekn.plantdata;


import com.hiekn.plantdata.Entity.User;
import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.exception.GraphException;
import com.hiekn.plantdata.infra.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 用户控制层
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Autowired
    private UserService userService;


    @RequestMapping(value = "getUsr")
    public void getUserInfo(){

//        userService.getUserInfo();
    }


    /**
     *
     * @return
     * @throws GraphException
     */
    @Test
    public void getUserLogin() throws Exception {
        String result = this.mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
                .param("username","xialf")
                .param("password","123456"))
                .andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}
