package com.beyond.algo.infra.impl;

import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitConfigModel;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabSession;
import org.gitlab.api.models.GitlabUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitLibSerivceImpl implements GitLibService {

    private final static Logger logger = LoggerFactory.getLogger(GitLibSerivceImpl.class);

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private JGitService jGitService;

    @Override
    public boolean addGitLibUser(String email, String password,String username,String fullName) throws Exception {
        //GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getGitBaseUrl(),gitConfigModel.getGitRootName(),gitConfigModel.getGitRootPasswd());
        //GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getGitBaseUrl(),gitlabSession.getPrivateToken());
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(),"jsu-pVsb8T5JegQjMGzM");
        GitlabUser gitlabUser = gitlabAPI.createUser(email,password,username,fullName,null,null,null,null,100000,null,null,null,false,false,true);
        if(gitlabUser.getId()!=null && gitlabUser.getId()!=0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean createGitLibProject(String projectName, String username, String password) throws Exception {
        GitlabSession gitlabSession = GitlabAPI.connect(gitConfigModel.getBaseUrl(),username,password);
        GitlabAPI gitlabAPI = GitlabAPI.connect(gitConfigModel.getBaseUrl(),gitlabSession.getPrivateToken());
        GitlabProject gitlabProject = gitlabAPI.createProject(projectName);
        jGitService.gitCloneProject(gitlabProject.getHttpUrl(),projectName,username,password);
        return true;
    }
}
