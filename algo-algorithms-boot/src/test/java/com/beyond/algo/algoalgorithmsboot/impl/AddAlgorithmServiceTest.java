package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.ModuleService;
import com.beyond.algo.common.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class AddAlgorithmServiceTest {

    @Autowired
    private ModuleService moduleService;

    /**
     * 新增算法测试
     */
    @Test
    public void AddAlgorithmService() throws Exception {

        //moduleService.addAlgModule(null);

        System.out.println("");
    }
    @Test
    public  void aa (){
        System.out.println(UUIDUtil.createUUID());
        System.out.println(UUIDUtil.createUUID());
        System.out.println(UUIDUtil.createUUID());
        System.out.println(UUIDUtil.createUUID());
    }
}
