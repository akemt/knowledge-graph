package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.JGitService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.common.FileUtil;
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

    @Autowired
    private ShowProjectFileService showProjectFileService;

    @Override
    public void gitCloneProject(GitUser gitUser) throws GitAPIException {
        //Git git = Git.cloneRepository().setURI(projectRepoURI).setDirectory(new File("E:/repo")).call();
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(gitUser.getProjectRepoURI());
        cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUser.getUsername(), gitUser.getPassword()));
        cloneCommand.setDirectory(new File(gitConfigModel.getLocalBasePath() + File.separator + gitUser.getUsername() + File.separator + gitUser.getProjectName()));
        cloneCommand.call();
        gitUser.setPath(gitConfigModel.getLocalBasePath() + File.separator + gitUser.getUsername() + File.separator + gitUser.getProjectName()+File.separator+".git");
        initCommitAndPushAllFiles(gitUser);
    }

    /**
     * 初始化时提交本地所有代码到远端仓库
     * @param :GitUser
     * @return
     */
    public boolean initCommitAndPushAllFiles(GitUser gitUser) {
        logger.info("initCommit方法传入本地仓库路径：{}用户名：{} 用户密码",gitUser.getPath(),gitUser.getUsername(),gitUser.getPassword());
        try {
            FileRepository localRepo = new FileRepository( gitUser.getPath());
            Git git = new Git(localRepo);
            AddCommand addCommand = git.add().addFilepattern(".");
            addCommand.call();

            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage("init project");
            commitCommand.setAllowEmpty(true);
            commitCommand.call();
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUser.getUsrCode(), gitUser.getPassword()));
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
    @Override
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
     * @param: loaclGitPath
     * @param :username
     * @param :password
     * @param :filePath
     * @return
     */
    public boolean commitAndPushDelAllFiles(GitUser gitUser)  throws Exception{
        logger.info("注册用户：{} 模块ID：{} 文件路径：{} 文件名{}",gitUser.getUsrCode(),gitUser.getModId(),gitUser.getCurrentPath(),gitUser.getFileName());
        String delPath = showProjectFileService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId()) + File.separator +gitUser.getCurrentPath() +File.separator+ gitUser.getFileName();//正式
        //本地删除
        FileUtil.delFile(delPath);
        //git服务器同步删除
        /**FileRepository localRepo = new FileRepository( gitUser.getPath());
         Git git = new Git(localRepo);
         AddCommand addCommand = git.add().setUpdate(true).addFilepattern(".");
         addCommand.call();
         CommitCommand commitCommand = git.commit();
         commitCommand.setMessage("init project");
         commitCommand.setAllowEmpty(true);
         commitCommand.call();
         PushCommand pushCommand = git.push();
         pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUser.getUsername(), gitUser.getPassword()));
         pushCommand.call();*/
        return true;


    }
}
