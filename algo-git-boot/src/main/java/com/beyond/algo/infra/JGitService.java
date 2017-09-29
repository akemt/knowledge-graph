package com.beyond.algo.infra;

import com.beyond.algo.model.GitUser;

import java.io.File;

public interface JGitService {

    void gitCloneProject(GitUser gitUser) throws  Exception;

    boolean initCommitAndPushAllFiles(GitUser gitUser);

    boolean commitAndPushDelAllFiles(GitUser gitUser) throws Exception;

    public void gitShowStatus(File repoDir);
}
