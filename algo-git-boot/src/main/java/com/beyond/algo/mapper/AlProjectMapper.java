package com.beyond.algo.mapper;

import com.beyond.algo.model.AlProject;
import java.util.List;

public interface AlProjectMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(AlProject record);

    AlProject selectByPrimaryKey(Integer projectId);

    List<AlProject> selectAll();

    int updateByPrimaryKey(AlProject record);
}