package com.beyond.algm.algmalgorithmsboot.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.DictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class DictionaryServiceImplTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void moduleAntBuildTest() throws Exception {
        String code="module_access_mode";
        //List<AlgDic> algDicList=dictionaryService.getDictionarylist(code);
    }

}
