package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.ModuleService;
import com.beyond.algm.algmalgorithmsboot.infra.PayCashService;
import com.beyond.algm.algmalgorithmsboot.infra.UserService;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigEntity;
import com.beyond.algm.common.AESUtil;
import com.beyond.algm.model.AlgCashTrans;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class PayCashServiceTest {

    @Autowired
    private PayCashService payCashService;

    @Test
    public void payCash() throws Exception {
        //payCashService.presentCash("480d93e04ab04faaaaa079adec4557e3c766aaaa",522.0f);
    }

    /**
     * @author :lindw
     * @Description:积分充值
     * @date ：11:32 2017/12/5
     */
    @Test
    public void recharge() throws Exception {
        AlgCashTrans algCashTrans =new AlgCashTrans();
        AlgUser algUser = new AlgUser();
        algUser.setUsrSn("480d93e04ab04f079adec4557e3c766a");
        algCashTrans.setCashPayAmount(5.6f);
        payCashService.recharge(algCashTrans,algUser);
    }
}