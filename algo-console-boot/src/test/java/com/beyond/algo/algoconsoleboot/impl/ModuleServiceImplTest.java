package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
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
        AlgUser algUser = new AlgUser();
        algUser.setUsrCode("zhang");
        algUser.setUsrSn("9c371a86c6e5439097de4b20024479f3");
        moduleService.initProject(algUser, "TestJavaZhang");
    }

    @Test
    public void findByUsrSnAndModIdTest() throws Exception {
        AlgModule algModule = moduleService.findByUsrSnAndModId("aac44b648b10429cbaf85a6ae0113a65", "TestJava");

        System.out.println(algModule.toString());
    }

    @Test
    public void getModuleMainFilePathTest() throws Exception {
        String algModule = moduleService.getModuleMainFilePath("1","1","1");

        System.out.println(algModule.toString());
    }
}