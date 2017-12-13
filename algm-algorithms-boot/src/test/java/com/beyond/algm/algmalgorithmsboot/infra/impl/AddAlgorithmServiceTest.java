package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.algmalgorithmsboot.infra.ModuleService;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class AddAlgorithmServiceTest {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private AlgModuleListService algModuleListService;

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
    @Test
    public  void modStar (){
        try {
            algModuleListService.modStar("aaaa","ss");
        } catch (AlgException e) {
            e.printStackTrace();
        }
    }
}
