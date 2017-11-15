package com.beyond.algm.algmcallboot.controller;

import com.beyond.algm.algmcallboot.infra.AlgUserCallService;
import com.beyond.algm.algmcallboot.model.AlgUserCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {


    @Autowired
    AlgUserCallService algUserCallService;
    @GetMapping("/test")
    public List<AlgUserCall> test(String callUsrSn){
        return algUserCallService.findByCallUsrSn(callUsrSn);
    }
}
