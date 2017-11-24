package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.GitLibService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class GitLibServiceImplTest {

    @Autowired
    private GitLibService gitLibService;

    @Test
    public void addGitUserAndPasswordTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setEmail("qihea1163@163.com");
        gitUser.setPassword("12345678");
        gitUser.setUsername("zhang");
        gitUser.setFullName("zhang");
        gitUser.setProjectName("TestJavaZhang");
        gitLibService.addGitLibUser(gitUser);
    }

    @Test
    public void createGitLibProjectTest() throws Exception {
        GitUser gitUser=new GitUser();
        gitUser.setProjectName("TestJavaZhang");
        gitUser.setUsername("zhang");
        gitUser.setPassword("12345678");
        GitlabProject result = gitLibService.createGitLibProject(gitUser);
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
