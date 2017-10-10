package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algo.common.*;
import com.beyond.algo.mapper.AlgUserMapper;
import com.beyond.algo.model.AlgUser;

import com.beyond.algo.infra.UserService;

import com.beyond.algo.model.ProjectConfigEntity;
import com.beyond.algo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AlgUserMapper algUserMapper;

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
    public Result createUser(AlgUser user){
        String uuid= UUIDUtil.creatUUID();
        user.setUsrSn(uuid);
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        logger.info("密码："+projectConfigEntity.getKeyAES());
        String passWord= AESUtil.encrypt(user.getPasswd(),projectConfigEntity.getKeyAES());
        user.setPasswd(passWord);
        algUserMapper.insert(user);
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
    public Result userLogin(AlgUser user) throws Exception{
        String password=user.getPasswd();
        user = algUserMapper.selectUsrname(user.getUsrName());
      // 如果没有这个用户
        if(Assert.isNULL(user)){
              return Result.failureResponse();
        }
      // 对比密码
        String passwordEncryp = AESUtil.decrypt(user.getPasswd(),projectConfigEntity.getKeyAES());
        if(!passwordEncryp.equals(password)){
            return  Result.failureResponse();
        }
     return  Result.successResponse();
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:09 2017/10/09
     */
    @Override
    public Result changePassword(User user) {
        // 判断两次输入密码是否一致
         if(!user.getConfirmPassword().equals(user.getNewPassword())){
             String message="两次输入新密码一致";
             return Result.failure(message);
         }else{
             // 当两次输入密码一致时候判断输入新密码和原始密码是否一致
             AlgUser  algUser =algUserMapper.selectByPrimaryKey(user.getUsrSn());
             String passwordEncryp = AESUtil.decrypt(algUser.getPasswd(),projectConfigEntity.getKeyAES());
             // 判断输入原始密码是否是数据库密码
             if(user.getPasswd().equals(passwordEncryp)){
                 if(user.getNewPassword().equals(passwordEncryp)){
                     String message="原始密码和新密码一致";
                     return Result.failure(message);
                 }
             }
         }
         String newPassWord=AESUtil.encrypt(user.getNewPassword(),projectConfigEntity.getKeyAES());
         user.setPasswd(newPassWord);
         user.setUpdateDate(new Date());
         algUserMapper.update(user);
         return  Result.successResponse();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户信息更改
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:15 2017/10/10
     */
    @Override
    public Result updateUserInformation(AlgUser user){
        // 判断邮箱是否合法
        boolean emailFlag =NumCheckUtil.checkEmail(user.getEmail());
        if(!emailFlag){
            String message="邮箱不合法";
            return Result.failure(message);
        }
        // 判断电话
        boolean telFlag =NumCheckUtil.checkTel(user.getTelephone());
        if(!telFlag){
            String message="电话不合法";
            return Result.failure(message);
        }
        // 判断个人主页
        boolean webFlag =NumCheckUtil.checkWebsite(user.getUsrUrl());
        if(!webFlag){
            String message="个人主页不合法";
            return Result.failure(message);
        }
        // 判断名字是否全是中文
        boolean nameFlag =NumCheckUtil.isChineseStr(user.getUsrName());
        if(!nameFlag){
            String message="必须全是中文";
            return Result.failure(message);
        }
        // 1：代码邮件 0：短信
        if("1".equals(user.getNeedNotify())){
            //多线程发送邮件
        }else{
           // 多线程发送短信
        }
        user.setUpdateDate(new Date());
        algUserMapper.update(user);
        return  Result.successResponse();
    }
}

