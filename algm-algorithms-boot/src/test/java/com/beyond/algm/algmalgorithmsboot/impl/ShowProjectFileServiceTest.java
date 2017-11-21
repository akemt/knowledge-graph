package com.beyond.algm.algmalgorithmsboot.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.ShowProjectFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
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
        //List<FileDir> aaa = showProjectFileService.ShowProjectFile("F:/001/0010001/");
    }
}
