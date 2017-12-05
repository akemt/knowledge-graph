package com.beyond.algm.algmcallboot.infra;

import com.beyond.algm.algmcallboot.model.AlgUser;

public interface AlgUserService {
    AlgUser findByUsrCode(String userCode);
}
