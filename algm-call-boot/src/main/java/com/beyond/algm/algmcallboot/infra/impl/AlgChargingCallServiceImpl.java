package com.beyond.algm.algmcallboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmcallboot.infra.AlgChargingCallService;
import com.beyond.algm.algmcallboot.model.*;
import com.beyond.algm.algmcallboot.repository.*;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AlgChargingCallServiceImpl implements AlgChargingCallService {
    @Autowired
    private AlgUserRepository algUserRepository;
    @Autowired
    private AlgModuleRepository algModuleRepository;
    @Autowired
    private AlgModuleVersionRepository algModuleVersionRepository;
    @Autowired
    private AlgAuthCodeDomainRepository algAuthCodeDomainRepository;
    @Autowired
    private AlgDicRepository algDicRepository;
    @Autowired
    private AlgAccountRepository algAccountRepository;
    @Autowired
    private RocketMQServiceImpl rocketMQService;
    @Autowired
    private AlgAuthCodeRepository algAuthCodeRepository;

    /**
     * lindewei
     * API调用计费
     */
    public AlgResult addChargingCall(String usrCode,String modId,String version,String keyValue) throws AlgException{
        //定义返回结果对象
        AlgResult algResult =new AlgResult();
        //获取用户信息
        AlgUser algUser =algUserRepository.findByUsrCode(usrCode);
        //获取调用者的串号
        String usrSn = algAuthCodeRepository.findByAcdId(keyValue);
        //权限验证
        if(!isPower(algUser,modId,version,keyValue)){
             algResult.setResult("没有权限。");
            return algResult;
        }

        //获取该项目的algModule对象
        AlgModule algModule =algModuleRepository.findByModSn(algUser.getUsrSn(),modId);
        //分解版本
        Integer verCodeL1 = Integer.valueOf(version.substring(0,version.indexOf(".")));
        Integer verCodeL2 = Integer.valueOf(version.substring(version.indexOf(".")+1,version.indexOf(".",
                version.lastIndexOf("."))));
        Integer verCodeL3 = Integer.valueOf(version.substring(version.lastIndexOf(".")+1,version.length()));

        //判断调用的方法是否存在。
        if(!isVersion(algModule.getModSn(),verCodeL1,verCodeL2,verCodeL3)){
            algResult.setResult("该调用的方法不存在。");
            return algResult;
        }

        //获取版本信息
        AlgModuleVersion algModuleVersion = algModuleVersionRepository.verLoyaltyFee(algModule.getModSn(),verCodeL1,verCodeL2,verCodeL3);

        if("0".equals(algModuleVersion.getIsOwn())){
            algResult.setResult("该算法不公开，不可调用。");
            return algResult;
        }

        //查询平台收费单价
        String unitPrice = algDicRepository.findByDicSn("price","price_default");

        //开始调用
        Long startTime =  new Date().getTime();
        //TODO 1、判断public还是private；
        //TODO 用户调用方法，去执行的接口。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        algResult.setResult("执行结果成功。");//TODO 放入调用方法后的结果
        //结束调用
        Long endTime =  new Date().getTime();
        //计算时间差
        Long timeDif = endTime - startTime;

        //查询用户当前积分信息。
        AlgAccount algAccount = algAccountRepository.findByUsrSn(algUser.getUsrSn());

        //计算调用总价钱
        Float total = Float.valueOf(unitPrice).floatValue() * timeDif + algModuleVersion.getVerLoyaltyFee();

        if(algAccount.getCashBal() >= total){
            //定义json、赋值。
            JSONObject algUserCall = new JSONObject();
            algUserCall.put("umcSn", UUIDUtil.createUUID());//调用串号
            algUserCall.put("algSn", UUIDUtil.createUUID());//未知
            algUserCall.put("callPayAmount", total);//调用总费用
            algUserCall.put("callUsrSn", usrSn);//调用用户
            algUserCall.put("duration", timeDif);//调用持续时间
            algUserCall.put("ownerUsrSn", algUser.getUsrSn());//方法拥有者
            algUserCall.put("startTime", startTime);//调用开始时间
            algUserCall.put("endTime", endTime);//调用结束时间
            algUserCall.put("verSn", version);//版本

            //rocketMq发送一条信息
            try {
                rocketMQService.modCallProducer(algUserCall);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //剩余积分
            Float remainingCost = algAccount.getCashBal() - total;

            //todo 加对象锁，防止用户账户损失
            //更新用户账户余额
            int flg = algAccountRepository.updateUsrSnByCashBal(remainingCost,algUser.getUsrSn());

            if( flg < 0){
                algResult.setResult("余额扣除失败，调用失败。");
                return algResult;
            }

        }else {
            algResult.setResult("余额不足，调用失败。");
        }
        //返回调用结果
        return algResult;
    }

    /**
     * lindewei
     * 判断调用的方法是否存在。
     */
    public Boolean isVersion(String modSn,Integer verCodeL1,Integer verCodeL2,Integer verCodeL3)throws AlgException{
        //查询数据库记录
        Long count = algModuleVersionRepository.findByVerSnCount(modSn,verCodeL1,verCodeL2,verCodeL3);
        //判断是否存在
        if(count > 0){
            return true;//存在
        }else {
            return false;//不存在
        }
    }

    /**
     * lindewei
     * 权限验证
     */
    public Boolean isPower(AlgUser algUser,String modId,String version,String keyValue)throws AlgException{
        //获取algAuthCodeDomain对象集
        List<String> algAuthCodeDomain = algAuthCodeDomainRepository.findByAddSn(algUser.getUsrSn(),keyValue);
         //判断algAuthCodeDomain对象是否为空
         if(Assert.isNotEmpty(algAuthCodeDomain)){
             //权限路径
             String str1 = null;
             //调用方法路径
             String str2 = "algo://" + algUser.getUsrCode() + "/" + modId + "/" + version + "/";

             for(String url:algAuthCodeDomain){
                 str1 = url;
                 //如果权限路径不含有"*",进行判断
                 if(str1.indexOf("*") != -1){
                     str1 = str1.substring(0,str1.indexOf("*"));
                 }
                 //判断是否包含
                 if(str2.indexOf(str1) != -1)
                 {
                     // 如果包含，说明有权限直接返回true
                     return true;
                 }
             }
             // 如果循环没有循环没有return，说明不包含，故没有权限直接返回false
             return false;

         }else {
             //判断algAuthCodeDomain对象是为空,直接返回false
             return false;
         }
    }
}
