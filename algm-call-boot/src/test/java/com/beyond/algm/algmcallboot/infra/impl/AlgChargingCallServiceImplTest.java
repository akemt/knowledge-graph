package com.beyond.algm.algmcallboot.infra.impl;

import com.beyond.algm.algmcallboot.AlgmCallBootApplication;
import com.beyond.algm.algmcallboot.infra.AlgChargingCallService;
import com.beyond.algm.algmcallboot.infra.AlgUserService;
import com.beyond.algm.algmcallboot.model.AlgResult;
import com.beyond.algm.algmcallboot.model.AlgUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmCallBootApplication.class)
public class AlgChargingCallServiceImplTest {

    @Autowired
    private AlgUserService algUserService;

    @Autowired
    private AlgChargingCallService algChargingCallService;

    @Test
    public void findByUsrCode() throws Exception {
        AlgUser aaa = algUserService.findByUsrCode("qihe");
        System.out.println("11111111111111111111111111"+aaa.getUsrName()+aaa.getUsrSn()+":"+aaa.getEmail());
    }

    @Test
    public void addChargingCall() throws Exception {
        System.out.println("1111111111111111111111111122222222222999999999999");
        AlgResult aaa = algChargingCallService.addChargingCall("lindw","TestJava","1.3.8","9a98142cbff747b38461259d5638ebf2", "11");
        System.out.println(aaa.toString());
    }

}