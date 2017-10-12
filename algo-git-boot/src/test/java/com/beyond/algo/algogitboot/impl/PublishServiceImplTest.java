package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.algogitboot.AlgoGitBootApplication;
import com.beyond.algo.infra.PublishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoGitBootApplication.class)
public class PublishServiceImplTest {

    @Autowired
    private PublishService publishService;

    @Test
    public void initProject() throws Exception {
        publishService.initBootProject("test1", "TestProject2", "This is a boot test.", "0.1.0");
    }

}