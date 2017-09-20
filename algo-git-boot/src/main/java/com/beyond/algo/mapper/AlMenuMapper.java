package com.beyond.algo.mapper;

import com.beyond.algo.model.AlMenu;
import java.util.List;

public interface AlMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(AlMenu record);

    AlMenu selectByPrimaryKey(Integer menuId);

    List<AlMenu> selectAll();

    int updateByPrimaryKey(AlMenu record);
}