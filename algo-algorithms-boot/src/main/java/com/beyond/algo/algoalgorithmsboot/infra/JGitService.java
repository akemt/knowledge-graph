package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.algoalgorithmsboot.model.GitUser;

import java.io.File;

public interface JGitService {

    void gitCloneProject(GitUser gitUser) throws  Exception;

    boolean  commitAndPushAllFiles(GitUser gitUser);

    boolean commitAndPushDelAllFiles(GitUser gitUser) throws Exception;

    public void gitShowStatus(File repoDir);
}
