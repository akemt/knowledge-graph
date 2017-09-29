package com.beyond.algo.mapper;

import com.beyond.algo.model.Moduleversion;
import java.util.List;

public interface ModuleversionMapper {
    int deleteByPrimaryKey(String versn);

    int insert(Moduleversion record);

    Moduleversion selectByPrimaryKey(String versn);

    List<Moduleversion> selectAll();

    int updateByPrimaryKey(Moduleversion record);
}