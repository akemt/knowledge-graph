package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.infra.OrgService;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
        org.setOwnerId("37bf2269ee4845da8e86861bbde2438a");

        orgService.createOrg(org, "qihe", "12345678");
    }

    @Test
    public void deleteOrgTest() throws Exception {
        orgService.deleteOrg("8e70e57b5e104f1b9d4d4dc0b141d83b");
    }

}
