package com.beyond.algm.algmdataboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;

/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserService {
    /**
     * 根据usr_code查找用户
     * @param usrCode
     * @return
     */
    AlgUser findByUsrCode(String usrCode) throws AlgException;

}
