package com.beyond.algo.algodataboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.UserAccountVo;
import com.beyond.algo.vo.UserVo;

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
