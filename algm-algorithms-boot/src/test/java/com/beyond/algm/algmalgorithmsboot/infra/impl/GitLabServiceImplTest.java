package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.exception.AlgException;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabSession;
import org.gitlab.api.models.GitlabUser;
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
        gitUser.setEmail("592427777@qq.com");
        gitUser.setPassword("12345678");
        gitUser.setUsrCode("xialf");
        gitUser.setFullName("xia");
        gitLabService.addGitLabUser(gitUser);
    }

    @Test
    public void createGitLabProjectTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setProjectName("TestJavaZhang");
        gitUser.setUsrCode("zhang");
        gitUser.setPassword("12345678");
        GitlabProject result = gitLabService.createGitLabProject(gitUser);
    }

    @Test
    public void createGitLabGroupTest() throws Exception {
        GitlabGroup gitlabGroup = gitLabService.createGitLabGroup("testOrg3", "测试组织3", "mHN8exdYzNja1eZkXPxc");
        assertTrue(gitlabGroup != null);
    }

    @Test
    public void deleteGitLabGroupTest() throws Exception {
        gitLabService.deleteGitLabGroup("testOrg3", "mHN8exdYzNja1eZkXPxc");
    }

    @Test
    public void updateGitLabGroupTest() throws Exception {
        gitLabService.updateGitLabGroup("testOrg2", "测试组织2", "jjzSdMo912X4kKhR1-dx");
    }

    @Test
    public void addGroupMemberTest() throws Exception {
        gitLabService.addGroupMember("testOrg2", "gaohaijun", "jjzSdMo912X4kKhR1-dx", "mHN8exdYzNja1eZkXPxc");
    }

    @Test
    public void deleteGroupMember() throws Exception {
        gitLabService.deleteGroupMember("testOrg2", "gaohaijun", "jjzSdMo912X4kKhR1-dx", "mHN8exdYzNja1eZkXPxc");
    }

    @Test
    public void deleteUserByGitUserId() throws AlgException {
        try {
            gitLabService.deleteUserByGitUserId(57);

        } catch (Exception e) {
            new AlgException("BEYOND.ALG.GITLAB.DELETE.0000001");
        }

    }

    @Test
    public void createGitLabGroupProjectTest() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setUsrCode("gaohaijun");
        gitUser.setPassword("12345678");
        gitUser.setModId("project04");
        gitUser.setOrgUsrCode("testGroup2");
        gitUser.setPrivateToken("mHN8exdYzNja1eZkXPxc");
        gitUser.setIsOrg("1");
        GitlabProject result = gitLabService.createGitLabGroupProject(gitUser);
    }
    @Test
    public void test() throws Exception {
 /*       GitlabAPI gitlabAPI = GitlabAPI.connect("http://192.168.1.88","jsu-pVsb8T5JegQjMGzM");
        GitlabSession gitlabSession = GitlabAPI.connect("http://192.168.1.88", "zhang1", "12345678");
        GitlabUser abc=  gitlabAPI.updateUser(gitlabSession.getId(),
               "", "123456789", "",
                "", "", "",
                "", "", 100000,
                "","",
                "",false,true);*/

    }

    @Test
    public void cloneToLocalPath() throws Exception {
        GitUser gitUser = new GitUser();
        gitUser.setPrivateToken("wJcznjQ-98fV6xFHnQMZ");
        gitUser.setModId("modoulTest01");
//        gitLabService.createGitLabGroupProject(gitUser);
    }

}
