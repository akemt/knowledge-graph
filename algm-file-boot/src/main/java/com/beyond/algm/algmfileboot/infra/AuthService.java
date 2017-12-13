package com.beyond.algm.algmfileboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @author ：zhangchuanzhi
 * @Description: 权限控制
 * @date ：10:18 2017/12/8
 */
public interface AuthService {
    /**
     * @author ：zhangchuanzhi
     * @Description: 数据下载权限需要付费逻辑
     * @param：
     * @date ： 2017-12-8 21:54:06
     */
    void isDataByUser(String usrCode, String usrCodeSn, String usrSn, String dataSet, String fileName)throws AlgException;
    /**
     * @author ：zhangchuanzhi
     * @Description:模型下载权限需要付费逻辑
     * @param：
     * @date ： 2017-12-11 21:54:06
     */
    void isModelByUser(String usrCode, String sessionUsrCode, String callUsrSn, String modelSetName, String fileName)throws AlgException;


    /**
     * @author ：zhangchuanzhi
     * @Description:在接口路径判断权限
     * @param：
     * @date ： 2017-12-11 21:54:06
     */
    void isPathByUser(String usrCode, String sessionUsrCode)throws AlgException;


}
