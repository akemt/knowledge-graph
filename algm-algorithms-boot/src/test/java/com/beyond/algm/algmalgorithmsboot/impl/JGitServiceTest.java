package com.beyond.algm.algmalgorithmsboot.impl;


import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.algmalgorithmsboot.infra.BuildAntProjectService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class JGitServiceTest {
    @Autowired
    private JGitService jGitService;
    @Autowired
    private BuildAntProjectService buildAntProjectService;
    private final static Logger logger = LoggerFactory.getLogger(JGitServiceTest.class);
    GitUser gitUser = new GitUser();

    @Test
    public void gitCloneProjectTest() throws Exception {
      //  jGitService.gitCloneProject("http://192.168.1.88/test1/testproject.git", "TestProject", "test1", "test1234");
    }

    @Test
    public void initCommitAllFilesTest() throws Exception {
        GitUser gitUser=new GitUser();
                gitUser.setPath("E:/repo/zhang/TestJavaZhang/.git");
                gitUser.setUsrCode("zhang");
                gitUser.setPassword("12345678");
                 String version= jGitService.commitAndPushAllFiles(gitUser);
                System.out.println(version);

    }

    @Test
    // 测试本地删除文件同时删除服务器
    public void commitAndPushDelAllFiles() throws Exception {
    //    jGitService.commitAndPushDelAllFiles("E:/repo/test1/TestProject/.git", "test1", "test1234", "E:/repo/test1/TestProject/222.txt");
    }

    @Test
    // 本地文件写入保存
    public void writeAndSaveFile() throws Exception {
        String content = "124444444443";
        FileUtil.writeFile("E:/repo/test1/TestProject/23.txt", content);

    }

    @Test
    // 本地编译打包上传测试
    public void BuildAntProject() throws Exception {
        gitUser.setPassword("test1234");
        gitUser.setPath("E:/repo/test1/TestProject");
        gitUser.setDescDir("D:/test");
        gitUser.setUsername("test1");
        buildAntProjectService.buildAndUpLoadProject(gitUser);

    }
    @Test
    // 本地编译打包上传测试
    public void getHistoryInfo() throws Exception {
          Git git=null;
        File gitDir = new File("E:/repo/zhang/TestJavaZhang/.git");

            if (git == null) {
                git = Git.open(gitDir);
            }
            Iterable<RevCommit> gitlog= git.log().setMaxCount(1).call();
            for (RevCommit revCommit : gitlog) {
                String version=revCommit.getName();//版本号
                System.out.println (revCommit.getParent(0).toString());
                revCommit.getAuthorIdent().getName();
                revCommit.getAuthorIdent().getEmailAddress();
                revCommit.getAuthorIdent().getWhen();//时间
                System.out.println(version);
            }

    }


}
