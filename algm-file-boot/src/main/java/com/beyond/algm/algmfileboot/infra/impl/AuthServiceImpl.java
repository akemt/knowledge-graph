package com.beyond.algm.algmfileboot.infra.impl;

import com.beyond.algm.algmfileboot.infra.AuthService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.TimeUtil;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.*;
import com.beyond.algm.model.AlgRUserDataCallTrans;
import com.beyond.algm.model.AlgRUserModelCallTrans;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDataVo;
import com.beyond.algm.vo.AlgModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ：zhangchuanzhi
 * @Description:用户权限控制
 * @date ：10:19 2017/12/8
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AlgRUserDataCallTransMapper algRUserDataCallTransMapper;
    @Autowired
    private AlgRUserModelCallTransMapper algRUserModelCallTransMapper;
    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    private AlgDataMapper algDataMapper;
    @Autowired
    private AlgModelMapper algModelMapper;

    /**
     * @author ：zhangchuanzhi
     * @Description: 数据下载权限控制
     * @param：usrCode：数据拥有者，sessionUsrCode：数据调用者，callUsrSn：数据调用者串号，dataSet：数据集，fileName：数据名称
     * @date ： 2017-12-08 21:54:06
     */
    @Override
    public void isDataByUser(String usrCode,String sessionUsrCode,String callUsrSn,String dataSet,String fileName)throws AlgException{
        log.info("路径用户:{},登录用户:{},调用用户串号:{},数据集名称:{},文件名:{}",usrCode,sessionUsrCode,callUsrSn,dataSet,fileName);
        // 判断是否是数据拥有人登录
        if(!usrCode.equals(sessionUsrCode)){
            // 取数据拥有者信息
            AlgUser algUser=algUserMapper.selectUsrCode(usrCode);
            AlgDataVo algDataVo=new AlgDataVo();
            algDataVo.setUsrSn(algUser.getUsrSn());
            algDataVo.setDataSetName(dataSet);
            algDataVo.setDataName(fileName);
            // 取出数据串号
            String dataSn=algDataMapper.dataSn(algDataVo);
            String nowTime=TimeUtil.getNowDateString(new Date());
            AlgRUserDataCallTrans record =new AlgRUserDataCallTrans();
            record.setDataSn(dataSn);
            record.setOwnerUsrSn(algUser.getUsrSn());
            record.setCallUsrSn(callUsrSn);
            AlgRUserDataCallTrans algBuyDataRecord=algRUserDataCallTransMapper.selectPayStatus(record);
            if(Assert.isNotNULL(algBuyDataRecord)) {
                int result = nowTime.compareTo(TimeUtil.getNowDateString(algBuyDataRecord.getEndTime()));
                // DATE_ADD(start_time,INTERVAL 1 MONTH) sql 时间问题增加一个月
                if (result>= 0) {
                    String[] checkMessage = {"支付时间过期请支付！", ""};
                    throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000008", checkMessage);
                } else {
                    String uuid = UUIDUtil.createUUID();
                    algBuyDataRecord.setUmcSn(uuid);
                    algBuyDataRecord.setCreatTime(new Date());
                    algRUserDataCallTransMapper.insert(algBuyDataRecord);
                }
            }else{
                String[] checkMessage = {"你没有支付，请支付！", ""};
                throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000009", checkMessage);
            }
        }
    }


    /**
     * @author ：zhangchuanzhi
     * @Description: 模型下载权限控制
     * @param：usrCode：数据拥有者，sessionUsrCode：数据调用者，callUsrSn：数据调用者串号，dataSet：数据集，fileName：数据名称
     * @date ： 2017-12-08 21:54:06
     */
    @Override
    public void isModelByUser(String usrCode,String sessionUsrCode,String callUsrSn,String modelSet,String fileName)throws AlgException{
        log.info("路径用户:{},登录用户:{},调用用户串号:{},模型集名称:{},文件名:{}",usrCode,sessionUsrCode,callUsrSn,modelSet,fileName);
        // 判断是否是数据拥有人登录
        if(!usrCode.equals(sessionUsrCode)){
            // 取数据拥有者信息
            AlgUser algUser=algUserMapper.selectUsrCode(usrCode);
            AlgModelVo algModel=new AlgModelVo();
            algModel.setUsrSn(algUser.getUsrSn());
            algModel.setModelSetName(modelSet);
            algModel.setModelName(fileName);
            // 取出数据串号
            String modelSn=algModelMapper.modelSn(algModel);
            String nowTime=TimeUtil.getNowDateString(new Date());
            AlgRUserModelCallTrans record =new AlgRUserModelCallTrans();
            record.setModelSn(modelSn);
            record.setOwnerUsrSn(algUser.getUsrSn());
            record.setCallUsrSn(callUsrSn);
            AlgRUserModelCallTrans algBuyModelRecord=algRUserModelCallTransMapper.selectPayStatus(record);
            if(Assert.isNotNULL(algBuyModelRecord)) {
                int result = nowTime.compareTo(TimeUtil.getNowDateString(algBuyModelRecord.getEndTime()));
                // DATE_ADD(start_time,INTERVAL 1 MONTH) sql 时间问题增加一个月
                if (result>= 0) {
                    String[] checkMessage = {"支付时间过期请支付！", ""};
                    throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000008", checkMessage);
                } else {
                    String uuid = UUIDUtil.createUUID();
                    algBuyModelRecord.setUmcSn(uuid);
                    algBuyModelRecord.setCreatTime(new Date());
                    algRUserModelCallTransMapper.insert(algBuyModelRecord);
                }
            }else{
                String[] checkMessage = {"你没有支付，请支付！", ""};
                throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000009", checkMessage);
            }
        }
    }


    /**
     * @author ：zhangchuanzhi
     * @Description: 用户名在用户上，通过路径查看权限,
     * @param：usrCode：登录用户session，sessionUsrCode:
     * @date ： 2017-12-08 21:54:06
     */
    @Override
    public void isPathByUser(String usrCode,String sessionUsrCode)throws AlgException{
        log.info("路径用户:{},登录用户:{}",usrCode,sessionUsrCode);
        if(!usrCode.equals(sessionUsrCode)){
            String[] checkMessage = {"对不起您没有权限！", ""};
            throw new AlgException("BEYOND.ALG.DATA.PAY.STATUS.0000010", checkMessage);
        }
    }

}
