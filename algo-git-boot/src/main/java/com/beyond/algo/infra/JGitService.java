package com.beyond.algo.infra;

public interface JGitService {

    void gitCloneProject(String projectRepoURI,String projectName,String username,String password) throws  Exception;

    boolean initCommitAndPushAllFiles(String loaclGitPath,String username,String password);

    boolean commitAndPushDelAllFiles(String loaclGitPath,String username,String password,String filePath);
}
