package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.RUserOrgInvite;
import java.util.List;

public interface RUserOrgInviteMapper {
    int deleteByPrimaryKey(String invsn);

    int insert(RUserOrgInvite record);

    RUserOrgInvite selectByPrimaryKey(String invsn);

    List<RUserOrgInvite> selectAll();

    int updateByPrimaryKey(RUserOrgInvite record);
}