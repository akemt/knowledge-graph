package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.ModuleService;
import com.beyond.algm.algmalgorithmsboot.infra.UserService;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algm.common.AESUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
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
        algUser.setUsrCode("erniu");
        algUser.setUsrSn("9c371a86c6e5439097de4b20024479f3");
        moduleService.initProject("","", "TestJavaC1","1");
    }

    @Test
    public void findByUsrSnAndModIdTest() throws Exception {
        AlgModule algModule = moduleService.findByUsrSnAndModId("aac44b648b10429cbaf85a6ae0113a65", "TestJava");

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


    /**
     * 测试组织创建项目接口
     *
     * @throws Exception
     */
    @Test
    public void addAlgModule() throws Exception {


        AlgModule algModule = new AlgModule();
        algModule.setOrgUsrCode("testGroup2");
        algModule.setUsrSn("1ab380d8078d414f8edc4dcc33a65348");
        algModule.setCreateSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        algModule.setModId("project09");
        algModule.setModName("项目09");
        algModule.setOrgUsrCode("testGroup2");
        algModule.setLanSn("1");
        algModule.setCatSn("8d78dbea72fe4baf8fd12635c329b96b");
        algModule.setLicSn("1");
        algModule.setAtlSn("1");
        algModule.setNeedWeb("1");
        algModule.setEnvType("1");
        algModule.setIsTrain("1");
        algModule.setIsColony("1");
        algModule.setModDesc("1");

        AlgUser algUser = new AlgUser();
        algUser.setUsrCode("gaohaijun");
        algUser.setPasswd("47c668187ec1fc010443a9372266d304");
        algUser.setPrivateToken("mHN8exdYzNja1eZkXPxc");
        algUser.setIsOrg("1");

        moduleService.addAlgModule(algModule,algUser);

    }
}