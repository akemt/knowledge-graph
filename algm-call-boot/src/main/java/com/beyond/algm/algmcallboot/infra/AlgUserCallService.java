package com.beyond.algm.algmcallboot.infra;

import com.beyond.algm.algmcallboot.model.AlgUserCall;

import java.util.List;

public interface AlgUserCallService {
    List<AlgUserCall> findByCallUsrSn(String callUsrSn);
}
