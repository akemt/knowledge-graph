package com.beyond.algm.algmcallboot.controller;

import com.beyond.algm.algmcallboot.infra.AlgChargingCallService;
import com.beyond.algm.algmcallboot.model.AlgResult;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping
public class AlgChargingCallController {

    @Autowired
    private AlgChargingCallService algChargingCallService;

    /**
     * @author ：lindewei
     * @Description: API调用计费
     * @date ：9:30 2017/11/29
     */
    @RequestMapping(value = "/{usrCode}/{modId}/{version:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result algChargingCall(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId,
                                  @PathVariable("version") String version, String keyValue) throws AlgException{
        AlgResult algResult = algChargingCallService.addChargingCall(usrCode,modId,version,keyValue);
        return Result.ok(algResult);
    }
}