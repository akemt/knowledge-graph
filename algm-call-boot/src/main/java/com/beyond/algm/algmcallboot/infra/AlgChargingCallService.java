package com.beyond.algm.algmcallboot.infra;

import com.beyond.algm.algmcallboot.model.AlgResult;
import com.beyond.algm.exception.AlgException;

public interface AlgChargingCallService {
    /**
     *
     * @param usrCode
     * @param modId
     * @param version
     * @param keyValue
     * @param jsonStr
     * @return
     * @throws AlgException
     */
    String addChargingCall(String usrCode, String modId, String version, String keyValue,String jsonStr) throws AlgException;
}
