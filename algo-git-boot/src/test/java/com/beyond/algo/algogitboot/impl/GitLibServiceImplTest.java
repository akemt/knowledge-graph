package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.algogitboot.AlgoGitBootApplication;
import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.model.GitUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoGitBootApplication.class)
public class GitLibServiceImplTest {

    @Autowired
    private GitLibService gitLibService;

    @Test
    public void addGitUserAndPasswordTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setEmail("qihea163@163.com");
        gitUser.setPassword("123456");
        gitUser.setUsername("erniu");
        gitUser.setProjectName("TestHello");
        boolean result = gitLibService.addGitLibUser(gitUser);
    }

    @Test
    public void createGitLibProjectTest() throws Exception {
       //boolean result = gitLibService.createGitLibProject("TestProject2","test1","test1234");
    }
}
