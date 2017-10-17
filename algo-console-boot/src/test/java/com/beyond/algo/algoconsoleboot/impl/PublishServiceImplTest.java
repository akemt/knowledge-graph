package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.infra.PublishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class PublishServiceImplTest {

    @Autowired
    private PublishService publishService;

    @Test
    public void initProject() throws Exception {
        publishService.initBootProject("test1", "TestProject2", "This is a boot test.", "0.1.0");
    }

}