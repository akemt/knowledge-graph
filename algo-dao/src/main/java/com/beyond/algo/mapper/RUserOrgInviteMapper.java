package com.beyond.algo.mapper;

import com.beyond.algo.model.RUserOrgInvite;
import java.util.List;

public interface RUserOrgInviteMapper {
    int deleteByPrimaryKey(String invsn);

    int insert(RUserOrgInvite record);

    RUserOrgInvite selectByPrimaryKey(String invsn);

    List<RUserOrgInvite> selectAll();

    int updateByPrimaryKey(RUserOrgInvite record);
}