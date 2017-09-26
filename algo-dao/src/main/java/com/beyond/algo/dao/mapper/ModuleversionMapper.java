package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Moduleversion;
import java.util.List;

public interface ModuleversionMapper {
    int deleteByPrimaryKey(String versn);

    int insert(Moduleversion record);

    Moduleversion selectByPrimaryKey(String versn);

    List<Moduleversion> selectAll();

    int updateByPrimaryKey(Moduleversion record);
}