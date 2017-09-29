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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


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
        String passWord= new BASE64Encoder().encode(PasswordEncrypUtil.encrypt(user.getPasswd().getBytes(),projectConfigEntity.getPassword()));
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
        String password=user.getPasswd();
        user = userMapper.selectUsrname(user.getUsrname());
      // 如果没有这个用户
        if(Assert.isNULL(user)){
              return Result.failureResponse();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(user.getPasswd());
      // 对比密码
       String passwordEncryp = new String(PasswordEncrypUtil.decrypt(buf,projectConfigEntity.getPassword()));
        if(!passwordEncryp.equals(password)){
            return  Result.failureResponse();
        }
     return  Result.successResponse();
    }

}

