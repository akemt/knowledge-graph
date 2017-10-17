package com.beyond.algo.infra;

import com.beyond.algo.model.GitUser;

public interface GitLibService {

    boolean addGitLibUser(GitUser gitUser) throws Exception;

    boolean createGitLibProject(GitUser gitUser) throws Exception;

    boolean createGitLib(String orgCode, String orgName, String createUserName, String password) throws Exception;
}
