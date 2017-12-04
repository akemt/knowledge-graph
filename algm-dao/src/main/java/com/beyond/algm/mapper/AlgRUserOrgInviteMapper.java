package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgRUserOrgInvite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlgRUserOrgInviteMapper {
    int deleteByPrimaryKey(String invSn);

    int insert(AlgRUserOrgInvite record);

    AlgRUserOrgInvite selectByPrimaryKey(String invSn);

    List<AlgRUserOrgInvite> selectAll();

    int updateByPrimaryKey(AlgRUserOrgInvite record);

    /**
     * 根据组织串号和用户串号获取关系表中唯一数据
     *
     * @param orgSn 组织串号
     * @param usrSn 用户串号
     */
    AlgRUserOrgInvite selectByOrgSnAndUsrSn(@Param("orgSn") String orgSn, @Param("usrSn") String usrSn);

    /**
     * 根据组织串号删除组织用户关系表中数据
     *
     * @param orgSn 组织串号
     */
    int deleteByOrgSn(String orgSn);

    /**
     * 根据组织串号和用户串号删除组织用户关系表中数据
     *
     * @param orgSn 组织串号
     * @param usrSn 用户串号
     */
    int deleteByOrgSnAndUsrSn(@Param("orgSn") String orgSn, @Param("usrSn") String usrSn);
}