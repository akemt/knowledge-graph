package com.beyond.algo.mapper;

import com.beyond.algo.model.AlActivity;
import java.util.List;

public interface AlActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(AlActivity record);

    AlActivity selectByPrimaryKey(Integer activityId);

    List<AlActivity> selectAll();

    int updateByPrimaryKey(AlActivity record);
}