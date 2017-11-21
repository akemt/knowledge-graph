package com.beyond.algm.algmdataboot.infra.impl;

import com.beyond.algm.algmdataboot.infra.UserService;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：zhangchuanzhi
 * @Description:
 * @date ：11:44 2017/11/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AlgUserMapper algUserMapper;
    @Override
    public AlgUser findByUsrCode(String usrCode){

        return algUserMapper.selectUsrCode(usrCode);
    }

}
