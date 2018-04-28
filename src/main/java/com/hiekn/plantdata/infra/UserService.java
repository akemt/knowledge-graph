package com.hiekn.plantdata.infra;

import com.hiekn.plantdata.Entity.User;
import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.exception.GraphException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     *  获取用户信息
     * @param userName
     * @return
     */
    public User  getUserInfo(String userName);


    /**
     * 用户登录
     *
     * @param user
     * @throws GraphException
     * @throws Exception
     */
    public Result userLogin(User user ,HttpSession session);


}
