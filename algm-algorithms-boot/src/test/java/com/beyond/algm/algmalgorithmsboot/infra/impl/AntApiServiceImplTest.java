package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.AntApiService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class AntApiServiceImplTest {

    @Autowired
    private AntApiService antApiService;

    @Test
    public void moduleAntBuildTest() throws Exception {
        GitUser gitUser=new GitUser();
        antApiService.moduleAntBuild(gitUser);
    }

}
