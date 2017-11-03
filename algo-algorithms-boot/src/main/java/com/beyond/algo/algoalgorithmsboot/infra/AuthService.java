package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;

public interface AuthService {

    void isModuleByUser(String usrCode,String modId) throws AlgException;
}
