package com.beyond.algo.infra.impl;

/**
 * @author ：zhangchuanzhi
 * @Description:用户算法使用情况
 * @date ：13:26 2017/10/12
 */



import com.beyond.algo.common.AESUtil;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.infra.UseAlgorithmService;
import com.beyond.algo.mapper.AlgCashHisMapper;
import com.beyond.algo.mapper.AlgRUserModuleCallTransMapper;
import com.beyond.algo.model.AlgCashTrans;
import com.beyond.algo.model.AlgRUserModuleCallTrans;
import com.beyond.algo.model.AlgUser;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UseAlgorithmServiceImpl implements UseAlgorithmService {
 /*   private final static Logger logger = LoggerFactory.getLogger(UseAlgorithmServiceImpl.class);
    @Autowired
    private AlgRUserModuleCallTransMapper algRUserModuleCallTransMapper;
    *//**
     * @author ：zhangchuanzhi
     * @Description:用户使用算法情况
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：13:16 2017/10/12
     *//*
    @Override
    public List<AlgRUserModuleCallTransVo> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo){
        //分页处理
        PageHelper.startPage(algRUserModuleCallTransVo.getPage(), algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTrans> lgRUserModuleCallTransList=algRUserModuleCallTransMapper.selectAlgorithmRecord(algRUserModuleCallTransVo.getCallUsrSn());
        if(Assert.isNotEmpty(lgRUserModuleCallTransList)){
            for(int i=0;i<lgRUserModuleCallTransList.size();i++){

            }
        }
        return lgRUserModuleCallTransList;
    }*/
}

