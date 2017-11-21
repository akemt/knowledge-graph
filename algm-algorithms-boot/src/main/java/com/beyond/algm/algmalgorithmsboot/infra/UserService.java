package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.UserAccountVo;
import com.beyond.algm.vo.UserVo;

/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserService {
    void createUser(AlgUser user) throws AlgException,Exception;
    void userLogin(AlgUser user) throws AlgException,Exception;
    void changePassword(UserVo userVo) throws AlgException,Exception;
    void updateUserInformation(AlgUser user) throws AlgException;
    UserAccountVo accountInformation(String accSn)throws AlgException;

    /**
     * 根据usr_code查找用户
     * @param usrCode
     * @return
     */
    AlgUser findByUsrCode(String usrCode) throws AlgException;

}
