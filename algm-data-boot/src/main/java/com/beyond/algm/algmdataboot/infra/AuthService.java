package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @author ：zhangchuanzhi
 * @Description: 权限控制
 * @date ：10:18 2017/12/8
 */
public interface AuthService {

    /**
     * @author ：zhangchuanzhi
     * @Description:在接口路径判断权限
     * @param：
     * @date ： 2017-12-11 21:54:06
     */
    void isPathByUser(String usrCode,String sessionUsrCode)throws AlgException;


}
