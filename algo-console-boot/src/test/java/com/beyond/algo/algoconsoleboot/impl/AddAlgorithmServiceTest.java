package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class AddAlgorithmServiceTest {

    @Autowired
    private ModuleService moduleService;

    /**
     * 新增算法测试
     */
    @Test
    public void AddAlgorithmService() throws Exception {

        moduleService.addAlgModule(null);

        System.out.println("");
    }
}
