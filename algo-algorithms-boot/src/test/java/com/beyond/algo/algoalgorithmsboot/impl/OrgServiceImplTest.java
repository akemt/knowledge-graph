package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.OrgService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class OrgServiceImplTest {

    @Autowired
    private OrgService orgService;

    @Test
    public void createOrgTest() throws AlgException {

        AlgUser org = new AlgUser();
        org.setUsrCode("testOrg2");
        org.setUsrName("测试组织2");
        org.setEmail("test@qq.com");
        org.setOwnerId("64bd0e5ee1a6409f97d12c271bb8fa68");

        orgService.createOrg(org, "qihe", "12345678");
    }

    @Test
    @Transactional
    public void deleteOrgTest() throws AlgException {
        orgService.deleteOrg("8e70e57b5e104f1b9d4d4dc0b141d83b");
    }

    @Test
    public void getOrgDetailTest() throws AlgException {
        orgService.getOrgDetail("1ab380d8078d414f8edc4dcc33a65348");
    }
}
