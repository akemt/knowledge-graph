package com.beyond.algo.mapper;

import com.beyond.algo.model.AlUserRole;
import java.util.List;

public interface AlUserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(AlUserRole record);

    AlUserRole selectByPrimaryKey(Integer userRoleId);

    List<AlUserRole> selectAll();

    int updateByPrimaryKey(AlUserRole record);
}