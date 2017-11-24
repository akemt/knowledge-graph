package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;

import java.util.List;

public interface OrgService {

    /**
     * 创建组织
     *
     * @param org 组织信息
     * @return 创建后组织对象
     */
    AlgUser createOrg(AlgUser org, String createUserCode, String password) throws AlgException;

    /**
     * 删除组织
     *
     * @param orgSn 组织串号
     * @return 是否删除成功
     */
    void deleteOrg(String orgSn) throws AlgException;

    /**
     * 获取组织详情
     *
     * @param orgSn 组织串号
     * @return 组织详情
     */
    OrgVo getOrgDetail(String orgSn) throws AlgException;

    /**
     * 获取组织列表
     *
     * @param usrSn 用户串号
     * @return 组织列表
     */
    List<OrgVo> getOrgList(String usrSn) throws AlgException;

    /**
     * 为组织添加成员
     *
     * @param orgSn    组织串号
     * @param memberSn 成员用户串号
     */
    void addMember(String orgSn, String memberSn) throws AlgException;
}