package com.beyond.algo.infra;

import com.beyond.algo.model.GitUser;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;

public interface GitLibService {

    GitlabUser addGitLibUser(GitUser gitUser) throws Exception;

    GitlabProject createGitLibProject(GitUser gitUser) throws Exception;

    GitlabGroup createGitLibGroup(String orgCode, String orgName, String createUserName, String password) throws Exception;
}
