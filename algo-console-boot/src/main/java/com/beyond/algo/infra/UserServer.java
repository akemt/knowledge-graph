package com.beyond.algo.infra;

import com.beyond.algo.common.AlgoplatResult;
import com.beyond.algo.dao.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author ：zhangchuanzhi
 * @Description:接口定义
 * @date ：13:32 2017/9/25
 */
public interface UserServer {
    AlgoplatResult createUser(User user);
    AlgoplatResult userLogin(User user) throws Exception;
}
