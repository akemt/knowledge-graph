package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.GitLibService;
import com.beyond.algm.algmalgorithmsboot.infra.OrgService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgRUserOrgInviteMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgRUserOrgInvite;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgModuleListVo;
import com.beyond.algm.vo.OrgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    private AlgRUserOrgInviteMapper algRUserOrgInviteMapper;
    @Autowired
    private GitLibService gitLibService;
    @Autowired
    private AlgModuleMapper algModuleMapper;

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

    @Override
    public OrgVo getOrgDetail(String orgSn) throws AlgException {
        OrgVo orgVo = algUserMapper.selectOrgVoByPrimaryKey(orgSn);
        if (Assert.isNotEmpty(orgVo.getOwnerId())) {
            AlgUser owner = algUserMapper.selectByPrimaryKey(orgVo.getOwnerId());
            orgVo.setOwner(owner);
        }

        // TODO:获取算法列表方法待定
        List<AlgModuleListVo> moduleList = algModuleMapper.findModuleList(null, null, null, null, null);
        orgVo.setModuleList(moduleList);
        // 根据算法列表计算组织总调用次数
        Long modCallCnt = 0L;
        if (moduleList.size() > 0) {
            for (AlgModuleListVo moduleVo:moduleList) {
                modCallCnt += Long.valueOf(moduleVo.getCallCnt());
            }
        }

        // TODO:获取组织成员

        return orgVo;
    }
}
