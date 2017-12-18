package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

public interface AuthService {


    void isModuleByUser(String usrCode,String modId,String sessionUsrCode,String usrSn) throws AlgException;

    /**
     * @author ：zhangchuanzhi
     * @Description:在接口路径判断权限
     * @param：
     * @date ： 2017-12-11 21:54:06
     */
    void isPathByUser(String usrCode,String sessionUsrCode)throws AlgException;
    /**
     * @author ：zhangchuanzhi
     * @Description:判断权限和算法名称权限
     * @param：
     * @date ： 2017-12-11 21:54:06
     */
    void isModuleAuth(String usrCode,String modId,String sessionUsrCode,String usrSn) throws AlgException;

}
