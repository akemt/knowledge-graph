package com.beyond.algm.algmquartzboot.infra.impl;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONArray;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
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
    public void callQuartzs(JSONArray jsonArray)throws AlgException {
        List<AlgRUserModuleCallTrans> list = new ArrayList<AlgRUserModuleCallTrans>();
        if(jsonArray.size()>0){
            for(int i=0;i<jsonArray.size();i++){
                //创建对象
                AlgRUserModuleCallTrans algRUserModuleCallTrans = new AlgRUserModuleCallTrans();
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject job = jsonArray.getJSONObject(i);
                // 得到每个对象中的属性值
                algRUserModuleCallTrans.setUmcSn(UUIDUtil.createUUID());
                algRUserModuleCallTrans.setVerSn(String.valueOf(job.get("VER_SN")));
                algRUserModuleCallTrans.setCallUsrSn(String.valueOf(job.get("CALL_USR_SN")));
                algRUserModuleCallTrans.setOwnerUsrSn(String.valueOf(job.get("OWNER_USR_SN")));
                algRUserModuleCallTrans.setDuration(Long.valueOf(String.valueOf(job.get("DURATION"))));
                algRUserModuleCallTrans.setBilledCallCnt(Long.valueOf(String.valueOf(job.get("BILLED_CALL_CNT"))));
                algRUserModuleCallTrans.setCallPayAmount(Float.parseFloat(String.valueOf(job.get("CALL_PAY_AMOUNT"))));
                algRUserModuleCallTrans.setModCallCnt(Long.valueOf(String.valueOf(job.get("ALL_CALL_AMOUNT"))));
                list.add(algRUserModuleCallTrans);
                this.updateUsage(String.valueOf(job.get("MOD_SN")),Long.valueOf(String.valueOf(job.get("ALL_CALL_AMOUNT"))));
            }
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
        //TODO 目前只一个modsn循环查一次，要给成存储过程做。
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
