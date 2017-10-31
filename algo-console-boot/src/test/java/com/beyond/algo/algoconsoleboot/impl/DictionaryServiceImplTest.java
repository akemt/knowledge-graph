package com.beyond.algo.algoconsoleboot.impl;

import com.beyond.algo.algoconsoleboot.AlgoConsoleBootApplication;
import com.beyond.algo.algoconsoleboot.infra.AntApiService;
import com.beyond.algo.algoconsoleboot.infra.DictionaryService;
import com.beyond.algo.model.AlgDic;
import com.beyond.algo.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgoConsoleBootApplication.class)
public class DictionaryServiceImplTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void moduleAntBuildTest() throws Exception {
        String code="module_access_mode";
        List<AlgDic> algDicList=dictionaryService.getDictionarylist(code);
    }

}
