package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.infra.ReadFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class ReadFileTest {

    @Autowired
    private ReadFileService readFileService;

    /**
     * 文件读取测试
     * @param :file 想要读取的文件对象
     * @return :返回文件内容
     */
    @Test
    public void readFile() throws Exception {
        File file = new File("D:/JGitServiceTest.java");
        System.out.println(readFileService.readFileString(file));
    }
}
