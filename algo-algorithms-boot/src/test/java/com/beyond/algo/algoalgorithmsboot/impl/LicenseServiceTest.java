package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.LicenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class LicenseServiceTest {

    @Autowired
    private LicenseService licenseService;

    /**
     * 新增算法测试
     */
    @Test
    public void LicenseService() throws Exception {

        licenseService.selectAlgLicense("");

        System.out.println("");
    }
}
