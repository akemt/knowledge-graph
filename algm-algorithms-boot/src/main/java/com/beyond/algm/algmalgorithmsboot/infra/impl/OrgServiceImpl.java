package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    private GitLabService gitLabService;
    @Autowired
    private AlgModuleMapper algModuleMapper;

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public AlgUser createOrg(AlgUser org, AlgUser owner) throws AlgException {
        if (algUserMapper.countOrgByCode(org.getUsrCode()) > 0) {
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"创建组织", "组织编码已存在"});
        }

        // 用户表中插入组织记录
        Date now = new Date();
        org.setUsrSn(UUIDUtil.createUUID());
        org.setOwnerId(owner.getUsrSn());
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
        algRUserOrgInvite.setUserEmail(owner.getEmail());
        //TODO:以下两个属性待定
        algRUserOrgInvite.setIsPend("0");
        algRUserOrgInvite.setIsOldUser("0");
        algRUserOrgInviteMapper.insert(algRUserOrgInvite);

        // 在gitlab中创建组织
        try {
            gitLabService.createGitLabGroup(org.getUsrCode(), org.getUsrName(), owner.getUsrCode(), owner.getPasswd());
        } catch (Exception e) {
            log.error("gitlab创建组织失败.", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000001", new String[]{"创建组织", org.getUsrCode(), owner.getUsrCode()}, e);
        }
        return org;
    }

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void deleteOrg(String orgSn) throws AlgException {
        AlgUser org = algUserMapper.selectByPrimaryKey(orgSn);
        if (org == null) {
            log.error("gitlab删除组织失败，组织不存在：" + orgSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"删除组织", "组织不存在"});
        }

        // 先删除组织用户关系表，再删除用户表中的组织数据
        algRUserOrgInviteMapper.deleteByOrgSn(orgSn);
        algUserMapper.deleteByPrimaryKey(orgSn);

        // 在gitlab中删除组织
        try {
            //TODO:gitlab中使用管理员账户进行操作，调用前需要做权限验证
            gitLabService.deleteGitLabGroup(org.getUsrCode());
        } catch (Exception e) {
            log.error("gitlab删除组织失败.", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000002", new String[]{"删除组织", org.getUsrCode()}, e);
        }
    }

    @Override
    public OrgVo getOrgDetail(String orgSn) throws AlgException {
        OrgVo orgVo = algUserMapper.selectOrgVoByPrimaryKey(orgSn);
        if (orgVo == null) {
            log.error("gitlab获取组织详情失败，组织不存在：" + orgSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"获取组织详情", "组织不存在"});
        }

        // 获取算法信息和调用次数
        setModuleInfo(orgVo);

        // 获取组织成员
        List<AlgUser> memberList = algUserMapper.selectOrgMemberList(orgSn);
        orgVo.setMemberList(memberList);
        return orgVo;
    }

    @Override
    public PageInfo<OrgVo> getOrgList(String usrSn, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        Page<OrgVo> orgList = algUserMapper.selectOrgVoListByUsrSn(usrSn);
        for (OrgVo orgVo : orgList) {
            // 获取算法信息和调用次数
            setModuleInfo(orgVo);
        }
        return new PageInfo<>(orgList);
    }

    /**
     * 获取算法信息并赋值到orgVo中
     */
    private void setModuleInfo(OrgVo orgVo) {
        // 获取算法列表
        List<AlgModuleListVo> moduleList = algModuleMapper.findModuleList(null, null, null, null, orgVo.getUsrCode());
        orgVo.setModuleList(moduleList);
        // 根据算法列表计算组织总调用次数
        Long modCallCnt = 0L;
        if (moduleList != null) {
            for (AlgModuleListVo moduleVo : moduleList) {
                if (Assert.isNotEmpty(moduleVo.getCallCnt())) {
                    modCallCnt += Long.valueOf(moduleVo.getCallCnt());
                }
            }
        }
        orgVo.setModCallCnt(modCallCnt);
    }

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void addMember(String orgSn, String memberSn) throws AlgException {
        AlgRUserOrgInvite invite = algRUserOrgInviteMapper.selectByOrgSnAndUsrSn(orgSn, memberSn);
        if (invite != null) {
            log.error("gitlab添加组织成员失败，该成员已经在组织中，组织串号：" + orgSn + "，用户串号：" + memberSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"添加组织成员", "该成员已经在组织中"});
        }
        AlgUser org = algUserMapper.selectByPrimaryKey(orgSn);
        if (org == null) {
            log.error("gitlab添加组织成员失败，组织不存在：" + orgSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"添加组织成员", "组织不存在"});
        }
        AlgUser member = algUserMapper.selectByPrimaryKey(memberSn);
        if (member == null) {
            log.error("gitlab添加组织成员失败，成员不存在：" + memberSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"添加组织成员", "成员不存在"});
        }

        // 在关系表中添加关联记录
        AlgRUserOrgInvite algRUserOrgInvite = new AlgRUserOrgInvite();
        algRUserOrgInvite.setInvSn(UUIDUtil.createUUID());
        algRUserOrgInvite.setOrgSn(orgSn);
        algRUserOrgInvite.setUsrSn(memberSn);
        algRUserOrgInvite.setUserEmail(member.getEmail());
        //TODO:以下两个属性待定
        algRUserOrgInvite.setIsPend("0");
        algRUserOrgInvite.setIsOldUser("0");
        algRUserOrgInviteMapper.insert(algRUserOrgInvite);

        // 在gitlab中添加组织成员
        try {
            //TODO:gitlab中使用管理员账户进行操作，调用前需要做权限验证
            gitLabService.addGroupMember(org.getUsrCode(), member.getUsrCode());
        } catch (Exception e) {
            log.error("gitlab添加组织成员失败.", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000003", new String[]{"添加组织成员"}, e);
        }
    }

    @Override
    @Transactional(rollbackFor = AlgException.class)
    public void removeMember(String orgSn, String memberSn) throws AlgException {
        AlgRUserOrgInvite invite = algRUserOrgInviteMapper.selectByOrgSnAndUsrSn(orgSn, memberSn);
        if (invite == null) {
            log.error("gitlab删除组织成员失败，该成员不在组织中，组织串号：" + orgSn + "，用户串号：" + memberSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"删除组织成员", "该成员不在组织中"});
        }
        AlgUser org = algUserMapper.selectByPrimaryKey(orgSn);
        if (org == null) {
            log.error("gitlab删除组织成员失败，组织不存在：" + orgSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"删除组织成员", "组织不存在"});
        }
        AlgUser member = algUserMapper.selectByPrimaryKey(memberSn);
        if (member == null) {
            log.error("gitlab删除组织成员失败，成员不存在：" + memberSn);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000004", new String[]{"删除组织成员", "成员不存在"});
        }

        // 删除组织用户关系表
        algRUserOrgInviteMapper.deleteByOrgSnAndUsrSn(orgSn, memberSn);

        // 在gitlab中删除组织成员
        try {
            //TODO:gitlab中使用管理员账户进行操作，调用前需要做权限验证
            gitLabService.deleteGroupMember(org.getUsrCode(), member.getUsrCode());
        } catch (Exception e) {
            log.error("gitlab删除组织成员失败.", e);
            throw new AlgException("BEYOND.ALG.ORG.GITLAB.0000003", new String[]{"删除组织成员"}, e);
        }
    }
}
