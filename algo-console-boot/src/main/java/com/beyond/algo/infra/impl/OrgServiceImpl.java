package com.beyond.algo.infra.impl;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.infra.OrgService;
import com.beyond.algo.mapper.AlgRUserOrgInviteMapper;
import com.beyond.algo.mapper.AlgUserMapper;
import com.beyond.algo.model.AlgRUserOrgInvite;
import com.beyond.algo.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired(required = false)
    private AlgUserMapper algUserMapper;
    @Autowired(required = false)
    private AlgRUserOrgInviteMapper algRUserOrgInviteMapper;

    @Override
    @Transactional
    public AlgUser createOrg(AlgUser org) throws AlgException {

        // 非空判断
        if (Assert.isEmpty(org.getUsrCode())) {
            throw new AlgException("组织账户名为空");
        }
        if (Assert.isEmpty(org.getUsrName())) {
            throw new AlgException("组织全名为空");
        }
        if (Assert.isEmpty(org.getEmail())) {
            throw new AlgException("组织电邮为空");
        }
        if (Assert.isEmpty(org.getOwnerId())) {
            throw new AlgException("创建者ID为空");
        }

        // 用户表中插入组织记录
        org.setUsrSn(UUIDUtil.createUUID());
        org.setIsOrg("1");
        org.setNeedNotify("0");
        org.setCreateDate(new Date());
        org.setUpdateDate(new Date());
        algUserMapper.insert(org);

        // 在关系表中添加关联记录
        AlgRUserOrgInvite algRUserOrgInvite = new AlgRUserOrgInvite();
        algRUserOrgInvite.setInvSn(UUIDUtil.createUUID());
        algRUserOrgInvite.setOrgSn(org.getUsrSn());
        algRUserOrgInvite.setUsrSn(org.getOwnerId());
        //TODO:两个属性待定
        algRUserOrgInvite.setIsPend("0");
        algRUserOrgInvite.setIsOldUser("0");
        algRUserOrgInviteMapper.insert(algRUserOrgInvite);

        return org;
    }

}
