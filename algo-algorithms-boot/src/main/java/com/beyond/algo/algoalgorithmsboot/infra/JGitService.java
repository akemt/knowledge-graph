package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.algoalgorithmsboot.model.GitUser;
import com.beyond.algo.exception.AlgException;

import java.io.File;

public interface JGitService {

    void gitCloneProject(GitUser gitUser) throws AlgException;

    boolean  commitAndPushAllFiles(GitUser gitUser)throws AlgException;

    boolean commitAndPushDelAllFiles(GitUser gitUser) throws AlgException;

    public void gitShowStatus(File repoDir)throws AlgException;
}
