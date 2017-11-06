package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.AntApiService;
import com.beyond.algo.algoalgorithmsboot.model.GitUser;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class AntApiServiceImplTest {

    @Autowired
    private AntApiService antApiService;

    @Test
    public void moduleAntBuildTest() throws Exception {
    /*    AlgUser algUser = new AlgUser();
        algUser.setUsrSn("37bf2269ee4845da8e86861bbde2438a");
        algUser.setUsrCode("qihe");
        algUser.setUsrName("祁贺");*/
        GitUser gitUser=new GitUser();
        antApiService.moduleAntBuild(gitUser);
    }

}
