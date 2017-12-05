package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
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
public class GitLabServiceImplTest {

    @Autowired
    private GitLabService gitLabService;

    @Test
    public void addGitUserAndPasswordTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setEmail("qihea1163@163.com");
        gitUser.setPassword("12345678");
        gitUser.setUsername("zhang");
        gitUser.setFullName("zhang");
        gitUser.setProjectName("TestJavaZhang");
        gitLabService.addGitLabUser(gitUser);
    }

    @Test
    public void createGitLabProjectTest() throws Exception {
        GitUser gitUser=new GitUser();
        gitUser.setProjectName("TestJavaZhang");
        gitUser.setUsername("zhang");
        gitUser.setPassword("12345678");
        GitlabProject result = gitLabService.createGitLabProject(gitUser);
    }

    @Test
    public void createGitLabGroupTest() throws Exception {
        GitlabGroup gitlabGroup = gitLabService.createGitLabGroup("testOrg1", "测试组织1", "qihe", "12345678");
        assertTrue(gitlabGroup != null);
    }

    @Test
    public void deleteGitLabGroupTest() throws Exception {
        gitLabService.deleteGitLabGroup("testOrg1");
    }

    @Test
    public void addGroupMemberTest() throws Exception {
        gitLabService.addGroupMember("testOrg2", "gaohaijun");
    }

    @Test
    public void deleteGroupMember() throws Exception {
        gitLabService.deleteGroupMember("testOrg2", "gaohaijun");
    }

}
