package com.beyond.algm.algmcallboot.infra;

import com.beyond.algm.algmcallboot.model.AlgResult;
import com.beyond.algm.exception.AlgException;

public interface AlgChargingCallService {
    /**
     * lindewei
     * API调用计费
     */
    AlgResult addChargingCall(String usrCode, String modId, String version, String keyValue) throws AlgException;
}
