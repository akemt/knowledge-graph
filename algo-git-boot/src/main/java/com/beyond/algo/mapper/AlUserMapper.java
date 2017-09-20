package com.beyond.algo.mapper;

import com.beyond.algo.model.AlUser;
import java.util.List;

public interface AlUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(AlUser record);

    AlUser selectByPrimaryKey(Integer userId);

    List<AlUser> selectAll();

    int updateByPrimaryKey(AlUser record);
}