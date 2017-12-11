package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @author ：zhangchuanzhi
 * @Description: 权限控制
 * @date ：10:18 2017/12/8
 */
public interface AuthService {
    void isDataByUser(String usrCode,String usrCodeSn,String usrSn,String dataSet,String fileName)throws AlgException;

    void isModelByUser(String usrCode,String sessionUsrCode,String callUsrSn,String modelSetName,String fileName)throws AlgException;

}
