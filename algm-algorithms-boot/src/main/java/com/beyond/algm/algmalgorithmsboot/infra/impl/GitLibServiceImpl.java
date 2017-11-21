package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.GitLibService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GitLibServiceImpl implements GitLibService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private JGitService jGitService;

    @Override
    public GitlabUser addGitLibUser(GitUser gitUser) throws Exception {
        log.info("addGitLibUser方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitConfigModel.getPrivateToken());
        return gitlabAPI.createUser(gitUser.getEmail(), gitUser.getPassword(), gitUser.getUsername(), gitUser.getFullName(),
                null, null, null, null, 100000, null,
                null, null, false, true, true);
    }

    @Override
    public GitlabProject createGitLibProject(GitUser gitUser) throws Exception {
        log.info("createGitLibProject方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitUser.getUsrCode(), gitUser.getPassword());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitlabSession.getPrivateToken());
        GitlabProject gitlabProject = gitlabAPI.createProject(gitUser.getModId());
        gitUser.setProjectRepoURI(gitlabProject.getHttpUrl());
        jGitService.gitCloneProject(gitUser);
        return gitlabProject;
    }

    @Override
    public GitlabGroup createGitLibGroup(String orgCode, String orgName, String createUserName, String password) throws Exception {
        log.info("createGitLibGroup方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), createUserName, password);
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitlabSession.getPrivateToken());
        return gitlabAPI.createGroup(orgName, orgCode);
    }

    @Override
    public void deleteGitLibGroup(String orgCode) throws Exception {
        log.info("deleteGitLibGroup方法调用时候gitlab的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitConfigModel.getPrivateToken());
        GitlabGroup gitlabGroup = gitlabAPI.getGroup(orgCode);
        gitlabAPI.deleteGroup(gitlabGroup.getId());
    }
}
