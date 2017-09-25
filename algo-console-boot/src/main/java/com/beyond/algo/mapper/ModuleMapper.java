package com.beyond.algo.mapper;

import com.beyond.algo.model.Module;
import java.util.List;

public interface ModuleMapper {
    int deleteByPrimaryKey(String modsn);

    int insert(Module record);

    Module selectByPrimaryKey(String modsn);

    List<Module> selectAll();

    int updateByPrimaryKey(Module record);
}