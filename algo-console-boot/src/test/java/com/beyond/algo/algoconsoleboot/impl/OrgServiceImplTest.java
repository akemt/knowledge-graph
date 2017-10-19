package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.infra.OrgService;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class OrgServiceImplTest {

    @Autowired
    private OrgService orgService;

    @Test
    public void createOrgTest() throws Exception {

        AlgUser org = new AlgUser();
        org.setUsrCode("testOrg2");
        org.setUsrName("测试组织2");
        org.setEmail("test@qq.com");
        org.setOwnerId("62a6a211ecfc480bbc9c67d65a44b535");

        orgService.createOrg(org, "qihe", "12345678");
    }

}
