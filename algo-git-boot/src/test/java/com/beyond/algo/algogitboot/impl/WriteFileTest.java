package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.algogitboot.AlgoGitBootApplication;
import com.beyond.algo.infra.ReadFileService;
import com.beyond.algo.infra.WriteFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoGitBootApplication.class)
public class WriteFileTest {

    @Autowired
    private WriteFileService writeFileService;

    /**
     * 内容写入文件
     * @param :写入文件内容
     */
    @Test
    public void writeFile() throws Exception {
        writeFileService.writeFileString("public void commitAndPushDelAllFiles() throws Exception ");
    }
}
