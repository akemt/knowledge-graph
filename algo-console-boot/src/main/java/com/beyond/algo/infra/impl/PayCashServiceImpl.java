package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户登录注册server层
 * @date ：13:26 2017/9/25
 */

import com.beyond.algo.infra.PayCashService;
import com.beyond.algo.mapper.AlgCashTransMapper;

import com.beyond.algo.model.*;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PayCashServiceImpl implements PayCashService {
    private final static Logger logger = LoggerFactory.getLogger(PayCashServiceImpl.class);
    @Autowired
    private AlgCashTransMapper algCashTransMapper;

    /**
     * @author ：zhangchuanzhi
     * @Description:用户充值记录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：15:09 2017/10/09
     */
    @Override
    public List<AlgCashTrans> payRecord(String usrSn) {
       //分页处理
        int page=0;
        int rows=2;
        PageHelper.startPage(page, rows);
        List<AlgCashTrans> algCashTransList=algCashTransMapper.payRecord(usrSn);
        return algCashTransList;
    }
}

