package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.ModuleService;
import com.beyond.algo.algoalgorithmsboot.infra.UserService;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algo.common.AESUtil;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class ModuleServiceImplTest {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectConfigEntity projectConfigEntity;

    @Test
    public void initProject() throws Exception {
        AlgUser algUser = new AlgUser();
        algUser.setUsrCode("zhang");
        algUser.setUsrSn("9c371a86c6e5439097de4b20024479f3");
        moduleService.initProject(algUser, "TestJavaZhang","1");
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

    @Test
    public void test() throws Exception {

      /*  AlgUser algModule = userService.findByUsrCode("zhang1");*/
   //     System.out.println("12345678");
 //      String pass = AESUtil.Encrypt("12345678",projectConfigEntity.getKeyAES());
       String passwordEncryp=  AESUtil.decryptAES( "47c668187ec1fc010443a9372266d304",projectConfigEntity.getKeyAES());

     //   String passwordEncryp= AESUtil.encrypt("12345678",projectConfigEntity.getKeyAES());
//        System.out.println(pass);
        System.out.println(passwordEncryp);


    }
}