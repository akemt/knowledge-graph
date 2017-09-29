package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algo.common.Result;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.PasswordEncrypUtil;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.mapper.UserMapper;
import com.beyond.algo.model.User;
import com.beyond.algo.infra.UserServer;

import com.beyond.algo.model.ProjectConfigEntity;
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
   @Autowired
    private ProjectConfigEntity projectConfigEntity;
/**
 * @author ：zhangchuanzhi
 * @Description:实现用户注册功能
 * @param：User
 * @Modify By :zhangchuanzhi
 * @date ：13:16 2017/9/25
 */
    @Override
    public Result createUser(User user){
        String uuid= UUIDUtil.creatUUID();
        user.setUsrsn(uuid);
        user.setUpdatedate(new Date());
        user.setCreatedate(new Date());
        logger.info("密码："+projectConfigEntity.getPassword());
        String passWord=PasswordEncrypUtil.encrypt(user.getPasswd().getBytes(),projectConfigEntity.getPassword()).toString();
        user.setPasswd(passWord);
        userMapper.insert(user);
        return Result.successResponse();
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户登录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:26 2017/9/28
     */
    @Override
    public Result userLogin(User user) throws Exception{
        user = userMapper.selectUsrname(user.getUsrname());
      // 如果没有这个用户
        if(Assert.isNULL(user)){
              return Result.failureResponse();
        }
      // 对比密码
       String passwordEncryp = PasswordEncrypUtil.decrypt(user.getPasswd().getBytes(),projectConfigEntity.getPassword()).toString();
        if(!passwordEncryp.equals(user.getPasswd())){
            return  Result.failureResponse();
        }
     return  Result.successResponse();
    }

}

