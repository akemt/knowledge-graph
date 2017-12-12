package com.beyond.algm.algmdataboot.infra.impl;

import com.beyond.algm.algmdataboot.infra.AuthService;

import com.beyond.algm.exception.AlgException;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


/**
 * @author ：zhangchuanzhi
 * @Description:用户权限控制
 * @date ：10:19 2017/12/8
 */
@Service
@Slf4j
public class AuthServiceImpl  implements AuthService {

    /**
     * @author ：zhangchuanzhi
     * @Description: 用户名在用户上，通过路径查看权限,
     * @param：usrCode：登录用户session，sessionUsrCode:
     * @date ： 2017-12-08 21:54:06
     */
    @Override
    public void isPathByUser(String usrCode,String sessionUsrCode)throws AlgException{
        log.info("路径用户:{},登录用户:{}",usrCode,sessionUsrCode);
        if(!usrCode.equals(sessionUsrCode)){
            String[] checkMessage = {"对不起您没有权限！", ""};
            throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000010", checkMessage);
        }
    }

}
