package com.beyond.algo.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.model.UserVo;
import com.beyond.algo.model.UserAccountVo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


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
    AlgUser findByUsername(String username) throws UsernameNotFoundException;

}
