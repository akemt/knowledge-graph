package com.beyond.algm.algmquartzboot.infra.impl;

import com.beyond.algm.algmquartzboot.infra.CallQuartzService;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleUsageMapper;
import com.beyond.algm.mapper.AlgRUserModuleCallTransMapper;
import com.beyond.algm.model.AlgModuleUsage;
import com.beyond.algm.model.AlgRUserModuleCallTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CallQuartzServiceImpl implements CallQuartzService {

    @Autowired
    private AlgRUserModuleCallTransMapper algRUserModuleCallTransMapper;
    @Autowired
    private AlgModuleUsageMapper algModuleUsageMapper;

    /**
     * 批量插入用户调用记录表
     *
     * @param
     * @return
     */
    @Override
    public void callQuartzs()throws AlgException {
        List<AlgRUserModuleCallTrans> list = new ArrayList<AlgRUserModuleCallTrans>();
        for (int i=0; i<500; i++){
            AlgRUserModuleCallTrans algRUserModuleCallTrans = new AlgRUserModuleCallTrans();
            algRUserModuleCallTrans.setUmcSn(UUIDUtil.createUUID());
            algRUserModuleCallTrans.setVerSn(UUIDUtil.createUUID());
            algRUserModuleCallTrans.setCallUsrSn(UUIDUtil.createUUID());
            algRUserModuleCallTrans.setEndTime(new Date());
            list.add(algRUserModuleCallTrans);
        }
        algRUserModuleCallTransMapper.insertList(list);
    }

    /**
     * 更新算法使用统计表
     */
    private int updateUsage(String modSn, Long sumCallCnt)throws AlgException {
        //判断算法是否存在调用记录
        Long count = algModuleUsageMapper.selectStarCntCount(modSn);
        //创建AlgModuleUsage对象
        AlgModuleUsage algModuleUsage = new AlgModuleUsage();

        if(count > 0){
            //存在调用记录，直接更新。
            Long callCount = algModuleUsageMapper.selectCallCnt(modSn);
            algModuleUsage.setModSn(modSn);
            algModuleUsage.setCallCnt(sumCallCnt+callCount);
            algModuleUsageMapper.updateByModSn(algModuleUsage);
        }else {
            //没有被调用记录，新插入。
            algModuleUsage.setUsgSn(UUIDUtil.createUUID());
            algModuleUsage.setModSn(modSn);
            algModuleUsage.setCallCnt(sumCallCnt);
            algModuleUsageMapper.insert(algModuleUsage);
        }
        return 1;
    }
}
