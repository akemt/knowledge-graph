package com.beyond.algm.algmcallboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmcallboot.call.AlgmHttpCall;
import com.beyond.algm.algmcallboot.infra.AlgChargingCallService;
import com.beyond.algm.algmcallboot.model.*;
import com.beyond.algm.algmcallboot.repository.*;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
    @Value("${server.base.url}")
    private String baseUrl;

    /**
     * lindewei
     * API调用计费
     */
    public AlgResult addChargingCall(String usrCode, String modId, String version, String keyValue, String jsonStr) throws AlgException {
        //定义返回结果对象
        AlgResult algResult = new AlgResult();
        //获取用户信息
        AlgUser algUser = algUserRepository.findByUsrCode(usrCode);
        log.info("获取用户信息，用户名:{},中文名:{}", algUser.getUsrCode(), algUser.getUsrName());
        //获取调用者的串号
        String usrSn = algAuthCodeRepository.findByAcdId(keyValue);
        log.info("获取调用者的串号:{}", usrSn);
        //权限验证
        isAuthByCall(algUser, modId, version, keyValue);
        log.info("权限验证通过");

        //获取该项目的algModule对象
        AlgModule algModule = algModuleRepository.findByModSn(algUser.getUsrSn(), modId);
        log.info("获取该项目的algModule对象，modId:{}", algModule.getModId());

        //分解版本
        String[] versions = version.split("\\.");
        int verCodeL1 = Integer.valueOf(versions[0]);
        int verCodeL2 = Integer.valueOf(versions[1]);
        int verCodeL3 = Integer.valueOf(versions[2]);
        log.info("分解版本，verCodeL1:{},verCodeL2:{},verCodeL3:{}", verCodeL1, verCodeL2, verCodeL3);

        //判断调用的方法是否存在。
        isVersionExistence(algModule.getModSn(), verCodeL1, verCodeL2, verCodeL3);
        log.info("调用的方法存在");

        //获取版本信息
        AlgModuleVersion algModuleVersion = algModuleVersionRepository.verLoyaltyFee(algModule.getModSn(), verCodeL1, verCodeL2, verCodeL3);
        log.info("分解版本，verCodeL1:{},verCodeL2:{},verCodeL3:{}", verCodeL1, verCodeL2, verCodeL3);

        //判断public还是private；
        if ("0".equals(algModuleVersion.getIsOwn())) {
            algResult.setResult("该算法不公开，不可调用。");
            return algResult;
        }
        log.info("判断是public");

        //查询平台收费单价
        String unitPrice = algDicRepository.findByDicSn("price", "price_default");

        //开始调用
        Long startTime = new Date().getTime();
        String result = new AlgmHttpCall(usrCode, modId, version, baseUrl, jsonStr).send();
        algResult.setResult(result);
        //结束调用
        Long endTime = new Date().getTime();
        //计算时间差
        Long timeDif = endTime - startTime;

        //查询用户当前积分信息。
        AlgAccount algAccount = algAccountRepository.findByUsrSn(algUser.getUsrSn());
        log.info("查询用户当前积分信息,积分串号:{}", algAccount.getAccSn());
        //计算调用总价钱
        Float total = Float.valueOf(unitPrice).floatValue() * timeDif + (Assert.isEmpty(algModuleVersion.getVerLoyaltyFee())?new Float(0):algModuleVersion.getVerLoyaltyFee());

        if (algAccount.getCashBal() >= total) {
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
                log.info("rocketMQ开始发送数据");
                rocketMQService.modCallProducer(algUserCall);
                log.info("rocketMQ发送数据成功");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //剩余积分
            Float remainingCost = algAccount.getCashBal() - total;

            //todo 加对象锁，防止用户账户损失
            //更新用户账户余额
            int flg = algAccountRepository.updateUsrSnByCashBal(remainingCost, algUser.getUsrSn());
            log.info("更新用户账户余额是否成功:{}", flg);
            if (flg < 0) {
                algResult.setResult("余额扣除失败，调用失败。");
                return algResult;
            }

        } else {
            algResult.setResult("余额不足，调用失败。");
        }
        //返回调用结果
        return algResult;
    }

    /**
     * lindewei
     * 判断调用的方法是否存在。
     */
    public void isVersionExistence(String modSn, Integer verCodeL1, Integer verCodeL2, Integer verCodeL3) throws AlgException {
        //查询数据库记录
        Long count = algModuleVersionRepository.findByVerSnCount(modSn, verCodeL1, verCodeL2, verCodeL3);
        //判断是否存在
        if (count <= 0) {
            throw new AlgException("BEYOND.ALG.CALL.COMMON.CHARGING.0000001");
        }
    }

    /**
     * lindewei
     * 权限验证
     */
    public void isAuthByCall(AlgUser algUser, String modId, String version, String keyValue) throws AlgException {
        //获取algAuthCodeDomain对象集
        List<String> algAuthCodeDomain = algAuthCodeDomainRepository.findByAddSn(algUser.getUsrSn(), keyValue);
        //判断algAuthCodeDomain对象是否为空
        if (Assert.isNotEmpty(algAuthCodeDomain)) {
            //权限路径
            String str1 = null;
            //调用方法路径
            String str2 = "algm://" + algUser.getUsrCode() + "/" + modId + "/" + version + "/";
            boolean result = true;
            for (String url : algAuthCodeDomain) {
                str1 = url;
                //如果权限路径不含有"*",进行判断
                if (str1.indexOf("*") != -1) {
                    str1 = str1.substring(0, str1.indexOf("*"));
                }
                //判断是否包含
                if (str2.indexOf(str1) != -1) {
                    // 如果包含，说明有权限直接返回true
                    result =false;
                    break;
                }
            }
            // 如果循环没有循环没有return，说明不包含，故没有权限直接返回false
            if(result){
                throw new AlgException("BEYOND.ALG.CALL.COMMON.CHARGING.0000002");
            }
        } else {
            //判断algAuthCodeDomain对象是为空,直接返回false
            throw new AlgException("BEYOND.ALG.CALL.COMMON.CHARGING.0000003");
        }
    }
}
