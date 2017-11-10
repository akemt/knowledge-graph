package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.infra.GitLibService;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.algoalgorithmsboot.infra.OrgService;
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
    @Autowired(required = false)
    private GitLibService gitLibService;

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public AlgUser createOrg(AlgUser org, String createUserCode, String password) throws AlgException {
        if (algUserMapper.countOrgByCode(org.getUsrCode()) > 0) {
            throw new AlgException("git创建组织失败，组织编码已存在:" + org.getUsrCode());
        }

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

        // 在gitlab中创建组织
        try {
            gitLibService.createGitLibGroup(org.getUsrCode(), org.getUsrName(), createUserCode, password);
        } catch (Exception e) {
            log.error("gitlab创建组织失败。", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000001", new String[]{org.getUsrCode(), createUserCode}, e);
        }
        return org;
    }

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void deleteOrg(String orgSn) throws AlgException {
        AlgUser org = algUserMapper.selectByPrimaryKey(orgSn);

        // 先删除组织用户关系表，再删除用户表中的组织数据
        algRUserOrgInviteMapper.deleteByOrgCode(orgSn);
        algUserMapper.deleteByPrimaryKey(orgSn);

        // 在gitlab中删除组织
        try {
            gitLibService.deleteGitLibGroup(org.getUsrCode());
        } catch (Exception e) {
            log.error("gitlab删除组织失败。", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000002", new String[]{org.getUsrCode()}, e);
        }
    }
}
