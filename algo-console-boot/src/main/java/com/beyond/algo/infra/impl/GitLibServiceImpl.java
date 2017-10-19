package com.beyond.algo.infra.impl;

import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitConfigModel;
import com.beyond.algo.model.GitUser;
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
        log.info("addGitLibUser方法调用时候gitlib的地址:" + gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), "jsu-pVsb8T5JegQjMGzM");
        GitlabUser gitlabUser = gitlabAPI.createUser(gitUser.getEmail(), gitUser.getPassword(), gitUser.getUsername(), gitUser.getFullName(), null, null, null, null, 100000, null, null, null, false, true, true);
        return gitlabUser;
    }

    @Override
    public GitlabProject createGitLibProject(GitUser gitUser) throws Exception {
        log.info("createGitLibProject方法调用时候gitlib的地址:" + gitConfigModel.getBaseUrl());
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitUser.getUsername(), gitUser.getPassword());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitlabSession.getPrivateToken());
        GitlabProject gitlabProject = gitlabAPI.createProject(gitUser.getProjectName());
        gitUser.setProjectRepoURI(gitlabProject.getHttpUrl());
        jGitService.gitCloneProject(gitUser);
        return gitlabProject;
    }

    @Override
    public GitlabGroup createGitLibGroup(String orgCode, String orgName, String createUserName, String password) throws Exception {
        log.info("createGitLibGroup方法调用时候gitlib的地址:" + gitConfigModel.getBaseUrl());
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), createUserName, password);
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitlabSession.getPrivateToken());
        GitlabGroup gitlabGroup = gitlabAPI.createGroup(orgName, orgCode);
        return gitlabGroup;
    }
}
