package com.beyond.algo.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.User;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserServer {
    Result createUser(User user);
    Result userLogin(User user) throws Exception;
}
