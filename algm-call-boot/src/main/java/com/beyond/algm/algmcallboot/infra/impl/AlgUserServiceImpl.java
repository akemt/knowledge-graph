package com.beyond.algm.algmcallboot.infra.impl;

import com.beyond.algm.algmcallboot.infra.AlgUserService;
import com.beyond.algm.algmcallboot.model.AlgUser;
import com.beyond.algm.algmcallboot.repository.AlgUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgUserServiceImpl implements AlgUserService {
    @Autowired
    private AlgUserRepository algUserRepository;

    public AlgUser findByUsrCode(String userCode) {
        return algUserRepository.findByUsrCode(userCode);
    }
}
