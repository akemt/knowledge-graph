package com.beyond.algo.infra.impl;

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
//    @Autowired(required = false)
//    private GitLibService gitLibService;

    @Override
    @Transactional
    public AlgUser createOrg(AlgUser org, String createUserCode, String password) throws AlgException {

        // 用户表中插入组织记录
        Date now = new Date();
        org.setUsrSn(UUIDUtil.createUUID());
        org.setIsOrg("1");
        org.setNeedNotify("0");
        org.setCreateDate(now);
        org.setUpdateDate(now);
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
