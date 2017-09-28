package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.infra.PublishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RestClientTest({ PublishService.class})
public class PublishServiceImplTest {

    @Autowired
    private PublishService publishService;

    @Test
    public void initProject() throws Exception {
        publishService.initBootProject("test1", "TestProject2", "This is a boot test.", "0.1.0");
    }

}