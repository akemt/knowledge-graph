package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.UserAccountVo;
import com.beyond.algo.vo.UserVo;

/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserService {
    Result createUser(AlgUser user);
    Result userLogin(AlgUser user) throws Exception;
    Result changePassword(UserVo userVo);
    Result updateUserInformation(AlgUser user);
    UserAccountVo accountInformation(String accSn);

    /**
     * 根据usr_code查找用户
     * @param usrCode
     * @return
     */
    AlgUser findByUsrCode(String usrCode) ;

}
