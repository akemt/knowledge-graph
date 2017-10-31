package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class AddAlgorithmServiceTest {

    @Autowired
    private AddAlgorithmService addAlgorithmService;

    /**
     * 新增算法测试
     */
    @Test
    public void AddAlgorithmService() throws Exception {

        addAlgorithmService.addAlgorithm("aac44b648b10429cbaf85asse0113aa5","Java","dee","apache协议","1","1",
               "1","1","1","1","1",
              "1","1");

        System.out.println("");
    }
}
