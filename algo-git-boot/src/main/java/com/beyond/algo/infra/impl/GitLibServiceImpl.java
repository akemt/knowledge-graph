package com.beyond.algo.infra.impl;

import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitConfigModel;
import com.beyond.algo.model.GitUser;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabSession;
import org.gitlab.api.models.GitlabUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public boolean addGitLibUser(GitUser gitUser) throws Exception {
        //GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getGitBaseUrl(),gitConfigModel.getGitRootName(),gitConfigModel.getGitRootPasswd());
        //GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getGitBaseUrl(),gitlabSession.getPrivateToken());
        log.info("addGitLibUser方法调用时候gitlib的地址:"+gitConfigModel.getBaseUrl());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(),"jsu-pVsb8T5JegQjMGzM");
        GitlabUser gitlabUser = gitlabAPI.createUser(gitUser.getEmail(),gitUser.getPassword(),gitUser.getUsername(),gitUser.getFullName(),null,null,null,null,100000,null,null,null,false,false,true);
        if(gitlabUser.getId()!=null && gitlabUser.getId()!=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean createGitLibProject(GitUser gitUser) throws Exception {
        log.info("createGitLibProject方法调用时候gitlib的地址:"+gitConfigModel.getBaseUrl());
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(),gitUser.getUsername(),gitUser.getPassword());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(),gitlabSession.getPrivateToken());
        GitlabProject gitlabProject = gitlabAPI.createProject(gitUser.getProjectName());
        gitUser.setProjectRepoURI(gitlabProject.getHttpUrl());
        jGitService.gitCloneProject(gitUser);
        return true;
    }

    public boolean createGitLib(String orgCode, String orgName, String createUserName, String password) throws Exception {
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(), createUserName, password);
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(), gitlabSession.getPrivateToken());
        gitlabAPI.createGroupViaSudo(orgName, orgCode, gitlabAPI.getUser());
        return true;
    }
}
