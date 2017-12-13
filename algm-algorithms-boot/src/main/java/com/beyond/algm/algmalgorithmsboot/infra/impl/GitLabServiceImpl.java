package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.http.Query;
import org.gitlab.api.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GitLabServiceImpl implements GitLabService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private JGitService jGitService;

    @Override
    public GitlabUser addGitLabUser(GitUser gitUser) throws AlgException {
        log.info("addGitLabUser方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabUser gitlabUser = null;
        try {
            GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitConfigModel.getPrivateToken());
            gitlabUser = gitlabAPI.createUser(gitUser.getEmail(), gitUser.getPassword(), gitUser.getUsrCode(), gitUser.getFullName(),
                    null, null, null, null, 100000, null,
                    null, null, false, true, true);
            GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitUser.getUsrCode(), gitUser.getPassword());
            gitlabUser.setPrivateToken(gitlabSession.getPrivateToken());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            new AlgException("BEYOND.ALG.GITLAB.ADDUSR.0000001");
        }
        return gitlabUser;
    }

    @Override
    public GitlabProject createGitLabProject(GitUser gitUser) throws Exception {
        log.info("createGitLabProject方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitUser.getPrivateToken());
        GitlabProject gitlabProject = gitlabAPI.createProject(gitUser.getModId());
        gitUser.setProjectRepoURI(gitlabProject.getHttpUrl());
        jGitService.gitCloneProject(gitUser);
        return gitlabProject;
    }

    @Override
    public GitlabGroup createGitLabGroup(String orgCode, String orgName, String privateToken) throws Exception {
        log.info("createGitLabGroup方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), privateToken);
        return gitlabAPI.createGroup(orgName, orgCode);
    }

    @Override
    public void deleteGitLabGroup(String orgCode, String privateToken) throws Exception {
        log.info("deleteGitLabGroup方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), privateToken);
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(orgCode);
        gitlabAPI.deleteGroup(gitlabGroup.getId());
    }

    @Override
    public GitlabGroup updateGitLabGroup(String orgCode, String orgName, String privateToken) throws Exception {
        log.info("updateGitLabGroup方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), privateToken);
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(orgCode);

        Query query = (new Query()).append("name", orgName);
        String tailUrl = "/groups/" + gitlabGroup.getId() + query.toString();
        return gitlabAPI.retrieve().method("PUT").to(tailUrl, GitlabGroup.class);
    }

    @Override
    public GitlabGroupMember addGroupMember(String orgCode, String userCode, String ownerPrivateToken, String memberPrivateToken) throws Exception {
        // 先获取成员对象
        GitlabAPI mGitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), memberPrivateToken);
        GitlabUser gitlabUser = mGitlabAPI.getUser();

        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), ownerPrivateToken);
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(orgCode);
        return gitlabAPI.addGroupMember(gitlabGroup, gitlabUser, GitlabAccessLevel.Developer);
    }

    @Override
    public void deleteGroupMember(String orgCode, String userCode, String ownerPrivateToken, String memberPrivateToken) throws Exception {
        // 先获取成员对象
        GitlabAPI mGitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), memberPrivateToken);
        GitlabUser gitlabUser = mGitlabAPI.getUser();

        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), ownerPrivateToken);
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(orgCode);
        gitlabAPI.deleteGroupMember(gitlabGroup, gitlabUser);
    }

    @Override
    public void deleteUserByGitUserId(Integer id) throws AlgException {
        log.info("deleteUserByGitUserId方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitConfigModel.getPrivateToken());
        try {
            gitlabAPI.deleteUser(id);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }

    }

    @Override
    public GitlabProject createGitLabGroupProject(GitUser gitUser) throws Exception {
        log.info("createGitLabGroupProject方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitUser.getPrivateToken());
        //通过组织编号，获取组织ID
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(gitUser.getOrgUsrCode());
        GitlabProject gitlabProject = null;
        try {
            // http://127.0.0.1/组织编号/项目编号
            Query query = (new Query()).append("name", gitUser.getModId()).appendIf("path", gitUser.getModId()).appendIf("namespace_id", gitlabGroup.getId());
            String tailUrl = "/projects" + query.toString();
            gitlabProject = (GitlabProject) gitlabAPI.dispatch().to(tailUrl, GitlabProject.class);

            gitUser.setProjectRepoURI(gitlabProject.getHttpUrl());
            jGitService.gitCloneProject(gitUser);
        } catch (Exception e) {
            log.error("createGitLabGroupProject false ,because of group's project", e.getMessage(), e);
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000002");
        }
        return gitlabProject;
    }
}
