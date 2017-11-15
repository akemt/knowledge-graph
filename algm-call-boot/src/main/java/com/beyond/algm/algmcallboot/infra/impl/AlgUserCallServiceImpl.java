package com.beyond.algm.algmcallboot.infra.impl;

import com.beyond.algm.algmcallboot.infra.AlgUserCallService;
import com.beyond.algm.algmcallboot.model.AlgUserCall;
import com.beyond.algm.algmcallboot.repository.AlgUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgUserCallServiceImpl implements AlgUserCallService {
    @Autowired
    AlgUserRepository algUserRepository;
    public List<AlgUserCall> findByCallUsrSn(String callUsrSn){
        return algUserRepository.findByCallUsrSn(callUsrSn);
    }
}
