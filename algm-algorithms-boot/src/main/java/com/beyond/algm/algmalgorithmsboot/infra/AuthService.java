package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

public interface AuthService {

    void isModuleByUser(String usrCode,String modId) throws AlgException;
}
