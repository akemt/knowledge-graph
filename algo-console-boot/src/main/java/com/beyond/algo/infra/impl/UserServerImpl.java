package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algo.common.AlgoplatResult;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.dao.mapper.UserMapper;
import com.beyond.algo.dao.model.User;
import com.beyond.algo.infra.UserServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServerImpl implements UserServer {
    private final static Logger logger = LoggerFactory.getLogger(UserServerImpl.class);
    @Autowired
    private UserMapper userMapper;
/**
 * @author ：zhangchuanzhi
 * @Description:实现用户注册功能
 * @param：User
 *@Modify By :zhangchuanzhi
 * @date ：13:16 2017/9/25
 */
    @Override
    public AlgoplatResult createUser(User user){
      String uuid= UUIDUtil.creatUUID();
      user.setUsrsn(uuid);
      user.setUpdatedate(new Date());
      user.setCreatedate(new Date());
                //  user.setPasswd();
      userMapper.insert(user);
      return AlgoplatResult.ok(null);
    }
}

