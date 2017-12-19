package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AuthService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgUserMapper algUserMapper;
    public void isModuleByUser(String usrCode,String modId,String sessionUsrCode,String usrSn) throws AlgException{
        log.info("路径用户:{},算法名称:{}，登录用户:{}，用户串号:{}",usrCode,modId,sessionUsrCode,usrSn);

        if(!usrCode.equals(sessionUsrCode)){
            int count= algUserMapper.countOrgByCode(usrCode);
            if(count>0){
                int countOrgCode=algUserMapper.countOrgCode(usrCode,sessionUsrCode);
                if(countOrgCode==0){
                    log.warn("不在组织内获取版本失败，你没有权限");
                    throw new AlgException("BEYOND.ALG.MODULE.GETVERSION.0000011");
                }
            }else if(count==0){
                log.warn("不是组织,获取版本失败，你没有权限");
                throw new AlgException("BEYOND.ALG.MODULE.GETVERSION.0000012");
            }
        }else{
            AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(usrSn, modId);
            if ((Assert.isNULL(algModule))) {
                log.warn("获取版本算法失败，你没有权限");
                throw new AlgException("BEYOND.ALG.MODULE.GETVERSION.0000013");
            }
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 用户名在用户上，通过路径查看权限
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


    @Override
    public Boolean isModuleAuth(String usrCode,String modId,String sessionUsrCode,String usrSn) throws AlgException{
        log.info("路径用户:{},算法名称:{}，登录用户:{}，用户串号:{}",usrCode,modId,sessionUsrCode,usrSn);
         // 判断是否是组织
        if(!usrCode.equals(sessionUsrCode)){
            // 查询是否是组织
            int count= algUserMapper.countOrgByCode(usrCode);
            if(count>0){
                // 查询用户是否在组织下
                int countOrgCode=algUserMapper.countOrgCode(usrCode,sessionUsrCode);
                // 用户不在组织下
                if(countOrgCode==0){
                    log.warn("不在组织内获取版本失败，你没有权限");
                    return false;
                }else{// 在组织下判断算法是否在组织下
                    AlgUser alguser = algUserMapper.selectUsrCode(usrCode);
                    // 查询算法是否在组织下
                    int countOgrModelId=algModuleMapper.selectIsRepeat(alguser.getUsrSn(),modId);
                    if(countOgrModelId==0){
                        log.warn("算法不在组织下");
                        return false;
                    }
                }
            }else if(count==0){
                log.warn("不是组织,获取版本失败，你没有权限");
                return false;
            }
        }else{
            AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(usrSn, modId);
            if ((Assert.isNULL(algModule))) {
                log.warn("获取版本算法失败，你没有权限");
                return false;
            }
        }
      return true;
    }
}
