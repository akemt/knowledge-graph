package com.beyond.algo.infra.impl;

import com.beyond.algo.common.FileUntil;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitConfigModel;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class JGitServiceImpl implements JGitService {

    private final static Logger logger = LoggerFactory.getLogger(JGitServiceImpl.class);

    @Autowired
    private GitConfigModel gitConfigModel;

    @Override
    public void gitCloneProject(String projectRepoURI, String projectName, String username, String password) throws GitAPIException {
        //Git git = Git.cloneRepository().setURI(projectRepoURI).setDirectory(new File("E:/repo")).call();
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(projectRepoURI);
        cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        cloneCommand.setDirectory(new File(gitConfigModel.getLocalBasePath() + "/" + username + "/" + projectName));
        cloneCommand.call();
        initCommitAndPushAllFiles(gitConfigModel.getLocalBasePath() + "/" + username + "/" + projectName+"/.git",username,password);
    }

    /**
     * 初始化时提交本地所有代码到远端仓库
     * @param loaclGitPath
     * @param username
     * @param password
     * @return
     */
    public boolean initCommitAndPushAllFiles(String loaclGitPath,String username,String password) {
        try {
            FileRepository localRepo = new FileRepository( loaclGitPath);
            Git git = new Git(localRepo);
            AddCommand addCommand = git.add().addFilepattern(".");
            addCommand.call();

            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage("init project");
            commitCommand.setAllowEmpty(true);
            commitCommand.call();
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            pushCommand.call();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param repoDir
     */
    public void gitShowStatus(File repoDir) {
        File RepoGitDir = new File(repoDir.getAbsolutePath() + "/.git");
        if (!RepoGitDir.exists()) {
            logger.info("Error! Not Exists : " + RepoGitDir.getAbsolutePath());
        } else {
            Repository repo = null;
            try {
                repo = new FileRepository(RepoGitDir.getAbsolutePath());
                Git git = new Git(repo);
                Status status = git.status().call();
                logger.info("Git Change: " + status.getChanged());
                logger.info("Git Modified: " + status.getModified());
                logger.info("Git UncommittedChanges: " + status.getUncommittedChanges());
                logger.info("Git Untracked: " + status.getUntracked());
            } catch (Exception e) {
                logger.info(e.getMessage() + " : " + repoDir.getAbsolutePath());
            } finally {
                if (repo != null) {
                    repo.close();
                }
            }
        }
    }

    /**
     * 删除本地文件同时同步服务器
     * author:zhangchuanzhi
     * @param loaclGitPath
     * @param username
     * @param password
     * @return
     */
    public boolean commitAndPushDelAllFiles(String loaclGitPath,String username,String password,String filePath) {
        try {
            logger.info("传入本地仓库路径：{}用户名：{} 用户密码：{} 文件路径：{}",loaclGitPath,username,password,filePath);
            FileUntil.delFile(filePath);
            FileRepository localRepo = new FileRepository( loaclGitPath);
            Git git = new Git(localRepo);
            AddCommand addCommand = git.add().setUpdate(true).addFilepattern(".");
            addCommand.call();
            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage("init project");
            commitCommand.setAllowEmpty(true);
            commitCommand.call();
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            pushCommand.call();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
