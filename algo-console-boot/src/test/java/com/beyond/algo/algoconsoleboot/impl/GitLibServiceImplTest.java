package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.model.GitUser;
import org.gitlab.api.models.GitlabGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class GitLibServiceImplTest {

    @Autowired
    private GitLibService gitLibService;

    @Test
    public void addGitUserAndPasswordTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setEmail("qihea163@163.com");
        gitUser.setPassword("12345678");
        gitUser.setUsername("qihe");
        gitUser.setFullName("qihe");
        gitUser.setProjectName("TestHello");
        gitLibService.addGitLibUser(gitUser);
    }

    @Test
    public void createGitLibProjectTest() throws Exception {
       //boolean result = gitLibService.createGitLibProject("TestProject2","test1","test1234");
    }

    @Test
    public void createGitLibGroupTest() throws Exception {
        GitlabGroup gitlabGroup = gitLibService.createGitLibGroup("testOrg1", "测试组织1", "qihe", "12345678");
        assertTrue(gitlabGroup != null);
    }

    @Test
    public void deleteGitLibGroupTest() throws Exception {
        gitLibService.deleteGitLibGroup("testOrg1");
    }

}
