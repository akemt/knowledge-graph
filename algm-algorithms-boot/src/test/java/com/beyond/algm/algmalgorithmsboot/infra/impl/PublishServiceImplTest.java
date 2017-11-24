package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.PublishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class PublishServiceImplTest {

    @Autowired
    private PublishService publishService;

    @Test
    public void initProject() throws Exception {
        publishService.initBootProject("1","gaohaijun", "TestProject2", "This is a boot test.", "0.1.0");
    }

}