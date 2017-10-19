package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgRUserOrgInvite;
import java.util.List;

public interface AlgRUserOrgInviteMapper {
    int deleteByPrimaryKey(String invSn);

    int insert(AlgRUserOrgInvite record);

    AlgRUserOrgInvite selectByPrimaryKey(String invSn);

    List<AlgRUserOrgInvite> selectAll();

    int updateByPrimaryKey(AlgRUserOrgInvite record);

    /**
     * 根据组织编码删除组织用户关系表中数据
     *
     * @param orgSn 组织串号
     */
    int deleteByOrgCode(String orgSn);
}