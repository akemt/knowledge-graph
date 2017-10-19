package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.common.FileDir;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class ShowProjectFileServiceTest {
    @Autowired
    private ShowProjectFileService showProjectFileService;

    /**
     * 展示同级目录所有文件和文件夹.
     * @param :path 文件路径
     * @return :返回目录下的所有文件和文件
     */
    @Test
    public void ShowProjectFile() throws Exception {
        List<FileDir> aaa = showProjectFileService.ShowProjectFile("F:/001/0010001/");
    }
}
