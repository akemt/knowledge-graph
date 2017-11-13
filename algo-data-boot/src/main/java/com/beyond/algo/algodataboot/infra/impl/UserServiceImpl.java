package com.beyond.algo.algodataboot.infra.impl;

import com.beyond.algo.algodataboot.infra.UserService;
import com.beyond.algo.mapper.AlgUserMapper;
import com.beyond.algo.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：zhangchuanzhi
 * @Description:
 * @date ：11:44 2017/11/13
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private AlgUserMapper algUserMapper;
    @Override
    public AlgUser findByUsrCode(String usrCode){

        return algUserMapper.selectUsrCode(usrCode);
    }

}
