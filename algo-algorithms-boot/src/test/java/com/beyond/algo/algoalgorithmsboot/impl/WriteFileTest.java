package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.WriteFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class WriteFileTest {

    @Autowired
    private WriteFileService writeFileService;

    /**
     * 内容写入文件
     * @param :写入文件内容
     */
    @Test
    public void writeFile() throws Exception {
        //writeFileService.writeFileString("public void commitAndPushDelAllFiles() throws Exception大连的的 ","F:/001/0010002/创建新文件.txt");
    }
}
