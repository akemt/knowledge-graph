package com.beyond.algm.algmcallboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmcallboot.AlgmCallBootApplication;
import com.beyond.algm.algmcallboot.infra.RocketMQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmCallBootApplication.class)
public class RocketMQServiceImplTest {
    @Autowired
    private RocketMQService rocketMQService;
    @Test
    public void modCallProducer() throws Exception {
        //rocketMQService.modCallProducer(JSONObject algUserCall);
    }

}