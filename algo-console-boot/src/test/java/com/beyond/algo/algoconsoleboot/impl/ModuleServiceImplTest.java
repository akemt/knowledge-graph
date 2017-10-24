package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.model.AlgModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class ModuleServiceImplTest {

    @Autowired
    private ModuleService moduleService;

    @Test
    public void initProject() throws Exception {
        moduleService.initProject("test1", "TestProject2");
    }

    @Test
    public void findByUsrSnAndModIdTest() throws Exception {
        AlgModule algModule = moduleService.findByUsrSnAndModId("aac44b648b10429cbaf85a6ae0113a65", "TestJava");

        System.out.println(algModule.toString());
    }
}