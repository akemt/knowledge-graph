package com.beyond.algo.algoalgorithmsboot.impl;

import com.beyond.algo.algoalgorithmsboot.AlgoAlgorithmsBootApplication;
import com.beyond.algo.algoalgorithmsboot.infra.DictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoAlgorithmsBootApplication.class)
public class DictionaryServiceImplTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void moduleAntBuildTest() throws Exception {
        String code="module_access_mode";
        //List<AlgDic> algDicList=dictionaryService.getDictionarylist(code);
    }

}
