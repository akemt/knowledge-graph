package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface AlgUserMapper {
    int deleteByPrimaryKey(String usrSn);

    int insert(AlgUser record);

    AlgUser selectByPrimaryKey(String usrSn);

    List<AlgUser> selectAll();

    int updateByPrimaryKey(AlgUser record);

    AlgUser selectUsrname(String usrsn);

    int update(AlgUser record);


    /**
     * 根据组织编码获取组织个数（用于新建组织时判断组织编码唯一性）
     *
     * @param orgCode 组织编码
     * @return 组织个数
     */
    int countOrgByCode(String orgCode);

    AlgUser selectUsrCode(String usrCode);

    /**
     * 获取组织详情
     *
     * @param orgCode 组织编码
     */
    OrgVo selectOrgVoByOrgCode(String orgCode);

    /**
     * 获取当前用户所在组织列表
     *
     * @param usrSn 当前用户串号
     */
    Page<OrgVo> selectOrgVoListByUsrSn(String usrSn);

    /**
     * 获取组织下成员列表
     *
     * @param orgSn 组织串号
     */
    List<AlgUser> selectOrgMemberList(String orgSn);
}