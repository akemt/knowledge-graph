package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
@Slf4j
@Service
public class JGitServiceImpl implements JGitService {

    private final static Logger logger = LoggerFactory.getLogger(JGitServiceImpl.class);

    @Autowired
    private PathService pathService;

    @Override
    public void gitCloneProject(GitUser gitUser) throws AlgException {
        log.info("gitCloneProject 增加git的clone项目");
        //Git git = Git.cloneRepository().setURI(projectRepoURI).setDirectory(new File("E:/repo")).call();
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setURI(gitUser.getProjectRepoURI());
        cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUser.getUsrCode(), gitUser.getPassword()));

        String  strUrlUsrCodeAndModId = "";
        String strPath = null;
        if("1".equals(gitUser.getIsOrg())) {//组所有者-下面的组织
            strUrlUsrCodeAndModId = pathService.getOrgAlgBasePath(gitUser.getOrgUsrCode(),gitUser.getModId());
            //E:\repo\组织编号\项目名\当前用户编号\src\algmarket\TestJavaK1 如：E:\repo\testGroup1\Project01\gaohaijun\src\algmarket\TestJavaK1
            strPath = pathService.getModuleBasePath(strUrlUsrCodeAndModId,gitUser.getUsrCode());
        }else{
            //E:\repo\当前登录用户\项目名\src\algmarket\TestJavaK1 如：E:\repo\erniu4\Project01\src\algmarket\TestJavaK1
            strPath = pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId());
        }


        cloneCommand.setDirectory(new File(strPath));
        try {
            cloneCommand.call();
        } catch (Exception e) {
            log.error("gitCloneProject 增加git的clone项目");
            log.error(e.toString());
            throw new AlgException("BEYOND.ALG.MODULE.ADD.0000002");
        }

        if("1".equals(gitUser.getIsOrg())) {//组所有者-下面的组织
            gitUser.setPath(pathService.getModuleBasePath(strUrlUsrCodeAndModId,gitUser.getUsrCode())+File.separator+".git");
            gitUser.setFilePath(pathService.getModuleBasePath(strUrlUsrCodeAndModId,gitUser.getUsrCode()));
        }else{
            gitUser.setPath(pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId())+File.separator+".git");
            gitUser.setFilePath(pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId()));
        }
    }

    /**
     * commit and push 本地所有代码到远端仓库
     * @param :GitUser
     * @return
     */
    public String commitAndPushAllFiles(GitUser gitUser)throws AlgException  {
        logger.info("initCommit方法传入本地仓库路径:{};用户名:{} ;用户密码:{};",gitUser.getPath(),gitUser.getUsrCode(),gitUser.getPassword());
        String version="";
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
            File gitDir = new File(gitUser.getPath());
            if (git == null) {
                git = Git.open(gitDir);
            }
            Iterable<RevCommit> gitlog= git.log().setMaxCount(1).call();
            for (RevCommit revCommit : gitlog) {
                version=revCommit.getName();//版本号
                revCommit.getAuthorIdent().getName();
                revCommit.getAuthorIdent().getEmailAddress();
                revCommit.getAuthorIdent().getWhen();//时间
            }

        } catch (Exception e) {
            log.info("提交git代码失败");
            String[] checkMessage = {"上传代码",""};
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000003",checkMessage);

        }
        return version;
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
    public boolean commitAndPushDelAllFiles(GitUser gitUser)  throws AlgException{
        //当前路径或者文件是否为空判断
        if(Assert.isEmpty(gitUser.getCurrentPath()) && Assert.isEmpty(gitUser.getFileName())){
            throw new AlgException("BEYOND.ALG.MODULE.GENERATE.00000012");
        }
        logger.info("注册用户：{} 模块ID：{} 文件路径：{} 文件名{}",gitUser.getUsrCode(),gitUser.getModId(),gitUser.getCurrentPath(),gitUser.getFileName());
        String delPath = pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId()) + File.separator +gitUser.getCurrentPath() +File.separator+ gitUser.getFileName();//正式
        //本地删除
        FileUtil.delFile(delPath);
        return true;
    }
}
