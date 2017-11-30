package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/24 8:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class DockerServiceImplTest {
    @Autowired
    private DockerService dockerService;
    @Test
    public void makeDockerImage() throws Exception {
        dockerService.bulidDockerImage("TestJava4","erniu4","0.0.1","");
    }

}