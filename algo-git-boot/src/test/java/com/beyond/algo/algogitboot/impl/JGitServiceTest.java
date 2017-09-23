package com.beyond.algo.algogitboot.impl;


import com.beyond.algo.common.FileUtil;
import com.beyond.algo.infra.BuildAntProjectService;
import com.beyond.algo.infra.GitLibService;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@RestClientTest({GitLibService.class})
public class JGitServiceTest {
    @Autowired
    private JGitService jGitService;
    @Autowired
    private BuildAntProjectService buildAntProjectService;
    private final static Logger logger = LoggerFactory.getLogger(JGitServiceTest.class);
    GitUser gitUser = new GitUser();

    @Test
    public void gitCloneProjectTest() throws Exception {
        jGitService.gitCloneProject("http://192.168.1.88/test1/testproject.git", "TestProject", "test1", "test1234");
    }

    @Test
    public void initCommitAllFilesTest() throws Exception {

        jGitService.initCommitAndPushAllFiles("E:/repo/test1/TestProject/.git", "test1", "test1234");
    }

    @Test
    // 测试本地删除文件同时删除服务器
    public void commitAndPushDelAllFiles() throws Exception {
        jGitService.commitAndPushDelAllFiles("E:/repo/test1/TestProject/.git", "test1", "test1234", "E:/repo/test1/TestProject/222.txt");
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


}
