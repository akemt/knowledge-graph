package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.KubernetesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/1 16:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class KubernetesServiceImplTest {
    @Test
    public void makeK8sSecretForNamespace() throws Exception {
        kubernetesService.makeK8sSecretForNamespace("qihe");
    }

    @Test
    public void makeK8sService() throws Exception {
        kubernetesService.makeK8sService("TestJavaO1","erniu4","0.0.3");
    }


    @Test
    public void makeK8sPod() throws Exception {
        kubernetesService.makeK8sPod("TestJavaO1","erniu4","0.0.3");
    }

    @Autowired
    private KubernetesService kubernetesService;

}