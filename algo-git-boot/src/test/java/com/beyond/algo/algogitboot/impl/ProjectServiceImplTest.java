package com.beyond.algo.algogitboot.impl;

import com.beyond.algo.algogitboot.AlgoGitBootApplication;
import com.beyond.algo.infra.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoGitBootApplication.class)
public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void initProject() throws Exception {
        projectService.initProject("test1", "TestProject2");
    }

}