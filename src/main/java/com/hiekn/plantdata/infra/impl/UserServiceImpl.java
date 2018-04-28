package com.hiekn.plantdata.infra.impl;

import com.hiekn.plantdata.Entity.User;
import com.hiekn.plantdata.common.Assert;
import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.common.UUIDUtil;
import com.hiekn.plantdata.common.WebSecurityConfig;
import com.hiekn.plantdata.exception.GraphException;
import com.hiekn.plantdata.infra.UserService;
import com.hiekn.plantdata.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService{


    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String userName) {

        return userMapper.queryUserInfoByUsrname(userName);
    }

    public Result userLogin(User user ,HttpSession session){
        String password = user.getUsrPassword();
        user = userMapper.queryUserInfoByUsrname(user.getUsrName());
        // 如果没有这个用户
        if (Assert.isNULL(user)) {
            return Result.success("",402,"用户名不存在!");
        }
        // 对比密码
        //  String passwordEncryp = AESUtil.decryptAES(user.getPasswd(), projectConfigEntity.getKeyAES());
        if (!user.getUsrPassword().equals(password)) {
            return Result.success("",401,"用户名或者密码不正确!");
        }else{
            session.setAttribute("userId", user.getUsrSID());
            Map<String,String> map = new HashMap<String,String>();
            map.put("token", UUIDUtil.createUUID());
            return Result.success(map,200,"登录成功!");
        }
    }


}
