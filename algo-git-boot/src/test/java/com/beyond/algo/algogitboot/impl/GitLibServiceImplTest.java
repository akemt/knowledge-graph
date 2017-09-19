package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.infra.GitLibService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RestClientTest({ GitLibService.class})
public class GitLibServiceImplTest {

    @Autowired
    private GitLibService gitLibService;

    @Test
    public void addGitUserAndPasswordTest() throws Exception {
        boolean result = gitLibService.addGitLibUser("test1@163.com","test1234","test1","test1");
    }

    @Test
    public void createGitLibProjectTest() throws Exception {
        boolean result = gitLibService.createGitLibProject("TestProject2","test1","test1234");
    }
}
