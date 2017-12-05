package com.beyond.algm.algmcallboot.controller;

import com.beyond.algm.algmcallboot.infra.AlgUserService;
import com.beyond.algm.algmcallboot.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    AlgUserService algUserService;
    @GetMapping("/test")
    public AlgUser test(String userName){
        return algUserService.findByUsrCode(userName);
    }
}
