package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.OrgVo;

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
     * @param orgSn 组织串号
     */
    OrgVo selectOrgVoByPrimaryKey(String orgSn);
}