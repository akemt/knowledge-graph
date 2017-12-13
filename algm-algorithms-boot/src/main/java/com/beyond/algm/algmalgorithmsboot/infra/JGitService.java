package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.exception.AlgException;

import java.io.File;

public interface JGitService {

    /**
     * 添加Git 到clone项目
     *
     * @param gitUser
     * @throws AlgException
     */
    void gitCloneProject(GitUser gitUser) throws AlgException;

    String  commitAndPushAllFiles(GitUser gitUser)throws AlgException;

    boolean commitAndPushDelAllFiles(GitUser gitUser) throws AlgException;

    public void gitShowStatus(File repoDir)throws AlgException;
}
