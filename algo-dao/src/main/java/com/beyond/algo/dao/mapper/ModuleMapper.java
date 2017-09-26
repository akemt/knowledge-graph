package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Module;
import java.util.List;

public interface ModuleMapper {
    int deleteByPrimaryKey(String modsn);

    int insert(Module record);

    Module selectByPrimaryKey(String modsn);

    List<Module> selectAll();

    int updateByPrimaryKey(Module record);
}