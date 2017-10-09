package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgRUserOrgInvite;
import java.util.List;

public interface AlgRUserOrgInviteMapper {
    int deleteByPrimaryKey(String invSn);

    int insert(AlgRUserOrgInvite record);

    AlgRUserOrgInvite selectByPrimaryKey(String invSn);

    List<AlgRUserOrgInvite> selectAll();

    int updateByPrimaryKey(AlgRUserOrgInvite record);
}