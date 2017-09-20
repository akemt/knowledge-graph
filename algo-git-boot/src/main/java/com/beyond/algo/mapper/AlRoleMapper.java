package com.beyond.algo.mapper;

import com.beyond.algo.model.AlRole;
import java.util.List;

public interface AlRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AlRole record);

    AlRole selectByPrimaryKey(Integer roleId);

    List<AlRole> selectAll();

    int updateByPrimaryKey(AlRole record);
}