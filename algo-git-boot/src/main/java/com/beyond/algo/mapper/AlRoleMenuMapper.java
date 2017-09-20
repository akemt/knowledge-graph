package com.beyond.algo.mapper;

import com.beyond.algo.model.AlRoleMenu;
import java.util.List;

public interface AlRoleMenuMapper {
    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(AlRoleMenu record);

    AlRoleMenu selectByPrimaryKey(Integer roleMenuId);

    List<AlRoleMenu> selectAll();

    int updateByPrimaryKey(AlRoleMenu record);
}