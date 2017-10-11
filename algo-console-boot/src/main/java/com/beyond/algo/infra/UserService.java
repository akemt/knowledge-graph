package com.beyond.algo.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.beyond.algo.model.UserAccount;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserService {
    Result createUser(AlgUser user);
    Result userLogin(AlgUser user) throws Exception;
    Result changePassword(User user);
    Result updateUserInformation(AlgUser user);
    AlgUser findByUsername(String username) throws UsernameNotFoundException;
    UserAccount accountInformation(String accSn);
}
