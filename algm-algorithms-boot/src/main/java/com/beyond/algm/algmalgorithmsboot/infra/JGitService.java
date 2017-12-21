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

    /**
     * commit and push 本地所有代码到远端仓库
     *
     * @param gitUser
     * @return
     * @throws AlgException
     */
    String  commitAndPushAllFiles(GitUser gitUser)throws AlgException;

    boolean commitAndPushDelAllFiles(GitUser gitUser) throws AlgException;

    public void gitShowStatus(File repoDir)throws AlgException;

    /**
     * 通过本地路径，从Git服务器上更新pull项目
     *
     * @param repoDir
     * @param gitUser
     * @throws AlgException
     */
    public void gitPull(File repoDir,GitUser gitUser)throws AlgException;
}
