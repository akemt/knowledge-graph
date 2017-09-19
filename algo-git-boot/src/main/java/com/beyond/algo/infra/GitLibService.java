package com.beyond.algo.infra;

public interface GitLibService {

    boolean addGitLibUser(String email, String password,String username,String fullName) throws Exception;

    boolean createGitLibProject(String projectName,String username,String password) throws Exception;

}
